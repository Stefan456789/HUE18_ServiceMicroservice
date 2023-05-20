package me.stefan.carmicroservice.client;

import com.google.gson.Gson;
import me.stefan.carmicroservice.server.dtos.EmployeeDto;
import me.stefan.carmicroservice.server.dtos.MicroserviceDTO;
import me.stefan.carmicroservice.server.dtos.ServiceDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("""
                          (1) Employees anzeigen
                          (2) Employee hinzufügen
                          (3) Services anzeigen
                          (4) Service hinzufügen
                          (5) Service löchen
                          (6) Bestimmten Service anzeigen
                          (7) Service bearbeiten
                          (8) Addresse eines Service anzeigen
                          (9) Exit
                        """);
                int userInput = Integer.parseInt(scanner.nextLine());

                String prompt = "http://localhost:8080/serviceBackend";
                String method = "GET";
                MicroserviceDTO dto = null;

                switch (userInput) {
                    case 1 -> {
                        prompt += "/employees";
                    }
                    case 2 -> {
                        method = "POST";
                        prompt += "/employees";
                        var emp = new EmployeeDto();
                        System.out.println("Name?");
                        emp.setName(scanner.nextLine());
                        System.out.println("Latitude?");
                        emp.setLatitude(scanner.nextLine());
                        System.out.println("Longitude?");
                        emp.setLongitude(scanner.nextLine());
                        dto = emp;

                    }
                    case 3 -> {
                        prompt += "/services";
                    }
                    case 4 -> {
                        System.out.println("Service hinzufügen");
                        prompt += "/services";
                        method = "POST";
                        var serviceDto = new ServiceDto();
                        System.out.println("Name?");
                        serviceDto.setName(scanner.nextLine());
                        System.out.println("Addresse? (longitude;latidude)");
                        serviceDto.setAddress(scanner.nextLine());
                        System.out.println("Date?");
                        serviceDto.setDate(scanner.nextLine());
                        System.out.println("EmployeeId?");
                        serviceDto.setEmployeeId(Integer.parseInt(scanner.nextLine()));
                        dto = serviceDto;
                    }
                    case 5 -> {
                        System.out.println("Welchen Service löschen?");
                        prompt += "/services/" + scanner.nextLine();
                        method = "DELETE";
                    }
                    case 6 -> {
                        System.out.println("Welchen Service anzeigen?");
                        prompt += "/services/" + scanner.nextLine();
                    }
                    case 7 -> {
                        System.out.println("Welchen Service bearbeiten?");
                        prompt += "/services/" + scanner.nextLine();
                        method = "PUT";
                        var serviceDto = new ServiceDto();
                        System.out.println("Name?");
                        serviceDto.setName(scanner.nextLine());
                        System.out.println("Addresse? (longitude;latidude)");
                        serviceDto.setAddress(scanner.nextLine());
                        System.out.println("Date?");
                        serviceDto.setDate(scanner.nextLine());
                        System.out.println("EmployeeId?");
                        serviceDto.setEmployeeId(Integer.parseInt(scanner.nextLine()));
                        dto = serviceDto;
                    }
                    case 8 -> {
                        System.out.println("Welchen Addresse anzeigen?");
                        prompt += "/services/" + scanner.nextLine() + "/address";
                    }
                    case 9 -> {
                        System.out.println("Exit");
                        break;
                    }

                }
                URL url = null;
                try {
                    url = new URL(prompt);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(method);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    if (dto != null) {
                        byte[] data = new Gson().toJson(dto).getBytes();
                        con.setFixedLengthStreamingMode(data.length);
                        con.getOutputStream().write(data);
                        con.getOutputStream().flush();
                        con.getOutputStream().close();
                    } else {
                        con.connect();
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder output = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null)
                            output.append(line);
                        in.close();
                        System.out.println(output);
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Bitte eine Zahl eingeben");
            }
        }
    }
}
