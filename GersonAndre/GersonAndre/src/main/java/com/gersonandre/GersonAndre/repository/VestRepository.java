package com.gersonandre.GersonAndre.repository;

import com.gersonandre.GersonAndre.model.Vest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VestRepository extends JpaRepository<Vest, Long> {
}
