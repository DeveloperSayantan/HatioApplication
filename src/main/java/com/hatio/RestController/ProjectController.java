package com.hatio.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hatio.Entity.ProjectEntity;
import com.hatio.Service.ProjectService;

import jakarta.validation.Valid;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PostMapping("/addProject")
	public ResponseEntity<String> addProject(@RequestBody ProjectEntity projectEntity){
		
		projectService.addProject(projectEntity);
		
		return new ResponseEntity<>("Project created",HttpStatus.OK);
	}
	
	@GetMapping("/viewProject")
	public List<ProjectEntity> fetchAllProject(){
		
		return projectService.fetchAllProject();
	}
	
	@GetMapping("/viewProject/{id}")
	public ProjectEntity viewProjectById(@PathVariable("id") int id) {
		
		return projectService.viewProjectById(id);
	}
	
	@PutMapping("/viewProject/{id}")
	public ResponseEntity<Object> updateProject(@PathVariable("id") int id, @RequestBody @Valid ProjectEntity updatedProject) {
	    ProjectEntity updateProject = projectService.updateProject(id, updatedProject);

	    if (updateProject != null) {
	        return ResponseEntity.ok(updateProject);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
