package org.solver;

import java.util.ArrayList;

public class Element {
    private Node start;
    private Node finish;
    private String name;
    private int type;

    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
    Element(Node start, Node finish, int type, String name){
        this.start = start;
        this.finish = finish;
        this.type = type;
        this.name = name;
    }
    public Node getFinish() {
        return finish;
    }

    public Node getStart() {
        return start;
    }

    public void setFinish(Node finish) {
        this.finish = finish;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

//    public void generateMatrix(){
//        switch (type){
//            case 1:
////                for (int i = 0; i < 3; i++){
////                    ArrayList<Double>
////                    matrix.add()
////                }
//                break;
//        }
//
//    }
}
