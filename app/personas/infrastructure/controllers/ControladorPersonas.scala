package personas.infrastructure.controllers

import personas.domains.services._

import javax.inject.Singleton
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import personas.infrastructure.controllers.dtos.PersonaDTO
import play.api.libs.json.Json
import javax.inject.Inject
import personas.domains.models.Persona

@Singleton
class ControladorPersonas @Inject() (val controllerComponents : ControllerComponents) extends BaseController {
    def obtenerPersonaPorCi(ci : String) = Action.async {
        ObtenerPersonas.obtenerPersona(ci).map(personaOptional => {
            personaOptional.map(persona => {
                val personaDTO : PersonaDTO = persona
                val json = Json.obj("Persona" -> personaDTO)
                Ok(json)
            }).getOrElse(NotFound("No se encontro la persona"))
        })
    }

    def obtenerPersonas() = Action.async {
        ObtenerPersonas.obtenerPersonas().map(listaOptional => {
            listaOptional.map(lista => {
                val listaDTO : List[PersonaDTO] = lista
                val json = Json.obj("Lista de personas" -> listaDTO)
                Ok(json)
            }).getOrElse(NotFound("No se encontro la persona"))
        })
    }

    def crearNuevoEmpleado() = Action.async(parse.json) {
        request =>
        val personaDTO = request.body.validate[PersonaDTO]
        personaDTO.asEither match {
            case Right(value) =>
                CrearPersonas.crearEmpleado(value).map(persona => {
                    if(persona.isEmpty) NotFound("No se pudo crear la persona")
                    else {
                        val personaDTO : PersonaDTO = persona.get
                        val json = Json.toJson(personaDTO)
                        Created(json)
                    }
                })
            case Left(error) =>
                Future.successful(BadRequest(error.toString))
        }
    }

    def crearNuevoEncargado() = Action.async(parse.json) {
        request =>
        val personaDTO = request.body.validate[PersonaDTO]
        personaDTO.asEither match {
            case Right(value) =>
                CrearPersonas.crearEncargado(value).map(persona => {
                    if(persona.isEmpty) NotFound("No se pudo crear la persona")
                    else {
                        val personaDTO : PersonaDTO = persona.get
                        val json = Json.toJson(personaDTO)
                        Created(json)
                    }
                })
            case Left(error) =>
                Future.successful(BadRequest(error.toString))
        }
    }

    def crearNuevoDuenio() = Action.async(parse.json) {
        request =>
        val personaDTO = request.body.validate[PersonaDTO]
        personaDTO.asEither match {
            case Right(value) =>
                CrearPersonas.crearDuenio(value).map(persona => {
                    if(persona.isEmpty) NotFound("No se pudo crear la persona")
                    else {
                        val personaDTO : PersonaDTO = persona.get
                        val json = Json.toJson(personaDTO)
                        Created(json)
                    }
                })
            case Left(error) =>
                Future.successful(BadRequest(error.toString))
        }
    }

    def actualizarPersona() = Action.async(parse.json) {
        request =>
        val personaDTO = request.body.validate[PersonaDTO]
        personaDTO.asEither match {
            case Right(value) =>
                ActualizarPersonas.actualizarPersona(value).map(persona => {
                    if(persona.isEmpty) NotFound("No se encontró la persona para actualizar")
                    else {
                        val personaDTO : PersonaDTO = persona.get
                        val json = Json.toJson(personaDTO)
                        Ok(json)
                    }
                })
            case Left(error) =>
                Future.successful(BadRequest(error.toString))
        }
    }

    def eliminarPersona(ci : String) = Action.async {
        EliminarPersonas.eliminarPersona(ci).map(personaOptional => {
            personaOptional.map(persona => {
                val personaDTO : PersonaDTO = persona
                val json = Json.obj("Persona eliminada" -> personaDTO)
                Ok(json)
            }).getOrElse(NotFound("No se encontro la persona para eliminar"))
        })
    }
}
