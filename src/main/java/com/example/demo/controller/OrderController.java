package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.OrderEntity;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	public OrderService service;
	
//	
//	@GetMapping("/Order")
//	public List<OrderEntity> getAllOrders()
//	{
//		return this.orderService.getAllOrder();
//	}
//	
//	@GetMapping("/Order/{id}")
//	public Optional<OrderEntity> getOrderById(@PathVariable long id)
//	{
//			return this.orderService.getOrderById(id);
//	}
//	
//	@PostMapping("/Order")
//	public OrderEntity saveOrder(@RequestBody OrderEntity Order)
//	{
//		return this.orderService.saveOrder(Order);
//	}
//	
//	@PutMapping("/Order/{id}")
//	public OrderEntity updateOrder(@RequestBody OrderEntity Order,@PathVariable long id)
//	{
//		return this.orderService.updateOrder(Order,id);
//	}
//
//	@DeleteMapping("/Order/{id}")
//	public void deleteOrder(@PathVariable long id)
//	{
//		this.orderService.deleteOrder(id);
//	}
//	
	
	@PostMapping
	@RequestMapping("/order")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrderEntity> createNewOrder_whenPostOrder(@RequestBody OrderEntity order) {
		OrderEntity createdUser = service.createNewOrder(order);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(createdUser.getId())
	.toUri();
	return ResponseEntity.created(uri).body(createdUser);
	}
	
	
	
	@DeleteMapping
	@RequestMapping("/order/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser_whenDeleteOrder(@PathVariable Long id) {
	service.deleteOrder(id);
	}

	@GetMapping
	@RequestMapping("/order/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<OrderEntity> list(@PathVariable Long id) {
	return ResponseEntity.ok().body(service.listOrder(id));
	}
	
	@GetMapping
	@RequestMapping("/order")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<OrderEntity>> listAllOrders_whenGetOrders() {
	return ResponseEntity.ok().body(service.listAllOrders());
	}
	
	@PutMapping
	@RequestMapping("/order/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<OrderEntity> updateOrder_whenPutOrder(@RequestBody OrderEntity order, @PathVariable Long id) {
	return ResponseEntity.ok().body(service.updateOrder(id, order));
	}
}
