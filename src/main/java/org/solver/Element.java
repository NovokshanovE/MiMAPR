package org.solver;

import java.util.ArrayList;

public class Element {
    private Node start;
    private Node finish;
    private String name;
    private double value;
    private int type;

    private ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    public Element(Node start, Node finish, int type, String name, double value) {
        this.start = start;
        this.finish = finish;
        this.type = type;
        this.name = name;
        this.value = value;
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
