package org.solver.mechanical;



import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.math4.legacy.linear.*;
import org.solver.Element;
import org.solver.Node;
import org.solver.Scheme;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Этот класс необходим для решения задачи расширенным узловым методом для механических систем.<br />
 * <img src="doc/img.png" width="500"/>
 * <img src="doc/img_1.png" width="500"/>
 * <img src="doc/img_2.png" width="500"/>
 */


public class Solver implements Cloneable{
    private Scheme scheme;
    private double dt= 0.00001;
    private double T = 1;
    private double r = 0.0001;
    private RealMatrix matrix;
    private RealVector vector;
    private RealVector unknown_prev;
    private RealVector unknown_curr;

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
        this.unknown_prev = new ArrayRealVector(MSize);

        this.unknown_curr = new ArrayRealVector(MSize);


        this.dt = 0.001;
        setDeltaVar();
        generateMatrix();
        printMatrix();
        generateVector();
        //printVector();
        System.out.print("Curr:\n" + vector.toString() + "\n");
        Solve();

    }
    private void Solve(){
        for(double time = dt; time < T; time += dt){
            DecompositionSolver solver = new LUDecomposition(matrix).getSolver();
            RealVector solution = solver.solve(vector.mapMultiplyToSelf(-1));
            unknown_prev = unknown_curr.copy();
            unknown_curr = unknown_curr.add(solution);
            generateVector();
            System.out.print("Curr:\n" + unknown_curr.toString() + "\n");
        }
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
                        res += direction*(1/val);
                    }
                    break;
                case 1:
                    if(elem.getType() == 4) {
                        res += direction*(val);
                    }
                    break;
                case 2:

                    //System.out.println("!!!");
                    if(elem.getType() == 3) {
                        res += direction * (1 / val);
                    }

                    break;
                case 3:

                    if(elem.getType() == 1) {
                        res = direction;
                        //elem.printInfo();
                    }
                    break;

            }
        }
        return res;
    }


    public void generateMatrix(){


        RealMatrix new_matrix = new Array2DRowRealMatrix(this.MSize,this.MSize);
        for(int i = 0; i < scheme.nodeNumbers()-1; i++){
            for(int j = 0; j < 3; j++){
                if(j<=1){
                    for(int k = 0; k < deltaVar.size(); k++){
                        if(k == i*3+j){
                            new_matrix.setEntry(i*3+j, k, 1.);
                        } else if(deltaVar.get(i*3+j).getLeft() == 0 && deltaVar.get(k).getLeft() == 2){
                            if(deltaVar.get(i*3+j).getRight() == deltaVar.get(k).getRight()){
                                new_matrix.setEntry(i*3+j, k, -1/dt);
                            }
                        } else if (deltaVar.get(i*3+j).getLeft() == 1 && deltaVar.get(k).getLeft() == 2) {
                            if(deltaVar.get(i*3+j).getRight() == deltaVar.get(k).getRight()){
                                new_matrix.setEntry(i*3+j, k, -dt);
                            }
                        }
//                        (j == deltaVar.get(k).getLeft()) {
//                            new_matrix.setEntry(i*3+j, k, -55);//-1/dt);
//
//                        }

                    }
                } else{
                    for(int k = 0; k < deltaVar.size(); k++){
                        int index = deltaVar.get(k).getRight();
                        int key = deltaVar.get(k).getLeft();
                        if(key <= 2){
                            Node current_node = scheme.getNode(index);
                            new_matrix.setEntry(i*3+j, k, solveNearestElementsToMatrix(current_node, key));
                        }
                        else{
                            index = deltaVar.get(i*3+j).getRight();

                            Node current_node = scheme.getNode(index);
                            new_matrix.setEntry(i*3+j, k, solveNearestElementsToMatrix(current_node, key));

                        }




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


        matrix = new_matrix;
    }
    public void printMatrix(){
        System.out.print("Matrix:\n       ");
        for(MutablePair<Integer, Integer> param: deltaVar) {
            switch (param.getLeft()){
                case 0:
                    System.out.printf("d(p%d) ", param.getRight());
                    break;
                case 1:
                    System.out.printf("inter(p%d) ", param.getRight());
                    break;
                case 2:
                    System.out.printf("p%d ", param.getRight());
                    break;
                case 3:
                    System.out.printf("I_{E_%d} ", param.getRight());
                    break;
            }
        }
        System.out.print("\n");
        for(int i = 0; i < MSize; i++){
            switch (deltaVar.get(i).getLeft()){
                case 0:
                    System.out.printf("d(p%d) ", deltaVar.get(i).getRight());
                    break;
                case 1:
                    System.out.printf("inter(p%d) ", deltaVar.get(i).getRight());
                    break;
                case 2:
                    System.out.printf("p%d  ", deltaVar.get(i).getRight());
                    break;
                case 3:
                    System.out.printf("I_{E_%d} ", deltaVar.get(i).getRight());
                    break;
            }
            for(int j = 0; j < MSize; j++){
                System.out.printf("%4.4f, ", matrix.getEntry(i, j));
            }
            System.out.print("\n");

        }


    }
    public void generateVector(){
        vector = new ArrayRealVector(MSize);
        for(int i = 0; i < MSize; i++) {
            int index = 0;
            switch (deltaVar.get(i).getLeft()){
                case 0:
                    index = unknownFromIndexKey(2, deltaVar.get(i).getRight());
                    vector.setEntry(i, unknown_curr.getEntry(i) - (unknown_curr.getEntry(index) - unknown_prev.getEntry(index))/dt);
                    break;
                case 1:
                    index = unknownFromIndexKey(1, deltaVar.get(i).getRight());
                    vector.setEntry(i, unknown_curr.getEntry(index) - (unknown_curr.getEntry(i)*dt + unknown_prev.getEntry(index)));
                    break;
                case 2:
                    Node node = scheme.getNode(deltaVar.get(i).getRight());
                    vector.setEntry(i, solveNearestElementsToVector(node));
                    break;
                case 3:
                    Element elem = scheme.getEMF(deltaVar.get(i).getRight());
                    vector.setEntry(i, solveNearestEMFtoVector(elem));
                    break;
            }
        }


    }
    public double solveNearestElementsToVector(Node node){

        ArrayList<Element> nearest_elems = node.getNearest_elems();
        double res = 0.;
        for(Element elem: nearest_elems){
            double p_s_0 = unknown_curr.getEntry(unknownFromIndexKey(0,elem.getStart().getNumber()));
            double p_f_0 = unknown_curr.getEntry(unknownFromIndexKey(0,elem.getFinish().getNumber()));
            double p_s_1 = unknown_curr.getEntry(unknownFromIndexKey(1,elem.getStart().getNumber()));
            double p_f_1 = unknown_curr.getEntry(unknownFromIndexKey(1,elem.getFinish().getNumber()));
            double p_s_2 = unknown_curr.getEntry(unknownFromIndexKey(2,elem.getStart().getNumber()));
            double p_f_2 = unknown_curr.getEntry(unknownFromIndexKey(2,elem.getFinish().getNumber()));

            double val = elem.getValue();
            int direction = 1;
            if(elem.getFinish() == node){
                direction = -1;
            } else if (elem.getStart() == node){
                direction = 1;
            }
            switch (elem.getType()){
                case 1:
                    res += direction*unknown_curr.getEntry(NSize+searchEMF_Index(elem));
                    break;
                case 2:
                    res += direction*elem.getValue()*(p_s_0 - p_f_0);
                    break;
                case 3:
                    res += direction*(1/elem.getValue())*(p_s_2 - p_f_2);
                    break;
                case 4:
                    res += direction*(1/elem.getValue())*(p_s_1 - p_f_1);
                    break;
                case 5:
                    res += direction*elem.getValue();
                    break;
            }


        }
        return res;
    }
    public double solveNearestEMFtoVector(Element elem){
        double res = 0.;
        double p_s = unknown_curr.getEntry(unknownFromIndexKey(2,elem.getStart().getNumber()));
        double p_f = unknown_curr.getEntry(unknownFromIndexKey(2,elem.getFinish().getNumber()));
        res += p_s - p_f - elem.getValue();
        return res;
    }

    public int unknownFromIndexKey(int key, int i){
        for(int j = 0; j < MSize; j++){
            if(deltaVar.get(j).getRight() == i && deltaVar.get(j).getLeft() == key){
                return i;
            }
        }
        return 0;

    }
    public int searchEMF_Index(Element elem){
        for(int i = 0; i < scheme.EMF_Numbers(); i++){
            if(scheme.getEMF(i) == elem){
                return i;
            }
        }
        return 0;
    }

    public void printVector(){
        System.out.print("Vector:\n");
        for(int i = 0; i < MSize; i++){
            System.out.printf("%4.4f\n", vector.getEntry(i));
        }
    }





}