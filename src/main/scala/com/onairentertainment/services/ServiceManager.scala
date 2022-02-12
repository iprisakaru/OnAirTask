package com.onairentertainment.services

import com.onairentertainment.GameBootstrap.configData

object ServiceManager {

  lazy val gameService = new GameService
  lazy val randomService = new RandomService(configData.maxRandomNumber)
  lazy val numberService = new NumberService
}
