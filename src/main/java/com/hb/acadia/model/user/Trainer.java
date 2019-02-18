package com.hb.acadia.model.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity

public class Trainer extends User {

	// nombre d'année d'experience
	@NotNull
	private int experience;

	// pour le payement
	@NotNull
	private String idStripe;

	// diplôme
	@NotNull
	private String qualification;

	// auto-publish
	@NotNull
	private boolean isCertified;
	@OneToMany(mappedBy="trainer",fetch=FetchType.LAZY)
	private Set<Training> trainings;

	@Override
	public String toString() {
		return "Trainer [experience=" + experience + ", idStrip=" + idStripe + ", qualification=" + qualification
				+ ", isCertified=" + isCertified + "]";
	}

}
