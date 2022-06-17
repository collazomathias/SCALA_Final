package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import personas.domains.models._
import personas.infrastructure.databases.ListaPersonas.listaPersonas

trait CrearPersonas {
    def crearEmpleado(persona : Persona) : Future[Option[Persona]] = Future {
        listaPersonas.exists(p => p.ci == persona.ci) match {
            case true => None
            case false => {
                val newPersona = persona.copy(cargo = Empleado())
                listaPersonas = listaPersonas.appended(newPersona)
                Some(newPersona)
            }
        }
    }

    def crearEncargado(persona : Persona) : Future[Option[Persona]] = Future {
        listaPersonas.exists(p => p.ci == persona.ci) match {
            case true => None
            case false => {
                val newPersona = persona.copy(cargo = Encargado())
                listaPersonas = listaPersonas.appended(newPersona)
                Some(newPersona)
            }
        }
    }
    
    def crearDuenio(persona : Persona) : Future[Option[Persona]] = Future {
        listaPersonas.exists(p => p.ci == persona.ci) match {
            case true => None
            case false => {
                val newPersona = persona.copy(cargo = Duenio())
                listaPersonas = listaPersonas.appended(newPersona)
                Some(newPersona)
            }
        }
    }
}

object CrearPersonas extends CrearPersonas
