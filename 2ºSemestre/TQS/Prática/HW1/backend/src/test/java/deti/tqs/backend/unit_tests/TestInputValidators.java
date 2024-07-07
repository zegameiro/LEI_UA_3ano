package deti.tqs.backend.unit_tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import deti.tqs.backend.services.ReservationFormValidator;

public class TestInputValidators {

  @Test
  @DisplayName("Check if the email has a valid format it should return true")
  void whenValidEmail_ReturnTrue() {
    ReservationFormValidator validator = new ReservationFormValidator();
    assertThat(validator.validateEmail("adriana@gmail.com")).isTrue();
  }

  @Test
  @DisplayName("Check if the email has a invalid format it should return false")
  void whenInvalidEmail_ReturnFalse() {
    ReservationFormValidator validator = new ReservationFormValidator();
    assertThat(validator.validateEmail("adriana@gmail")).isFalse();
    assertThat(validator.validateEmail("adriana.pt")).isFalse();
  }

  @Test
  @DisplayName("Check if the phone has a valid size")
  void whenValidPhone_ReturnTrue() {
    ReservationFormValidator validator = new ReservationFormValidator();
    assertThat(validator.validatePhone("912345678")).isTrue();
  }

  @Test
  @DisplayName("Check if the phone has a invalid size")
  void whenInvalidPhone_ReturnFalse() {
    ReservationFormValidator validator = new ReservationFormValidator();
    assertThat(validator.validatePhone("91234567")).isFalse();
    assertThat(validator.validatePhone("9123456789")).isFalse();
  } 
}
