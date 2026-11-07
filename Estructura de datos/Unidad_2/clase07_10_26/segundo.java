package Unidad_2.clase07_10_26;

public class segundo {
    public double recursive(int n,int i, int f){
        if (i==f) {
            return n*f;
        }
        System.out.println(n + " x " + i + " = "+ (n*i));
        return recursive(n, i+1, f); 
    }
    public void iterativo(int n,int i, int f){
        
            for (int a = i; a<=f; a++) {
                System.out.println(n + " x " + a + " = "+ (n*a));
        }
        
    }
}
