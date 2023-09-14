package com.jimdo.demo.loggingconsumer

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension
import org.springframework.cloud.stream.binder.test.InputDestination
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
import org.springframework.context.annotation.Import
import org.springframework.messaging.support.GenericMessage

@SpringBootTest
@Import(TestChannelBinderConfiguration::class)
@ExtendWith(OutputCaptureExtension::class)
class HandlersTest(
    @Autowired
    private val input: InputDestination
) {

    @Test
    fun testLog(capturedOutput: CapturedOutput) {
        input.send(GenericMessage(Person("Max Mustermann")), "log-topic")
        assertTrue(capturedOutput.out.contains("Received: Person(name=Max Mustermann)"))
    }
}

