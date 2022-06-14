package personas.domains.models

trait Cargo

case class Duenio() extends Cargo
case class Encargado() extends Cargo
case class Empleado() extends Cargo

case class Persona (
    ci : String,
    nombre : String, 
    apellido : String, 
    edad : Int, 
    cargo : Cargo
)