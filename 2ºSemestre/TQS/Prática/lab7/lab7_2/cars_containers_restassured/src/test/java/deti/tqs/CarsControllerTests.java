package deti.tqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import deti.tqs.Controller.CarController;
import deti.tqs.Models.Car;
import deti.tqs.Services.CarManagerService;

@WebMvcTest(CarController.class)
public class CarsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() throws Exception {}

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car car1 = new Car("Tesla","X");

        when(carManagerService.save(car1)).thenReturn(car1);

        mvc.perform(post("/api/addCar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car1)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is(car1.getMaker())))
            .andExpect(jsonPath("$.model", is(car1.getModel())));

        verify(carManagerService, times(1)).save(Mockito.any());
    }

    @Test
    public void whenGetCarByID_thenReturnCar() throws Exception {
        Car car1 = new Car("Tesla","X");

        when(carManagerService.getCarDetails(Mockito.anyLong())).thenReturn(java.util.Optional.of(car1));

        mvc.perform(get("/api/car/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car1)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(car1.getMaker())))
            .andExpect(jsonPath("$.model", is(car1.getModel())));

        verify(carManagerService, times(1)).getCarDetails(Mockito.anyLong());
    }

    @Test
    public void whenGetAllCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("Tesla","X");
        Car car2 = new Car("Ford","Focus");
        Car car3 = new Car("Toyota","Yaris");
        Car car4 = new Car("Renault","Clio");

        when(carManagerService.getAllCars()).thenReturn(java.util.List.of(car1, car2, car3, car4));

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car1.getModel())))
            .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
            .andExpect(jsonPath("$[1].model", is(car2.getModel())))
            .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
            .andExpect(jsonPath("$[2].model", is(car3.getModel())))
            .andExpect(jsonPath("$[3].maker", is(car4.getMaker())))
            .andExpect(jsonPath("$[3].model", is(car4.getModel())));

        verify(carManagerService, times(1)).getAllCars();
    }
}