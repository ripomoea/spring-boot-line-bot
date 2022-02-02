package me.r09i.springbootlinebot

interface CustomEnv {
    val rakutenApiKey: String
}

class CustomEnvImpl : CustomEnv {
    override val rakutenApiKey: String = System.getenv("RAKUTEN_API_KEY")
}

val customEnv: CustomEnv = CustomEnvImpl()