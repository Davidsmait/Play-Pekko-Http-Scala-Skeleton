package api.src.main.scala.com.skeleton.pekkohttp

import api.src.main.scala.com.skeleton.pekkohttp.pages.controllers.{
  PagesController,
  PagesControllerImpl
}
import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class ApiModule extends AbstractModule with PekkoGuiceSupport {
  override def configure(): Unit = {
    bind(classOf[PagesController])
      .to(classOf[PagesControllerImpl])
  }
}
