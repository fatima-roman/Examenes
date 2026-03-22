package repasoexamenespooFACIL;

public class main {

    public static void main(String[] args) {

        int opc =0;
        try {
            do {
                System.out.println("1. Añadir vehículo\r\n"
                + "2. Listar vehículos\r\n"
                + "3. Buscar vehículo por matrícula\r\n"
                + "4. Modificar días alquilados\r\n"
                + "5. Modificar recargo premium\r\n"
                + "6. Modificar precio seguro diario\r\n"
                + "7. Eliminar vehículo\r\n"
                + "8. Ver estadísticas\r\n"
                + "9. Salir\r\n");
                
                opc = ListaVehiculos.reader.nextInt();
                
                switch (opc) {
                case 1: {
                    Vehiculo vehiculo = ListaVehiculos.datosVehiculo();
                    if (ListaVehiculos.anadirVehiculo(vehiculo)) System.out.println("Añadido correctamente");

                    break;
                }
                case 2 : {
                    ListaVehiculos.listarTodos();
                    break;
                }
                case 3: {
                    String matricula = ListaVehiculos.pedirMatricula();
                    Vehiculo vehiculo = ListaVehiculos.buscarPorMatricula(matricula);
                    // PROBLEMA:
                    // buscarPorMatricula puede devolver null y ya imprime un mensaje de error.
                    // Si es null, llamar a vehiculo.toString() lanza NullPointerException.
                    // Para un 10: primero compruebas vehiculo != null antes de imprimir.
                    System.out.println(vehiculo.toString());
                    break;
                }
                case 4: {
                    int dias = ListaVehiculos.pedirDias();
                    String matricula = ListaVehiculos.pedirMatricula();
                    if(ListaVehiculos.modificarDiasAlquilados(matricula, dias)) {
                        System.out.println("Modificado correctamente");
                    }

                    break;
                }
                case 5: {
                    double porcentaje = ListaVehiculos.pedirPorcentaje();
                    String matricula = ListaVehiculos.pedirMatricula();
                    if (ListaVehiculos.modificarRecargoPremium(matricula, porcentaje)) {
                        System.out.println("Modificado correctamente");
                    }
                    break;
                }
                case 6: {
                    double seguro = ListaVehiculos.pedirSeguro();
                    if(ListaVehiculos.modificarPrecioSeguro(seguro)) System.out.println("Modificado correctamente");
                    break;
                }
                case 7: {
                    String matricula = ListaVehiculos.pedirMatricula();
                    if (ListaVehiculos.eliminarPorMatricula(matricula)) System.out.println("Eliminado correctamente");
                    break;
                }
                case 8: {
                    double sumaTotal = ListaVehiculos.calcularIngresoTotalRecargos();
                    System.out.println("Suma total de ingreso por recargo: " + sumaTotal);
                    System.out.println("Lista de vehículos premium");
                    ListaVehiculos.listarVehiculosPremium();
                    break;
                }
                }; // FIn 

            }while(opc !=9);

        } catch (Exception e) {
            // Manejo de errores muy tosco:
            // - Capturas cualquier excepción (demasiado general).
            // - Cierras el programa sin reintentar ni informar bien al usuario sobre qué ha fallado.
            // Para un 10, deberías controlar las entradas de usuario más cuidadosamente
            // y no usar un catch tan genérico alrededor de todo el bucle.
            System.out.println("Cerrando programa. Error: " + e );
        }

        // FALTA:
        // No hay case 9 en el switch para la opción de salir, y el enunciado
        // pide explícitamente mostrar: “Gracias por usar el sistema. ¡Hasta pronto!”.
        // Ahora mismo nunca se muestra ese mensaje porque nunca lees 'opc' y nunca sales de forma limpia.
    }
}