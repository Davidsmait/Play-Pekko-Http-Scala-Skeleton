package services

import org.apache.pekko.actor.typed.scaladsl.{ AbstractBehavior, ActorContext, Behaviors }
import org.apache.pekko.actor.typed.{ ActorRef, Behavior }

object HelloActorObject {
  final case class SayHello(
      name: String,
      replyTo: ActorRef[String]
  )

  def create(): Behavior[HelloActorObject.SayHello] = {
    Behaviors.setup(context => new HelloActorObject(context))
  }
}

final class HelloActorObject private (
    context: ActorContext[HelloActorObject.SayHello]
) extends AbstractBehavior(context) {

  override def onMessage(msg: HelloActorObject.SayHello): Behavior[HelloActorObject.SayHello] = {
    msg.replyTo ! s"Hello, ${msg.name}"
    this
  }
}
