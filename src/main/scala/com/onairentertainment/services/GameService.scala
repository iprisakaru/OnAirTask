package com.onairentertainment.services

import com.onairentertainment.model.Player

class GameService {
  def playGame(players: Int) = {
    val playerNumbers = ServiceManager.randomService.generateNumbersForPlayers(players)
    val numberResults = playerNumbers.map(ServiceManager.numberService.result)
    val result = playerNumbers.zip(numberResults).sortBy(_._2).reverse.zip(List.range(1, players + 1))

    result.map(data => Player(data._2, data._1._1, data._1._2))
  }
}
