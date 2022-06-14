package personas.infrastructure.databases

import personas.domains.models._

object ListaPersonas {
    val listaPersonas : List[Persona] = List(
        Persona("1", "Juan", "Pérez", 20, Empleado()),
        Persona("2", "Pedro", "Gonzáles", 21, Empleado()),
        Persona("3", "Jose", "Martínez", 22, Encargado()),
        Persona("4", "Pablo", "Moreira", 23, Duenio()),
    )
}
