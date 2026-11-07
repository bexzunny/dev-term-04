package Unidad_1.Clase28_9_2026.act_1;

public class fact {
    public double fac(int n){
        if(n==0){
            return 1;
        }
        return n*fac(n-1);
    }
}
