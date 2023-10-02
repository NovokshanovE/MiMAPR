package org.solver;

public class Element {
    private Node start;
    private Node finish;
    private String name;
    private int type;
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
}
