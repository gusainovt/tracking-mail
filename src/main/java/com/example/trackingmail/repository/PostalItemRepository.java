package com.example.trackingmail.repository;

import com.example.trackingmail.model.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {
}
