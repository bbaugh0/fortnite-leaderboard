package com.brandon.fortniteleaderboard.service.impl

import com.brandon.fortniteleaderboard.client.FortniteApiClient
import com.brandon.fortniteleaderboard.dto.GameModeStats
import com.brandon.fortniteleaderboard.model.FortniteModel
import com.brandon.fortniteleaderboard.model.GameModeModel
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

    private fun convertModelToStats(model: GameModeModel, id: String): GameModeStats {
        return GameModeStats(
            id,
            model.score.valueInt,
            model.kills.valueInt,
            model.top1.valueInt,
            model.matches.valueInt,
            model.kpm.value.toDouble(),
            model.kpg.value.toDouble(),
            model.scorePerMatch.value.toDouble(),
            model.winRatio.value.toDouble()
        )
    }

    private fun getSoloLeaderboardStats(models: List<FortniteModel?>): List<GameModeStats> {
        try {
            val dtos = models.parallelStream().map { value ->
                convertModelToStats(value!!.stats.solos, value.epicUserHandle)
            }.collect(Collectors.toList())
            dtos.sortWith(compareBy<GameModeStats?>({it!!.wins}, {it!!.winRatio}).reversed())

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
            val dtos = models.parallelStream().map { value ->
                convertModelToStats(value!!.stats.duos, value.epicUserHandle)
            }.collect(Collectors.toList())
            dtos.sortWith(compareBy<GameModeStats?>({it!!.wins}, {it!!.winRatio}).reversed())

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
            val dtos = models.parallelStream().map { value ->
                convertModelToStats(value!!.stats.trios, value.epicUserHandle)
            }.collect(Collectors.toList())
            dtos.sortWith(compareBy<GameModeStats?>({it!!.wins}, {it!!.winRatio}).reversed())

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
            val dtos = models.parallelStream().map { value ->
                convertModelToStats(value!!.stats.squads, value.epicUserHandle)
            }.collect(Collectors.toList())
            dtos.sortWith(compareBy<GameModeStats?>({it!!.wins}, {it!!.winRatio}).reversed())

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