package deti.tqs.backend.controllers_tests;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import deti.tqs.backend.controllers.BusTripController;
import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.services.BusTripService;

@WebMvcTest(controllers = BusTripController.class)
class TestsBusTripController {
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private BusTripService busTripService;

  @BeforeEach
  void setUp() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setId(1);
    busTrip1.setFromCity("Porto");
    busTrip1.setToCity("Leiria");
    busTrip1.setDate("2021-05-01");
    busTrip1.setTime("08h00");
    busTrip1.setPrice(10.0);

    when(busTripService.getDates()).thenReturn(List.of("2021-05-01", "2021-05-02", "2021-05-03", "2021-05-04"));
    when(busTripService.getFromCities()).thenReturn(List.of("Porto", "Lisboa", "Faro", "Braga"));
    when(busTripService.getToCities()).thenReturn(List.of("Leiria", "Aveiro", "Guarda", "Coimbra"));
    when(busTripService.getBusTripById(1, "EUR")).thenReturn(busTrip1);
    when(busTripService.getBusTripById(0, "EUR")).thenReturn(null);
    when(busTripService.listFilteredTrips("Porto", "Leiria", "2021-05-01", "EUR")).thenReturn(List.of(busTrip1));
    
  }

  @Test
  @DisplayName("Test if all the dates are retrieved correctly")
  void testGetBusTripDates() throws Exception {
    mvc.perform(get("/api/bustrips/get_dates").content("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$", hasSize(4)))
      .andExpect(jsonPath("$[0]", is("2021-05-01")))
      .andExpect(jsonPath("$[1]", is("2021-05-02")))
      .andExpect(jsonPath("$[2]", is("2021-05-03")))
      .andExpect(jsonPath("$[3]", is("2021-05-04")));
  }

  @Test
  @DisplayName("Test if all the from cities are retrieved correctly")
  void testGetBusTripFromCities() throws Exception {
    mvc.perform(get("/api/bustrips/get_from_cities").content("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$", hasSize(4)))
      .andExpect(jsonPath("$[0]", is("Porto")))
      .andExpect(jsonPath("$[1]", is("Lisboa")))
      .andExpect(jsonPath("$[2]", is("Faro")))
      .andExpect(jsonPath("$[3]", is("Braga")));
  }

  @Test
  @DisplayName("Test if all the to cities are retrieved correctly")
  void testGetBusTripToCities() throws Exception {
    mvc.perform(get("/api/bustrips/get_to_cities").content("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$", hasSize(4)))
      .andExpect(jsonPath("$[0]", is("Leiria")))
      .andExpect(jsonPath("$[1]", is("Aveiro")))
      .andExpect(jsonPath("$[2]", is("Guarda")))
      .andExpect(jsonPath("$[3]", is("Coimbra")));
  }

  @Test
  @DisplayName("Test if given a bus trip id and a currency it then returns")
  void testGetBusTripById() throws Exception {
    mvc.perform(get("/api/bustrips/get_bus_trip?id=1&currency=EUR").content("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$.fromCity", is("Porto")))
      .andExpect(jsonPath("$.toCity", is("Leiria")))
      .andExpect(jsonPath("$.date", is("2021-05-01")))
      .andExpect(jsonPath("$.time", is("08h00")))
      .andExpect(jsonPath("$.price", is(10.0)));
  }

  @Test
  @DisplayName("Test if given a bus trip id that does not exist it returns null")
  void testGetBusTripByIdNotFound() throws Exception {
    mvc.perform(get("/api/bustrips/get_bus_trip?id=0&currency=EUR"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").doesNotExist());
  }

  @Test
  @DisplayName("Test endpoint filter trips with multiple parameters")
  void testFilterTrips() throws Exception {
    mvc.perform(get("/api/bustrips/get?fromCity=Porto&toCity=Leiria&date=2021-05-01&currency=EUR").content("application/json"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].fromCity", is("Porto")))
      .andExpect(jsonPath("$[0].toCity", is("Leiria")))
      .andExpect(jsonPath("$[0].date", is("2021-05-01")))
      .andExpect(jsonPath("$[0].time", is("08h00")))
      .andExpect(jsonPath("$[0].price", is(10.0)));
  }

}
