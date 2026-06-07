# Sistema de gestión de tickets

Aplicación de línea de comandos en Java para crear, resolver y buscar tickets usando:

- una cola de prioridad para tickets pendientes;
- una lista enlazada simple para tickets resueltos.

## Requisitos

- JDK instalado.
- Terminal en la raíz del proyecto.

## Compilación

```sh
javac -d out src/Main.java src/presentation/UI.java src/entities/Ticket.java src/utils/*.java
```

## Ejecución

```sh
java -cp out Main
```

## Funcionalidad principal

- Menú de usuario para crear tickets y buscar tickets resueltos por ID.
- Menú de administrador para ver el ticket al frente de la cola y resolverlo.
- Cada ticket guarda descripción, nombre completo, fecha de creación y fecha de resolución.
# Tarea_estructura_de_datos_sistema_tickets
