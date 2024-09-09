package com.bhushan.project.uber.uberApp.entities;

import java.util.List;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
		@Index(name = "idx_driver_vehicle_id", columnList = "vehicleId")
})
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private Double rating;
	
	private Boolean available;
	
	private String vehicleId;
	
	@Column(columnDefinition = "Geometry(Point,4326)")	// 4326 is for earth as we are pointing to earth here and lattitude & longitude to support location
	private Point currentLocation;
	
}
