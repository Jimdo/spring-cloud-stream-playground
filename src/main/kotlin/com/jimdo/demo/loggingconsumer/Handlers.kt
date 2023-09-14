package com.jimdo.demo.loggingconsumer

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class Handlers {

    private val log = LoggerFactory.getLogger(Handlers::class.java)

    @Bean
    fun log(): Consumer<Person> {
        return Consumer { person: Person ->
            log.info("Received: $person")
        }
    }
}
