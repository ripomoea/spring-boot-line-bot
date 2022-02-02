package me.r09i.springbootlinebot

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.Broadcast
import com.linecorp.bot.model.PushMessage
import com.linecorp.bot.model.message.TextMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutionException

@Component
class TestTask {
    @Value("\${line.bot.channel-token}")
    private lateinit var channelToken: String

    private val client: LineMessagingClient by lazy {
        LineMessagingClient.builder(channelToken).build()
    }

    @Scheduled(initialDelay = 5000, fixedRate = 30000)
    fun task1() {
        return

        val textMessage = TextMessage("Hello")

        val botApiResponse = try {
            client.broadcast(Broadcast(textMessage)).get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return
        } catch (e: ExecutionException) {
            e.printStackTrace()
            return
        }

        println(botApiResponse)
    }
}