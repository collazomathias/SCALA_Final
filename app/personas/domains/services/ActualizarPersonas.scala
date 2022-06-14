package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait ActualizarPersonas {
    def actualizarPersona(persona : Persona) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(p => p.ci == persona.ci)) {
            val per1 = listaPersonas.filter(p => p.ci == persona.ci).head
            val newPersona = per1.copy(nombre = persona.nombre, apellido = persona.apellido, edad = persona.edad, cargo = persona.cargo)
            Some(newPersona)
        } else None
    }
}

object ActualizarPersonas extends ActualizarPersonas