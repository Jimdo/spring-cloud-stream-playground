package com.jimdo.demo.loggingconsumer

import org.slf4j.LoggerFactory
import org.springframework.cloud.function.context.PostProcessingFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Function

@Configuration
class PostProcessors {

    private class Logger : PostProcessingFunction<Person, Person> {

        private val log = LoggerFactory.getLogger(javaClass)

        override fun postProcess(result: Message<Person>) {
            log.info("post processing: ${result.payload}")
        }
    }

    @Bean
    fun logger(): Function<Person, Person> {
        return Logger()
    }
}
