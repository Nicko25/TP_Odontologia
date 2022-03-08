# Trabajo Practico - Sistema de reserva de turnos

Se desea implementar un sistema que permita administrar la reserva de turnos
para una clínica odontológica. Los requerimientos que debe cumplir son los
siguientes:

- **Administración de datos de odontólogos:** Agregar y modificar los datos de los
odontólogos. Registrar apellido, nombre y matrícula de los mismos.
Administración de pacientes: Registrar, modificar y eliminar los pacientes. De
cada uno se almacenan: nombre, apellido, domicilio, DNI, fecha de alta, usuario y
password.
- **Login:** Validar el ingreso al sistema mediante un login con usuario y password.
- **Registrar turno:** Cuando un paciente se loguea al sistema, ingresa al menú
principal donde tiene la opción de registrar un nuevo turno. El sistema muestra en
un combo los odontólogos y una vez seleccionado el mismo muestra en otro combo
los días y horarios disponibles para la semana que tiene dicho odontólogo. En el
paso siguiente, el paciente selecciona el deseado y el sistema registra el turno al
paciente logueado.
- **Consultas:** Mostrar en una grilla los turnos de la semana próxima indicando
nombre y apellido del odontólogo, nombre y apellido del paciente y día y hora del
turno.

## Requerimientos Tecninos

La aplicación debe ser desarrollada en capas:

- Capa de presentación (MVC): La vista deberá ser desarrollada con Java
Swing mediante la utilización de Controladores.
- Capa de negocio: Son las clases Java de nuestro negocio modelado a través
del paradigma orientado a objetos.
- Capa de acceso a datos (DAO): Son las clases que se encargaran de persistir
y recuperar los objetos.
- Capa de datos: base de datos orientada a objetos NeoDatis o archivos.

### Prueba de la aplicacion

&nbsp; Es necesario ingresar un usuario y contraseña para poder testear la aplicacion, de esta forma, dependiendo de el nivel de jerarquia (si es admin o no) se podra acceder
a diferentes opciones disponibles.

| Modo | usuario | contraseña | Descripcion
| ----------- | ----------- |
| Admin | admin | admin | podra generar nuevos usuarios
| User | user | user | podra sacar turnos seleccionados desde un calendario implementado desde la app.

