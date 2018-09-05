package client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import org.scalamock.scalatest.MockFactory

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.Future

class MainTest extends TestKit(ActorSystem("S3BucketSpec"))
  with WordSpecLike with Matchers with MockFactory with BeforeAndAfterAll  {

  implicit val actorSystem = system
  implicit val ec = actorSystem.dispatcher
  implicit val actorMaterializer = ActorMaterializer()(system)

//  val httpStub = stub[Http]
//
//  (httpStub.singleRequest(_)).when(*).returns(Future(HttpResponse(StatusCodes.OK)))



  "Main" should {

    "be cool" in {
      //Await.result(httpStub.sampleTextFile("http://example.com/1"), 1 second) should be ("Response 1")
    }
  }

  override def afterAll(): Unit = {
    val termination = system.terminate()
    Await.ready(termination, Duration.Inf)
  }
}
