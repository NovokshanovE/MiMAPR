package org.solver.nodal.advanced_for_mechanical_systems;


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



//    Метод в котором формируется матрица Якоби для заданного элемента
    public RealMatrix elementMatrix(Element elem){
        RealMatrix matrix;// = new Array2DRowRealMatrix(new double[][]{{0,0},{0,0}});
        double val = elem.getValue();
        switch (elem.getType()){
            case 1:
                matrix = new Array2DRowRealMatrix(new double[][]{{0,0},{0,0}});
                break;
            case 2:
                matrix = new Array2DRowRealMatrix(new double[][]{{val/this.dt,-val/this.dt},{-val/this.dt,val/this.dt}});
                break;
            case 3:
                matrix = new Array2DRowRealMatrix(new double[][]{{1/val,-1/val},{-1/val,1/val}});
                break;
            case 4:
                matrix = new Array2DRowRealMatrix(new double[][]{{this.dt/val,-this.dt/val},{-this.dt/val,this.dt/val}});
                break;
            case 5:
                matrix = new Array2DRowRealMatrix(new double[][]{{0,0},{0,0}});
                break;
            default:
                matrix = new Array2DRowRealMatrix(new double[][]{{0,0},{0,0}});
        }
        return matrix;
    }
//    Метод в котором формируется столбец текущих значений
    public RealVector elementColumn(Element elem){
        RealVector column;
        double val = elem.getValue();
        double potential_i = elem.getStart().getPotential();
        double potential_j = elem.getFinish().getPotential();
        switch (elem.getType()){
            case 1:
                column = new ArrayRealVector(new double[]{val/r,-val/r});

                break;
            case 2:
                column = new ArrayRealVector(new double[]{val/this.dt*(potential_i-potential_j - elem.getVal_n_1()),
                        -val/this.dt*(potential_i-potential_j - elem.getVal_n_1())});
                break;
            case 3:
                column = new ArrayRealVector(new double[]{(potential_i-potential_j)/val,-(potential_i-potential_j)/val});
                break;
            case 4:
                column = new ArrayRealVector(new double[]{((potential_i-potential_j)*this.dt/val+elem.getVal_n_1()),
                        -((potential_i-potential_j)*this.dt/val+elem.getVal_n_1())});
                break;
            case 5:
                column = new ArrayRealVector(new double[]{val,-val});
                break;
            default:
                column = new ArrayRealVector(new double[]{val,-val});
        }


        return column;
    }

    public void generateMatrix() {

//        this.matrix = new Array2DRowRealMatrix();
    }


    public void generateVector() {

    }
}
