package com.gersonandre.GersonAndre.repository;

import com.gersonandre.GersonAndre.model.Suit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuitRepository extends JpaRepository<Suit, Long> {
}
