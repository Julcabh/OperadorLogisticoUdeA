public class Terrestre extends Envio{
    private static final double TARIFA_BASE = 5000;
    private static final double COSTO_POR_KG = 1500;
    private static final double COSTO_POR_KM = 800;
    
    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }
    
    @Override
    public double calcularTarifa() {
        return TARIFA_BASE + 
               (getPeso() * COSTO_POR_KG) + 
               (getDistancia() * COSTO_POR_KM);
    }
}
