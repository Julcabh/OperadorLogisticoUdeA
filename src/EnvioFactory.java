// Factory para crear envíos - Depende de abstracciones, no de implementaciones concretas
public class EnvioFactory {
    public static Envio crearEnvio(String tipo, String codigo, String cliente, 
                                  double peso, double distancia) {
        switch (tipo.toLowerCase()) {
            case "terrestre":
                return new Terrestre(codigo, cliente, peso, distancia);
            case "aéreo":
            case "aereo":
                return new Aereo(codigo, cliente, peso, distancia);
            case "marítimo":
            case "maritimo":
                return new Maritimo(codigo, cliente, peso, distancia);
            default:
                throw new IllegalArgumentException("Tipo de envío no soportado: " + tipo);
        }
    }
}