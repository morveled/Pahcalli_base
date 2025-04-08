package mx.uam.ayd.proyecto.modelo;

public class cliente extends usuario {

    public cliente(int id, String nombreCompleto, String correo, String telefono,
                   String nombreUsuario, String contrasena) {
        super(id, nombreCompleto, correo, telefono, nombreUsuario, contrasena, "Cliente", "");
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión como Cliente: " + nombreUsuario);
    }

    public void buscarProducto(String nombreProducto) {
        System.out.println("Buscando producto: " + nombreProducto);
    }

    public void filtrarBusqueda(String categoria) {
        System.out.println("Filtrando productos por categoría: " + categoria);
    }

    public void consultarDisponibilidad(String producto) {
        System.out.println("Consultando disponibilidad del producto: " + producto);
    }

    public void verAlternativas(String producto) {
        System.out.println("Mostrando alternativas para: " + producto);
    }

    public void realizarPago(String metodo) {
        System.out.println("Realizando pago con método: " + metodo);
    }
}