package com.onairentertainment.controller

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives.{handleWebSocketMessages, path}
import akka.stream.scaladsl.Flow
import com.onairentertainment.model.{NumberOfPlayersResponse, Player, RequestError}
import com.onairentertainment.services.ServiceManager
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, parser}

import scala.util.Try

trait JsonFormatter {
  implicit val decodeNumber: Decoder[NumberOfPlayersResponse] =
    Decoder.forProduct1("players")(NumberOfPlayersResponse.apply)
  implicit val encodePlayer: Encoder[Player] =
    Encoder.forProduct3("position", "result", "number")(u => (u.position, u.number, u.result))
  implicit val encodeResults: Encoder[List[Player]] = Encoder.encodeList[Player]
  implicit val encodeError: Encoder[RequestError] =
    Encoder.forProduct2("code", "message")(u => (u.code, u.message))

}

object Routes extends JsonFormatter {
  def playRoute = path("play") {
    handleWebSocketMessages(
      Flow.fromFunction(messageParser)
    )
  }

  private def messageParser(msg: Message) = {
    val decodeResult = Try(parser.parse(msg.asTextMessage.getStrictText).map(_.as[NumberOfPlayersResponse].toSeq).toSeq.flatten.head).toEither
    val result = decodeResult match {
      case Right(value) => ServiceManager.gameService.playGame(value.numOfPlayers).asJson
      case Left(value) => RequestError(400, "Bad Request").asJson
    }

    TextMessage.Strict(result.toString())
  }
}
