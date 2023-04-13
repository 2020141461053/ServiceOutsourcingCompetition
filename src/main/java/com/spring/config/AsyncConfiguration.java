package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AsyncConfiguration {

    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
