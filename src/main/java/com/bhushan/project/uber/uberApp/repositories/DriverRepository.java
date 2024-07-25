package com.bhushan.project.uber.uberApp.repositories;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bhushan.project.uber.uberApp.entities.Driver;

// ST_Distance (point 1, point 2)
// ST_DWithin (point1, 10000)

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

	@Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance "+
			"FROM drivers AS d "+
			"WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000)" +
			"ORDER BY distance "+
			"LIMIT 10", nativeQuery = true)
	List<Driver> findTenNearestDrivers(Point pickupLocation);

}
