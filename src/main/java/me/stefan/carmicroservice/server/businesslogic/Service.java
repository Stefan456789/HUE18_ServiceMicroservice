package me.stefan.carmicroservice.server.businesslogic;

import lombok.Data;

@Data
public class Service {
    private int id;
    private String name;
    private int employeeId;
    private String date;
    private String address;
}
