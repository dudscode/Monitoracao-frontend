package com.monitoracao.monitoramentoerros.repositories;

import com.monitoracao.monitoramentoerros.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByApplicationId(Long applicationId);
    List<LogEntry> findBySessionId(Long sessionId);
}
