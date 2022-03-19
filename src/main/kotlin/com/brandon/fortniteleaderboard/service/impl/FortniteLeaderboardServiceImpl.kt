package com.brandon.fortniteleaderboard.service.impl

import com.brandon.fortniteleaderboard.client.FortniteApiClient
import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel
import com.brandon.fortniteleaderboard.service.FortniteLeaderboardService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
@Slf4j
class FortniteLeaderboardServiceImpl(
    @Autowired
    var client: FortniteApiClient,

    var gamertags: MutableList<String> = mutableListOf("LMS BLACK MOSES", "wiscomurkin", "Wiscostyle", "OGWiscoMurk1n", "CorgiDogLuver12", "Th3F0XX")
): FortniteLeaderboardService {

    private fun getAllPlayerStats(): List<FortniteModel?> {
        return gamertags.parallelStream().map{ value ->  client.getPlayerStats(value) }.collect(Collectors.toList())
    }

    private fun getSoloLeaderboardStats(models: List<FortniteModel?>): List<GameModeStats> {
        try {
            var dtos = models.parallelStream().map { value ->
                GameModeStats(
                    value!!.epicUserHandle,
                    value.stats.solos.score.valueInt,
                    value.stats.solos.kills.valueInt,
                    value.stats.solos.top1.valueInt,
                    value.stats.solos.matches.valueInt,
                    value.stats.solos.kpm.value.toDouble(),
                    value.stats.solos.kpg.value.toDouble(),
                    value.stats.solos.scorePerMatch.value.toDouble(),
                    value.stats.solos.winRatio.value.toDouble()
                )
            }.collect(Collectors.toList())
            dtos = dtos.stream().sorted { o1, o2 -> o2.wins.compareTo(o1.wins) }.collect(Collectors.toList())

            for (stats: GameModeStats in dtos) {
                when (stats.id) {
                    "Wiscostyle" -> stats.id = "MAK DEM PAY"
                    "OGwiscoMurk1n" -> stats.id = "LAZERS KILL"
                }
            }
            return dtos
        } catch (e: Exception) {
            throw Exception("Something went wrong getting solo stats ${e.stackTrace}")
        }
    }

    private fun getDuosLeaderboardStats(models: List<FortniteModel?>): List<GameModeStats> {
        try {
            var dtos = models.parallelStream().map { value ->
                GameModeStats(
                    value!!.epicUserHandle,
                    value.stats.duos.score.valueInt,
                    value.stats.duos.kills.valueInt,
                    value.stats.duos.top1.valueInt,
                    value.stats.duos.matches.valueInt,
                    value.stats.duos.kpm.value.toDouble(),
                    value.stats.duos.kpg.value.toDouble(),
                    value.stats.duos.scorePerMatch.value.toDouble(),
                    value.stats.duos.winRatio.value.toDouble()
                )
            }.collect(Collectors.toList())
            dtos = dtos.stream().sorted { o1, o2 -> o2.wins.compareTo(o1.wins) }.collect(Collectors.toList())

            for (stats: GameModeStats in dtos) {
                when (stats.id) {
                    "Wiscostyle" -> stats.id = "MAK DEM PAY"
                    "OGwiscoMurk1n" -> stats.id = "LAZERS KILL"
                }
            }
            return dtos
        } catch (e: Exception) {
            throw Exception("Something went wrong getting duos stats ${e.stackTrace}")
        }
    }

    private fun getTriosLeaderboardStats(models: List<FortniteModel?>): List<GameModeStats> {
        try {
            var dtos = models.parallelStream().map { value ->
                GameModeStats(
                    value!!.epicUserHandle,
                    value.stats.trios.score.valueInt,
                    value.stats.trios.kills.valueInt,
                    value.stats.trios.top1.valueInt,
                    value.stats.trios.matches.valueInt,
                    value.stats.trios.kpm.value.toDouble(),
                    value.stats.trios.kpg.value.toDouble(),
                    value.stats.trios.scorePerMatch.value.toDouble(),
                    value.stats.trios.winRatio.value.toDouble()
                )
            }.collect(Collectors.toList())
            dtos = dtos.stream().sorted { o1, o2 -> o2.wins.compareTo(o1.wins) }.collect(Collectors.toList())

            for (stats: GameModeStats in dtos) {
                when (stats.id) {
                    "Wiscostyle" -> stats.id = "MAK DEM PAY"
                    "OGwiscoMurk1n" -> stats.id = "LAZERS KILL"
                }
            }
            return dtos
        } catch (e: Exception) {
            throw Exception("Something went wrong getting trios stats ${e.stackTrace}")
        }
    }

    private fun getSquadsLeaderboardStats(models: List<FortniteModel?>): List<GameModeStats> {
        try {
            var dtos = models.parallelStream().map { value ->
                GameModeStats(
                    value!!.epicUserHandle,
                    value.stats.squads.score.valueInt,
                    value.stats.squads.kills.valueInt,
                    value.stats.squads.top1.valueInt,
                    value.stats.squads.matches.valueInt,
                    value.stats.squads.kpm.value.toDouble(),
                    value.stats.squads.kpg.value.toDouble(),
                    value.stats.squads.scorePerMatch.value.toDouble(),
                    value.stats.squads.winRatio.value.toDouble()
                )
            }.collect(Collectors.toList())
            dtos = dtos.stream().sorted { o1, o2 -> o2.wins.compareTo(o1.wins) }.collect(Collectors.toList())

            for (stats: GameModeStats in dtos) {
                when (stats.id) {
                    "Wiscostyle" -> stats.id = "MAK DEM PAY"
                    "OGwiscoMurk1n" -> stats.id = "LAZERS KILL"
                }
            }
            return dtos
        } catch (e: Exception) {
            throw Exception("Something went wrong getting squads stats ${e.stackTrace}")
        }
    }

    private fun convertStatsToHtml(gameModeType: String, stats: List<GameModeStats>): String {
        var builder =
                "<h2> $gameModeType Leaderboards </h2>" +
                "<ol>"
        stats.forEach {
            builder += "<li> ${it.id} has ${it.wins} wins with ${it.kills} kills" +
                    " in ${it.matches} games with ${it.killsPerGame} kills per game and a win ratio of ${it.winRatio}%</li>"
        }
        builder += "</ol>"
        return builder
    }

    override fun getAllStatsLeaderboard(): String {
        val builder = StringBuilder()
        builder
            .append("<html>")
            .append("<body>")

        val models = getAllPlayerStats()

        builder.append(convertStatsToHtml("Solo", getSoloLeaderboardStats(models)))
        builder.append(convertStatsToHtml("Duos", getDuosLeaderboardStats(models)))
        builder.append(convertStatsToHtml("Trios", getTriosLeaderboardStats(models)))
        builder.append(convertStatsToHtml("Squads", getSquadsLeaderboardStats(models)))

        builder
            .append("</body>")
            .append("</html>")
        return builder.toString()
    }

}