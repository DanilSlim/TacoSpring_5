package web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.data.interfaces.OrderRepository;
import tacos.data.jdbc.OrderRepositoryJDBC;

@Controller
@RequestMapping("/ordersjdbc")
@SessionAttributes("order")
public class OrderControllerJDBC {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderControllerJDBC(OrderRepositoryJDBC orderRepo) {
		
		this.orderRepo=orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		
		return "orderFormJDBC";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		
		if (errors.hasErrors()) {
			
			return "orderFormJDBC";
			}
		
		orderRepo.save(order);
		
		sessionStatus.setComplete();//clear session
		
		return "redirect:/";
	}

}
