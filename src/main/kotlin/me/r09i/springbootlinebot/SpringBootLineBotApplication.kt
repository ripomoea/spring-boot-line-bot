package me.r09i.springbootlinebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@Suppress("RedundantModalityModifier")
@SpringBootApplication
open class SpringBootLineBotApplication

fun main(args: Array<String>) {
    runApplication<SpringBootLineBotApplication>(*args)
}
