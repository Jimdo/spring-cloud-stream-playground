package com.jimdo.demo.loggingconsumer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val streamBridge: StreamBridge,
    @Value("\${spring.cloud.stream.bindings.log-in-0.destination}")
    private val topic: String
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/test/{value}")
    @ResponseStatus(HttpStatus.OK)
    fun values(@PathVariable value: String) {
        log.info("Sending value $value to topic $topic.")
        streamBridge.send(topic, Person(name = value))
    }
}
