package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;
	@InjectMocks
	private OrderService orderService;

	 @Test
	 public void whenSaveOrderEntity_shouldReturnOrderEntity() 
	 {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("Test Name");
        when(orderRepository.save(ArgumentMatchers.any(OrderEntity.class))).thenReturn(orderEntity);
        OrderEntity created = orderService.createNewOrder(orderEntity);
        assertThat(created.getName()).isSameAs(orderEntity.getName());
        verify(orderRepository).save(orderEntity);
	 }
	 
	 @Test
	 public void shouldReturnAllOrderEntitys() {
	 List<OrderEntity> orderEntity = new ArrayList();
	 orderEntity.add(new OrderEntity());
	 given(orderRepository.findAll()).willReturn(orderEntity);
	 List<OrderEntity> expected = orderService.listAllOrders();
	 assertEquals(expected, orderEntity);
	 verify(orderRepository).findAll();
	 }
	 
	 @Test
	 public void whenGivenId_shouldDeleteOrderEntity_ifFound(){
		 OrderEntity orderEntity = new OrderEntity();
		 orderEntity.setId(1);
		 orderEntity.setName("Rahul");
		 orderEntity.setAmount(1000);
		 orderEntity.setDate("01/01/2020");
		 when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));
		 orderService.deleteOrder(orderEntity.getId());
		 verify(orderRepository).deleteById(orderEntity.getId());
	 }
		 
	 @Test
	 public void whenGivenId_shouldUpdateOrderEntity_ifFound() {
		 OrderEntity orderEntity = new OrderEntity();
		 orderEntity.setId(1);
		 orderEntity.setName("Rahul");
		 orderEntity.setAmount(1000);
		 orderEntity.setDate("01/01/2020");
		 OrderEntity newOrderEntity = new OrderEntity();
		 newOrderEntity.setName("Rahul");
		 newOrderEntity.setAmount(1000);
		 newOrderEntity.setDate("01/01/2020");
		 given(orderRepository.findById(orderEntity.getId())).willReturn(Optional.of(orderEntity));
		 orderService.updateOrder(orderEntity.getId(), newOrderEntity);
		 verify(orderRepository).save(newOrderEntity);
		 verify(orderRepository).findById(orderEntity.getId());
	 }
		 
	 @Test
	 public void whenGivenId_shouldReturnOrderEntity_ifFound() {
		 OrderEntity orderEntity = new OrderEntity();
		 orderEntity.setId(1);
		 when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));
		 OrderEntity expected = orderService.listOrder(orderEntity.getId());
		 assertThat(expected).isSameAs(orderEntity);
		 verify(orderRepository).findById(orderEntity.getId());
	 }
}
