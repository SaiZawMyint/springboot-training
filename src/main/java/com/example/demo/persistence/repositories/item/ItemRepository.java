package com.example.demo.persistence.repositories.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.model.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
}
