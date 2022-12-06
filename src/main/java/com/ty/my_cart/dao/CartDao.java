package com.ty.my_cart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.my_cart.dto.Cart;
import com.ty.my_cart.repository.CartRepository;

@Repository
public class CartDao {

	@Autowired
	private CartRepository cartRepository;

	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	public Optional<Cart> getCartById(int id) {
		return cartRepository.findById(id);
	}

	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	public void deleteCart(int id) {
		Optional<Cart> opt = cartRepository.findById(id);
		cartRepository.delete(opt.get());
	}

	public Cart updateCart(int id, Cart cart) {
		return cartRepository.save(cart);
	}
}
