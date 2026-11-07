package Unidad_2.clase07_10_26;

public class primer {
    public double recursive(int n){
        if (n==0) {
            return 1;
        }
        return (Math.pow(2, n)/Math.pow(3, n)) + recursive(n-1);
    }
    public double iterativo(int n){
        double sum=0;
            for (int i = 1, o = 1; i <= n; i *= 2, o *= 3) {
                sum+= (i/o);
        }
        return sum;
    }
}
