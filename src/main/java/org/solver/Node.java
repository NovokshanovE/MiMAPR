package org.solver;


import java.util.ArrayList;

public class Node {
    private ArrayList<Element> nearest_elems = new ArrayList<Element>();

    private int number;
    private Double potential = 0.;
    Node(int number){
        this.number = number;

    }
    public void addNearest_elems(Element elem) {
        this.nearest_elems.add(elem);
        System.out.println("Add new elem to "+number + "--Type:" + elem.getType() + "\n");
    }

    public int getNumber() {
        return number;
    }

    public void printNearestElems(){
        int n = this.nearest_elems.size();
        System.out.printf("Node: %d\n", number);
        for(Element elem: nearest_elems){
            elem.printInfo();
        }
    }
}


