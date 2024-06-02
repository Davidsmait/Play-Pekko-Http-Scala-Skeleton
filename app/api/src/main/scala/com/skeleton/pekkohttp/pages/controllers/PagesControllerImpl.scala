package api.src.main.scala.com.skeleton.pekkohttp.pages.controllers

import play.api.mvc._

import javax.inject.{ Inject, Singleton }

@Singleton
class PagesControllerImpl @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with PagesController {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
