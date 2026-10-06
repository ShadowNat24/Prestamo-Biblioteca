# Préstamos de biblioteca

Aplicación Java Swing para registrar préstamos de libros a estudiantes.

## Arquitectura
- Model: datos del préstamo.
- View: interfaz gráfica.
- Controller: coordina eventos.
- Service: reglas de negocio.

## Reglas
1. Estudiante obligatorio.
2. Libro obligatorio.
3. Días mayores que 0.
4. Préstamo normal máximo 15 días.
5. Consulta en sala máximo 1 día.
6. No se repite el mismo libro para el mismo estudiante.

No utiliza persistencia; los préstamos se mantienen en memoria mientras la aplicación está abierta.