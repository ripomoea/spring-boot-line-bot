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

    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>): Message {
        log.info("event: $event")
        val originalMessageText = event.message.text
        return TextMessage(originalMessageText)
    }

    @EventMapping
    fun handleDefaultMessageEvent(event: Event) {
        log.info("event: $event")
    }
}