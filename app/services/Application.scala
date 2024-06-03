package services

import javax.inject._

import org.apache.pekko.actor._
import play.api.mvc._

@Singleton
class Application @Inject() (system: ActorSystem, cc: ControllerComponents)
    extends AbstractController(cc) {
  val helloActor: ActorRef = system.actorOf(HelloActor.props, "hello-actor")

}
