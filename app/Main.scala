import org.apache.pekko.actor.FSM.Failure
import org.apache.pekko.actor.typed.{ ActorSystem, Scheduler }
import org.apache.pekko.actor.typed.scaladsl.AskPattern.Askable
import org.apache.pekko.util.Timeout
import services.actors.SimpleActor
import services.actors.SimpleActor.{ Control, Response }

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.concurrent.duration._
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

object Main {

  def main(args: Array[String]): Unit = {
    implicit val timeout: Timeout        = Timeout(5.seconds)
    //crear actor system
    val actorSytem: ActorSystem[Control] = ActorSystem(SimpleActor(), "simpleActor")
    implicit val scheduler: Scheduler    = actorSytem.scheduler
    // Enviar un mensaje al actor
//    actorSytem ! SimpleActor.SayHello("david")

    val futureResponse: Future[SimpleActor.Response] =
      actorSytem ? (ref => SimpleActor.SayHello("david", ref))

    val getHello: Future[SimpleActor.Response] =
      actorSytem ? (ref => SimpleActor.GetHello(ref))

    futureResponse.onComplete {
      case Success(value)                =>
        value match {
          case SimpleActor.Greeted(message) =>
            actorSytem.log.info(s"message: ${message}")
            (actorSytem ? (ref => SimpleActor.GetHello(ref))).onComplete {
              case Success(value)                =>
                value match {
                  case SimpleActor.Greeted(message) => actorSytem.log.info(s"message: ${message}")
                }
              case scala.util.Failure(exception) =>
                println(s"Ha ocurrido un error: ${exception.getMessage}")
            }
        }
      case scala.util.Failure(exception) =>
        println(s"Ha ocurrido un error: ${exception.getMessage}")
    }
  }

}
