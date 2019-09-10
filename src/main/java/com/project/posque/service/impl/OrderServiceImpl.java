package com.project.posque.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.posque.entity.Item;
import com.project.posque.entity.OrderD;
import com.project.posque.entity.OrderDId;
import com.project.posque.entity.OrderH;
import com.project.posque.entity.OrderRequest;
import com.project.posque.entity.Product;
import com.project.posque.repository.OrderDJpaRepository;
import com.project.posque.repository.OrderHJpaRepository;
import com.project.posque.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderHJpaRepository orderHJpaRepository;
	
	@Autowired
	private OrderDJpaRepository orderDJpaRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Transactional
	@Override
	public int addOrder(OrderRequest orderRequest) {
		OrderH newOrderH = orderHJpaRepository.save(new OrderH(orderRequest.getCustNo(), 0));
		for(Item item: orderRequest.getItems()) {
			OrderDId id = new OrderDId(newOrderH.getOrderNo(), item.getPcode());
			
			String productUrl = "http://localhost:8081/products/" + item.getPcode();
			Product product = restTemplate.getForObject(productUrl, Product.class);
			
			orderDJpaRepository.save(new OrderD(id, item.getQty(), product.getSellPrice(), product.getSellPrice() * item.getQty()));
		}
		long totalAmount = orderDJpaRepository.sumByOrderNo(newOrderH.getOrderNo());
		newOrderH.setAmount(totalAmount);
		orderHJpaRepository.save(newOrderH);
		return newOrderH.getOrderNo();
	}

}
