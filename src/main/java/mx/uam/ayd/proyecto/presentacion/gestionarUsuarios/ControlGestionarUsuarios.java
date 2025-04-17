package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.SucursalRepository;
import mx.uam.ayd.proyecto.datos.TipoEmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Sucursal;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;

@Component
public class ControlGestionarUsuarios {

    @Autowired
    private VentanaGestionarUsuarios ventanaGestionarUsuarios;

    @Autowired
    private ControlAgregarUsuario controlAgregarUsuario;

    @Autowired
    private ServicioEmpleado servicioEmpleado;

    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public void inicia() {
        ventanaGestionarUsuarios.setControl(this);

        List<Empleado> empleados = servicioEmpleado.getAll();
        for (Empleado empleado : empleados) {
            ventanaGestionarUsuarios.agregaEmpleadoATabla(empleado);
        }

        ventanaGestionarUsuarios.muestra();
    }

    public void lanzarVentanaAgregarUsuario() {
        controlAgregarUsuario.inicia(ventanaGestionarUsuarios, this);
    }

    public void agregaEmpleado(Empleado empleado) {
        Empleado guardado = servicioEmpleado.crear(empleado);
        ventanaGestionarUsuarios.agregaEmpleadoATabla(guardado);
    }

    public void editarEmpleado(Empleado empleado, VentanaGestionarUsuarios ventana) {
        VentanaEditarUsuario ventanaEditarUsuario = new VentanaEditarUsuario(ventana, empleado, this);
        ventanaEditarUsuario.setVisible(true);
    }

    public void actualizarEmpleado(Empleado empleadoActualizado) {
        Empleado actualizado = servicioEmpleado.actualizar(empleadoActualizado);
        ventanaGestionarUsuarios.actualizarEmpleadoEnTabla(actualizado);
    }

    public void eliminarEmpleado(Empleado empleado) {
        servicioEmpleado.eliminar(empleado.getIdEmpleado());
        ventanaGestionarUsuarios.eliminarEmpleadoDeTabla(empleado);
    }

    public List<TipoEmpleado> obtenerTiposEmpleado() {
        return StreamSupport
                .stream(tipoEmpleadoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Sucursal> obtenerSucursales() {
        return StreamSupport
                .stream(sucursalRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
