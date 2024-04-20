package com.hatio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hatio.Entity.ProjectEntity;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Integer>{

}
