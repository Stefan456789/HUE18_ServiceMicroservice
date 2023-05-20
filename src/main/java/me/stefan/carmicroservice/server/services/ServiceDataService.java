package me.stefan.carmicroservice.server.services;

import me.stefan.carmicroservice.server.resources.ServiceResource;
import me.stefan.carmicroservice.server.businesslogic.Service;
import me.stefan.carmicroservice.server.dtos.ServiceDto;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceDataService {

    private HashMap<Integer, Service> services = new HashMap<>();
    private int id = 0;
    public void createInitialServices() {
        Service s = new Service();
        s.setId(id);
        id++;
        s.setName("ServiceName");
        s.setDate("12.12.2020");
        s.setEmployeeId(1);
        s.setAddress("123;456");
        this.services.put(s.getId(), s);
    }

    public List<ServiceResource> getServiceResources() {
        return services.values().stream().map(this::convertServiceToServiceResource).collect(Collectors.toList());

    }

    private ServiceResource convertServiceToServiceResource(Service service) {
        ServiceResource serviceResource = new ServiceResource();
        serviceResource.setId(service.getId());
        serviceResource.setDate(service.getDate());
        serviceResource.setName(service.getName());
        serviceResource.setEmployeeId(service.getEmployeeId());
        serviceResource.setLatitude(service.getAddress().split(";")[0]);
        serviceResource.setLongitude(service.getAddress().split(";")[1]);
        return serviceResource;
    }

    public ServiceResource addService(ServiceDto serviceDto) {
        Service newService = new Service();
        newService.setId(id);
        id++;
        newService.setName(serviceDto.getName());
        newService.setEmployeeId(serviceDto.getEmployeeId());
        newService.setDate(serviceDto.getDate());
        newService.setAddress(serviceDto.getAddress());
        this.services.put(newService.getId(), newService);
        return convertServiceToServiceResource(newService);
    }

    public ServiceResource deleteService(int serviceId) {
        Service serviceToRemove = services.get(serviceId);
        this.services.remove(serviceId);
        return convertServiceToServiceResource(serviceToRemove);
    }

    public ServiceResource getServiceResourceById(int serviceId) {
        Service service = services.get(serviceId);
        return convertServiceToServiceResource(service);
    }

    public ServiceResource editServiceResourceById(int serviceId, ServiceDto serviceDto) {
        Service serviceToChange = services.get(serviceId);
        serviceToChange.setName(serviceDto.getName());
        serviceToChange.setEmployeeId(serviceDto.getEmployeeId());
        serviceToChange.setDate(serviceDto.getDate());
        serviceToChange.setAddress(serviceDto.getAddress());
        return convertServiceToServiceResource(serviceToChange);
    }

    public String getAddress(int serviceId) {
        Service service = services.get(serviceId);
        return service.getAddress();
    }
}
