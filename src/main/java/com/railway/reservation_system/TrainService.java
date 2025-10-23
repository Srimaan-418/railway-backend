package com.railway.reservation_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainService {
    @Autowired private TrainRepository trainRepository;

    public Train addTrain(Train train) { return trainRepository.save(train); }
    public List<Train> getAllTrains() { return trainRepository.findAll(); }
 // In TrainService.java
    public List<Train> searchTrainsByCityName(String sourceCityName, String destinationCityName) {
    	return trainRepository.findTrainsByCityNames(sourceCityName, destinationCityName);
    }
    public Train getTrainById(Long trainId) {
        return trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + trainId));
    }
}
