package com.brandon.fortniteleaderboard.dto

data class GameModeStats(
    var id: String,
    var score: Int,
    var kills: Int,
    var wins: Int,
    var matches: Int,
    var killsPerMinute: Double,
    var killsPerGame: Double
)
