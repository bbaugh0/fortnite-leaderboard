package com.brandon.fortniteleaderboard.service.impl

import com.brandon.fortniteleaderboard.client.FortniteApiClient
import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.service.FortniteLeaderboardService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class FortniteLeaderboardServiceImpl(
    @Autowired
    var client: FortniteApiClient
): FortniteLeaderboardService {
    override fun getSoloStats(id: String): GameModeStats {
        val model = client.getPlayerStats(id)!!
        return GameModeStats(
            model.stats.solos.score.valueInt,
            model.stats.solos.kills.valueInt,
            model.stats.solos.top1.valueInt,
            model.stats.solos.matches.valueInt,
            model.stats.solos.kpm.value.toDouble(),
            model.stats.solos.kpg.value.toDouble()
        )
    }

}