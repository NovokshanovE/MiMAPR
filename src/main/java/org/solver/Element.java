package org.solver;

import java.util.ArrayList;

public class Element {
    private int index;
    private Node start;
    private Node finish;
    private String name;
    private double value;
    private int type;
    private double val_n_1 = 0;

    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    public Element(Node start, Node finish, int type, String name, double value, int index) {
        this.start = start;
        this.finish = finish;
        this.type = type;
        this.name = name;
        this.value = value;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getVal_n_1() {
        return val_n_1;
    }

    public void setVal_n_1(double val_n_1) {
        this.val_n_1 = val_n_1;
    }

    public int getIndex() {
        return index;
    }

    public double getValue() {
        return value;
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
    public void printInfo(){
        System.out.printf("------------------------------\nName: %s\nValue:%f\nType:%d\nNode start: %d  Node finish: %d\n" +
                "------------------------------\n", name, value, type, start.getNumber(),finish.getNumber());

    }

}

