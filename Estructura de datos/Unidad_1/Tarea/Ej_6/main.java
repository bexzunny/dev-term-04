package Unidad_1.Tarea.Ej_6;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {

        String opcion;

        String menu = "MENU MATRIZ\n"
                + "1. Llenar matriz\n"
                + "2. Mostrar matriz\n"
                + "3. Suma de todos los elementos\n"
                + "4. Suma de cada fila\n"
                + "5. Suma de cada columna\n"
                + "6. Número mayor y posición\n"
                + "7. Número menor y posición\n"
                + "8. Suma diagonal principal\n"
                + "9. Suma diagonal secundaria\n"
                + "10. Números pares\n"
                + "11. Números impares\n"
                + "12. Verificar si es simétrica\n"
                + "13. Salir";

        matrices numeros = new matrices();

        while(true){

            opcion = JOptionPane.showInputDialog(menu);

            switch(opcion){

                case "1":
                    numeros.llenar_arreglo();
                    break;

                case "2":
                    System.out.println("===== MATRIZ =====");
                    numeros.mostrarMatriz();
                    break;

                case "3":
                    System.out.println("Suma total: " + numeros.sumaTotal());
                    break;

                case "4":
                    System.out.println("===== SUMA DE CADA FILA =====");
                    for(int i=0;i<6;i++){
                        System.out.println("Fila " + (i+1) + ": "+ numeros.sumaFila(i));
                    }
                    break;

                case "5":
                    System.out.println("===== SUMA DE CADA COLUMNA =====");
                    for(int i=0;i<6;i++){
                        System.out.println("Columna " + (i+1) + ": "+ numeros.sumaColumna(i));
                    }
                    break;

                case "6":
                    System.out.println("Número mayor: " + numeros.mayor()+ "\nPosición:\n"+ numeros.posicionMayor());
                    break;

                case "7":
                    System.out.println("Número menor: " + numeros.menor()+ "\nPosición:\n"+ numeros.posicionMenor());
                    break;

                case "8":
                    System.out.println("Suma de la diagonal principal: "+ numeros.diagonalPrincipal());
                    break;

                case "9":
                    System.out.println("Suma de la diagonal secundaria: "+ numeros.diagonalSecundaria());
                    break;


                case "10":
                    System.out.println("Números pares: " + numeros.pares());
                    break;

                case "11":
                    System.out.println("Números impares: " + numeros.impares());
                    break;

                case "12":
                    if(numeros.esSimetrica()){
                        System.out.println("La matriz es simétrica");
                    }else{
                        System.out.println("La matriz no es simétrica");
                    }
                    break;

                case "13":
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Opción no válida");
                    break;

            }

        }

    }

}