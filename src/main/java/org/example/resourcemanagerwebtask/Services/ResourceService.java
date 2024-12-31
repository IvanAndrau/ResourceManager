package org.example.resourcemanagerwebtask.Services;

import org.example.resourcemanagerwebtask.Models.Resource;
import org.example.resourcemanagerwebtask.Repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> GetAll(Long userId) {
        return resourceRepository.findAll();
    }

    public Resource Add(Resource resource, Long userId) {
        return resourceRepository.save(resource);
    }

    /*
    *     public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    public boolean deleteBookById(int id) {
        return bookRepository.deleteById(id);
    }
    * */

/*
*     List<T> getResourcesByUserId(Long userId);

    void addResource(T resource, Long userId);

    T getResourceById(Long resourceId);

    void updateResource(Long resourceId, T updatedResource, Long userId);

    void removeResourceById(Long resourceId, Long userId);
* */

}
