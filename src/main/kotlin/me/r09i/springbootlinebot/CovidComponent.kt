package me.r09i.springbootlinebot

import me.r09i.springbootlinebot.domain.model.Covid
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CovidComponent {

    // TODO: Getting value.
    @Value("\${rakuten-api.key}")
    private lateinit var rakutenApiKey: String

    private val template: RestTemplate by lazy {
        RestTemplateBuilder()
            .defaultHeader("x-rapidapi-key", customEnv.rakutenApiKey)
            .defaultHeader("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
            .build()
    }

    fun get(): Result {
        val response = template.getForEntity(
            "https://covid-19-data.p.rapidapi.com/country/code?code=jp",
            Array<Covid>::class.java,
        )
        if (!response.hasBody()) {
            return Result.Failure("取得できませんでした。")
        }
        return Result.Success(response.body!![0])
    }

    sealed class Result {
        data class Success(val value: Covid) : Result()
        data class Failure(val value: String) : Result()
    }
}