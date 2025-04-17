package mx.uam.ayd.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.VentanaGestionarUsuarios;

@Configuration
public class ConfiguracionSwing {

    @Bean
    public VentanaGestionarUsuarios ventanaGestionarUsuarios() {
        return new VentanaGestionarUsuarios();
    }
}
