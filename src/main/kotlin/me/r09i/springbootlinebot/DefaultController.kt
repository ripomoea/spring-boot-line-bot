package me.r09i.springbootlinebot

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DefaultController {
    @RequestMapping("/")
    fun home(): String {
        return "Hello World!"
    }
}