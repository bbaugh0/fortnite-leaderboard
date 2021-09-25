package com.brandon.fortniteleaderboard.model

data class StatsModel(
    var label: String,
    var field: String,
    var category: String,
    var valueInt: Int,
    var value: String,
    var rank: Int,
    var percentile: Double,
    var displayType: Int,
    var displayValue: String
)
