public abstract class Envio {
    private String codigo;
    private String cliente;
    private double peso;
    private double distancia;
    
    public Envio(String codigo, String cliente, double peso, double distancia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.peso = peso;
        this.distancia = distancia;
    }
    
    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public double getPeso() { return peso; }
    public double getDistancia() { return distancia; }
    
    public abstract double calcularTarifa();
    
    @Override
    public String toString() {
        return String.format("%s - %s (Peso: %.1f, Distancia: %.1f)", 
                           codigo, cliente, peso, distancia);
    }

}
