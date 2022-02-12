package com.onairentertainment

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.onairentertainment.controller.Routes.playRoute
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

object GameBootstrap extends App {

  private implicit val system: ActorSystem = ActorSystem()
  protected implicit val executor: ExecutionContext = system.dispatcher
  val configData: Settings = Settings(ConfigFactory.load)

  val routes = playRoute

  println(s"Server started at ${configData.httpInterface}:${configData.httpPort}")
  val binding = Http().bindAndHandle(routes, configData.httpInterface, configData.httpPort)

}