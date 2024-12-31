package org.example.resourcemanagerwebtask.Repositories;

import org.example.resourcemanagerwebtask.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {}