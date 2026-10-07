# libreria-dominio

Modelo de dominio de una librería: clases de negocio en Java 21 organizadas en un único paquete
`com.itson.libreria.dominio`. Cubre catálogo de libros, usuarios, carritos, órdenes, envíos y pagos.
No incluye interfaz de usuario, persistencia ni clases con `main` (el `exec.mainClass` del `pom.xml`
aún apunta a una clase inexistente).

## Compilar

Requisitos: JDK 21 o superior. Maven es opcional.

Con Maven:

```
mvn compile
mvn test
```

Sin Maven (PowerShell):

```
$files = (Get-ChildItem -Recurse -Filter *.java src\main\java).FullName
javac --release 21 -encoding UTF-8 -d out $files
```

Con Bash:

```
javac --release 21 -encoding UTF-8 -d out $(find src/main/java -name "*.java")
```

## Clases del modelo

| Área | Clases |
| --- | --- |
| Identidad | `Usuario`, `Rol`, `Direccion` |
| Catálogo | `Libro`, `Autor`, `Categoria`, `Editorial`, `Formato`, `FormatoLibro` |
| Inventario | `ItemStock`, `AjusteInventario` |
| Compra | `Carrito`, `ItemCarrito`, `Orden`, `DetalleOrden`, `EstadoOrden` |
| Entrega | `Envio` |
| Pago | `Pago`, `EstadoPago`, `MetodoPago` |
| Digital | `Descarga` |

## Decisiones de modelado

1. **Carrito único**: existe una sola clase de carrito (`Carrito` + `ItemCarrito`), en lugar de un
   carrito por compra.
2. **Precio y stock por formato**: `Libro` no tiene precio; cada `FormatoLibro` (pasta dura, bolsillo,
   digital, ...) tiene su propio precio y su `ItemStock`. El formato digital no tiene stock.
3. **Pago dispara los efectos**: `Pago.completar()` llama a `Orden.procesarPago()`, que descuenta el
   stock de los formatos físicos y genera la `Descarga` de los digitales (una sola vez, por
   `Orden.isPagoProcesado()`).
4. **Envío modelado**: `Direccion` (con predeterminada por usuario) y `Envio` (paquetería y número de
   guía) son clases aparte de `Orden`.
5. **Pago modelado**: el monto debe ser igual a `Orden.getTotal()`; estados `PENDIENTE`,
   `COMPLETADO`, `RECHAZADO` y `REEMBOLSADO`.
6. **Total calculado**: `Orden.getTotal()` suma `DetalleOrden.getSubtotal()`; no hay campo `total`.
7. **Cancelación**: `EstadoOrden.CANCELADO` más la regla de que no se puede cancelar una orden
   `ENVIADO` ni `ENTREGADO`, ni reabrir una orden cancelada.
8. **Usuario**: teléfono obligatorio y regla de compra compartida (`Usuario.puedeComprar()`), que
   tanto CLIENTE como ADMINISTRADOR pueden comprar.
9. **Relaciones inversas**: cada lado mantiene su referencia (el lado dueño guarda la lista) y las
   multiplicidades se respetan en los setters (máximo un `Envio` y un `Pago` por `Orden`).
10. **Nombre uniforme**: ambos detalles usan `getSubtotal()`.

Estados de la orden:

```
PENDIENTE -> PROCESANDO -> ENVIADO -> ENTREGADO   (terminal)
     |             |
     +-------------+-> CANCELADO                  (terminal)
```

`Orden.avanzarEstado()` recorre la primera línea y exige un `Envio` para pasar a `ENVIADO`;
`Orden.cancelar()` solo se permite desde `PENDIENTE` o `PROCESANDO`.

El diagrama StarUML del caso (`Diagrama de Clases - Avance 1 - Componente de Dominio_EquipoVerde.mdj`)
puede desactualizarse frente a este código.
