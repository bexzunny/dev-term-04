package Unidad_1.Clase28_9_2026.act_1;
public class exp {
    public int expo(int n, int x){
        if(x == 1){
            return n;
        }
        return n * expo(n, x-1);
    }
}
