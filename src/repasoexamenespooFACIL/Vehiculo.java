package repasoexamenespooFACIL;

public class Vehiculo {
    // OK: atributos correctos según enunciado (matrícula, modelo, precio base, recargo, seguro estático, días).
    // Para 10/10 sería recomendable añadir Javadoc de clase explicando brevemente el propósito.
    private String matricula;
    private String modelo;
    private double precioBaseDia;
    private double porcentajeRecargoPremium; // 0-25%
    private static double precioSeguroDiario = 5.0; // Estático, común a todos
    private int diasAlquilados;

    // PROBLEMA GRAVE:
    // Estos dos campos se calculan aquí usando precioBaseDia y porcentajeRecargoPremium,
    // pero en este punto aún NO se han asignado (siguen a 0.0), así que recargoPremium queda 0.0 siempre.
    // Además, cuando luego cambias precioBaseDia, porcentajeRecargoPremium o diasAlquilados,
    // estos dos campos NO se recalculan nunca, así que quedan desincronizados.
    //
    // Para sacar un 10:
    //  - O NO guardar estos valores como atributos y calcularlos en métodos:
    //      double calcularRecargoPremium() { return precioBaseDia * porcentajeRecargoPremium / 100.0; }
    //      double calcularTotalSeguro() { return diasAlquilados * precioSeguroDiario; }
    //      double calcularIngresoTotal() { return precioBaseDia * diasAlquilados + calcularRecargoPremium() * diasAlquilados + calcularTotalSeguro(); }
    //  - O si quieres campos, recalcular SIEMPRE en los setters (o en un método privado recalcularTotales()).
    private double recargoPremium =(precioBaseDia*porcentajeRecargoPremium/100);
    private double totalGenerado = (precioBaseDia+recargoPremium+precioSeguroDiario)* diasAlquilados;

    public Vehiculo(String matricula, String modelo, double precioBaseDia, double porcentajeRecargoPremium, int diasAlquilados) {
        // OJO: llamas a los setters después de que recargoPremium y totalGenerado se hayan inicializado a 0.0.
        // Como no los recalculas dentro de los setters, seguirán en 0.0.
        // Para un 10, o bien eliminas esos atributos calculados, o en cada setter llamas a un método que recalcula.
        setMatricula(matricula);
        setModelo(modelo);
        setDiasAlquilados(diasAlquilados);
        setPorcentajeRecargoPremium(porcentajeRecargoPremium);
        setPrecioBaseDia(precioBaseDia);
    }

    public Vehiculo(String matricula, String modelo, double precioBaseDia, double porcentajeRecargoPremium) {
        // Mismo problema: recargoPremium y totalGenerado quedarán con valores incorrectos (0.0) y no se recalculan.
        setMatricula(matricula);
        setModelo(modelo);
        setPorcentajeRecargoPremium(porcentajeRecargoPremium);
        setPrecioBaseDia(precioBaseDia);
    }

    // Getters
    // Para un 10, se agradecería Javadoc breve en cada getter/setter público.
    public String getMatricula() { return matricula; }
    public String getModelo() { return modelo; }
    public double getPrecioBaseDia() { return precioBaseDia; }
    public double getPorcentajeRecargoPremium() { return porcentajeRecargoPremium; }
    public static double getPrecioSeguroDiario() { return precioSeguroDiario; }
    public int getDiasAlquilados() { return diasAlquilados; }
    public double getRecargoPremium() {
        // Este getter devuelve un atributo que no se recalcula.
        // El enunciado pedía “método que devuelva el importe del recargo premium (precio base × porcentaje / 100)”.
        // Mejor sería implementar la fórmula aquí directamente en vez de un campo congelado.
        return recargoPremium;
    }
    public double getTotalGenerado() {
        // Igual que arriba: totalGenerado debería calcularse con los valores actuales.
        // El enunciado pedía “método que devuelva el ingreso total”.
        // Lo ideal es que este método aplique la fórmula con los campos actuales, no dependa de un atributo fijo.
        return totalGenerado;
    }

    // Setters validados
    public void setMatricula(String matricula) {
        // PROBLEMA DE RESPONSABILIDAD:
        // Aquí mezclas validación de “vehículo ya introducido” con la lógica del modelo.
        // Esa comprobación es responsabilidad de la colección (ListaVehiculos), no del propio Vehiculo.
        // Además, imprimir en un setter es mala práctica de clean code.
        // Para un 10, este setter solo debería validar formato (si acaso) y asignar.
        if (this.matricula != null && this.matricula.equals(matricula)) {
            System.out.println("Vehículo ya introducido.");
            return;
        }
        this.matricula = matricula; 
        // Si siguieras usando recargoPremium/totalGenerado como campos, aquí no recalculas nada.
        // Faltaría llamar a un método recalcularTotales() si la matrícula influyera (en este caso no, pero vale de ejemplo).
    }

    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setPrecioBaseDia(double precioBaseDia) {
        // Validación correcta (precio > 0).
        // PERO: si el precio cambia, recargoPremium y totalGenerado deberían cambiar también.
        // Para 10/10, aquí deberías recalcular o no tener esos atributos calculados.
        if (precioBaseDia > 0) this.precioBaseDia = precioBaseDia;
    }
    public void setPorcentajeRecargoPremium(double porcentaje) {
        // Validas rango 0-25, bien.
        // Falta feedback al usuario si el porcentaje no se aplica (por ejemplo, devolver boolean o lanzar excepción).
        // Además, si cambias el porcentaje, debería recalcularse recargoPremium y totalGenerado.
        if (porcentaje >= 0 && porcentaje <= 25) this.porcentajeRecargoPremium = porcentaje;
    }
    public static void setPrecioSeguroDiario(double nuevoPrecio) {
        // Validación bien (precio > 0).
        // Pero cambiar un valor static afecta a todos los vehículos:
        // si usas totalGenerado como campo, TODOS los totales calculados previamente se quedan desfasados.
        // Para 10, totalGenerado debería ser cálculo dinámico o se debería forzar recálculo global.
        if (nuevoPrecio > 0) precioSeguroDiario = nuevoPrecio;
    }
    public void setDiasAlquilados(int dias) {
        // Validación simple correcta (>=0).
        // Pero cambiar los días debería influir en totalGenerado.
        // Para un diseño excelente, aquí recalcularías el total o usarías métodos sin atributo totalGenerado.
        if (dias >= 0) this.diasAlquilados = dias;
    }

    // equals básico por matrícula (pendiente)
    @Override
    public boolean equals(Object obj) {
        // Buena implementación básica de equals con comprobación de referencia y tipo.
        // Para ser ultra riguroso, se podría comprobar que matricula != null antes de equals,
        // pero en principio si se garantiza que nunca es null, está bien.
        if (this == obj) return true; // Misma referencia
        if (!(obj instanceof Vehiculo)) return false;
        Vehiculo o = (Vehiculo) obj;
        return matricula.equals(o.matricula); // CONTENIDO
    }

    // toString personalizado (pendiente)
    @Override
    public String toString() {
        // PROBLEMAS:
        // 1) Formato no coincide con el formato exacto del enunciado (faltan saltos de línea correctos).
        //    Debería ser algo como:
        //    "1234ABC - Toyota Corolla\nPrecio Base/Día: 45.0 | Recargo Premium: 4.5 (10%)\nDías alquilado: 6 | Total generado: 64.5"
        //
        // 2) String.format mal usado:
        //    - %.0f€ está bien para precioBaseDia (double).
        //    - recargoPremium es double, pero usas %d (entero): ERROR.
        //    - porcentajeRecargoPremium es double, pero usas %d (entero): ERROR.
        //    - diasAlquilados es int, usas %i (se acepta, pero en Java se suele usar %d).
        //    - totalGenerado es double, pero usas %d: ERROR.
        //
        // 3) Faltan espacios: "%s - %s" + "Precio..." hace que modelo y "Precio" queden pegados.
        //
        // 4) No se muestran los % con "%%" ni se redondea de forma controlada.
        //
        // Para un 10, deberías:
        // - Respetar el formato del enunciado, incluyendo saltos de línea.
        // - Usar los especificadores de formato correctos para cada tipo.
        // - Usar los métodos de cálculo correctos (y actualizados) para recargo y total.
        return String.format("%s - %s"
        + "Precio Base/Dia: %.0f€ | Recargo Premium: %d (%d)"
        + "Días alquilado: %i | Total generado: %d"
        , matricula, modelo, precioBaseDia,recargoPremium ,porcentajeRecargoPremium, diasAlquilados, totalGenerado );
    }

    @Override
    public int hashCode() {
        // hashCode coherente con equals, basado solo en matrícula.
        // Para 10/10, también es buena práctica documentar que equals/hashCode dependen solo de matrícula.
        return matricula != null ? matricula.hashCode() : 0; // Basado solo en matrícula
    }

    public boolean superior12 () {
        // Correcto: cumple el requisito “método que retorne true si el porcentaje de recargo premium es superior al 12%”.
        return porcentajeRecargoPremium > 12;
    }
}