package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import personas.domains.models._
import personas.infrastructure.databases.ListaPersonas.listaPersonas

trait ObtenerPersonas {
    def obtenerPersona(ci : String) : Future[Option[Persona]] = Future {
        listaPersonas.filter(persona => persona.ci == ci).headOption
    }

    def obtenerPersonas() : Future[Option[List[Persona]]] = Future {
        if(listaPersonas.isEmpty) None
        else Some(listaPersonas)
    }
}

object ObtenerPersonas extends ObtenerPersonas