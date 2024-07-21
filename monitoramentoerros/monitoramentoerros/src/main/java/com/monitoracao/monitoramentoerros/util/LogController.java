package com.monitoracao.monitoramentoerros.util;

import com.monitoracao.monitoramentoerros.entities.Application;
import com.monitoracao.monitoramentoerros.entities.LogEntry;
import com.monitoracao.monitoramentoerros.entities.Session;
import com.monitoracao.monitoramentoerros.repositories.ApplicationRepository;
import com.monitoracao.monitoramentoerros.repositories.LogEntryRepository;
import com.monitoracao.monitoramentoerros.repositories.SessionRepository;
import com.monitoracao.monitoramentoerros.services.ApplicationService;
import com.monitoracao.monitoramentoerros.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private  LogEntryRepository logEntryRepository;
    @Autowired
    private  ApplicationRepository applicationRepository;
    @Autowired
    private SessionRepository sessionRepository;



    @GetMapping("/applications/{id}/logs")
    public ResponseEntity<List<LogEntry>> getLogsByApplicationsId(@PathVariable Long applicationId){
        List<LogEntry>  logEntryList= logEntryRepository.findByApplicationId(applicationId);
       return  ResponseEntity.status(HttpStatus.OK).body(logEntryList);
    }

    @GetMapping("applications")
    public  ResponseEntity<List<Application>> getAllApplication(){
        return  ResponseEntity.status(HttpStatus.OK).body(
                applicationRepository.findAll()
        );
    }

    @PostMapping("/applications")
    public  ResponseEntity<Application>  createApplication(@RequestBody Application application){
        application.setAcessCount(0);
        application.setErrorCount(0);
        Application applicationCreated = applicationRepository.save(application);
        return  ResponseEntity.status(HttpStatus.CREATED).body(applicationCreated);
    }
    @PutMapping("/applications/acess/{idApplication}")
    public  Application  createAcessApplication(@PathVariable Long idApplication){
       return  applicationRepository.findById(idApplication).map(
                app -> {
                    app.setAcessCount(app.getAcessCount() +1);
                    return applicationRepository.save(app);
                }
                )
                .orElseThrow(() -> new RuntimeException(" Aplicação não encontrada!") );
    }

    @PostMapping("/session")
    public ResponseEntity<Session> createdSession(@RequestBody Session session){
        Session createdSession =  sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }


    @PostMapping("/logs/{idApplication}/{idSession}")
    public ResponseEntity<LogEntry> createLogs(@RequestBody LogEntry log, @PathVariable Long idApplication, @PathVariable Long idSession){
        Application application = applicationRepository.findById(idApplication).map(
                app -> {
                    app.setErrorCount(app.getErrorCount() + 1);
                    return applicationRepository.save(app);
                }
        ).orElseThrow(() -> new RuntimeException(" Aplicação não encontrada!") );
        Session session = sessionRepository.findById(idSession).orElseThrow(() -> new RuntimeException("sessão não encontrada!") );
        log.setApplication(application);
        log.setTimestamp(LocalDateTime.now());
        log.setSession(session);
        LogEntry logCreated = logEntryRepository.save(log);
        return  ResponseEntity.status(HttpStatus.CREATED).body(logCreated);
    }

    @GetMapping("/logs/{idApplication}")
    public ResponseEntity<List<LogEntry>> getLogsByApplication(@PathVariable Long idApplication){
        List<LogEntry> logs = logEntryRepository.findByApplicationId(idApplication);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }

    @GetMapping("/logs/session/{idSession}")
    public ResponseEntity<List<LogEntry>> getLogsBySession(@PathVariable Long idSession){
        List<LogEntry> logs = logEntryRepository.findBySessionId(idSession);
        return ResponseEntity.status(HttpStatus.OK).body(logs);
    }
}


