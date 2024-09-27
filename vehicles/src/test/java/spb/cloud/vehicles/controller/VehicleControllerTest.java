package spb.cloud.vehicles.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spb.cloud.vehicles.model.dto.VehicleDto;
import spb.cloud.vehicles.model.entity.Vehicle;
import spb.cloud.vehicles.model.mapper.VehicleMapper;
import spb.cloud.vehicles.service.VehicleService;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleMapper vehicleMapper;

    @Test
    void testGetAllVehicles() throws Exception {
        var vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setYear(2022);
        vehicle.setColor("Blue");

        when(vehicleService.getAllVehicles()).thenReturn(List.of(vehicle));

        mockMvc.perform(get("/v1/vehicle")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].make").value("Toyota"))
            .andExpect(jsonPath("$[0].model").value("Corolla"))
            .andExpect(jsonPath("$[0].year").value(2022))
            .andExpect(jsonPath("$[0].color").value("Blue"));
    }

    @Test
    void getVehicleByIdSuccessfully() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setYear(2022);
        vehicle.setColor("Blue");

        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        mockMvc.perform(get("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.make").value("Toyota"))
            .andExpect(jsonPath("$.model").value("Corolla"))
            .andExpect(jsonPath("$.year").value(2022))
            .andExpect(jsonPath("$.color").value("Blue"));
    }

    @Test
    void getVehicleByIdNotFound() throws Exception {
        when(vehicleService.getVehicleById(1L)).thenReturn(null);

        mockMvc.perform(get("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCreateVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Honda");
        vehicle.setModel("Civic");
        vehicle.setYear(2023);
        vehicle.setColor("Red");

        Vehicle savedVehicle = new Vehicle();
        savedVehicle.setId(1L);
        savedVehicle.setMake("Honda");
        savedVehicle.setModel("Civic");
        savedVehicle.setYear(2023);
        savedVehicle.setColor("Red");

        when(vehicleService.createVehicle(any(Vehicle.class))).thenReturn(savedVehicle);

        mockMvc.perform(post("/v1/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.make").value("Honda"))
            .andExpect(jsonPath("$.model").value("Civic"))
            .andExpect(jsonPath("$.year").value(2023))
            .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void updateVehicleNotFound() throws Exception {
        var vehicleDto = new VehicleDto("ford", "focus", 2021, "green");

        when(vehicleService.updateVehicle(any(Long.class), any(Vehicle.class))).thenReturn(null);
        when(vehicleMapper.toEntity(any(VehicleDto.class))).thenReturn(new Vehicle());

        mockMvc.perform(put("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDto)))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteVehicleSuccessfully() throws Exception {
        mockMvc.perform(delete("/v1/vehicle/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }


}