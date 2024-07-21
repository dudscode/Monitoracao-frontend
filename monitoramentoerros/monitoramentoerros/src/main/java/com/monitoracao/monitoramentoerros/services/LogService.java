package com.monitoracao.monitoramentoerros.services;

import com.monitoracao.monitoramentoerros.entities.Application;
import com.monitoracao.monitoramentoerros.entities.LogEntry;
import com.monitoracao.monitoramentoerros.repositories.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    private final LogEntryRepository logEntryRepository;
    private final ApplicationService applicationService;

    @Autowired
    public LogService(LogEntryRepository logEntryRepository, ApplicationService applicationService) {
        this.logEntryRepository = logEntryRepository;
        this.applicationService = applicationService;
    }

    public void saveLog(Long applicationId, String level, String message) {
        LogEntry logEntry = new LogEntry();
        logEntry.setLevel(level);
        logEntry.setMessage(message);
        logEntry.setTimestamp(LocalDateTime.now());
        Application application = applicationService.getApplicationById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Aplicação não encontrada com ID: " + applicationId));
        logEntry.setApplication(application);
        application.setErrorCount(application.getErrorCount() + 1);
        logEntryRepository.save(logEntry);
    }
}
