package org.solver;

import java.util.ArrayList;

public class Scheme implements Cloneable{
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Element> elems = new ArrayList<Element>();
//   list of  index sources of EMF
    private ArrayList<Integer> sources_EMF = new ArrayList<Integer>();

//    list of index current sources
    private ArrayList<Integer> sources_current = new ArrayList<Integer>();
    public Scheme(){
        this.nodes.add(new Node(0));

    }
    public void AddNewElem(int type, int start, int finish, String name, double value){

        Node start_node = nodes.get(start);


        Node finish_node = nodes.get(finish);


//  Add index of source to list
        if(type == 1){
            sources_EMF.add(elems.size());
        } else if (type == 5) {
            sources_current.add(elems.size());
        }

        Element new_elem = new Element(start_node, finish_node, type, name, value);

        start_node.addNearest_elems(new_elem);
        finish_node.addNearest_elems(new_elem);
        this.elems.add(new_elem);
        System.out.print("Add new elem to Scheme\n");
    }
    public void printInfo(){
        System.out.printf("Number of sources of EMF: %d\n", sources_EMF.size());
        System.out.printf("Number of current sources: %d\n", sources_current.size());
        for(Node node: this.nodes){
            node.printNearestElems();
        }
    }

    public void AddNewNode(int node) {
        Node new_node = new Node(node);
        nodes.add(new_node);
    }
    public Scheme clone() throws CloneNotSupportedException{

        return (Scheme) super.clone();
    }
}
