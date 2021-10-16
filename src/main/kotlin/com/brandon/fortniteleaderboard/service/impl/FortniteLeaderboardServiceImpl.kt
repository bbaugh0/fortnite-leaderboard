package com.brandon.fortniteleaderboard.service.impl

import com.brandon.fortniteleaderboard.client.FortniteApiClient
import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel
import com.brandon.fortniteleaderboard.service.FortniteLeaderboardService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StopWatch
import org.springframework.web.servlet.function.ServerResponse.async
import reactor.core.publisher.Flux
import java.util.stream.Collectors

@Service
@Slf4j
class FortniteLeaderboardServiceImpl(
    @Autowired
    var client: FortniteApiClient,

    var gamertags: MutableList<String> = mutableListOf("LMS BLACK MOSES", "wiscomurkin", "Wiscostyle", "OGWiscoMurk1n", "CorgiDogLuver12", "Th3F0XX")
): FortniteLeaderboardService {
    override fun getSoloStats(id: String): GameModeStats {
        val model = client.getPlayerStats(id)!!
        return GameModeStats(
            "Stats for $id:",
            model.stats.solos.score.valueInt,
            model.stats.solos.kills.valueInt,
            model.stats.solos.top1.valueInt,
            model.stats.solos.matches.valueInt,
            model.stats.solos.kpm.value.toDouble(),
            model.stats.solos.kpg.value.toDouble(),
            "Solo"
        )
    }

    override fun getSoloStringStats(id: String): String {
        val model = client.getPlayerStats(id)!!
        return "$id has ${model.stats.solos.kills.valueInt} kills and ${model.stats.solos.top1.valueInt} wins in solos"
    }

    private fun getAllPlayerStats(): List<FortniteModel?> {
        return gamertags.parallelStream().map{ value ->  client.getPlayerStats(value) }.toList()
    }

    override fun getSoloLeaderboardStats(): List<GameModeStats> {
        var dtos = getAllPlayerStats().parallelStream().map { value ->
            GameModeStats(
                value!!.epicUserHandle,
                value.stats.solos.score.valueInt,
                value.stats.solos.kills.valueInt,
                value.stats.solos.top1.valueInt,
                value.stats.solos.matches.valueInt,
                value.stats.solos.kpm.value.toDouble(),
                value.stats.solos.kpg.value.toDouble(),
                "Solos"
            )
        }.toList()
       dtos =  dtos.stream().sorted { o1, o2 ->  o2.wins.compareTo(o1.wins)}.toList()

        var i = 1
        for (stats: GameModeStats in dtos) {
            when(stats.id) {
                "Wiscostyle" -> stats.id = "MAK DEM PAY"
                "OGwiscoMurk1n" -> stats.id = "LAZERS KILL"
            }
            stats.id = "Rank $i: ${stats.id}"
            i++
        }
        return dtos
    }

}