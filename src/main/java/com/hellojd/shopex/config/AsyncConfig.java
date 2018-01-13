package com.hellojd.shopex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
@PropertySource("classpath:shopex.properties")
@Configuration
public class AsyncConfig {
    @Value("${task.core_pool_size}")
    private int corePoolSize;
    @Value("${task.max_pool_size}")
    private int maxPoolSize;
    @Value("${task.keep_alive_seconds}")
    private int keepAliveSeconds = 60;
    @Value("${task.queue_capacity}")
    private int queueCapacity ;

    @Bean
    public TaskExecutor taskExecutor(){
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        return executor;
    }
}
