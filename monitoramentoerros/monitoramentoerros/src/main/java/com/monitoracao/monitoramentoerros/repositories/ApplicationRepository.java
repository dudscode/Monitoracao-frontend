package com.monitoracao.monitoramentoerros.repositories;

import com.monitoracao.monitoramentoerros.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
