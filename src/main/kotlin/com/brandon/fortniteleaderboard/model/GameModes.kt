package com.brandon.fortniteleaderboard.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GameModes(
    @JsonProperty("p2")
    var solos: GameModeModel,

    @JsonProperty("p10")
    var duos: GameModeModel,

    @JsonProperty("p9")
    var squads: GameModeModel,

    @JsonProperty("ltm")
    var limitedTimeMode: GameModeModel,

    @JsonProperty("curr_p2")
    var currentSolos: GameModeModel?,

    @JsonProperty("curr_p10")
    var currentDuos: GameModeModel?,

    @JsonProperty("curr_trios")
    var currentTrios: GameModeModel?,

    @JsonProperty("curr_p9")
    var currentSquads: GameModeModel?,

    var trios: GameModeModel


)
