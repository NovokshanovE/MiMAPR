package org.solver;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private ArrayList<Element> nearest_elems = new ArrayList<Element>();

    private int number;
    private Double potential = 0.;
    private Double potetial_prev = 0.;
    Node(int number){
        this.number = number;

    }
    private boolean findElem(Element elem){
        for(Element elem_i: this.nearest_elems){
            if(elem_i.getIndex() == elem.getIndex()){
//                System.out.printf("%d ---- %d", elem_i.getIndex(), elem.getIndex());
                return false;
            }
        }
        return true;

    }
    public void addNearest_elems(Element elem) {
        if(findElem(elem)){
            this.nearest_elems.add(elem);
            System.out.println("Add new elem to "+number + "--Type:" + elem.getType() + "\n");
            return;
        }

        System.out.println("Element has already added.\n");

    }

    public Double getPotential() {
        return potential;
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
    public ArrayList<Element> getNearest_elems() {
        return nearest_elems;
    }
}


