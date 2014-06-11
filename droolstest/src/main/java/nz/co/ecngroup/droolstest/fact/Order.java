package nz.co.ecngroup.droolstest.fact;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<OrderItem> items = new ArrayList<OrderItem>();

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public void addItem(OrderItem item) {
		this.items.add(item);
	}
	
	
}
