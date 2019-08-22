package de.bonify.project.web

import akka.http.scaladsl.model.{ HttpMethods, HttpRequest, StatusCodes }
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{ RouteTestTimeout, ScalatestRouteTest }
import akka.testkit.TestKit

import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

import scala.concurrent.duration.FiniteDuration

class WebServiceSpec extends WebServiceTestServiceT with WebServiceT {

  implicit val routeTestTimeout = RouteTestTimeout(FiniteDuration(30, "seconds"))


  "The Web Service " should {
    "Process a vaild Dynamic URL request " in {
      HttpRequest(
        method = HttpMethods.GET,
        uri    = "/api/dynamic",

      ) ~> Route.seal(routes) ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "/users/*dynamic*/info/*dynamic*"
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
  override val filePath: String = "data/url.csv"
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

