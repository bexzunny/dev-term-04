<?php

$conexion = new mysqli(
    "mysql",
    "usuario",
    "password123",
    "practicas"
);

if ($conexion->connect_error) {
    die("Error: " . $conexion->connect_error);
}

echo "¡PHP y MySQL funcionan correctamente!";