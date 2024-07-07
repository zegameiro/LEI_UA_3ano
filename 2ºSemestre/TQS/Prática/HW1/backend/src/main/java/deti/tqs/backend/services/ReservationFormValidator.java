package deti.tqs.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReservationFormValidator {
  
  private static final Logger logger = LoggerFactory.getLogger(ReservationFormValidator.class);

  public boolean validateEmail(String email) {
    logger.info("Validating email {}", email);
    return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$");
  }

  public boolean validatePhone(String phone) {
    logger.info("Validating phone {}", phone);
    return String.valueOf(phone).matches("^[0-9]{9}$") && phone.length() == 9;
  }
}
