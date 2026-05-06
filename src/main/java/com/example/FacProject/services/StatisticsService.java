package com.example.FacProject.services;

import com.example.FacProject.dto.CollectivityLocalStatisticsDTO;
import com.example.FacProject.repositories.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticsService {
    StatisticsRepository repository;
    public StatisticsService(StatisticsRepository repository) {
        this.repository = repository;
    }
    public List<CollectivityLocalStatisticsDTO> getCollectivityStatisticsById(String id, LocalDate from, LocalDate to){
        return repository.findCollectivityStatisticsById(id,from,to);
    }
}
