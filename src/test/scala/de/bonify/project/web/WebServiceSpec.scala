package de.bonify.project.web

import akka.http.scaladsl.model.{HttpMethods, HttpRequest, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration.FiniteDuration

class WebServiceSpec extends WebServiceTestServiceT with WebServiceT {

  implicit val routeTestTimeout = RouteTestTimeout(FiniteDuration(30, "seconds"))


  "The Web Service " should {
    "Reject a Get Typecast Query without a given parameter 'identifier' " in {
      HttpRequest(
        method = HttpMethods.GET,
        uri    = "/api/fetch",

      ) ~> Route.seal(routes) ~> check {
        status shouldEqual StatusCodes.NotFound
        responseAs[String] shouldEqual "Request is missing required query parameter 'identifier'"
      }
    }
    "leave requests to base path unhandled" in {
      Get() ~> routes ~> check {
        handled shouldEqual false
      }
    }
    "leave requests to other paths unhandled" in {
      Get("/other") ~> routes ~> check {
        handled shouldEqual false
      }
    }

  }
  override val filePath: String = "data/file.csv"
}

private[web] abstract class WebServiceTestServiceT extends Matchers
  with WordSpecLike
  with ScalatestRouteTest
  with BeforeAndAfterAll
{
  override def beforeAll {
    Thread.sleep(2000)
  }

  override def afterAll {
    Thread.sleep(2000)
    TestKit.shutdownActorSystem(system)
  }
}

