package org.solver;

public class VoltageSource extends Element {
    private boolean type_voltage_source;
    public VoltageSource(Node start, Node finish, int type, String name, double value, int index) {
        super(start, finish, type, name, value, index);
    }

    public void setType_voltage_source(boolean type_voltage_source) {
        this.type_voltage_source = type_voltage_source;
    }

    public boolean isType_voltage_source() {
        return type_voltage_source;
    }
}
