package com.bhushan.project.uber.uberApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Rating;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long>{

	List<Rating> findByRider(Rider rider);
	
	List<Rating> findByDriver(Driver Driver);

	Optional<Rating> findByRide(Ride ride);
	
}
