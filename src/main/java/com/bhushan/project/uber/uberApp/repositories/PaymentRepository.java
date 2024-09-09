 package com.bhushan.project.uber.uberApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.project.uber.uberApp.entities.Payment;
import com.bhushan.project.uber.uberApp.entities.Ride;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Optional<Payment> findByRide(Ride ride);

}
