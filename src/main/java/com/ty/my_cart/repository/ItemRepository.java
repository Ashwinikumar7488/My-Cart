package com.ty.my_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.my_cart.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
