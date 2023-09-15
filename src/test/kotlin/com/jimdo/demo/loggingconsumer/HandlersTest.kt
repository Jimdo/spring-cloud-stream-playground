package com.jimdo.demo.loggingconsumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension
import org.springframework.cloud.stream.binder.test.InputDestination
import org.springframework.cloud.stream.binder.test.OutputDestination
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
import org.springframework.context.annotation.Import
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage

@SpringBootTest(
    properties = [
        "spring.cloud.function.definition=log;uppercase",
        "spring.cloud.stream.bindings.uppercase-in-0.destination=person-topic",
        "spring.cloud.stream.bindings.uppercase-out-0.destination=uppercase-topic",
    ]
)
@Import(TestChannelBinderConfiguration::class)
@ExtendWith(OutputCaptureExtension::class)
class HandlersTest(
    @Autowired
    private val input: InputDestination,
    @Autowired
    private val output: OutputDestination,
    @Autowired
    private val objectMapper: ObjectMapper,
) {

    @Test
    fun testLogConsumer(capturedOutput: CapturedOutput) {
        input.send(GenericMessage(Person(firstName = "Max", lastName = "Mustermann")), "person-topic")
        assertTrue(capturedOutput.out.contains("log handler received: Person(firstName=Max, lastName=Mustermann)"))
    }

    @Test
    fun testUppercaseFunction(capturedOutput: CapturedOutput) {
        input.send(GenericMessage(Person(firstName = "Max", lastName = "Mustermann")), "person-topic")
        assertTrue(capturedOutput.out.contains("uppercase handler received: Person(firstName=Max, lastName=Mustermann)"))
        val message = output.receive(0, "uppercase-topic")
        assertEquals("MAX", deserialize<Person>(message).firstName)
    }

    private inline fun <reified T> deserialize(message: Message<ByteArray>): T {
        return objectMapper.readValue(message.payload, T::class.java)
    }
}
