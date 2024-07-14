package com.guibedan.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.guibedan.course.entities.Category;
import com.guibedan.course.entities.Order;
import com.guibedan.course.entities.OrderItem;
import com.guibedan.course.entities.Payment;
import com.guibedan.course.entities.Product;
import com.guibedan.course.entities.Users;
import com.guibedan.course.entities.enums.OrderStatus;
import com.guibedan.course.repository.CategoryRepository;
import com.guibedan.course.repository.OrderItemRepository;
import com.guibedan.course.repository.OrderRepository;
import com.guibedan.course.repository.ProductRepository;
import com.guibedan.course.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		Users u1 = new Users(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Users u2 = new Users(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		Users u3 = new Users(null, "guilherme", "gui@gmail.com", "19993337275", "senha123");
		Users u4 = new Users(null, "Felipe", "Felipe@gmail.com", "977772343", "senha123");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

		Category c1 = new Category(null, "Eletronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Clothes");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "Nike shoes", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);

		o1.setPayment(pay1);

		orderRepository.save(o1);

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(c2);
		p2.getCategories().add(c1);
		p3.getCategories().add(c1);
		p4.getCategories().add(c3);
		p5.getCategories().add(c2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
	}

}
