package com.brandon.fortniteleaderboard.service

import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel

interface FortniteLeaderboardService {
    fun getAllStatsLeaderboard(): String
}