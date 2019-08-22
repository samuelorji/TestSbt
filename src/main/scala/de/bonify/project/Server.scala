package de.bonify.project

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import core.config.BonifyConfig
import web.WebServiceT

import scala.io.Source

object Server extends App {

  implicit val system       = ActorSystem()
  implicit val materializer = ActorMaterializer()

  /**
    * Data Model*/
  case class BankRecord(bank : String, identifier : String)

  val filePathOpt   = args.headOption
  filePathOpt match {
    case Some(filepath) =>
      Http().bindAndHandle(new WebServiceT {
        override val filePath: String = filepath
      }.routes,BonifyConfig.webHost,BonifyConfig.webPort)
    case None           =>
      throw new IllegalArgumentException("Application needs FilePath")
  }

  def processContract(`type`: String): Unit = {
    if ("electricity" == `type`) System.out.println("Processed electricity")
    else if ("dsl" == `type`) System.out.println("Processed dsl")
    else if ("appartment_rent" == `type`) System.out.println("Processed appartment")
  }
}
