package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.TipoEmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.ControlGestionarUsuarios;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ControlAgregarUsuario {

    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;

    public void inicia(JFrame parent, ControlGestionarUsuarios controlGestionarUsuarios) {
        List<TipoEmpleado> tiposEmpleado = StreamSupport
                .stream(tipoEmpleadoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        VentanaAgregarUsuario ventana = new VentanaAgregarUsuario(parent, controlGestionarUsuarios, tiposEmpleado);
        ventana.setVisible(true);
    }
}
