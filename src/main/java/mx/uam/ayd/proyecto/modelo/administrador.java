package mx.uam.ayd.proyecto.modelo;

public class administrador extends usuario {

    public administrador(int id, String nombreCompleto, String correo, String telefono,
                         String nombreUsuario, String contrasena, String sucursal) {
        super(id, nombreCompleto, correo, telefono, nombreUsuario, contrasena, "Administrador", sucursal);
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión exitoso como Administrador: " + nombreUsuario);
    }

    public void gestionarUsuarios() {
        System.out.println("Gestionando usuarios del sistema...");
    }

    public void gestionarSucursales() {
        System.out.println("Gestionando sucursales del sistema...");
    }
}