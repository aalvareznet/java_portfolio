package com.gersonandre.GersonAndre.repository;

import com.gersonandre.GersonAndre.model.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Long> {
}
