package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.TipoEmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicioEmpleadoTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private TipoEmpleadoRepository tipoEmpleadoRepository;

    @InjectMocks
    private ServicioEmpleado servicioEmpleado;

    private Empleado empleado;
    private TipoEmpleado tipoEmpleado;

    @BeforeEach
    void setUp() {
        tipoEmpleado = new TipoEmpleado();
        tipoEmpleado.setIdTipo(1L);
        tipoEmpleado.setNombre("Administrador");

        empleado = new Empleado();
        empleado.setIdEmpleado(1L);
        empleado.setNombre("Juan");
        empleado.setApellidoPaterno("Pérez");
        empleado.setNumeroEmpleado("EMP001");
        empleado.setCorreoElectronico("juan.perez@example.com");
        empleado.setTipo(tipoEmpleado);
    }

    @Test
    void testGetAll() {
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado));

        List<Empleado> empleados = servicioEmpleado.getAll();

        assertNotNull(empleados);
        assertEquals(1, empleados.size());
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Empleado resultado = servicioEmpleado.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorNumeroEmpleado() {
        when(empleadoRepository.findByNumeroEmpleado("EMP001")).thenReturn(empleado);

        Empleado resultado = servicioEmpleado.obtenerPorNumeroEmpleado("EMP001");

        assertNotNull(resultado);
        assertEquals("EMP001", resultado.getNumeroEmpleado());
        verify(empleadoRepository, times(1)).findByNumeroEmpleado("EMP001");
    }

    @Test
    void testCrear() {
        when(tipoEmpleadoRepository.findById(1L)).thenReturn(Optional.of(tipoEmpleado));
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado resultado = servicioEmpleado.crear(empleado);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void testActualizar() {
        when(empleadoRepository.existsById(1L)).thenReturn(true);
        when(tipoEmpleadoRepository.findById(1L)).thenReturn(Optional.of(tipoEmpleado));
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado resultado = servicioEmpleado.actualizar(empleado);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void testEliminar() {
        when(empleadoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(empleadoRepository).deleteById(1L);

        boolean resultado = servicioEmpleado.eliminar(1L);

        assertTrue(resultado);
        verify(empleadoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testValidarEmpleado() {
        boolean esValido = servicioEmpleado.validarEmpleado(empleado);

        assertTrue(esValido);
    }
}
