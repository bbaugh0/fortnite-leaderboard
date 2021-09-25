package com.brandon.fortniteleaderboard.model

import java.time.LocalDateTime

data class RecentMatchesModel(
    val id: String,
    val accountId: String,
    var playlist: String,
    var minutesPlayed: Int,
    var top1: Int,
    var top3: Int,
    var top5: Int,
    var top6: Int,
    var top10: Int,
    var top12: Int,
    var top25: Int,
    var matches: Int,
    var dateCollected: LocalDateTime,
    var score: Int,
    var platform: Int,
    var playlistId: Int,
    var playersOutLived: Int
)
