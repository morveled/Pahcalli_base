package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

public class UsuarioTabla {

    private String numeroEmpleado;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String correo;
    private String telefono;
    private String puesto;
    private String sucursal;

    public UsuarioTabla(String numeroEmpleado, String nombre, String apellido, String contrasena,
                        String correo, String telefono, String puesto, String sucursal) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.correo = correo;
        this.telefono = telefono;
        this.puesto = puesto;
        this.sucursal = sucursal;
    }

    public Object[] toRow() {
        return new Object[] {
            numeroEmpleado,
            nombre,
            apellido,
            contrasena,
            correo,
            telefono,
            puesto,
            sucursal,
            "Editar",
            "Eliminar"
        };
    }

    // Getters y setters si se necesitan más adelante
}