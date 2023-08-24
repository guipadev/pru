# Prueba Técnica

Crear la siguiente entidad:

Materiales: nombre, descripción, tipo, serial, numero interno, precio, fecha de compra, fecha de venta, estado (activo, disponible, asignado).

Este Material se encuentra en una ciudad (se encuentran en diferentes ciudades).

Diseñar una API RESTFul que permite administrar los Materiales, debería permitir:

• Buscar por todos los Materiales.
• Buscar los Materiales por: tipo, fecha de compra, serial.
• Crear nuevos Materiales.
• Actualizar Materiales.

## Estructura Arquitectura:

Declaración de las Interfaces y su Implementación

• Controller -&gt; End Points
• Model -&gt; Molejos Entidades JPA
• Repository -&gt; Persistencia
• Service -&gt; Lógica del negocio!

## Tecnologías Back:

Debe utilizar las siguientes tecnologías:

• Plataforma Java 11 o superior.
• Framework Spring Boot.
• JPA, Document Mongo u otro.

## Front:

Angular 10 o Superior

## Tecnologías extras

Puede utilizar alguna de las siguientes tecnologías:

• Maven, Gradle u otro.

## Motor de base de datos

Es libre de utilizar cualquier motor de base de datos relacional o no relacional, pero se recomienda ,Oracle, MYSQL, Postgresql, MongoDB.

## Reglas
• El código debe estar versionado en GIT, siguiendo algún modelo de entrega. (se sugiere GIT, basado en un flujo de rama por característica).
• Se deben validar las fechas (nunca una fecha de compra debe ser superior a una fecha de venta).
• No debe haber excepciones sin capturar.
• Las respuestas HTTP de la API deben estar estandarizadas a:
	o 200 por consultas exitosas o
	o 404 para búsquedas sin resultados.
	o 500 para errores que pasen en la capa de backend.
	o Todas las respuestas deben contener una descripción o resumen de lo ocurrido.

## Entregables
• Repositorio con el código donde se puedan ver los commit realizados.

## Puntos adicionales
Los siguientes puntos no son requeridos en su solución, se tendrá en cuenta para validar si es (Junior, Senior o Máster).

• Documentación de la API: esta puede ser realizada manualmente o utilizando una herramienta, Swagger u otra.
• Soporte de autenticación por token de alguno de los servicios REST.
• Demostrar el manejo de librería de Log (log4j, u otra).
• Pruebas Unitarias (JUnit, otra).