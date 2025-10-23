package com.railway.reservation_system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/stations")
@CrossOrigin
public class StationController {

    @Autowired
    private StationService stationService;

    // Endpoint to ADD a new station
    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        Station savedStation = stationService.addStation(station);
        return ResponseEntity.ok(savedStation);
    }

    // Endpoint to GET all stations
    @GetMapping
    public ResponseEntity<List<Station>> listAllStations() {
        List<Station> stations = stationService.getAllStations();
        return ResponseEntity.ok(stations);
    }
}