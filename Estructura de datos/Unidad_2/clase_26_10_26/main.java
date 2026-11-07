package Unidad_2.clase_26_10_26;

import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        int opcion, dato, posicion, datob, idemp,ant;
        String nom, depa;
        double sueldon;
        ListaEmpleados listita = new ListaEmpleados();


        do { 
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Elije una opcion:"+
                "\n1. Insertar al frente\n2. INsertar al final\n3. Insertar por posición"+
                "\n4. Insertar por valor\n5. Eliminar al frente\n6. Eliminar al final"+
                "\n7. Eliminar por posición"
            ));

            switch (opcion) {
                case 1:
                    idemp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de empleado"));
                    nom = JOptionPane.showInputDialog("Teclea el nombre del empoleado");
                    depa =  JOptionPane.showInputDialog("Teclea el departamento");
                    sueldon = Double.parseDouble("Teclea el sueldo del empleado");
                    ant = Integer.parseInt(JOptionPane.showInputDialog("Teclea la antiguedad"));
                    Empleado emple = new Empleado(ant, depa, idemp, nom, sueldon);
                    //listita.insertarInicio(emple);
                    break;
                case 2:
                    idemp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de empleado"));
                    nom = JOptionPane.showInputDialog("Teclea el nombre del empoleado");
                    depa =  JOptionPane.showInputDialog("Teclea el departamento");
                    sueldon = Double.parseDouble("Teclea el sueldo del empleado");
                    ant = Integer.parseInt(JOptionPane.showInputDialog("Teclea la antiguedad"));
                    Empleado emplea = new Empleado(ant, depa, idemp, nom, sueldon);
                    //listita.insertarFinal(emplea);
                    break;
                case 3:
                    posicion = Integer.parseInt(JOptionPane.showInputDialog("Teclea la posición donde se insertará el nuevo nodo"));
                    
                    idemp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de empleado"));
                    nom = JOptionPane.showInputDialog("Teclea el nombre del empoleado");
                    depa =  JOptionPane.showInputDialog("Teclea el departamento");
                    sueldon = Double.parseDouble("Teclea el sueldo del empleado");
                    ant = Integer.parseInt(JOptionPane.showInputDialog("Teclea la antiguedad"));
                    Empleado emplead = new Empleado(ant, depa, idemp, nom, sueldon);
                    listita.insertarPorPosicion(posicion, emplead);;
                    break;
                case 4:
                    datob = Integer.parseInt(JOptionPane.showInputDialog("Teclea el id del nodo en el que se ensertará"));
                    
                    idemp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de empleado"));
                    nom = JOptionPane.showInputDialog("Teclea el nombre del empoleado");
                    depa =  JOptionPane.showInputDialog("Teclea el departamento");
                    sueldon = Double.parseDouble("Teclea el sueldo del empleado");
                    ant = Integer.parseInt(JOptionPane.showInputDialog("Teclea la antiguedad"));
                    Empleado empleads = new Empleado(ant, depa, idemp, nom, sueldon);
                    listita.insertarPorValor(datob, empleads);
                    break;
                case 5: 
                   // listita.eliminarFrente();
                    break;
                case 6:
                    //listita.eliminarFinal();
                    break;
                case 7:
                    posicion= Integer.parseInt(JOptionPane.showInputDialog("Teclea la posición del empleado a buscar"));
                    //listita.elimi();
                    break;
                case 8:
                    datob = Integer.parseInt(JOptionPane.showInputDialog("Teclea el id del empleado a buscar"));
                    listita.eliminarPorValor(datob);
                    break;
                case 9:
                    datob = Integer.parseInt(JOptionPane.showInputDialog("Teclea el id del empleado a buscar"));
                    //listita..buscar();
                    break;
                case 10:
                    datob = Integer.parseInt(JOptionPane.showInputDialog("Teclea el id del empleado a modificar"));
                    dato = Integer.parseInt(JOptionPane.showInputDialog("Teclea el nuevo valor del nodo"));
                    //listita..modificarPorValor();
                    break;
                case 11:
                    listita.mostrar();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (true);


    }
}
