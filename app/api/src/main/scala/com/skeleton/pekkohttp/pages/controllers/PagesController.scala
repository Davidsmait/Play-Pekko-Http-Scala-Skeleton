package api.src.main.scala.com.skeleton.pekkohttp.pages.controllers

import com.google.inject.ImplementedBy
import play.api.mvc.{ Action, AnyContent }

@ImplementedBy(classOf[PagesControllerImpl])
trait PagesController {
  def index(): Action[AnyContent]
}
