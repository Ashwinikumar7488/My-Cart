package com.ty.my_cart.service;

import static org.springframework.http.HttpStatus.CREATED;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.my_cart.dao.CartDao;
import com.ty.my_cart.dto.Cart;
import com.ty.my_cart.dto.Item;
import com.ty.my_cart.exception.IdNotFoundException;
import com.ty.my_cart.util.ResponseStructure;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public ResponseEntity<ResponseStructure<Cart>> saveCart(Cart cart) {
		List<Item> items = cart.getItems();
		cart.setTotalCost(cart.getItems().stream().map(item -> item.getCost() * item.getQuantity())
				.collect(Collectors.summarizingDouble(Double::doubleValue)).getSum());
		ResponseStructure<Cart> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(cartDao.saveCart(cart));
		return new ResponseEntity<>(responseStructure, CREATED);
	}

	public ResponseEntity<ResponseStructure<Cart>> getCartById(int id) {
		Optional<Cart> opt = cartDao.getCartById(id);
		ResponseStructure<Cart> responseStructure = new ResponseStructure<>();
		if (opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(opt.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID Not Found!");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteCart(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Optional<Cart> opt = cartDao.getCartById(id);
		if (opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData("DATA DELETED SUCCESSFULLY!");
			cartDao.deleteCart(id);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID Not Found!");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Cart>> updateCart(int id, Cart cart) {
		double sum = 0;
		List<Item> items = cart.getItems();
		cart.setItems(items);
		for (Item item : items) {
			sum += item.getCost() * item.getQuantity();
			cart.setItems(items);
		}
		cart.setTotalCost(sum);
		Optional<Cart> opt = cartDao.getCartById(id);
		if (opt.isPresent()) {
			Cart ret = opt.get();
			ret.setStatus(cart.getStatus());
			ret.setStatus(cart.getStatus());
			ret.setItems(cart.getItems());
			ret.setTotalCost(cart.getTotalCost());
			ResponseStructure<Cart> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(cartDao.saveCart(ret));
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given ID " + id + " doesn't exist!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Cart>>> getAll() {
		ResponseStructure<List<Cart>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(cartDao.getAll());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
