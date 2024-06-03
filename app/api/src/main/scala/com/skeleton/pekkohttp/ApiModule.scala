package api.src.main.scala.com.skeleton.pekkohttp

import api.src.main.scala.com.skeleton.pekkohttp.pages.controllers.{
  PagesController,
  PagesControllerImpl
}
import com.google.inject.AbstractModule
import org.apache.pekko.actor.typed.{ ActorRef, ActorSystem, Behavior }
import play.api.libs.concurrent.PekkoGuiceSupport
import services.{ ConfiguredActor, CounterActor, HelloActor }
import services.CounterActor.Command
import org.apache.pekko.actor.typed.scaladsl.adapter._
import play.api._
import play.api.routing.Router
import javax.inject.{ Inject, Provider }

class ApiModule extends AbstractModule with PekkoGuiceSupport {

  override protected def configure(): Unit = {
    bind(classOf[PagesController]).to(classOf[PagesControllerImpl])
    bind(classOf[ActorRef[Command]]).toProvider(classOf[CounterActorProvider]).asEagerSingleton()
  }

  private class CounterActorProvider @Inject() (actorSystem: ActorSystem[Command])
      extends Provider[ActorRef[Command]] {
    override def get(): ActorRef[Command] = {
      val counterBehavior: Behavior[Command] = CounterActor.apply()
      val counterActor                       = actorSystem.systemActorOf(counterBehavior, "counterActor")
      counterActor
    }
  }

//  final class AppComponents(context: ApplicationLoader.Context)
//      extends BuiltInComponentsFromContext(context)
//      with NoHttpFiltersComponents {
//    val router: Router = Router.empty
//
//    val helloActor: ActorRef[HelloActor.SayHello] = {
//      actorSystem.spawn(HelloActor.create(), "hello-actor")
//    }
//    val configuredActor: ActorRef[ConfiguredActor.GetConfig] = {
//      val behavior = ConfiguredActor.create(configuration)
//      actorSystem.spawn(behavior, "configured-actor")
//    }
//
////    val main = new Main(helloActor, configuredActor)
//  }
}
