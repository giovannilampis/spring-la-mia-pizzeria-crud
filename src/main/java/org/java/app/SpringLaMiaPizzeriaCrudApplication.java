package org.java.app;

import org.java.app.pizzeria.pojo.Pizza;
import org.java.app.pizzeria.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String photo = "https://www.finedininglovers.it/sites/g/files/xknfdk1106/files/styles/recipes_1200_800_fallback/public/fdl_content_import_it/margherita-50kalo.jpg?itok=v9nHxNMS";
		
		Pizza pizza1 = new Pizza("pizza 1", "desc pizza 1", photo, 10);
		Pizza pizza2 = new Pizza("pizza 2", "desc pizza 2", photo, 12);
		Pizza pizza3 = new Pizza("pizza 3", "desc pizza 3", photo, 14);
		Pizza pizza4 = new Pizza("pizza 4", "desc pizza 4", photo, 16);
		Pizza pizza5 = new Pizza("pizza 5", "desc pizza 5", photo, 18);
		
		pizzaServ.save(pizza1);
		pizzaServ.save(pizza2);
		pizzaServ.save(pizza3);
		pizzaServ.save(pizza4);
		pizzaServ.save(pizza5);
		
		System.out.println("Insert OK");
	}

}
