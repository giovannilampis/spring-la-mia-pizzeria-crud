package org.java.app.mvc.controller;

import java.util.List;


import org.java.app.pizzeria.pojo.Pizza;
import org.java.app.pizzeria.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaServ pizzaServ;

    @GetMapping
    public String getIndex(Model model, @RequestParam(required = false) String nome) {

        List<Pizza> pizzas = nome == null ? pizzaServ.findAll() : pizzaServ.findByName(nome);

        model.addAttribute("pizzas", pizzas);
        System.out.println("pizza: " + pizzas);

        return "pizza-index";
    }

	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		System.out.print("show");
		Pizza pizza = pizzaServ.findById(id);
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
}
