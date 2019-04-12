package com.hb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hb.model.Video;
import com.hb.service.VideoService;
import com.hb.utils.StreamHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author anis
 *
 */
@RestController
@Slf4j
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private StreamHelper streamHelper;

	/**
	 * @return every saved videos
	 */
	@GetMapping("/public/videos")
	public List<Video> listVideoRespBody() {
		return videoService.getAllVideos();
	}
	
	/**
	 * @param uuid
	 * @return the matching saved video
	 */
	@GetMapping("/public/videos/{uuid}")
	public Video byUuid(@PathVariable("uuid") String uuid) {
		return videoService.getByUuid(uuid);
	}
	
	/**
	 * @param file
	 * @return the result of the operation w/ or w/o errors
	 */
	@PutMapping("/api/videos")
	@PreAuthorize("hasRole('ROLE_TRAINER') and hasRole('ROLE_ADMIN')")
	public List<ObjectError> update(@RequestParam("uuid") String uuid, @RequestParam("video") MultipartFile file) {
		
		if (file.isEmpty() || uuid == null || uuid == "") {	
			return Collections.singletonList(
					new ObjectError("updateError", "Requête incorrecte.")); 
		}
		
		try {
			videoService.updateVideo(file);
		} catch (IOException e) {
			log.error("Erreur lors du téléversement du fichier. " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @param file
	 * @return the result of the operation w/ or w/o errors
	 */
	@PostMapping("/api/videos")
	@PreAuthorize("hasRole('ROLE_TRAINER') and hasRole('ROLE_ADMIN')")
	public List<ObjectError> create(@RequestParam("video") MultipartFile file) {
		
		if (file.isEmpty()) {
			return Collections.singletonList(
					new ObjectError("createError", "Fichier(s) vide(s)."));
		}
		
		try {
			videoService.createVideo(file);
		} catch (IOException e) {
			log.error("Erreur lors du téléversement du(des) fichier(s). "+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param uuid
	 * @return the result of the operation w/ or w/o errors
	 */
	@DeleteMapping("/api/videos")
	@PreAuthorize("hasRole('ROLE_TRAINER') and hasRole('ROLE_ADMIN')")
	public List<ObjectError> delete(@RequestParam("uuid") String uuid) {
		
		if (videoService.getByUuid(uuid) == null) {
			return Collections.singletonList(
					new ObjectError("deleteError", "File does not exists."));
		}
		
		videoService.deleteVideo(uuid);
		
		return null;
	}
	
	/**
	 * Download video method
	 * @param response
	 * @param uuid
	 */
	@GetMapping( "/api/download")
	@PreAuthorize("hasRole('ROLE_TRAINER') and hasRole('ROLE_ADMIN')")
	public void produceHome(HttpServletResponse response, @RequestParam(value = "uuid") String uuid) {
		
		Video video = videoService.getByUuid(uuid);
		File file = new File(video.getPath());
		streamHelper.addStreamHelperToResponse(file.getPath(), "video/mp4", response);
	}
}
