package org.solver.mechanical;



import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;
import org.solver.Element;
import org.solver.Node;
import org.solver.Scheme;

import java.util.ArrayList;

/**
 * Этот класс необходим для решения задачи расширенным узловым методом для механических систем.<br />
 * <img src="doc/img.png" width="500"/>
 * <img src="doc/img_1.png" width="500"/>
 * <img src="doc/img_2.png" width="500"/>
 */


public class Solver implements Cloneable{
    private Scheme scheme;
    private double dt;
    private double r = 0.0001;
    private RealMatrix matrix;
    private RealVector vector;

    /**
     * количество строк
     */
    private int MSize;

    /**
     * количество строк, связанных с потенциалами в узлах
     */
    private int NSize;

    /**
     * количество строк, связанных с
     * током в ЭДС
     */
    private int EMFSize;

    /**
     * Mассив, где хранится необходимая информация о переменных:
     * 0 - производная;
     * 1 - интеграл;
     * 2 - потенциал;
     * 3 - ток ЭДС.
     * */
    private ArrayList<MutablePair<Integer, Integer>> deltaVar = new ArrayList<>();
    public Solver(Scheme scheme) throws CloneNotSupportedException {


        this.scheme = scheme.clone();


    }
    public void Solution(){
        System.out.println("-------------------------------------\n" +
                "-------------------------------------\n" +
                "Solver(Scheme info):\n");
        scheme.printInfo();
        this.MSize = 3*(scheme.nodeNumbers()-1) + scheme.EMF_Numbers();
        this.NSize = 3*(scheme.nodeNumbers()-1);
        this.EMFSize = scheme.EMF_Numbers();
        dt = 0.001;
        setDeltaVar();
        generateMatrix();
    }

    public void setDeltaVar() {
        for (int i = 1; i < scheme.nodeNumbers(); i++) {
            MutablePair<Integer, Integer> new_var = new MutablePair<Integer, Integer>(0, i);
            deltaVar.add(new_var);
            new_var = new MutablePair<Integer, Integer>(1, i);
            deltaVar.add(new_var);
            new_var = new MutablePair<Integer, Integer>(2, i);
            deltaVar.add(new_var);
        }
        for (int i = 0; i < scheme.EMF_Numbers(); i++) {
            MutablePair<Integer, Integer> new_var = new MutablePair<Integer, Integer>(3, i);
            deltaVar.add(new_var);
        }
    }
    public double solveNearestElementsToMatrix(Node node, int key){

        ArrayList<Element> nearest_elems = node.getNearest_elems();
        double res = 0.;
        for(Element elem: nearest_elems){
            double val = elem.getValue();
            int direction = 1;
            if(elem.getFinish() == node){
                direction = -1;
            }
            switch (key){
                case 0:
                    if(elem.getType() == 2) {
                        res += direction * val;
                    }
                    break;
                case 1:
                    if(elem.getType() == 4) {
                        res += direction*(1/val);
                    }
                    break;
                case 2:
                    if(elem.getType() == 3) {
                        res += direction * (1 / val);
                    }
                    break;
                case 3:
                    if(elem.getType() == 1) {
                        res += direction;
                    }
                    break;

            }
        }
        return res;
    }
    // *
    // * ArrayList<Element> nearest_elems = node.getNearest_elems();
    //     double res = 0.;
    //     for(Element elem: nearest_elems){
    //         double val = elem.getValue();
    //         int direction = 1;
    //         if(elem.getFinish() == node){
    //             direction = -1;
    //         }
    //         switch (elem.getType()){
    //             case 1:
    //                 res += direction;
    //                 break;
    //             case 2:
    //                 res += direction*(val);
    //                 break;
    //             case 3:
    //                 res += direction*(1/val);
    //                 break;
    //             case 4:
    //                 res += direction*(1/val);
    //                 break;
    //             case 5:
    //                 res += direction*(1/val);
    //                 break;
    //             default:
    //                 res += direction*(1/val);
    //         }
    //     }
    //     return res;
    //     *

    public void generateMatrix(){


        RealMatrix new_matrix = new Array2DRowRealMatrix(this.MSize,this.MSize);
        for(int i = 0; i < scheme.nodeNumbers()-1; i++){
            for(int j = 0; j < 3; j++){
                if(j<=1){
                    for(int k = 0; k < deltaVar.size(); k++){
                        if(k == i*3+j){
                            new_matrix.setEntry(i*3+j, k, 1.);
                        } else if (j == deltaVar.get(k).getLeft()) {
                            new_matrix.setEntry(i*3+j, k, -1./dt);

                        }

                    }
                } else{
                    for(int k = 0; k < deltaVar.size(); k++){
                        int index = deltaVar.get(k).getRight();
                        int key = deltaVar.get(k).getLeft();
                        Node current_node = scheme.getNode(index);
                        new_matrix.setEntry(i*3+j, k, solveNearestElementsToMatrix(current_node, key));



                    }
                }

            }
        }
        for(int i = NSize; i < MSize; i++){
            for(int k = 0; k < deltaVar.size(); k++){
                if(deltaVar.get(k).getLeft() == 2){
                    int index = deltaVar.get(k).getRight();
                    int key = deltaVar.get(k).getLeft();
                    Node current_node = scheme.getNode(index);
                    new_matrix.setEntry(i, k, solveNearestElementsToMatrix(current_node, 3));
                }
            }
        }

        System.out.print("Matrix:\n");
        for(int i = 0; i < MSize; i++){
            for(int j = 0; j < MSize; j++){
                System.out.printf("%f, ", new_matrix.getEntry(i, j));
            }
            System.out.print("\n");

        }
        matrix = new_matrix;
    }
    public void generateVector(){

    }


}