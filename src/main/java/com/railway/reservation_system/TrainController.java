package com.railway.reservation_system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/api/trains")
public class TrainController {
	@GetMapping("/{id}")
	public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
	    Train train = trainService.getTrainById(id);
	    return ResponseEntity.ok(train);
	}
    @Autowired private TrainService trainService;

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        Train savedTrain = trainService.addTrain(train);
        return ResponseEntity.ok(savedTrain);
    }

    @GetMapping
    public ResponseEntity<List<Train>> listAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }
 // In TrainController.java
 // GET /api/trains/search?from=HYB&to=SC
 @GetMapping("/search")
 public ResponseEntity<List<Train>> searchTrains(
         @RequestParam("fromCity") String fromCityName,
         @RequestParam("toCity") String toCityName) {

	 List<Train> trains = trainService.searchTrainsByCityName(fromCityName, toCityName);
     return ResponseEntity.ok(trains);
 }
 
}
