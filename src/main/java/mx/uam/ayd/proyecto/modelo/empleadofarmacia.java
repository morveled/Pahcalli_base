package mx.uam.ayd.proyecto.modelo;

public class empleadofarmacia extends usuario {

    public empleadofarmacia(int id, String nombreCompleto, String correo, String telefono,
                             String nombreUsuario, String contrasena, String sucursal) {
        super(id, nombreCompleto, correo, telefono, nombreUsuario, contrasena, "Cajero", sucursal);
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión como Empleado de Farmacia: " + nombreUsuario);
    }

    public void registrarVenta(String producto, int cantidad) {
        System.out.println("Registrando venta: " + cantidad + " unidades de " + producto);
    }

    public void mostrarPromociones() {
        System.out.println("Mostrando promociones y productos relacionados...");
    }

    public void consultarStockOtrasSucursales(String producto) {
        System.out.println("Consultando stock de '" + producto + "' en otras sucursales...");
    }

    public void verProductosMasVendidos() {
        System.out.println("Mostrando lista de productos más vendidos...");
    }

    public void consultarInventarioSucursal() {
        System.out.println("Consultando inventario de la sucursal actual...");
    }

    public void solicitarReabastecimiento(String producto, int cantidad) {
        System.out.println("Solicitando reabastecimiento: " + cantidad + " unidades de " + producto);
    }
}