package com.tasktd

import com.tasktd.services.PokeService
import com.tasktd.services.PokeServiceOnHttp
import com.tasktd.services.PokeAsyncServiceOnHttp
import monix.execution.Scheduler.Implicits.global

object PokemonProject {
  def main(args: Array[String]): Unit =
    val aoeSvc = new PokeAsyncServiceOnHttp(http = new AsyncHttp)

    aoeSvc.getPokemons()
      .runAsync {
        case Left(err) =>
          println(s"Got err: ${err.toString}")
        case Right(units) =>
          println(units.head)
      }
}
