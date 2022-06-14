package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait ObtenerPersonas {
    def obtenerPersona(ci : String) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(persona => persona.ci == ci)) {
            Some(listaPersonas.filter(persona => persona.ci == ci).head)
        } else None
    }
}

object ObtenerPersonas extends ObtenerPersonas