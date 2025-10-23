package com.railway.reservation_system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Add this import
import org.springframework.data.repository.query.Param; // Add this import
import java.util.List; // Add this import

public interface TrainRepository extends JpaRepository<Train, Long> {
	@Query("SELECT s1.train FROM Schedule s1 JOIN Schedule s2 ON s1.train.id = s2.train.id " +
		       "JOIN Station station1 ON s1.station.id = station1.id " +
		       "JOIN Station station2 ON s2.station.id = station2.id " +
		       "WHERE LOWER(station1.stationName) LIKE LOWER(CONCAT('%', :sourceCityName, '%')) " +    // Use LIKE with % wildcard
		       "AND LOWER(station2.stationName) LIKE LOWER(CONCAT('%', :destinationCityName, '%')) " + // Use LIKE with % wildcard
		       "AND s1.stopNumber < s2.stopNumber")
		List<Train> findTrainsByCityNames(@Param("sourceCityName") String sourceCityName,
		                                  @Param("destinationCityName") String destinationCityName);
}