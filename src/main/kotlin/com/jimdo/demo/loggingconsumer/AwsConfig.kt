package com.jimdo.demo.loggingconsumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider

@Configuration
class AwsConfig {

    @Bean
    fun awsCredentialsProvider(): AwsCredentialsProvider {
        return AnonymousCredentialsProvider.create()
    }
}
