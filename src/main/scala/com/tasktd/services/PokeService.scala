package com.tasktd.services

import com.tasktd.models.PokemonCase
import monix.eval.Task
import com.tasktd.Http
import spray.json._
import DefaultJsonProtocol._
import com.tasktd.json.JsonFormatters._
import com.tasktd.AsyncHttp

trait PokeService {

  def getPokemons(): Seq[PokemonCase]

}

trait PokeAsyncService {

  def getPokemons(): Task[Seq[PokemonCase]]

}

class PokeServiceOnHttp(http: Http) extends PokeService {

  def getPokemons(): Seq[PokemonCase] = {
    http.get("https://pokeapi.co/api/v2/pokemon/1")
      .parseJson
      .asJsObject
      .convertTo[Seq[PokemonCase]]
  }

}

class PokeAsyncServiceOnHttp(http: AsyncHttp) extends PokeAsyncService {

  def getPokemons(): Task[Seq[PokemonCase]] =
    http.get("https://pokeapi.co/api/v2/pokemon/1")
      .map(content => content
        .parseJson.asJsObject
        .convertTo[Seq[PokemonCase]]
      )
}