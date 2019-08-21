package de.bonify.project
package web

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source
import scala.util.{Failure, Success}

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

import core.db.postgres.BonifyMapper
import Server.BankRecord


trait WebServiceT {

  val filePath : String
  val source = Source.fromFile(filePath)

  private def insertIntoDb =
    BonifyMapper.insertIntoDatabase(
      source.fromFile(filePath).getLines().drop(1).map{x =>
        val entries = x.split(";")
        require(entries.length == 2, "Invalid text format")
        BankRecord(entries.head,entries.last)
      }.toList)

  lazy val routes = pathPrefix("api"){
    path("load"){
      get{
        onComplete(insertIntoDb) {
          case Success(_) =>
            //ensure source is closed to
            source.close()
            complete(StatusCodes.OK, "Data Loaded into Database")
          case Failure(ex) =>
            //We can either log the error, but for simplicity, let us just print the result to the console
            println(ex.getMessage)
            complete(StatusCodes.InternalServerError, "Error While loading Data")
        }
      }
    } ~
      path ("fetch") {
        get{
          parameter('identifier){ id =>
            complete(BonifyMapper.findById(id).map(result =>
              result.rows match {
                case Some(row) =>
                  if(row.isEmpty){
                    StatusCodes.NotFound -> None
                  }else{
                    StatusCodes.OK -> Some(row.head("bank_name").asInstanceOf[String])
                  }
                case None      => StatusCodes.NotFound -> None
              }))
          }
        }
      }
  }
}
