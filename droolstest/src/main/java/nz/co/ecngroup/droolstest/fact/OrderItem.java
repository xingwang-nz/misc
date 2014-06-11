package nz.co.ecngroup.droolstest.fact;

public class OrderItem {
    private String name;
    private int value;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    public OrderItem() {

    }

    public OrderItem(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderItem [name=" + name + ", value=" + value + ", selected=" + selected + "]";
    }

}
