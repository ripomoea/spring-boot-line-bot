package me.r09i.springbootlinebot

import com.linecorp.bot.model.event.Event
import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.message.Message
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@LineMessageHandler
class Callback {
    private val log: Logger = LoggerFactory.getLogger(Callback::class.java)

    private val covidController by lazy { CovidComponent() }

    private val covidMatchCases = arrayOf(
        Regex("コロナ"),
    )

    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>): Message {
        log.info("event: $event")
        val originalMessageText = event.message.text

        return when {
            containsMatch(originalMessageText, *covidMatchCases) -> {
                when (val result = covidController.get()) {
                    is CovidComponent.Result.Success -> {
                        val message = """
                            最新の COVID 情報です。

                            確認済み: ${result.value.confirmed} 人
                            回復済み: ${result.value.recovered} 人
                            重傷者: ${result.value.critical} 人
                            死亡者: ${result.value.deaths} 人
                        """.trimIndent()
                        TextMessage(message)
                    }
                    is CovidComponent.Result.Failure -> {
                        TextMessage(result.value)
                    }
                }
            }
            else -> {
                TextMessage(originalMessageText)
            }
        }
    }

    @EventMapping
    fun handleDefaultMessageEvent(event: Event) {
        log.info("event: $event")
    }

    private fun containsMatch(
        text: String,
        vararg regexes: Regex,
    ): Boolean = regexes.all { regex -> regex.containsMatchIn(text) }
}