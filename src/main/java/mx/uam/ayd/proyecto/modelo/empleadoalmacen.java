package mx.uam.ayd.proyecto.modelo;

public class empleadoalmacen extends usuario {

    public empleadoalmacen(int id, String nombreCompleto, String correo, String telefono,
                           String nombreUsuario, String contrasena, String sucursal) {
        super(id, nombreCompleto, correo, telefono, nombreUsuario, contrasena, "Almacenista", sucursal);
    }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión como Empleado de Almacén: " + nombreUsuario);
    }

    public void registrarEnvio(String producto, int cantidad, String destino) {
        System.out.println("Registrando envío de " + cantidad + " unidades de " + producto + " a " + destino + ".");
    }

    public void actualizarStock(String producto, int cantidad) {
        System.out.println("Actualizando stock del producto: " + producto + ", cantidad enviada: " + cantidad);
    }

    public void asignarPrioridad(String producto, String prioridad) {
        System.out.println("Asignando prioridad [" + prioridad + "] al producto: " + producto);
    }

    public void gestionarProducto(String accion, String producto) {
        System.out.println(accion + " el producto: " + producto);
    }
}