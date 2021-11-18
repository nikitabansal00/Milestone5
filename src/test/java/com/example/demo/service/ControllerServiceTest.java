package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.OrderController;
import com.example.demo.entity.OrderEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
class ControllerServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private OrderService service;
	
	@Test
	public void createUser_whenPostMethod() throws Exception {
		OrderEntity order = new OrderEntity();
		order.setId(1);
		order.setName("Rahul");
		order.setAmount(1000);
		order.setDate("01/01/2020");
		given(service.createNewOrder(order)).willReturn(order);
		mockMvc.perform(post("/order")
		.contentType(MediaType.APPLICATION_JSON)
		.content(JsonUtil.toJson(order)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id", is(order.getId())))
		.andExpect(jsonPath("$.name", is(order.getName())))
		.andExpect(jsonPath("$.amount", is(order.getAmount())))
		.andExpect(jsonPath("$.date", is(order.getDate())));
	}
	
	@Test
	public void listAllUsers_whenGetMethod() throws Exception {
		OrderEntity order = new OrderEntity();
		order.setId(1);
		order.setName("Rahul");
		order.setAmount(1000);
		order.setDate("01/01/2020");
		List<OrderEntity> allUsers = Arrays.asList(order);
		given(service
		.listAllOrders())
		.willReturn(allUsers);
		mockMvc.perform(get("/order")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("$[0].id", is(order.getId())))
		.andExpect(jsonPath("$[1].name", is(order.getName())))
		.andExpect(jsonPath("$[2].amount", is(order.getAmount())))
		.andExpect(jsonPath("$[3].date", is(order.getDate())));
	}
	
	@Test
	public void removeUserById_whenDeleteMethod() throws Exception {
		OrderEntity order = new OrderEntity();
		order.setId(1);
		order.setName("Rahul");
		order.setAmount(1000);
		order.setDate("01/01/2020");
		doNothing().when(service).deleteOrder(order.getId());
		mockMvc.perform(delete("/order/" + order.toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	@Test
	public void listUserById_whenGetMethod() throws Exception {
		OrderEntity order = new OrderEntity();
		order.setId(1);
		order.setName("Rahul");
		order.setAmount(1000);
		order.setDate("01/01/2020");
		given(service.listOrder(order.getId())).willReturn(order);
		mockMvc.perform(get("/order/" + order.toString())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("order", is(order.getName())));
	}
	
	@Test
	public void updateUser_whenPutUser() throws Exception {
		OrderEntity order = new OrderEntity();
		order.setId(1);
		order.setName("Rahul");
		order.setAmount(1000);
		order.setDate("01/01/2020");
		given(service.updateOrder(order.getId(), order)).willReturn(order);
		ObjectMapper mapper = new ObjectMapper();
		
		mockMvc.perform(put("/order/" + order.toString())
		.content(mapper.writeValueAsString(order))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("order", is(order.getName())));
	}	

}
