package com.onairentertainment.services

class NumberService {
  def result(number: Int): Int = {
    val numOfEntrances = getNumberOfEntrances(number)
    numOfEntrances.map(data => computeNumber(data._1, data._2)).sum.toInt
  }

  private def getNumberOfEntrances(number: Int): Seq[(Int, Int)] = number.toString.distinct.map(symbol => (symbol.asDigit, number.toString.count(_ == symbol)))

  private def computeNumber(number: Int, times: Int) = Math.pow(10, times - 1) * number

}
