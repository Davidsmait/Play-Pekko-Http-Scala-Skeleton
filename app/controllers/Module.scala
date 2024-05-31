import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controllers.domain.pages.actions.{ PagesController, PagesControllerImpl }
import play.api.libs.concurrent.PekkoGuiceSupport

import scala.concurrent.ExecutionContext

class Module extends AbstractModule with PekkoGuiceSupport {
  override def configure(): Unit = {
    bind(classOf[PagesController])
      .to(classOf[PagesControllerImpl])
  }
}
