package server.test

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.{HttpEntity, HttpRequest, MediaTypes, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import akka.http.scaladsl.model.HttpMethods
import akka.util.ByteString
import server._
import spray.json.{JsValue, enrichString}

class InterfaceTest extends WordSpec with Matchers with ScalatestRouteTest {

  def postRequest(path: String, json: ByteString): HttpRequest =
    HttpRequest(HttpMethods.POST,
      uri = path,
      entity = HttpEntity(MediaTypes.`application/json`, json)
    )
  def getRequest(path : String, json : ByteString) : HttpRequest =
    HttpRequest(HttpMethods.GET,
      uri = path)

  import server.Main._

  "The server" should {
      Post("/game/player_pos",interface.PlayerPosUpdMesg(0, Up)) ~> interface.route ~> check {
        status shouldEqual StatusCodes.OK
      }
  }
}