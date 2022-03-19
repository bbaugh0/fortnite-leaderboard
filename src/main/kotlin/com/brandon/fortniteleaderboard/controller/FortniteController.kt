package com.brandon.fortniteleaderboard.controller

import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel
import com.brandon.fortniteleaderboard.service.FortniteLeaderboardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Controller
class FortniteController(
    @Autowired
    var leaderboardService: FortniteLeaderboardService
) {
    @GetMapping(produces = arrayOf(MediaType.TEXT_HTML_VALUE))
    @ResponseBody
    fun getAllPlayerStats(): String {
        return leaderboardService.getAllStatsLeaderboard()
    }
}