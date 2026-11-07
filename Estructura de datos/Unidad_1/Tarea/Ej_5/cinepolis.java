package Unidad_1.Tarea.Ej_5;

import javax.swing.JOptionPane;

public class cinepolis {
    int F = 8;
    int C = 10;
    int sala [][];
    public cinepolis(){
        this.F = 8;
        this.C =10;
        this.sala = new int[F][C];
    }

    public void mostrarAsientos(){
        for(int i = 0; i<F;i++){
            for(int o =  0; o<C;o++){
                System.out.print(sala[i][o] + " ");
            }
            System.out.print("\n");
        }
    }
    public void reservarAsiento(int fi, int co){
        if(sala[fi][co] == 0){
            sala[fi][co] = 1;
            System.out.println("El asiento: ["+(fi+1)+"]["+(co+1)+"] ha sido reservado con éxito");
        }else{
            System.out.println("Lo lamentamos, el asiento: ["+(fi+1)+"]["+(co+1)+"] Ya está reservado");
        }
    }
    public void cancelarReserva(int fi, int co){
        if(sala[fi][co] == 1){
            sala[fi][co] = 0;
            System.out.println("El asiento: ["+(fi+1)+"]["+(co+1)+"] ya no está reservado");
        }else{
            System.out.println("El asiento: ["+(fi+1)+"]["+(co+1)+"] no estaba reservado");
        }
    }

    public void consultarReserva(int fi, int co){
        if(sala[fi][co] == 1){
            System.out.println("El asiento: ["+(fi+1)+"]["+(co+1)+"] ya esta reservado");
        }else{
            System.out.println("El asiento: ["+(fi+1)+"]["+(co+1)+"] no esta reservado");
        }
    }
    public int asientosDisp(){
        int disp=0;
        for(int i = 0; i < F; i++){
            for(int o = 0; o< C; o++){
                if(sala[i][o] == 0){
                    disp++;
                }
            }
        }
            return disp;
    }

    public int asientosOcup(){
        int ocup=0;
        for(int i = 0; i < F; i++){
            for(int o = 0; o< C; o++){
                if(sala[i][o] == 1){
                    ocup++;
                }
            }
        }
            return ocup;
    }

    public double porcentajeOcupacion(){
            return (asientosOcup()*100.0)/80;
    }
    public int filaMasOcup(){
        int filamax =0;
        int sumMax = 0;
        int sum = 0;
        for(int i = 0; i < F;i++){
            sum=0;
            for(int o = 0; o<C;o++){
                sum+= sala[i][o];
            }
            if(sumMax<sum){
                sumMax=sum;
                filamax = i;
            }
        }
        return filamax;
    }

    public String mostrarFilasOcup(){
        int sum;
        String mensaje="";
        for(int i = 0; i < F;i++){
            sum=0;
            for(int o = 0; o<C;o++){
                sum+= sala[i][o];
            }
            if(sum == 10){
                mensaje += "Fila: " + (i+1) + " Llena";
            }
        }
        return mensaje;
    }
}


