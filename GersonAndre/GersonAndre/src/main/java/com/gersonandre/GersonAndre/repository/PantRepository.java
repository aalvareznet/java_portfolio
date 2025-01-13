package com.gersonandre.GersonAndre.repository;

import com.gersonandre.GersonAndre.model.Pant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantRepository extends JpaRepository<Pant, Long> {
}
