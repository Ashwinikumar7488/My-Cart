package com.ty.my_cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ty.my_cart.dto.Cart;
import com.ty.my_cart.service.CartService;
import com.ty.my_cart.util.ResponseStructure;

@RequestMapping("/carts")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping()
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart) {
		return cartService.saveCart(cart);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseStructure<Cart>> updateCart(@PathVariable int id, @RequestBody Cart cart) {
		return cartService.updateCart(id, cart);
	}

	@GetMapping()
	public ResponseEntity<ResponseStructure<List<Cart>>> getAll() {
		return cartService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Cart>> getCartById(@PathVariable int id) {
		return cartService.getCartById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCart(@PathVariable int id) {
		return cartService.deleteCart(id);
	}

}