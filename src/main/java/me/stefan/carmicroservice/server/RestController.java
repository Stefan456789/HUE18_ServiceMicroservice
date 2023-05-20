package me.stefan.carmicroservice.server;

import me.stefan.carmicroservice.server.dtos.EmployeeDto;
import me.stefan.carmicroservice.server.dtos.ServiceDto;
import me.stefan.carmicroservice.server.resources.EmployeeResource;
import me.stefan.carmicroservice.server.resources.ServiceResource;
import me.stefan.carmicroservice.server.services.EmployeeDataService;
import me.stefan.carmicroservice.server.services.ServiceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private EmployeeDataService employeeDataService = new EmployeeDataService();
    @Autowired
    private ServiceDataService serviceDataService = new ServiceDataService();

    @GetMapping("/serviceBackend/employees")
    public List<EmployeeResource> getAllEmployees() {
        return employeeDataService.getEmployeeResources();
    }

    @PostMapping("serviceBackend/employees")
    public EmployeeResource addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeDataService.addEmployee(employeeDto);
    }

    @GetMapping("serviceBackend/services")
    public List<ServiceResource> getAllServices() {
        return serviceDataService.getServiceResources();
    }

    @PostMapping("serviceBackend/services")
    public ServiceResource addService(@RequestBody ServiceDto serviceDto) {
        System.out.println("Test");
        return serviceDataService.addService(serviceDto);
    }

    @DeleteMapping("serviceBackend/services/{serviceId}")
    public ServiceResource deleteService(@PathVariable int serviceId) {
        return serviceDataService.deleteService(serviceId);
    }

    @GetMapping("serviceBackend/services/{serviceId}")
    public ServiceResource getServiceResourceById(@PathVariable int serviceId) {
        return serviceDataService.getServiceResourceById(serviceId);
    }

    @PutMapping("serviceBackend/services/{serviceId}")
    public ServiceResource editServiceResourceById(@PathVariable int serviceId, @RequestBody ServiceDto serviceDto) {
        return serviceDataService.editServiceResourceById(serviceId, serviceDto);
    }

    @GetMapping("serviceBackend/services/{serviceId}/address")
    public String getAddress(@PathVariable int serviceId) {
        return serviceDataService.getAddress(serviceId);
    }
}
