package com.jimdo.demo.loggingconsumer

import com.github.javafaker.Faker
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Supplier

@Configuration
class Suppliers {

    private val log = LoggerFactory.getLogger(javaClass)

    private val faker = Faker()

    @Bean
    fun personCreator(): Supplier<Person> {
        return Supplier {
            Person(name = faker.name().fullName()).also { log.info("supplying: $it") }
        }
    }

    @Bean
    fun addressCreator(): Supplier<Address> {
        return Supplier {
            with(faker.address()) {
                Address(
                    streeName = streetName(),
                    buildingNumber = buildingNumber(),
                    zipCode = this.zipCode(),
                    city = city(),
                    country = country(),
                ).also { log.info("supplying: $it") }
            }
        }
    }
}
