package com.onairentertainment.services

import scala.util.Random

class RandomService(randomToNumber: Int) {
  private def generateRandomNumber(): Int = {
    Random.nextInt(randomToNumber)
  }

  def generateNumbersForPlayers(numOfPlayers: Int): List[Int] = {
    List.fill(numOfPlayers)(generateRandomNumber())
  }
}
