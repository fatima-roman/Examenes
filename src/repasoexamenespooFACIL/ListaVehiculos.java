package repasoexamenespooFACIL;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListaVehiculos {

    // PROBLEMA DE TIPADO:
    // Usas un Map sin genéricos (raw type). En Java moderno y en un examen exigente,
    // esto se considera mala práctica clara.
    // Debería ser: Map<String, Vehiculo> vehiculosLista = new HashMap<>();
    // Con raw types pierdes seguridad de tipos y claridad.
    public static Map vehiculosLista = new HashMap<>();
    public static Scanner reader = new Scanner(System.in);
    final static String MENSAJE_ERROR = "Vehículo no encontrado";

    public static Vehiculo datosVehiculo() {
        // Esta función mezcla lógica de entrada (leer por consola) con lógica de creación del objeto.
        // Para un diseño de 10, esta clase debería ser un "repositorio" y la lectura por consola estaría en main.
        String matricula = pedirMatricula();
        System.out.println("Introduce el modelo");
        String modelo = reader.next();
        System.out.println("Introduce Precio Base/Día");
        double precioBase = reader.nextDouble();
        double porcentajeRecargo = pedirPorcentaje();

        // Aquí creas el Vehiculo usando el constructor que NO inicializa días.
        // Ok, pero recuerda que en tu clase Vehiculo, recargoPremium y totalGenerado no se recalculan
        // y quedarán mal. Eso hace que las estadísticas económicas no sean fiables.
        Vehiculo vehiculo = new Vehiculo(matricula, modelo, precioBase, porcentajeRecargo);

        return vehiculo; 
    }

    public static String pedirMatricula() {
        // Correcto como método de ayuda.
        // Para 10/10 en clean code, este tipo de métodos estaría en una clase de utilidades de entrada/salida,
        // y ListaVehiculos solo gestionaría la colección.
        System.out.println("Introduce la matrícula");
        String matricula = reader.next();
        return matricula;
    }

    public static int pedirDias() {
        System.out.println("Introduce los días de alquiler");
        int dias = reader.nextInt();
        return dias;
    }

    public static double pedirPorcentaje() {
        System.out.println("Introduce porcentaje de recargo premium");
        double porcentaje = reader.nextDouble();
        return porcentaje;
    }

    public static double pedirSeguro() {
        System.out.println("Introduce el precio del seguro diario");
        double seguro = reader.nextDouble();
        return seguro;
    }

    public static boolean anadirVehiculo(Vehiculo v) {
        // Bien: validas null, matrícula null y duplicados.
        // Para un 10, sería un plus separar la lógica de mensajes (System.out.println) de la lógica de retorno boolean.
        if (v == null || v.getMatricula() == null || vehiculosLista.containsKey(v.getMatricula())) {
            System.out.println("No es posible añadir vehículo, compruebe la matrícula");
            return false; 
        }
        // Aquí el Map se usa efectivamente como <String, Vehiculo>, pero sin genéricos.
        // En un examen exigente, esto baja nota.
        vehiculosLista.put(v.getMatricula(), v); // Key: matrícula, Value: Vehiculo
        return true; 
    }

    public static void listarTodos() {
        // Recorres values del Map (OK).
        // Falta manejar el caso de mapa vacío: si no hay vehículos, estaría bien imprimir un mensaje específico.
        for (Vehiculo v : (Iterable<Vehiculo>) vehiculosLista.values()) {
            // Cast necesario porque el Map es raw; otro motivo más para usar genéricos.
            System.out.println(v.toString());
        }
        System.out.println("TODOS LOS VEHÍCULOS AÑADIDOS");
    }

    public static Vehiculo buscarPorMatricula(String matricula) {
        // Método correcto, pero de nuevo necesitas cast por el Map sin genéricos.
        if (!vehiculosLista.containsKey(matricula)) {
            System.out.println(MENSAJE_ERROR);
            return null; 
        }else {
            return (Vehiculo) vehiculosLista.get(matricula);
        }
    }

    public static boolean modificarDiasAlquilados(String matricula, int dias) {
        Vehiculo modificar = buscarPorMatricula(matricula);
        if (modificar == null) {
            System.out.println(MENSAJE_ERROR);
            return false;
        }else {
            // Modificas los días; correcto en principio.
            // PERO, como en Vehiculo no recalculas totalGenerado, los totales quedan incoherentes.
            modificar.setDiasAlquilados(dias);
            vehiculosLista.replace(matricula, modificar);
            // replace es innecesario porque modificar es la misma referencia, pero no es un fallo grave.
            return true; 
        }
    }

    public static boolean modificarRecargoPremium(String matricula, double porcentaje) {
        Vehiculo modificar = buscarPorMatricula(matricula);
        if (modificar == null) {
            System.out.println(MENSAJE_ERROR);
            return false;
        }else {
            // PROBLEMA:
            // El enunciado dice: “Validar que el porcentaje esté en el rango 0-25%”.
            // Tu setter en Vehiculo ya valida, pero si el porcentaje está fuera de rango,
            // NO informas de que no se ha cambiado. Este método devuelve true igual.
            // Para un 10:
            //  - Deberías comprobar si el valor se ha aplicado (por ejemplo, comparando el valor anterior).
            //  - O validar explícitamente aquí el rango y devolver false si no se cumple.
            modificar.setPorcentajeRecargoPremium(porcentaje);;
            vehiculosLista.replace(matricula, modificar);
            return true; 
        }
    }

    public static boolean modificarPrecioSeguro (double precio) {
        // Ajustas el campo estático en Vehiculo.
        // Esto afecta a todos los vehículos, pero tus totales (totalGenerado) no se recalculan.
        // Para un 10, los totales deberían depender siempre del precio actual (cálculo dinámico).
        Vehiculo.setPrecioSeguroDiario(precio);
        if (Vehiculo.getPrecioSeguroDiario() == precio) {
            return true; 
        }else {
            System.out.println("Precio no modificado");
            return false;
        }
    }

    public static boolean eliminarPorMatricula(String matricula) {
        Vehiculo eliminar = buscarPorMatricula(matricula);
        if (eliminar == null) {
            System.out.println(MENSAJE_ERROR);
            return false;
        }else {
            vehiculosLista.remove(matricula, eliminar);
            return true; 
        }
    }

    public static double calcularIngresoTotalRecargos() {
        double sumaTotal = 0; 
        for (Vehiculo v : (Iterable<Vehiculo>) vehiculosLista.values()) {
            // Aquí sumas v.getRecargoPremium(), pero en tu modelo recargoPremium es:
            // precioBaseDia * porcentaje / 100, SIN multiplicar por días.
            // El enunciado habla de ingreso total por recargos premium, lo lógico sería considerar también días.
            // Además, como recargoPremium no se recalcula, puede estar desactualizado.
            // Para un 10, este método debería usar un método bien definido en Vehiculo que aplique la fórmula correcta.
            sumaTotal += v.getRecargoPremium();
        }
        return sumaTotal;
    }

    public static void listarVehiculosPremium() {
        for ( Vehiculo v : (Iterable<Vehiculo>) vehiculosLista.values()) {
            // Bien: usas el método superior12() de Vehiculo.
            if(v.superior12()) {
                System.out.println(v.toString());
            }
        }
    }
}