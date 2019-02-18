package com.hb.acadia.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.Category;
import com.hb.acadia.model.Video;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Training {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String uuid;
	@NotNull
	private String description;
	@NotNull
	private String difficulty;
	@NotNull
	private String duration;
	@NotNull
	private String price;
	@ManyToOne(fetch=FetchType.EAGER)
	private Trainer trainer;
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Video> videos;
	
	private boolean isActive;

	@Override
	public String toString() {
		return "Training [id=" + id + ", uuid=" + uuid + ", description=" + description + ", difficulty=" + difficulty
				+ ", duration=" + duration + ", price=" + price + ", isActive=" + isActive + "]";
	}
	
	
	

	
}
