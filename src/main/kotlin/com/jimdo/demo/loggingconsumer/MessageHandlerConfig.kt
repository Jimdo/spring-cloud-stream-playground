package com.jimdo.demo.loggingconsumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class MessageHandlerConfig {

    @Bean
    fun queueMessageHandlerFactory() {
        val factory = MappingJackson2HttpMessageConverter()
    }
}