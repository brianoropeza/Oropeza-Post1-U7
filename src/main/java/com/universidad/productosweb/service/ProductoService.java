package com.universidad.productosweb.service;

import com.universidad.productosweb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductoService {

    // Almacén en memoria: clave = id del producto, valor = objeto Producto
    private final Map<Long, Producto> productos = new LinkedHashMap<>();

    // Contador autoincrementable para asignar IDs únicos
    private Long contadorId = 1L;

    /**
     * Constructor: carga tres productos de ejemplo al iniciar la aplicación.
     */
    public ProductoService() {
        guardar(new Producto(null, "Laptop",  "Laptop 15 pulgadas 16GB RAM", 1299.99));
        guardar(new Producto(null, "Mouse",   "Mouse inalámbrico ergonómico", 29.99));
        guardar(new Producto(null, "Teclado", "Teclado mecánico TKL", 89.99));
    }

    /**
     * Retorna todos los productos registrados como lista.
     */
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    /**
     * Busca un producto por ID. Retorna Optional vacío si no existe.
     */
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    /**
     * Guarda o actualiza un producto.
     * Si el ID es null se trata de una creación; si ya tiene ID, es una edición.
     */
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    /**
     * Elimina el producto con el ID indicado.
     */
    public void eliminar(Long id) {
        productos.remove(id);
    }
}
