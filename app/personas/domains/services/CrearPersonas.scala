package personas.domains.services

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.personas.domains.models._
import _root_.personas.infrastructure.databases.ListaPersonas.listaPersonas

trait CrearPersonas {
    def crearEmpleado(persona : Persona) : Future[Option[Persona]] = Future {
        val exist = listaPersonas.exists(p => p.ci == persona.ci)
        exist match {
            case true => None
            case false => {
                val newPersona = persona.copy(cargo = Empleado())
                listaPersonas = listaPersonas.appended(newPersona)
                Some(newPersona)
            }
        }
    }

    def crearEncargado(persona : Persona) : Future[Option[Persona]] = Future {
        val exist = listaPersonas.exists(p => p.ci == persona.ci)
        exist match {
            case true => None
            case false => {
                val newPersona = persona.copy(cargo = Encargado())
                listaPersonas = listaPersonas.appended(newPersona)
                Some(newPersona)
            }
        }
    }
    
    def crearDuenio(persona : Persona) : Future[Option[Persona]] = Future {
        val exist = listaPersonas.exists(p => p.ci == persona.ci)
        exist match {
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
