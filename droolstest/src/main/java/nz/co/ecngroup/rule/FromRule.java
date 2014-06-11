package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.Order;
import nz.co.ecngroup.droolstest.fact.OrderItem;

import org.drools.runtime.StatefulKnowledgeSession;

public class FromRule extends AppBaseRule {

    @Override
    public void doRunRule(final StatefulKnowledgeSession session) throws Exception {
        session.setGlobal("dbService", dbService);

        Order nzorder = new Order();
        nzorder.setType("NZ");
        nzorder.addItem(new OrderItem("Item1", 50));
        nzorder.addItem(new OrderItem("Item2", 120));
        nzorder.addItem(new OrderItem("Item3", 130));
        nzorder.addItem(new OrderItem("Item4", 140));
        session.insert(nzorder);

        Order order = new Order();
        order.setType("AU");
        order.addItem(new OrderItem("Item1", 60));
        order.addItem(new OrderItem("Item2", 70));
        order.addItem(new OrderItem("Item3", 130));
        order.addItem(new OrderItem("Item4", 140));
        session.insert(order);

        session.fireAllRules();

        System.out.println(nzorder);
        System.out.println(order);
    }

}
