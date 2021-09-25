package com.brandon.fortniteleaderboard.model

data class GameModeModel(
    var trnRating: StatsModel?,
    var score: StatsModel,
    var top1: StatsModel,
    var top3: StatsModel,
    var top5: StatsModel,
    var top6: StatsModel,
    var top10: StatsModel,
    var top12: StatsModel,
    var top25: StatsModel,
    var kd: StatsModel,
    var winRatio: StatsModel,
    var matches: StatsModel,
    var kills: StatsModel,
    var minutesPlayed: StatsModel,
    var kpm: StatsModel,
    var kpg: StatsModel,
    var avgTimePlayed: StatsModel,
    var scorePerMatch: StatsModel,
    var scorePerMin: StatsModel
)