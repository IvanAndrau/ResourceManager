package org.example.resourcemanagerwebtask.Services;

import org.example.resourcemanagerwebtask.Models.Resource;
import org.example.resourcemanagerwebtask.Models.Subscription;
import org.example.resourcemanagerwebtask.Models.User;
import org.example.resourcemanagerwebtask.Repositories.ResourceRepository;
import org.example.resourcemanagerwebtask.Repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, UserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }

    public User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(new User(), "User with ID " + userId + " not found."));
    }

    public Resource validateResource(Long resourceId, Long userId) {
        return resourceRepository.findById(resourceId)
                .filter(resource -> resource.getUser().getUserId().equals(userId))
                .orElseThrow(() -> new ObjectNotFoundException(new Resource(), "Resource not found or unauthorized."));
    }

    public Resource validateSubscription(Long resourceId, Long userId) {
        return resourceRepository.findById(resourceId)
                .filter(subscription -> subscription.getUser().getUserId().equals(userId))
                .orElseThrow(() -> new ObjectNotFoundException(new Subscription(), "Resource not found or unauthorized."));
    }

    //Subscription methods
    public List<Resource> getSubscriptionsByUserId(Long userId) {
        return resourceRepository.findAll().stream()
                .filter(subscription -> subscription.getUser().getUserId().equals(userId))
                .toList();
    }

    public void addSubscription(Subscription subscription, Long userId) {
        User user = validateUser(userId);
        subscription.setUser(user);
        //subscription.SetType("Subscription");
        resourceRepository.save(subscription);
    }

    public void updateSubscription(Long resourceId, Subscription updatedSubscription, Long userId) {
        Resource existingSubscription = validateSubscription(resourceId, userId);
        updatedSubscription.setId(existingSubscription.getId());
        updatedSubscription.setUser(existingSubscription.getUser());
        //subscription.SetType("Subscription");
        resourceRepository.save(updatedSubscription);
    }

    public void removeSubscriptionById(Long resourceId, Long userId) {
        Resource subscription = validateSubscription(resourceId, userId);
        resourceRepository.delete(subscription);
    }   //TODO: move to SubscriptionService useful only if we have separate tables for resources and subscriptions

    //Resource methods
    public List<Resource> getResourcesByUserId(Long userId) {
        return resourceRepository.findAll().stream()
                .filter(resource -> resource.getUser().getUserId().equals(userId))
                .toList();
    }

    public void addResource(Resource resource, Long userId) {
        User user = validateUser(userId);
        resource.setUser(user);
        //resource.setType("RESOURCE");
        resourceRepository.save(resource);
    }

    public void updateResource(Long resourceId, Resource updatedResource, Long userId) {
        Resource existingResource = validateResource(resourceId, userId);
        updatedResource.setId(existingResource.getId());
        updatedResource.setUser(existingResource.getUser());
        //updatedResource.setType("RESOURCE");
        resourceRepository.save(updatedResource);
    }

    public void removeResourceById(Long resourceId, Long userId) {
        Resource resource = validateResource(resourceId, userId);
        resourceRepository.delete(resource);
    }
}
//    public Resource getSubscriptionById(Long resourceId) {
//        return resourceRepository.findById(resourceId).orElse(null);
//    }
//
//    public Resource getResourceById(Long resourceId) {
//        return resourceRepository.findById(resourceId).orElse(null);
//    }