package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait EliminarPersonas {
    def eliminarPersona(ci : String) : Future[Option[Persona]] = Future {
        val per = listaPersonas.filter(persona => persona.ci == ci).headOption
        listaPersonas = listaPersonas.filter(persona => persona.ci != ci)
        per
    }
}

object EliminarPersonas extends EliminarPersonas
