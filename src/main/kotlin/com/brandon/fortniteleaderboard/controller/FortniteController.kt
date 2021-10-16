package com.brandon.fortniteleaderboard.controller

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

    @GetMapping("/getSoloLeaderboards")
    fun getAllPlayerStats(): ResponseEntity<List<GameModeStats>> {
        return ResponseEntity.ok(leaderboardService.getSoloLeaderboardStats())
    }

    @GetMapping
    fun getSoloStats(@RequestParam id: String): ResponseEntity<GameModeStats> {
        return ResponseEntity.ok(leaderboardService.getSoloStats(id))
    }

    @GetMapping("/solos")
    fun getSoloStatsString(@RequestParam id: String): ResponseEntity<String> {
        return ResponseEntity.ok(leaderboardService.getSoloStringStats(id))
    }
}