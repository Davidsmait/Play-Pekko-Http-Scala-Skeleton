package api.src.main.scala.com.skeleton.pekkohttp.pages.controllers

import play.api.mvc._

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext
import org.apache.pekko.actor.typed.scaladsl.AskPattern._
import services.CounterActor.{ Command, Incremental }
import org.apache.pekko.actor.typed.{ ActorRef, ActorSystem }
import org.apache.pekko.util.Timeout
import services.HelloActor.SayHello
import scala.concurrent.duration._

@Singleton
class PagesControllerImpl @Inject() (actorRef: ActorRef[Command], cc: ControllerComponents)(
    implicit executionContext: ExecutionContext,
    implicit val system: ActorSystem[_]
) extends AbstractController(cc)
    with PagesController {

//  private implicit val timeout: Timeout = Timeout.create(system.settings.config.getDuration("my-app.routes.ask-timeout"))

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    //    Ok("ok")
    Ok(views.html.index())
  }

  def incrementCounter(): Action[AnyContent] = Action.async {
    implicit val timeout: Timeout = 5.seconds
    actorRef.ask(Incremental).map { _ =>
      Ok("Counter incremented")
    }
  }

//  def sayHello(name: String) = Action.async {
//    (helloActor ? SayHello(name)).mapTo[String].map { message => Ok(message) }
//  }

}
