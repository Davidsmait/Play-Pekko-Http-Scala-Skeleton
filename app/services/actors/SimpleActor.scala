package services.actors

import org.apache.pekko.actor.typed.{ ActorRef, Behavior }
import org.apache.pekko.actor.typed.scaladsl.Behaviors

object SimpleActor {
  // demine los mensajes que el actor puede manejar
  sealed trait Control
  case class SayHello(name: String, replyTo: ActorRef[Response]) extends Control
  case class GetHello(replyTo: ActorRef[Response])               extends Control

  sealed trait Response
  case class Greeted(message: String) extends Response

  //define el comportamiento del actor

  def apply(): Behavior[Control] = Behaviors.receive { (context, message) =>
    {
      message match {
        case SayHello(name, replyTo) =>
          context.log.info(s"Simple actor : Hello, ${name}!")
          replyTo ! Greeted(name)
          Behaviors.same

        case GetHello(replyTo) =>
          replyTo ! Greeted("other")
          Behaviors.same
      }
    }
  }

}
