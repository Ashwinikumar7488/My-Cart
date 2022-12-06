package com.ty.my_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.my_cart.dto.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
