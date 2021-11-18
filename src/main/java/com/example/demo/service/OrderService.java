package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService
{
	@Autowired
	OrderRepository repository;
	
	public OrderEntity createNewOrder(OrderEntity order) {
	return repository.save(order);
	}
	
	public void deleteOrder(Long id) {
		repository.findById(id);
		repository.deleteById(id);
		}
	
	public OrderEntity listOrder(Long id) {
		return repository.findById(id).get();
		}
	
	public List<OrderEntity> listAllOrders() {
		return repository.findAll();
		}
	
	public OrderEntity updateOrder(Long id, OrderEntity order) {
		repository.findById(id);
		order.setId(id);
		return repository.save(order);
		}

}
