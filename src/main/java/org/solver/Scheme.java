package org.solver;

import java.util.ArrayList;

public class Scheme {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Element> elems = new ArrayList<Element>();



    public void AddNewElem(int type, int start, int finish, String name){
        Node start_node = nodes.get(start-1);
        Node finish_node = nodes.get(finish-1);

        Element new_elem = new Element(start_node, finish_node, type, name);
        start_node.addNearest_elems(new_elem);
        finish_node.addNearest_elems(new_elem);
        this.elems.add(new_elem);
        System.out.print("Add new elem to Scheme\n");
    }

    public void AddNewNode(int node) {
        Node new_node = new Node(node);
        nodes.add(new_node);
    }
}
