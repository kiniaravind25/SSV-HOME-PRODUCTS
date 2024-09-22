package com.ssvhomeproducts.backendApplication.repository;

import com.ssvhomeproducts.backendApplication.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
