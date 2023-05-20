package me.stefan.carmicroservice.server.dtos;

import lombok.Data;

@Data
public class ServiceDto implements MicroserviceDTO{
    private String name;
    private int employeeId;
    private String date;
    private String address;
}
