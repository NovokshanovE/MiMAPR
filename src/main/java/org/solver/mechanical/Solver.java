package org.solver.mechanical;


import jdk.internal.net.http.common.Pair;
import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;
import org.solver.Scheme;

import java.util.ArrayList;

/*
* Этот класс необходим для решения задачи расширенным узловым методом для механических систем.
* */
public class Solver implements Cloneable{
    private Scheme scheme;
    private double dt;
    private double r = 0.0001;
    private RealMatrix matrix;
    private RealVector vector;

    private int MSize;
    private int NSize;
    private int EMFSize;
    private ArrayList<Pair<Integer, Integer>> deltaVar;
    public Solver(Scheme scheme) throws CloneNotSupportedException {


        this.scheme = scheme.clone();


    }
    public void Solution(){
        System.out.println("-------------------------------------\n" +
                "-------------------------------------\n" +
                "Solver(Scheme info):\n");
        scheme.printInfo();
        this.MSize = 3*scheme.nodeNumbers() + scheme.EMF_Numbers();
        this.NSize = 3*scheme.nodeNumbers();
        this.EMFSize = scheme.EMF_Numbers();

        generateMatrix();
    }

    public void setDeltaVar() {
        for (int i = 1; i < scheme.nodeNumbers(); i++) {
            Pair<Integer, Integer> new_var = new Pair<Integer, Integer>(1, i);
            deltaVar.add(new_var);
            new_var = new Pair<Integer, Integer>(2, i);
            deltaVar.add(new_var);
            new_var = new Pair<Integer, Integer>(3, i);
            deltaVar.add(new_var);
        }
        for (int i = 0; i < scheme.EMF_Numbers(); i++) {
            Pair<Integer, Integer> new_var = new Pair<Integer, Integer>(4, i);
            deltaVar.add(new_var);
        }
    }
    public void generateMatrix(){


        RealMatrix new_matrix = new Array2DRowRealMatrix(this.MSize,this.MSize);
        for(int i = 1; i < scheme.nodeNumbers(); i++){
            for(int j = 0; j < 3; j++){
                for(Pair<Integer, Integer> var: deltaVar){

                }
            }
        }
        System.out.print(new_matrix);
    }


}
