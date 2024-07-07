package deti.tqs.backend.controllers_tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import deti.tqs.backend.controllers.BusController;
import deti.tqs.backend.models.Bus;
import deti.tqs.backend.services.BusService;

@WebMvcTest(controllers = BusController.class)
public class TestsBusController {
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private BusService busService;

  @BeforeEach
  void setUp() {
    Bus bus1 = new Bus();
    bus1.setId(1);
    bus1.setCapacity(30);
    bus1.setName("Flix Bus 1");

    Bus bus2 = new Bus();
    bus2.setId(2);
    bus2.setCapacity(40);
    bus2.setName("Flix Bus 2");

    Bus bus3 = new Bus();
    bus3.setId(3);
    bus3.setCapacity(50);
    bus3.setName("Flix Bus 3");

    Bus bus4 = new Bus();
    bus4.setId(4);
    bus4.setCapacity(60);
    bus4.setName("Flix Bus 4");

    when(busService.getAllBuses()).thenReturn(List.of(bus1, bus2, bus3, bus4));
    when(busService.getBusById(1)).thenReturn(bus1);
    when(busService.getBusById(2)).thenReturn(bus2);
    when(busService.getBusById(3)).thenReturn(bus3);
    when(busService.getBusById(4)).thenReturn(bus4);
  }

  @Test
  @DisplayName("Test endpoint get a list containing all the buses")
  void testGetAllBuses() throws Exception {
    mvc.perform(get("/api/bus/getAll").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$", hasSize(4)))
      .andExpect(jsonPath("$[0].name", is("Flix Bus 1")))
      .andExpect(jsonPath("$[1].name", is("Flix Bus 2")))
      .andExpect(jsonPath("$[2].name", is("Flix Bus 3")))
      .andExpect(jsonPath("$[3].name", is("Flix Bus 4")));
  }

  @Test
  @DisplayName("Test endpoint get a bus by id")
  void testGetBusById() throws Exception {
    mvc.perform(get("/api/bus/get?id=1").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.name", is("Flix Bus 1")));

    mvc.perform(get("/api/bus/get?id=2").contentType("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.name", is("Flix Bus 2")));
  }

  @Test
  @DisplayName("Test endpoint get a bus by id that does not exist")
  void testGetBusByIdNotFound() throws Exception {
    mvc.perform(get("/api/bus/get?id=5").contentType("application/json"))
      .andExpect(status().isNotFound());
  }
}
