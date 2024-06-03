package services

import org.apache.pekko.actor.typed.{ ActorRef, Behavior }
import org.apache.pekko.actor.typed.scaladsl.{ AbstractBehavior, ActorContext, Behaviors }

object CounterActor {
  sealed trait Command
  final case class Incremental(replyTo: ActorRef[Int]) extends Command
  final case class GetValue(replyTo: ActorRef[Int])    extends Command

  def apply(): Behavior[Command] = Behaviors.setup(context => new CounterActor(context))
}

class CounterActor(context: ActorContext[CounterActor.Command])
    extends AbstractBehavior[CounterActor.Command](context) {
  import CounterActor._

  private var value: Int = 0
  override def onMessage(msg: Command): Behavior[Command] = {
    msg match {
      case Incremental(replyTo) =>
        value += 1
        replyTo.tell(value)
      case GetValue(replyTo)    =>
        replyTo.tell(value)
    }
    this
  }
}
