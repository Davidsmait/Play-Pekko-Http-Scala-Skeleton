package services

import com.google.inject.Provides
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.ActorRef
import org.apache.pekko.actor.typed.Behavior
import play.api.libs.concurrent.ActorModule
import play.api.Configuration

object ConfiguredActor extends ActorModule {
  type Message = GetConfig

  final case class GetConfig(replyTo: ActorRef[String])

  @Provides
  def create(configuration: Configuration): Behavior[GetConfig] = {
    Behaviors.setup { _ =>
      val config = configuration.get[String]("my.config")
      Behaviors.receiveMessage[GetConfig] { case GetConfig(replyTo) =>
        replyTo ! config
        Behaviors.same
      }
    }
  }
}
