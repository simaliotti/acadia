package com.hb.acadia.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.user.Trainer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String uuid;
	
	@NotNull(message = "Aucun titre renseigné.")
	@NotEmpty(message = "Aucun titre renseigné.")
	private String title;
	
	@NotNull(message = "Aucune description renseignée.")
	@NotEmpty(message = "Aucune description renseignée.")
	private String description;
	
	@NotNull(message = "Aucune difficulté renseignée.")
	@NotEmpty(message = "Aucune difficulté renseignée.")
	private Level difficulty;
	
	@NotNull(message = "Aucune durée renseignée.")
	@NotEmpty(message = "Aucune durée renseignée.")
	private int duration;
	
	@NotNull(message = "Aucun prix renseigné.")
	@NotEmpty(message = "Aucun prix renseigné.")
	private double price;
	
	private boolean isActive;
	
	@NotNull(message = "Aucun formateur associé.")
	@NotEmpty(message = "Aucun formateur associé.")
	@ManyToOne(fetch = FetchType.EAGER)
	private Trainer trainer;
	
	@NotEmpty(message = "Aucune catégorie associé.")
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@NotNull(message = "Aucune vidéo associée.")
	@NotEmpty(message = "Aucune vidéo associée.")
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Video> videos;
	
	@OneToMany(mappedBy="training",fetch=FetchType.EAGER)
	private Set<Comment> comments;
	
	/**
	 * 
	 * @author anis
	 * 
	 * Level of training difficulty.
	 *
	 */
	public static enum Level {
		BEGINNER, INTERMEDIATE, CONFIRMED
	}
	
	public Training() {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * @param trainer
	 * @param category
	 * @param videos
	 * @param comments
	 * @param description
	 * @param difficulty
	 * @param duration
	 * @param price
	 * @param isActive
	 */
	public Training(@NotNull @NotEmpty String title, 
			@NotNull @NotEmpty Trainer trainer, 
			@NotNull @NotEmpty Category category,
			@NotNull @NotEmpty Set<Video> videos, 
			Set<Comment> comments, 
			@NotNull @NotEmpty String description, 
			@NotNull @NotEmpty Level difficulty,
			@NotNull @NotEmpty int duration, 
			@NotNull @NotEmpty double price, boolean isActive) {
		this.title = title;
		this.trainer = trainer;
		this.category = category;
		this.videos = videos;
		this.comments = comments;
		this.description = description;
		this.difficulty = difficulty;
		this.duration = duration;
		this.price = price;
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + duration;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Training other = (Training) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (difficulty != other.difficulty)
			return false;
		if (duration != other.duration)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
