package mx.uam.ayd.proyecto.presentacion.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

    // Definición de constantes de estilo según el estándar
    private static final Font FONT_TITLE = new Font("Dialog", Font.BOLD, 20);
    private static final Font FONT_SUBTITLE = new Font("Dialog", Font.BOLD, 14);
    private static final Font FONT_LABEL = new Font("Dialog", Font.PLAIN, 12);
    private static final Font FONT_BUTTON = new Font("Dialog", Font.BOLD, 12);
    
    private static final Color COLOR_PRIMARY = new Color(78, 154, 241); // #4e9af1
    private static final Color COLOR_SECONDARY = new Color(240, 240, 240); // #f0f0f0
    private static final Color COLOR_TEXT = new Color(51, 51, 51); // #333333
    private static final Color COLOR_BACKGROUND = Color.WHITE;
    private static final Color COLOR_PANEL = new Color(245, 245, 245); // #f5f5f5

	private JPanel contentPane;
	
	private ControlPrincipal control;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		// Configuración básica de la ventana según el estándar (punto 1)
		setTitle("Sistema Pahcalli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500); // Dimensiones estándar
		setLocationRelativeTo(null);
		setBackground(COLOR_BACKGROUND);
		setLayout(new BorderLayout());
		
		// Panel principal con márgenes estándar (punto 1)
		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		contentPane.setBackground(COLOR_BACKGROUND);
		setContentPane(contentPane);
		
		// Panel de título en la parte superior (punto 2)
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setBackground(COLOR_BACKGROUND);
		
		JLabel lblTitle = new JLabel("Sistema de Farmacia Pahcalli", JLabel.CENTER);
		lblTitle.setFont(FONT_TITLE);
		lblTitle.setForeground(COLOR_TEXT);
		titlePanel.add(lblTitle);
		
		// Panel de bienvenida
		JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		welcomePanel.setBackground(COLOR_BACKGROUND);
		
		JLabel lblBienvenida = new JLabel("Bienvenido/a al sistema. Por favor seleccione una opción:");
		lblBienvenida.setFont(FONT_SUBTITLE);
		lblBienvenida.setForeground(COLOR_TEXT);
		welcomePanel.add(lblBienvenida);
		
		// Panel central con botones de acciones principales
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(COLOR_BACKGROUND);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		
		// Panel de botones con Grid Layout (2 filas, 2 columnas)
		JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 15, 15));
		buttonsPanel.setBackground(COLOR_BACKGROUND);
		
		// Botones estándar (punto 3)
		JButton btnAgregarUsuario = createStandardButton("Agregar usuario");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregarUsuario();
			}
		});
		
		JButton btnListarUsuarios = createStandardButton("Listar usuarios");
		btnListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.listarUsuarios();
			}
		});
		
		JButton btnRealizarVenta = createStandardButton("Realizar venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.realizarVenta();
			}
		});
		
		JButton btnMostrarLoggin = createStandardButton("Acceder al sistema");
		btnMostrarLoggin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraLoggin();
			}
		});
		
		JButton btnSolicitudes = createStandardButton("Solicitudes de reabastecimiento");
		btnSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si se añade esta funcionalidad al control en el futuro
				// control.visualizarSolicitudes();
			}
		});
		
		// Añadir los botones al panel
		buttonsPanel.add(btnAgregarUsuario);
		buttonsPanel.add(btnListarUsuarios);
		buttonsPanel.add(btnRealizarVenta);
		buttonsPanel.add(btnMostrarLoggin);
		buttonsPanel.add(btnSolicitudes);
		
		// Añadir paneles al panel central
		centerPanel.add(welcomePanel);
		centerPanel.add(buttonsPanel);
		
		// Añadir los paneles principales
		contentPane.add(titlePanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Método auxiliar para crear botones estándar
	 */
	private JButton createStandardButton(String text) {
		JButton button = new JButton(text);
		button.setFont(FONT_BUTTON);
		button.setBackground(COLOR_PRIMARY);
		button.setForeground(Color.WHITE);
		button.setPreferredSize(new Dimension(150, 30));
		button.setFocusPainted(false);
		return button;
	}
	
	/**
	 * Muestra la ventana principal
	 * @param control Referencia al controlador principal
	 */
	public void muestra(ControlPrincipal control) {
		this.control = control;
		setVisible(true);
	}
}
