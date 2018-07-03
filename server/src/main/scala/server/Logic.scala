package server

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging


// data
case class Pos(x : Int, y : Int)

case class Ball(pos : Pos)

class Player(id : Int, pos : Pos)(implicit var screen_size : Pos){

  var _pos : Pos = pos

  def move(dir : Direction): Unit = dir match {
    case Up => if (_pos.y < screen_size.y){
      _pos = Pos(_pos.x, _pos.y + 1)
    }
    case Down => if (pos.y > 0){
      _pos = Pos(_pos.x, _pos.y - 1)
    }
  }
}

// messages
trait Direction
case object Up extends Direction
case object Down extends Direction

case class PlayerMove(id : Int, dir : Direction)

class Logic(
             implicit var screen_size : Pos = Pos(200,100),
             var ball : Ball = Ball(Pos(0,0))) extends Actor {

  var players : (Player, Player) = (new Player(0, Pos(0,50)), new Player(1, Pos(200, 50)))

  val log = Logging(context.system, this)

  def receive: PartialFunction[Any, Unit] = {
    case PlayerMove(0, move) => log.info(s"received move $move")
    case _      ⇒ log.info("received unknown message")
  }
}
