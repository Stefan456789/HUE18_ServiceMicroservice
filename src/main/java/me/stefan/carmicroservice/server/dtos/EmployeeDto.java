package me.stefan.carmicroservice.server.dtos;

import lombok.Data;

@Data
public class EmployeeDto implements MicroserviceDTO{
    private String name;
    private String longitude;
    private String latitude;
}
