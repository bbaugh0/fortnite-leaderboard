package com.brandon.fortniteleaderboard.client

import com.brandon.fortniteleaderboard.model.FortniteModel
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class FortniteApiClient (
    var webClient: WebClient = WebClient.builder()
        // TODO: put this in app yml
        .baseUrl("https://api.fortnitetracker.com/v1/")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("TRN-Api-Key", "f8af0c8f-ca06-45d2-88fc-e318d826db2d")
        .build()
        ) {

    fun getPlayerStats(id: String): FortniteModel? {
        return webClient
            .get()
            .uri{builder -> builder
                .path("profile")
                .pathSegment("gamepad")
                .pathSegment(id)
                .build()}
            .retrieve()
            .bodyToMono(FortniteModel::class.java)
            .block()
    }
}