import java.util.ArrayList;
import java.util.List;

// Servicio que maneja la lógica de negocio (S - Single Responsibility)
public class EnvioServicio {
    private List<Envio> envios;
    
    public EnvioServicio() {
        this.envios = new ArrayList<>();
    }
    
    // O - Open/Closed: Podemos agregar nuevos tipos de Envio sin modificar este código
    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }
    
    // L - Liskov Substitution: Todos los subtipos de Envio pueden usarse aquí
    public boolean quitarEnvio(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }
        return false;
    }
    
    public List<Envio> getEnvios() {
        return new ArrayList<>(envios); // Retorna copia para encapsulamiento
    }
    
    // Polimorfismo en acción - cada envio calcula su tarifa específica
    public double calcularTarifaEnvio(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            return envios.get(posicion).calcularTarifa();
        }
        throw new IllegalArgumentException("Posición inválida");
    }
    
    // Método para mostrar en tabla
    public Object[][] obtenerDatosParaTabla() {
        Object[][] datos = new Object[envios.size()][6];
        
        for (int i = 0; i < envios.size(); i++) {
            Envio envio = envios.get(i);
            datos[i][0] = envio.getClass().getSimpleName();
            datos[i][1] = envio.getCodigo();
            datos[i][2] = envio.getCliente();
            datos[i][3] = envio.getPeso();
            datos[i][4] = envio.getDistancia();
            datos[i][5] = envio.calcularTarifa();
        }
        
        return datos;
    }
}
