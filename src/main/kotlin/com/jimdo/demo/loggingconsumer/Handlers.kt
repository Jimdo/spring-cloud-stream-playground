package com.jimdo.demo.loggingconsumer

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.support.ErrorMessage
import java.util.function.Consumer
import java.util.function.Function

@Configuration
class Handlers {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun log(): Consumer<Person> {
        return Consumer { person: Person ->
            log.info("log handler received: $person")
        }
    }

    @Bean
    fun uppercase(): Function<Person, Person> {
        return Function { person: Person ->
            log.info("uppercase handler received: $person")
            Person(
                firstName = person.firstName.uppercase(),
                lastName = person.lastName.uppercase(),
            )
        }
    }

    @Bean
    fun errorHandler(): Consumer<ErrorMessage> {
        return Consumer { errorMessage ->
            log.warn("Error processing message: ${errorMessage.originalMessage}")
        }
    }
}
