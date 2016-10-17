package services

import javax.inject._

import scala.concurrent.Future
import models.{Restaurante, Restaurantes}
/**
  * Created by ubuntu on 17/10/16.
  */
@Singleton
class RestauranteService @Inject()(restaurantes: Restaurantes) {

  def traerTodo : Future[Seq[Restaurante]]={
    restaurantes.traerTodo
  }
}
