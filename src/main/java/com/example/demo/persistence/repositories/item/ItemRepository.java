package com.example.demo.persistence.repositories.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.model.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	@Query(value = "SELECT item FROM Item item WHERE item.name = :name AND (item.id != :ignoreId OR :ignoreId IS NULL)")
	Item getNameByIgnoreId(@Param(value = "name") String name, @Param(value = "ignoreId") Long ignoreId);
}
