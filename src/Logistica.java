import javax.swing.SwingUtilities;

public class Logistica {
    
    public static void main(String[] args) {
        // Ejemplo de uso del sistema
        EnvioServicio servicio = new EnvioServicio();
        
        // Agregar diferentes tipos de envíos
        servicio.agregarEnvio(new Terrestre("T001", "Cliente A", 1000, 500));
        servicio.agregarEnvio(new Aereo("A001", "Cliente B", 500, 2000));
        servicio.agregarEnvio(new Maritimo("M001", "Cliente C", 2000, 3000));
        
        // Demostración de polimorfismo
        System.out.println("=== CÁLCULO DE TARIFAS ===");
        for (Envio envio : servicio.getEnvios()) {
            System.out.printf("%s: $%,.2f%n", 
                envio.getClass().getSimpleName(), 
                envio.calcularTarifa());
        }
        
        // Lanzar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new FrmOperadorLogistico().setVisible(true);
        });
    }
}