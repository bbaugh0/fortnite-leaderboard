package com.brandon.fortniteleaderboard.config

import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@EnableScheduling
open class SchedulingConfig {

    @Scheduled(cron = "0 * * * *")
    @CacheEvict("responses")
    open fun cacheEvict() {
        // Nothing needed here
    }
}