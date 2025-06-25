package com.LegendOfBrains.singo_server.repo;

import com.LegendOfBrains.singo_server.entity.SinGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinGoRepository extends JpaRepository<SinGo, Long> {
}
