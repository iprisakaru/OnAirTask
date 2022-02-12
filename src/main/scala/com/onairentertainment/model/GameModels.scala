package com.onairentertainment.model

case class Player(position: Int, result: Int, number: Int)

case class NumberOfPlayersResponse(numOfPlayers: Int)

case class RequestError(code: Int, message: String)