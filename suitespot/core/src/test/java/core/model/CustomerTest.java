package core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  private Customer c1, c2;

  @BeforeEach
  public void setUp() {
    this.c1 = new Customer("Steve", "Jobs", "apple@gmail.com", "12345678");
    this.c2 = new Customer("Odd", "Hansen", "jall@jalla.com", "87654321");

  }

  @Test
  public void testGetLastName() {
    assertEquals("Jobs", c1.getLastName());
  }

  @Test
  public void testNameValidation() {
    assertThrows(IllegalArgumentException.class, () -> {
      c1.setFirstName("0lav"); // numbers are not permitted
    });

    assertThrows(IllegalArgumentException.class, () -> {
      c1.setFirstName("Gunn-Britt/"); // other symbols are not permitted
    });

    assertThrows(IllegalArgumentException.class, () -> {
      c2.setFirstName("Kong.Harald"); // other symbols are not permitted
    });
  }

  @Test
  public void setEmail() {
    c2.setEmail("duck@goose.com");
    assertNotEquals("jall@jalla.com", c2.getEmail(), "The e-mail did not change");

  }

  @Test
  public void testEmailValidation() {
    assertThrows(IllegalArgumentException.class, () -> {
      c2.setEmail("notValidEmail");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      c2.setEmail("@notvalidemail");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      c1.setEmail("@test@");
    });
  }

  @Test
  void testPhoneValidation() {
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      c1.setPhone("123456a8"); // letters are not valid
    });

    assertTrue(thrown.getMessage().length() > 0);

    assertThrows(IllegalArgumentException.class, () -> {
      c1.setPhone("12343"); // the length of the numbers is too short ( < 8)
    });

    assertThrows(IllegalArgumentException.class, () -> {
      c1.setPhone("123456789"); // the length of the numbers is too long ( > 8)
    });
  }
}