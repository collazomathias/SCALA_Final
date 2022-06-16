package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait EliminarPersonas {
    def eliminarPersona(ci : String) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(persona => persona.ci == ci)) {
            val per = listaPersonas.filter(persona => persona.ci == ci).head
            listaPersonas = listaPersonas.filter(persona => persona.ci != ci)
            Some(per)
        } else None
    }
}

object EliminarPersonas extends EliminarPersonas
