package deti.tqs.backend.services_tests;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.repositories.BusTripRepository;
import deti.tqs.backend.services.BusTripService;
import deti.tqs.backend.services.CurrencyExchangeService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestsBustripService {
  
  @Mock
  private BusTripRepository busTripRepository;

  @Mock
  private CurrencyExchangeService currencyExchangeService;

  @InjectMocks
  private BusTripService busTripService;

  @Test
  @DisplayName("Check get all existing bus trips")
  void checkGetAllBusTrips() {
    BusTrip busTrip1 = new BusTrip();
    BusTrip busTrip2 = new BusTrip();
    BusTrip busTrip3 = new BusTrip();

    when(busTripRepository.findAll()).thenReturn(List.of(busTrip1, busTrip2, busTrip3));
    
    assertThat(busTripService.listTrips()).isEqualTo(List.of(busTrip1, busTrip2, busTrip3)).hasSize(3);

    verify(busTripRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Check get all dates of all bus trips")
  void checkGetAllDates() {

    when(busTripRepository.findDates()).thenReturn(List.of("2011-12-23", "2000-11-12", "2021-06-03"));
    
    assertThat(busTripService.getDates()).isEqualTo(List.of("2011-12-23", "2000-11-12", "2021-06-03")).hasSize(3);

    verify(busTripRepository, times(1)).findDates();
  }

  @Test
  @DisplayName("Check get all from cities of all bus trips")
  void checkGetAllFromCities() {

    when(busTripRepository.findFromCities()).thenReturn(List.of("Lisboa", "Pombal", "Leiria", "Aveiro"));
    
    assertThat(busTripService.getFromCities()).isEqualTo(List.of("Lisboa", "Pombal", "Leiria", "Aveiro")).hasSize(4);

    verify(busTripRepository, times(1)).findFromCities();
  }

  @Test
  @DisplayName("Check get all to cities of all bus trips")
  void checkGetAllToCities() {

    when(busTripRepository.findToCities()).thenReturn(List.of("Lisboa", "Pombal", "Leiria", "Aveiro", "Porto", "Coimbra"));
    
    assertThat(busTripService.getToCities()).isEqualTo(List.of("Lisboa", "Pombal", "Leiria", "Aveiro", "Porto", "Coimbra")).hasSize(6);

    verify(busTripRepository, times(1)).findToCities();
  }

  @Test
  @DisplayName("Check filtered trips when currency is null")
  void checkListFilteredTrips_WhenCurrencyIsNull() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Lisboa");
    busTrip1.setToCity("Porto");
    busTrip1.setDate("2021-06-03");
    busTrip1.setPrice(12.39);

    when(busTripRepository.findByFromCityAndToCityAndDate("Lisboa", "Porto", "2021-06-03")).thenReturn(List.of(busTrip1));
    
    assertThat(busTripService.listFilteredTrips("Lisboa", "Porto", "2021-06-03", null).get(0).getPrice()).isEqualTo(12.39);

    verify(busTripRepository, times(1)).findByFromCityAndToCityAndDate(anyString(), anyString(), anyString());
  }

  @Test
  @DisplayName("Check filtered trips when given all the parameters")
  void checkListFilteredTrips_WhenGivenAllParameters() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Leiria");
    busTrip1.setToCity("Batalha");
    busTrip1.setDate("2005-10-30");
    busTrip1.setPrice(12.39);

    when(busTripRepository.findByFromCityAndToCityAndDate("Lisboa", "Porto", "2021-06-03")).thenReturn(List.of());
    when(busTripRepository.findByFromCityAndToCityAndDate("Leiria", "Batalha", "2005-10-30")).thenReturn(List.of(busTrip1));
    
    assertThat(busTripService.listFilteredTrips("Lisboa", "Porto", "2021-06-03", "USD")).isEmpty();
    assertThat(busTripService.listFilteredTrips("Leiria", "Batalha", "2005-10-30", "EUR")).contains(busTrip1);

    verify(busTripRepository, times(2)).findByFromCityAndToCityAndDate(anyString(), anyString(), anyString());
  }

  @Test
  @DisplayName("Check get bus trip by id with currency")
  void checkGetBusById_WitCurrency() throws Exception {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Coimbra");
    busTrip1.setToCity("Santarém");
    busTrip1.setDate("2003-07.03");
    busTrip1.setPrice(30.4);

    when(busTripRepository.findById(1)).thenReturn(busTrip1);
    when(currencyExchangeService.exchange("EUR", "USD")).thenReturn(3.6);
    
    assertThat(busTripService.getBusTripById(1, "USD").getPrice()).isEqualTo(109.44);

    verify(busTripRepository, times(1)).findById(anyInt());
    verify(currencyExchangeService, times(1)).exchange(anyString(), anyString());
  }

  @Test
  @DisplayName("Check method trip exists")
  void checkTripExists() {

    when(busTripRepository.existsById(1)).thenReturn(true);
    
    assertThat(busTripService.tripExists(1)).isTrue();

    verify(busTripRepository, times(1)).existsById(anyInt());
  }

  @Test
  @DisplayName("Check get bus trip by id without currency")
  void checkGetBusById_WithoutCurrency() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Coimbra");
    busTrip1.setToCity("Santarém");
    busTrip1.setDate("2003-07.03");
    busTrip1.setPrice(30.4);

    when(busTripRepository.findById(1)).thenReturn(busTrip1);
    
    assertThat(busTripService.getBusTripById(1, null).getPrice()).isEqualTo(30.4);

    verify(busTripRepository, times(1)).findById(anyInt());
  }

  @Test
  @DisplayName("Check filter trips when there are no trips")
  void checkListFilteredTrips_WhenNoTrips() {
    when(busTripRepository.findByFromCityAndToCityAndDate("Lisboa", "Porto", "2021-06-03")).thenReturn(List.of());
    
    assertThat(busTripService.listFilteredTrips("Lisboa", "Porto", "2021-06-03", "USD")).isEmpty();

    verify(busTripRepository, times(1)).findByFromCityAndToCityAndDate(anyString(), anyString(), anyString());
  }
}
