public class Aereo extends Envio {
    private static final double TARIFA_BASE = 15000;
    private static final double COSTO_POR_KG = 3000;
    private static final double COSTO_POR_KM = 1200;
    
    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    
    @Override
    public double calcularTarifa() {
        return TARIFA_BASE + 
               (getPeso() * COSTO_POR_KG) + 
               (getDistancia() * COSTO_POR_KM);
    }
}