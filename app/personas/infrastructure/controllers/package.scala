package personas.infrastructure

import play.api.libs.json.Json
import personas.infrastructure.controllers.dtos.PersonaDTO
import personas.domains.models._

package object controllers {
    implicit val serializador = Json.format[PersonaDTO]

    implicit def PersonaToDTO(persona : Persona) : PersonaDTO = {
        PersonaDTO(persona.ci, persona.nombre, persona.apellido, persona.edad, persona.cargo.toString)
    }

    implicit def DTOToPersona(personaDTO : PersonaDTO) : Persona = {
        personaDTO.cargo match {
            case "Empleado()" => Persona(personaDTO.ci, personaDTO.nombre, personaDTO.apellido, personaDTO.edad, Empleado())
            case "Encargado()" => Persona(personaDTO.ci, personaDTO.nombre, personaDTO.apellido, personaDTO.edad, Encargado())
            case "Duenio()" => Persona(personaDTO.ci, personaDTO.nombre, personaDTO.apellido, personaDTO.edad, Duenio())
        }        
    }

    implicit def PersonaToDTOList(personas : List[Persona]) : List[PersonaDTO] = {
        personas.map(PersonaToDTO)
    }

}
