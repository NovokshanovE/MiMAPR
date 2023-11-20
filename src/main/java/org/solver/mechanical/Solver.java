package org.solver.mechanical;


import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.ArrayRealVector;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;
import org.solver.Element;
import org.solver.Scheme;

/*
* Этот класс необходим для решения задачи узловым методом.
* */
public class Solver implements Cloneable{
    private Scheme scheme;
    private double dt;
    private double r = 0.0001;
    private RealMatrix matrix;
    private RealVector vector;
    public Solver(Scheme scheme) throws CloneNotSupportedException {


        this.scheme = scheme.clone();
        System.out.println("-------------------------------------\n" +
                "-------------------------------------\n");
        scheme.printInfo();

    }

}
