package mx.uam.ayd.proyecto.negocio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;


@Service
public class ServicioProducto {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    
    /**
     * Obtiene todos los productos registrados
     * @return Lista de productos
     */
    public List<Producto> getAll() {
        return StreamSupport.stream(productoRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene un producto por su ID
     * @param id ID del producto
     * @return Producto si existe, null si no
     */
    public Producto obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    public Producto obtenerPorCodigo(String codigo){
        Optional<Producto> producto = productoRepository.findByCodigo(codigo);
        return producto.orElse(null);
    }
    
    /**
     * Obtiene un producto por su nombre
     * @param nombre Nombre del producto
     * @return Producto si existe, null si no 
    **/
    public Producto obtenerPorNombre(String nombre) {
        Optional<Producto> producto = productoRepository.findByNombre(nombre);
        return producto.orElse(null);
    }

    /**
     * Registra un nuevo producto
     * @param producto Producto a registrar
     * @return Producto registrado
     */
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }
    
    /**
     * Actualiza un producto existente
     * @param producto Producto a actualizar
     * @return Producto actualizado
     */
    public Producto actualizar(Producto producto) {
        if (!productoRepository.existsById(producto.getIdProducto())) {
            return null;
        }
        return productoRepository.save(producto);
    }
    
    /**
     * Elimina un producto
     * @param id ID del producto a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
    
    /**
     * Valida que los campos del producto sean correctos
     * @param producto Producto a validar
     * @return true si es válido, false si no
     */
    public boolean validarProducto(Producto producto) {
        if (producto == null) return false;
        if (producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()) return false;
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) return false;
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) return false;
        if (producto.getIngrediente() == null || producto.getIngrediente().getNombre().trim().isEmpty()) return false;
        if (producto.getLaboratorio() == null || producto.getLaboratorio().getNombre().trim().isEmpty()) return false;
        if (producto.getContenido() == null || producto.getContenido().trim().isEmpty()) return false;
        if (producto.getCategoria() == null) return false;
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) return false; // Validación de precio agregada
        return true;
    }
}