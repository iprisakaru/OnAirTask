package com.onairentertainment

import com.typesafe.config.Config

case class Settings(config: Config) {
  private val httpConfig = config.getConfig("http")
  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

  private val gameConfig = config.getConfig("game")
  val maxRandomNumber: Int = gameConfig.getInt("maxRandomNumber")
}