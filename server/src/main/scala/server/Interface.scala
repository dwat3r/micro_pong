package server

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol

class Interface(){

  // messages
  case class PlayerPosUpdMesg(id : Int, dir : Direction)
  // marshallers
  object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
    implicit val posFormat = jsonFormat2(Pos)
    implicit val playerPosUpdMsgFormat = jsonFormat2(PlayerPosUpdMesg)
  }

  val route =
    path("game" / "player_pos") {
      post {
        complete {
          "undefined"
        }
      }
    }
}