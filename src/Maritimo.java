public class Maritimo extends Envio {
    private static final double TARIFA_BASE = 8000;
    private static final double COSTO_POR_KG = 800;
    private static final double COSTO_POR_KM = 400;
    
    public Maritimo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    
    @Override
    public double calcularTarifa() {
        return TARIFA_BASE + 
               (getPeso() * COSTO_POR_KG) + 
               (getDistancia() * COSTO_POR_KM);
    }
}