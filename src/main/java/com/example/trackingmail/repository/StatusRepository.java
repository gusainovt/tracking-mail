package com.example.trackingmail.repository;

import com.example.trackingmail.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<List<Status>> findStatusesByPostalItemId(Long postalItemId);

}
