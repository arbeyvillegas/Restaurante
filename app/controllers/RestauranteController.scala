package controllers

import javax.inject._

import models.Restaurante
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.RestauranteService
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by ubuntu on 17/10/16.
  */
@Singleton
class RestauranteController @Inject() (restauranteService : RestauranteService) extends Controller {
  implicit val restauranteWrites: Writes[Restaurante]=(
    (JsPath \ "id").write[Int] and
      (JsPath \ "nombre").write[String] and
      (JsPath \ "direccion").write[String]
    )(unlift(Restaurante.unapply))


  def consultarRestaurantes =Action.async{ implicit request =>
    restauranteService.traerTodo map { restaurantes=>
      val json = Json.toJson(restaurantes)
      Ok(json)
    }
  }
}
