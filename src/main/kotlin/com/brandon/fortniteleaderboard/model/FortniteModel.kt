package com.brandon.fortniteleaderboard.model

data class FortniteModel(
    var accountId: String,
    var avatar: String,
    var platformId: Int,
    var platformName: String,
    var platformNameLong: String,
    var epicUserHandle: String,
    var stats: GameModes,
    var lifeTimeStats: List<KeyValueModel>,
    var recentMatches: List<RecentMatchesModel>
    )