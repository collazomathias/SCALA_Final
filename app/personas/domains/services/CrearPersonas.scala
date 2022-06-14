package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait CrearPersonas {
    def crearEmpleado(persona : Persona) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(p => p.ci == persona.ci)) None
        else Some(persona.copy(cargo = Empleado()))
    }

    def crearEncargado(persona : Persona) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(p => p.ci == persona.ci)) None
        else Some(persona.copy(cargo = Encargado()))
    }
    
    def crearDuenio(persona : Persona) : Future[Option[Persona]] = Future {
        if(listaPersonas.exists(p => p.ci == persona.ci)) None
        else Some(persona.copy(cargo = Duenio()))
    }
}

object CrearPersonas extends CrearPersonas
