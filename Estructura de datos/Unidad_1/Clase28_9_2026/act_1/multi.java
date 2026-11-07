package Unidad_1.Clase28_9_2026.act_1;

public class multi {
    public int mul(int n1, int n2){
        if(n2 == 1){
            return n1;
        }
        return n1 + mul(n1,n2-1);
    }
}
