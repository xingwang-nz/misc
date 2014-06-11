package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.Order;
import nz.co.ecngroup.droolstest.fact.OrderItem;

import org.drools.runtime.StatefulKnowledgeSession;

public class FromRule extends AppBaseRule {

	@Override
	public void doRunRule(StatefulKnowledgeSession session) throws Exception {
		session.setGlobal("dbService", dbService);
		
		final Order order = new Order();
		order.addItem(new OrderItem("Item1", 50));
		order.addItem(new OrderItem("Item2", 150));
		order.addItem(new OrderItem("Item2", 120));
		session.insert(order);
		session.fireAllRules();
	}

}
