package tacos.data.interfaces.jpa;

import org.springframework.data.repository.CrudRepository;


import tacos.jpa.OrderJPA;

public interface OrderRepositoryJPA extends CrudRepository <OrderJPA, Long> {

}
