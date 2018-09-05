package client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

//case class Pos(x : Int, y : Int)

object Main extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val ip = "192.168.1.182:8000"

  val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = s"http://$ip/get-pos"))

  responseFuture
    .onComplete {
      case Success(res) => println(res)
      case Failure(ex)   => sys.error(s"something wrong $ex")
    }
}
