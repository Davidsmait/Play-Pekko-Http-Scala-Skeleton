package services

import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.{ ActorRef, Behavior }

import org.apache.pekko.actor._

object HelloActor {
  def props: Props = Props[HelloActor]()

  case class SayHello(name: String)
}

class HelloActor extends Actor {
  import HelloActor._

  def receive: Receive = { case SayHello(name: String) =>
    sender() ! "Hello, " + name
  }
}

//object HelloActor {
//  final case class SayHello(
//      name: String,
//      replyTo: ActorRef[String]
//  )
//
//  def create(): Behavior[SayHello] = {
//    Behaviors.receiveMessage[SayHello] { case SayHello(name, replyTo) =>
//      replyTo ! s"Hello, $name"
//      Behaviors.same
//    }
//  }
//}
