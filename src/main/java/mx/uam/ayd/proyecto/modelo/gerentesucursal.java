package mx.uam.ayd.proyecto.modelo;

public class gerentesucursal extends usuario {

    public gerentesucursal(int id, String nombreCompleto, String correo, String telefono,
                           String nombreUsuario, String contrasena, String sucursal) {
        super(id, nombreCompleto, correo, telefono, nombreUsuario, contrasena, "Gerente", sucursal);
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión como Gerente de sucursal: " + nombreUsuario);
    }

    public void verReporteMensualVentas() {
        System.out.println("Mostrando gráfico de ventas mensuales por producto...");
    }

    public void modificarStockRecibido() {
        System.out.println("Modificando stock recibido en la sucursal...");
    }
}