import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmOperadorLogistico extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
    private JComboBox<String> cmbTipoEnvio;

    private JButton btnGuardar, btnCancelar;

    private EnvioServicio envioServicio;

    public FrmOperadorLogistico() {
        envioServicio = new EnvioServicio();
        setSize(700, 500);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Barra de herramientas
        JToolBar tbOperador = new JToolBar();

        JButton btnAgregarEnvio = new JButton();
        // Si los iconos no se encuentran, usar texto como fallback
        try {
            btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarCuenta.png")));
        } catch (Exception e) {
            btnAgregarEnvio.setText("Agregar");
        }
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarEnvioClick();
            }
        });
        tbOperador.add(btnAgregarEnvio);

        JButton btnQuitarEnvio = new JButton();
        try {
            btnQuitarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarCuenta.png")));
        } catch (Exception e) {
            btnQuitarEnvio.setText("Quitar");
        }
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuitarEnvioClick();
            }
        });
        tbOperador.add(btnQuitarEnvio);

        // Panel principal con BoxLayout (vertical)
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));

        // Panel de edición - Visible por defecto para testing
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(600, 120));
        pnlEditarEnvio.setLayout(null);

        // Primera fila: Número y Tipo
        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 60, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(70, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(180, 10, 40, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox<>(new String[]{"Terrestre", "Marítimo", "Aéreo"});
        cmbTipoEnvio.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(cmbTipoEnvio);

        // Segunda fila: Cliente y Distancia
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 60, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(70, 40, 150, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia Km");
        lblDistancia.setBounds(230, 40, 80, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(310, 40, 80, 25);
        pnlEditarEnvio.add(txtDistancia);

        // Tercera fila: Peso y botones
        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 60, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(70, 70, 80, 25);
        pnlEditarEnvio.add(txtPeso);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(160, 70, 100, 25);
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarEnvioClick();
            }
        });
        pnlEditarEnvio.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 70, 100, 25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelarEnvioClick();
            }
        });
        pnlEditarEnvio.add(btnCancelar);

        //Hacer visible el panel de edición
        pnlEditarEnvio.setVisible(true);

        // Tabla de envíos
        String[] columnNames = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        tblEnvios = new JTable(new Object[][]{}, columnNames);
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        // Agregar componentes al panel principal
        pnlPrincipal.add(pnlEditarEnvio);
        pnlPrincipal.add(spListaEnvios);

        // Agregar componentes al frame
        getContentPane().add(tbOperador, BorderLayout.NORTH);
        getContentPane().add(pnlPrincipal, BorderLayout.CENTER);

        // Cargar datos de ejemplo
        cargarDatosEjemplo();
    }

    private void cargarDatosEjemplo() {
        // Datos de ejemplo
        envioServicio.agregarEnvio(new Terrestre("10001", "Polimeros Col", 1200.0, 400.0));
        envioServicio.agregarEnvio(new Terrestre("10002", "Textiles Pepalta", 500.0, 600.0));
        envioServicio.agregarEnvio(new Aereo("10003", "Flores Colombi", 1500.0, 2000.0));
        
        actualizarTabla();
    }

    private void actualizarTabla() {
        Object[][] datos = envioServicio.obtenerDatosParaTabla();
        tblEnvios.setModel(new DefaultTableModel(
            datos,
            new String[]{"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"}
        ));
    }

    private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
        limpiarCampos();
    }

    private void btnQuitarEnvioClick() {
        int posicion = tblEnvios.getSelectedRow();
        if (posicion >= 0) {
            if (envioServicio.quitarEnvio(posicion)) {
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Envío eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el envío");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un envío");
        }
    }

    private void btnGuardarEnvioClick() {
        try {
            String numero = txtNumero.getText();
            String cliente = txtCliente.getText();
            String tipo = (String) cmbTipoEnvio.getSelectedItem();
            double peso = UtilServicio.leerReal(txtPeso.getText());
            double distancia = UtilServicio.leerReal(txtDistancia.getText());

            if (UtilServicio.esTextoValido(numero) && 
                UtilServicio.esTextoValido(cliente) && 
                peso > 0 && distancia > 0) {
                
                Envio nuevoEnvio = EnvioFactory.crearEnvio(tipo, numero, cliente, peso, distancia);
                envioServicio.agregarEnvio(nuevoEnvio);
                
                actualizarTabla();
                pnlEditarEnvio.setVisible(false);
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Envío guardado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos con valores válidos");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
        }
    }

    private void btnCancelarEnvioClick() {
        pnlEditarEnvio.setVisible(false);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FrmOperadorLogistico().setVisible(true);
            }
        });
    }
}