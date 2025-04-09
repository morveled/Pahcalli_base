package mx.uam.ayd.proyecto.presentacion.menu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.SucursalRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Sucursal;
import mx.uam.ayd.proyecto.presentacion.gestionInventario.ControladorGestionInventario;
import mx.uam.ayd.proyecto.presentacion.mostrarInventario.ControladorMostrarInventario;
import mx.uam.ayd.proyecto.presentacion.visualizarSolicitudesAbastecimiento.ControlVisualizarSolicitudesAbastecimiento;

@Component
public class ControlMenu {

    @Autowired
    VentanaMenu ventanaMenu;
    @Autowired
    private ControladorGestionInventario controlGestionInventario;
    @Autowired
    ControladorMostrarInventario controladorMostrarInventario;  
    
    @Autowired
    ControlVisualizarSolicitudesAbastecimiento controlSolicitudesAbastecimiento;

    Sucursal sucursal;
    Empleado empleado;
    
    @Autowired
    private SucursalRepository sucursalRepository;

    public void inicia() {
        ventanaMenu.muestra(this);
    }

    public void inicia(Empleado empleado) {
        this.empleado = empleado;
        ventanaMenu.muestra(this);
    }
    
    public void mostrarGestionInventario() {
        Sucursal sucursal = null;
        try {
            Optional<Sucursal> sucursalOpt = sucursalRepository.findByIdSucursal(1L);
            sucursal = sucursalOpt.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controlGestionInventario.inicia(sucursal);
    }
    
    public void mostrarMostrarInventario() {
        if(empleado == null) {
            sucursal = sucursalRepository.findByIdSucursal(1L).orElse(null);
        }
        controladorMostrarInventario.inicia(sucursal);
    }
    
    /**
     * Método que arranca la historia de usuario "visualizar solicitudes de abastecimiento"
     */
    public void mostrarSolicitudesAbastecimiento() {
        controlSolicitudesAbastecimiento.inicia();
    }
}
