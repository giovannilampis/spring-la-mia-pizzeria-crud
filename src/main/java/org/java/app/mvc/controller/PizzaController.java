package org.java.app.mvc.controller;

import java.util.List;


import org.java.app.pizzeria.pojo.Pizza;
import org.java.app.pizzeria.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaServ pizzaServ;

    @GetMapping
    public String getIndex(@RequestParam(required = false) String searchTitle, Model model) {
    	
        List<Pizza> pizzas = searchTitle == null
        
        	? pizzaServ.findAll()
        	: pizzaServ.findByName(searchTitle);

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("searchTitle", searchTitle);

        return "pizza-index";
    }

	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		System.out.print("show");
		Pizza pizza = pizzaServ.findById(id);
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
	
	@GetMapping("/create")
	public String getCreateFormt(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-create";
	}
	@PostMapping("/create")
	public String storeNewPizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model
		) {
		
		System.out.println("pizza:\n" + pizza);
		
		if (bindingResult.hasErrors()) {
			
			System.out.println("Errors");
			bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
				.forEach(System.out::println);
			
			return "pizza-create";
		}
		
		pizzaServ.save(pizza);
		
		return "redirect:/";
	}
}
