# Modelo de base de datos - Mascotas

## Tabla: mascota

La tabla corresponde directamente a la entidad `Mascota` del proyecto.

| Campo | Tipo SQLite | Restricción | Descripción |
|---|---|---|---|
| id | INTEGER | PRIMARY KEY AUTOINCREMENT | Identificador único de cada registro |
| nombre | TEXT | NOT NULL | Nombre de la mascota |
| foto | INTEGER | NOT NULL | Identificador del recurso drawable de la fotografía |
| rating | INTEGER | NOT NULL | Calificación dada por el usuario, de 1 a 5 |

### Relación con el POJO

```text
Mascota
├── id      : int
├── nombre  : String
├── foto    : int
└── rating  : int
```

Room crea y administra esta tabla mediante `Mascota.java`.

## Regla de las últimas 5

Cada vez que el usuario selecciona un rating:

1. Se crea un registro de `Mascota`.
2. Se guarda en la tabla `mascota`.
3. Se eliminan los registros anteriores al grupo de las 5 entradas más recientes.
4. La aplicación consulta las últimas 5 y las muestra en pantalla.

Por esto la tabla no conserva más de cinco mascotas calificadas.
