public class UtilServicio {
    
    public static double leerReal(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor numérico inválido: " + texto);
        }
    }
    
    public static int leerEntero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor entero inválido: " + texto);
        }
    }
    
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}