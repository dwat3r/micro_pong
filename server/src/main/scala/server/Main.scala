package server

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{DateTime => _, _}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json._

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import spray.json.DefaultJsonProtocol

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  //val pinger = system.actorOf(Props[Pinger], "pinger")
  val logic = system.actorOf(Props(new Logic), "logic")

  val interface = new Interface()

  val bindingFuture = Http().bindAndHandle(interface.route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
