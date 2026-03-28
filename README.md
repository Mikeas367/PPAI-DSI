# RED SISMICA
## Proyecto de Aplicacion Integrador - Diseño De Sistemas De Informacion

En este trabajo practico se lleva a cabo la realizacion de un caso de uso especifico (C.U N°37) del sistema de informacion de una Red Sismica Digital Virtual, el cual se encarga de  gestionar y coordinar la conexión entre Estaciones Sismológicas (ES) distribuidas en el país y en países limítrofes.

## Tecnologias 
#### Lenguaje de Programacion:
* ♨️ **Java**: Utilizamos JAVA para todo el desarrollo de la aplicacion, JAVA SWING para la interfaz gráfica e Hibernate y JPA para la persistencia de datos
#### Base de Datos:
 * 💾 **MySql**

## 📝 Desarrollo y Aprendizaje 
Para las primeras etapas del desarrollo de la aplicacion fuimos creando las distintas clases de dominio que relevamos que eran relevantes para nuestro caso de uso.
Luego creamos el gestor que se encargaria de gestionar toda la ejecucion de la logica que necesitaba el caso de uso, que anteriormente describimos en el [diagrama de secuencia](docs/digramas/DiagramaDeSecuencia.pdf).

Luego se nos pidio que aplicaramos un **patron de diseño**, discutiendo en grupo llegamos al acuerdo que el mas interesante de aplicar seria el patron **observer** ya que podriamos implementar notificaciones reales via email como se decia en la descripcion del caso de uso.

# 🏗️ Estructura del proyecto
* **La aplicacion (`/aplicacion`)**: Aqui se encuentra tanto el backend, como la interfaz grafica

* **documentacion (`/docs`)**: Aqui se podran ver todos los tanto los archivos/documentos que se nos brindaron para el desarrollo como los que utilizamos para la resolucion del trabajo.

## Diagramas del análisis 
### Diagrama de clases
Utilizamos el diagrama de clases para poder definir las relaciones entre las diferentes entidades del dominio y las entidades de la solucion.

![Diagrama De clases](docs/digramas/DiagramaDeClases.jpg)

### Diagrama de secuencia
Utilizamos este diagrama para ver como se comporta el sistema en tiempo de ejecucion y como interactuan las entidades entre si medieante el envio de mensajes.

para ver el diagrama de secuencia [Abrir Diagrama De Secuencia](docs/digramas/DiagramaDeSecuencia.pdf)
