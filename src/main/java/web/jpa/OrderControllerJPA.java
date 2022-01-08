package web.jpa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import tacos.data.interfaces.jpa.OrderRepositoryJPA;
import tacos.jpa.OrderJPA;
import tacos.security.User;

@Controller
@RequestMapping("/ordersjpa")
@SessionAttributes("orderjpa")
public class OrderControllerJPA {
	
	
	private final OrderRepositoryJPA orderRepo;
	
	@Autowired
	public OrderControllerJPA(OrderRepositoryJPA orderRepo) {
		this.orderRepo=orderRepo;
	}

	
	@GetMapping("/current")
	public String orderForm() {
		
		return "orderFormJPA";
	}
	
	@PostMapping
	public String processOrder(@Valid @ModelAttribute("orderjpa") OrderJPA order, Errors errors, 
								SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		
		if (errors.hasErrors()) {
			
			return "orderFormJPA";
			}
		
		order.setUser(user);
		
		orderRepo.save(order);
		
		sessionStatus.setComplete();//clear session
		
		return "redirect:/";
	}
}
