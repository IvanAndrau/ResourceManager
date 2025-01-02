package org.example.resourcemanagerwebtask.Controllers;

import org.example.resourcemanagerwebtask.Models.Resource;
import org.example.resourcemanagerwebtask.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<Resource> getResources(@RequestParam Long userId) {
        return resourceService.getResourcesByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<String> addResource(@RequestBody Resource resource, @RequestParam Long userId) {
        resourceService.addResource(resource, userId);
        return ResponseEntity.ok("Resource added successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateResource(@PathVariable Long id, @RequestBody Resource resource, @RequestParam Long userId) {
        resourceService.updateResource(id, resource, userId);
        return ResponseEntity.ok("Resource updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id, @RequestParam Long userId) {
        resourceService.removeResourceById(id, userId);
        return ResponseEntity.ok("Resource deleted successfully");
    }
}
