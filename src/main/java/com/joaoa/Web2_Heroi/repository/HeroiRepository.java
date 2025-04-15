package com.joaoa.Web2_Heroi.repository;

import com.joaoa.Web2_Heroi.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

}
