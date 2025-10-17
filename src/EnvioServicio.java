import java.util.ArrayList;
import java.util.List;

// Servicio que maneja la lógica de negocio 
public class EnvioServicio {
    private List<Envio> envios;
    
    public EnvioServicio() {
        this.envios = new ArrayList<>();
    }
    
    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }
    
    public boolean quitarEnvio(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }
        return false;
    }
    
    public List<Envio> getEnvios() {
        return new ArrayList<>(envios); 
    }
    
    //calcular tarifa dependiendo del tipo
    public double calcularTarifaEnvio(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            return envios.get(posicion).calcularTarifa();
        }
        throw new IllegalArgumentException("Posición inválida");
    }
    
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
