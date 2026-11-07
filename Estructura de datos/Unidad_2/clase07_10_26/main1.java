package Unidad_2.clase07_10_26;

public class main1 {
    public static void main(String[] args) {
        primer prim = new primer();
        prim.recursive( 10);

        segundo seg = new segundo();
        seg.recursive(5, 1, 10);
        seg.iterativo(5, 1, 10);
    }
}
