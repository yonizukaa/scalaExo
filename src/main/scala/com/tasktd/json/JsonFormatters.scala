package com.tasktd.json

import spray.json._
import DefaultJsonProtocol._
import com.tasktd.models.PokemonCase

object JsonFormatters extends DefaultJsonProtocol {

  implicit val intOrString: JsonFormat[Int | String] = new JsonFormat[Int | String] {
    override def read(json: JsValue): Int | String =
      json match {
        case JsNumber(nb) => nb.toInt
        case JsString(str) => str
        case _ =>
          throw new RuntimeException(s"Invalid js value for intOrString: ${json.prettyPrint}")
      }

    override def write(obj: Int | String): JsValue = {
      obj match {
        case x: Int => JsNumber(x)
        case str: String => JsString(str)
      }
    }
  }

  implicit val pokemonJsonFormat: JsonFormat[PokemonCase] = jsonFormat3(PokemonCase.apply)
}
