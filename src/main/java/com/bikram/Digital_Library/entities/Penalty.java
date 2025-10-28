package com.bikram.Digital_Library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Penalty {
	@Id
	@GeneratedValue
	private int penaltyId;
	private int noOfDaysDifference;
	private float amount;
	private String description;
}
