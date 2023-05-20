package me.stefan.carmicroservice.server;

import me.stefan.carmicroservice.server.services.EmployeeDataService;
import me.stefan.carmicroservice.server.services.ServiceDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public EmployeeDataService createDataServiceEmployee() {
        EmployeeDataService e = new EmployeeDataService();
        e.createInitialEmployees();
        return e;
    }

    @Bean
    public ServiceDataService createDataServiceService() {
        ServiceDataService s = new ServiceDataService();
        s.createInitialServices();
        return s;
    }
}
