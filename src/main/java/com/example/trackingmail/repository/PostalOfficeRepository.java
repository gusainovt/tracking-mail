package com.example.trackingmail.repository;

import com.example.trackingmail.model.PostalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, Long> {
    Optional<PostalOffice> findByName(String name);

}
