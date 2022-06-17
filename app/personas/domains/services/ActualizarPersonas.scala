package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import personas.domains.models._
import personas.infrastructure.databases.ListaPersonas.listaPersonas

trait ActualizarPersonas {
    def actualizarPersona(persona : Persona) : Future[Option[Persona]] = Future {
        listaPersonas.exists(p => p.ci == persona.ci) match {
            case true => {
                listaPersonas = listaPersonas.map(p => if (p.ci == persona.ci) persona else p)
                Some(persona)
            }
            case false => None
        }
    }
}

object ActualizarPersonas extends ActualizarPersonas