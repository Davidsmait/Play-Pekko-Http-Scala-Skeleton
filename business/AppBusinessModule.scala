package business

import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class AppBusinessModule extends AbstractModule with PekkoGuiceSupport {

  override def configure(): Unit = {}
}
