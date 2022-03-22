package com.brandon.fortniteleaderboard.config

import lombok.extern.slf4j.Slf4j
import org.apache.commons.logging.Log
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.logging.Logger

@EnableScheduling
@Component
class SchedulingConfig {

    private val logger = LoggerFactory.getLogger(javaClass)

//    @Scheduled(cron = "* 0/30 * * * *")
//    @CacheEvict("responses")
//    open fun cacheEvict() {
//        logger.info("Evicting cache")
//        // Nothing needed here
//    }
}