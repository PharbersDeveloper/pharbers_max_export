package controllers

import play.api.mvc._
import io.circe.syntax._
import akka.actor.ActorSystem
import bricks.export.ExportMaxResult
import play.api.libs.circe.Circe
import javax.inject.{Inject, Singleton}
import com.pharbers.pattern.frame.PlayEntry
import com.pharbers.jsonapi.model.RootObject
import com.pharbers.jsonapi.json.circe.CirceJsonapiSupport

@Singleton
class PhJsonApiController @Inject()(implicit val cc: ControllerComponents, implicit val actorSystem: ActorSystem)
        extends AbstractController(cc) with Circe with CirceJsonapiSupport {

    def routes(pkg: String, step: Int): Action[RootObject] = Action(circe.json[RootObject]) { implicit request =>
        Ok(
            (pkg, step) match {
                case ("exportmaxresult", 0) => PlayEntry().excution(ExportMaxResult()).asJson
                case (_, _) => throw new Exception("Bad Request for input")
            }
        )
    }

    def routes2(pkg1: String, pkg2: String, step: Int): Action[RootObject] = routes(pkg1 + "/" + pkg2, step)
}
