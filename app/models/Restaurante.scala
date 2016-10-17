package models

import javax.inject._
import play.api.Application
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import play.api.db.slick.DatabaseConfigProvider
import slick.backend.DatabaseConfig
import scala.concurrent.Future

/**
  * Created by ubuntu on 17/10/16.
  */
case class Restaurante (var id:Int,var nombre:String,var direccion:String)


class RestauranteTableDef(tag: Tag) extends Table[Restaurante](tag, "Restaurantes") {

  def id = column[Int]("id", O.PrimaryKey,O.AutoInc)
  def nombre = column[String]("Nombre")
  def direccion = column[String]("Direccion")

  override def * =
    (id, nombre, direccion) <>(Restaurante.tupled, Restaurante.unapply)
}

class Restaurantes @Inject() (appProvider: Provider[Application]) {

  def dbConfig(): DatabaseConfig[JdbcProfile] =
        DatabaseConfigProvider.get[JdbcProfile](appProvider.get())

  val restaurantes = TableQuery[RestauranteTableDef]

  var lista :List[Restaurante]={
    List(
      Restaurante(
        3
        ,"La casa blanca"
        ,"Argentina"
      ),
      Restaurante(
        4
        ,"La casa rosta"
        ,"Manizales"
      )
    )
  }

  def adicionar(restaurante: Restaurante)={
    lista = lista ::: List(restaurante)
  }

  def traerTodo:Future[Seq[Restaurante]]={
    dbConfig().db.run(restaurantes.result)
  }
}


