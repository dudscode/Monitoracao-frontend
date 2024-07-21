package com.monitoracao.monitoramentoerros.services;

import com.monitoracao.monitoramentoerros.entities.Application;
import com.monitoracao.monitoramentoerros.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public  ApplicationService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllAplications() {
     return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application saveApplication(Application application){
       return applicationRepository.save(application);
    }

    public void deleteApplication(Long id){
        applicationRepository.deleteById(id);
    }
}
