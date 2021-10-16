package com.brandon.fortniteleaderboard.service

import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel

interface FortniteLeaderboardService {
    /**
     * Returns game mode stats for solos
     */
    fun getSoloStats(id: String): GameModeStats

    fun getSoloStringStats(id: String): String

    fun getSoloLeaderboardStats(): List<GameModeStats>
}