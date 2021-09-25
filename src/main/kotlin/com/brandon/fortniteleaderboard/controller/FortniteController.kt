package com.brandon.fortniteleaderboard.controller

import com.brandon.fortniteleaderboard.client.FortniteApiClient
import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel
import com.brandon.fortniteleaderboard.service.FortniteLeaderboardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FortniteController(
    @Autowired
    var leaderboardService: FortniteLeaderboardService
) {

    @GetMapping("/getSoloStats")
    fun getSoloStats(@RequestParam id: String): ResponseEntity<GameModeStats> {
        return ResponseEntity.ok(leaderboardService.getSoloStats(id))
    }
}