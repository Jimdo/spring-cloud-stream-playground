package com.jimdo.demo.loggingconsumer

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.GlobalChannelInterceptor
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.ChannelInterceptor

@Configuration
class Interceptors {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    @GlobalChannelInterceptor(patterns = ["uppercasePersonCreatorOut"])
    fun personInterceptor(): ChannelInterceptor {
        return object : ChannelInterceptor {
            override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
                log.info("intercepting person message: $message")
                return super.preSend(message, channel)
            }
        }
    }

    @Bean
    @GlobalChannelInterceptor(patterns = ["addressCreator-*"])
    fun addressInterceptor(): ChannelInterceptor {
        return object : ChannelInterceptor {
            override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
                log.info("intercepting address message: $message")
                return super.preSend(message, channel)
            }
        }
    }
}
