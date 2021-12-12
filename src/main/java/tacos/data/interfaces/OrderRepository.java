package tacos.data.interfaces;

import tacos.Order;

public interface OrderRepository {
	
	Order save(Order order);

}
