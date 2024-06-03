package com.skeleton.pekkohttp

import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors

import scala.concurrent.ExecutionContext


object MainApp {
  private def startHttServer()(implicit
                               system: ActorSystem[_]): Unit = {
    import system.executionContext
    system.log.info("Server is running")


  }

  def main(args: Array[String]): Unit = {

    val rootBehavior = Behaviors.setup[Nothing]{ context => {
      startHttServer()(context.system)
      context.system.log.info("infolol")
      Behaviors.empty
    }}

    val system = ActorSystem[Nothing](rootBehavior, "ActorPekkoServer")
  }
}
