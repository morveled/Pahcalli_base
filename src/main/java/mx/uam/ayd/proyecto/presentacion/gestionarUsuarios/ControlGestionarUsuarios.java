package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import java.util.List;
import java.util.Optional;

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
            UsuarioTabla usuario = new UsuarioTabla(
                empleado.getNumeroEmpleado(),
                empleado.getNombre(),
                (empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno()).trim(),
                "", // contraseña no disponible desde la entidad Empleado
                empleado.getCorreoElectronico(),
                empleado.getTelefono(),
                empleado.getTipo() != null ? empleado.getTipo().getNombre() : "",
                empleado.getSucursal() != null ? empleado.getSucursal().getNombre() : ""
            );

            ventanaGestionarUsuarios.agregaUsuarioATabla(usuario);
        }
    
        ventanaGestionarUsuarios.muestra();
    }

    /**
     * Método que llama al control para abrir la ventana de agregar usuario.
     */
    public void lanzarVentanaAgregarUsuario() {
        controlAgregarUsuario.inicia(ventanaGestionarUsuarios, this);
    }

    /**
     * Método que se invoca desde VentanaAgregarUsuario para agregar un nuevo usuario.
     */
    public void agregaUsuario(UsuarioTabla usuarioTabla) {
        // 1. Crear nuevo empleado
        Empleado empleado = new Empleado();
        empleado.setNumeroEmpleado(usuarioTabla.getNumeroEmpleado());
        empleado.setNombre(usuarioTabla.getNombre());

        String[] apellidos = usuarioTabla.getApellido().split(" ", 2);
        empleado.setApellidoPaterno(apellidos[0]);
        empleado.setApellidoMaterno(apellidos.length > 1 ? apellidos[1] : "");

        empleado.setCorreoElectronico(usuarioTabla.getCorreo());
        empleado.setTelefono(usuarioTabla.getTelefono());

        // 2. Asignar TipoEmpleado si existe
        TipoEmpleado tipo = tipoEmpleadoRepository.findByNombre(usuarioTabla.getPuesto());
        if (tipo != null) {
            empleado.setTipo(tipo);
        }

        // 3. Asignar Sucursal si existe
        Optional<Sucursal> sucursalOpt = sucursalRepository.findByNombre(usuarioTabla.getSucursal());
        if (sucursalOpt.isPresent()) {
            empleado.setSucursal(sucursalOpt.get());
        }

        // 4. Guardar en base de datos
        Empleado guardado = servicioEmpleado.crear(empleado);

        // 5. Convertir a UsuarioTabla y mostrarlo en tabla
        UsuarioTabla nuevoUsuario = new UsuarioTabla(
            guardado.getNumeroEmpleado(),
            guardado.getNombre(),
            guardado.getApellidoPaterno() + " " + guardado.getApellidoMaterno(),
            usuarioTabla.getContrasena(),
            guardado.getCorreoElectronico(),
            guardado.getTelefono(),
            guardado.getTipo() != null ? guardado.getTipo().getNombre() : usuarioTabla.getPuesto(),
            guardado.getSucursal() != null ? guardado.getSucursal().getNombre() : usuarioTabla.getSucursal()
        );

        ventanaGestionarUsuarios.agregaUsuarioATabla(nuevoUsuario);
    }

    /**
     * Lanza la ventana para editar un usuario existente.
     */
    public void editarUsuario(UsuarioTabla usuario, VentanaGestionarUsuarios ventana) {
        VentanaEditarUsuario ventanaEditarUsuario = new VentanaEditarUsuario(ventana, usuario, this);
        ventanaEditarUsuario.setVisible(true);
    }

    /**
     * Recibe los cambios de un usuario editado y actualiza la tabla.
     */
    public void actualizarUsuario(UsuarioTabla usuario) {
        ventanaGestionarUsuarios.actualizarUsuarioEnTabla(usuario);
    }

    public void eliminarUsuario(UsuarioTabla usuario) {
        ventanaGestionarUsuarios.eliminarUsuarioDeTabla(usuario);
    }
    
}