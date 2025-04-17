package mx.uam.ayd.proyecto.modelo;

public abstract class usuario {
    protected int id;
    protected String nombreCompleto;
    protected String correo;
    protected String telefono;
    protected String nombreUsuario;
    protected String contrasena;
    protected String puesto;
    protected String sucursal;

    public usuario(int id, String nombreCompleto, String correo, String telefono,
                   String nombreUsuario, String contrasena, String puesto, String sucursal) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.puesto = puesto;
        this.sucursal = sucursal;
    }

    public abstract void iniciarSesion();
}