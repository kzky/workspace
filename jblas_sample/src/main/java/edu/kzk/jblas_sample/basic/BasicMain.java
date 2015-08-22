package edu.kzk.jblas_sample.basic;

import org.jblas.DoubleMatrix;

public class BasicMain {

    public static void main(String[] args) {
        
        // simple sample
        DoubleMatrix a = new DoubleMatrix(
                new double[][]{
                    {1.0, 2.0, 3.0},
                    {4.0, 5.0, 6.0},
                    {7.0, 8.0, 9.0}
                });
        
        DoubleMatrix b = new DoubleMatrix(3, 1, 1.0, 2.0, 3.0);
        
        DoubleMatrix x = a.mmul(b); 
        
        System.out.println("### Matrix A ###");
        System.out.println(a);
        System.out.println("### Matrix B ###");
        System.out.println(b);
        System.out.println("### Vector A * B ###");
        System.out.println(x);
        System.out.println("nrows, ncols: " + x.getRows() + ", " + x.getColumns());
    }
}
