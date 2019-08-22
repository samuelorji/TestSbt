package de.bonify.project

import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import core.config.BonifyConfig
import web.WebServiceT

object Server extends App {

  implicit val system       = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val filePathOpt   = args.headOption
  filePathOpt match {
    case Some(filepath) =>
      Http().bindAndHandle(new WebServiceT {
        override val filePath: String = filepath
      }.routes,BonifyConfig.webHost,BonifyConfig.webPort)
    case None           =>
      throw new IllegalArgumentException("Application needs FilePath")
  }
}
