package de.bonify.project
package web

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success}


trait WebServiceT {

  val filePath : String

  private val isDynamic : Set[String] => Boolean = set => set.size != 1
  private def fetchDynamicRoute : Future[String] = {
    Future {
      val source = Source.fromFile(filePath).getLines().toList
      val paths = source.head.replaceFirst("/", "").split('/')

      val pathLength = paths.length
      val result = source.collect { case x: String if x.nonEmpty => x }.foldLeft(Array.fill[Set[String]](pathLength)(Set())) {
        case (set, url) =>
          val entries = url.replaceFirst("/", "").split('/')
          (0 until pathLength).map(x => set(x) + entries(x)).toArray
      }

      (paths zip result).map {
        case (path, set) =>
          if (isDynamic(set)) "*dynamic*" else path
      }.mkString("/","/","")
    }
  }

  lazy val routes = pathPrefix("api"){
    path("dynamic"){
      get{
        onComplete(fetchDynamicRoute){
          case Success(res) => complete(StatusCodes.OK, res)
          case Failure(_)  => complete(StatusCodes.InternalServerError )
        }
      }
    }
  }
}
