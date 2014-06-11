package nz.co.ecngroup.droolstest.fact;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @Override
    public String toString() {
        return "Order [type=" + type + ", items=" + items + "]";
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    private List<OrderItem> items = new ArrayList<OrderItem>();

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(final List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(final OrderItem item) {
        this.items.add(item);
    }

}
