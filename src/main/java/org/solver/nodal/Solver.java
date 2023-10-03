package org.solver.nodal;


import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;
import org.solver.Element;
import org.solver.Scheme;

import javax.crypto.Mac;
import java.io.Serializable;

/*
* Этот класс необходим для решения задачи узловым методом.
* */
public class Solver implements Cloneable{
    private Scheme scheme;
    private double dt;

    private RealMatrix matrix;
    private RealVector vector;
    public Solver(Scheme scheme) throws CloneNotSupportedException {


        this.scheme = scheme.clone();
        System.out.println("-------------------------------------\n" +
                "-------------------------------------\n");
        scheme.printInfo();

    }





    public void elementSolver(Element elem){
        RealMatrix matrix = new Array2DRowRealMatrix(new double[][]{{0,0},{0,0}});
        switch (elem.getType()){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                matrix = new Array2DRowRealMatrix(new double[][]{{1,1},{1,1}});
        }


    }
}
