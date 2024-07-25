package com.bhushan.project.uber.uberApp.services.impl;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.services.DistanceService;

@Service
public class DistanceServiceOSRMImpl implements DistanceService{

	@Override
	public double calculateDistance(Point src, Point dest) {
		// call the third party api called OSRM to fetch the distance
		return 0;
	}

}
