package customer;

import org.example.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(12);
        testCustomer.setFirstName("Mari");
        testCustomer.setLastName("Brown");
        testCustomer.setCustomerBalance(12.28);
    }

    @Test
    void getId() {
        assertNotNull(testCustomer);
        assertEquals(12, testCustomer.getId());
    }

    @Test
    void setId() {
        assertNotNull(testCustomer);
        testCustomer.setId(2);
        assertEquals(2, testCustomer.getId());
    }

    @Test
    void getFirstName() {
        assertNotNull(testCustomer);
        assertEquals("Mari", testCustomer.getFirstName());
    }

    @Test
    void setFirstName() {
        assertNotNull(testCustomer);
        testCustomer.setFirstName("Gideon");
        assertEquals("Gideon", testCustomer.getFirstName());
    }

    @Test
    void getLastName() {
        assertNotNull(testCustomer);
        assertEquals("Brown", testCustomer.getLastName());
    }

    @Test
    void setLastName() {
        assertNotNull(testCustomer);
        testCustomer.setLastName("Red");
        assertEquals("Red", testCustomer.getLastName());
    }

    @Test
    void getCustomerBalance() {
        assertNotNull(testCustomer);
        assertEquals(12.28, testCustomer.getCustomerBalance());
    }

    @Test
    void setCustomerBalance() {
        assertNotNull(testCustomer);
        testCustomer.setCustomerBalance(30.45);
        assertEquals(30.45, testCustomer.getCustomerBalance());
    }

    @Test
    void createNoArgsConstructor() {
        Customer testCustomer2 = new Customer();
        assertEquals(0, testCustomer2.getId());
        assertNull(testCustomer2.getFirstName());
        assertNull(testCustomer2.getLastName());
        assertEquals(0.0, testCustomer2.getCustomerBalance());
    }

    @Test
    void createAllArgsConstructor() {
        assertEquals(12, testCustomer.getId());
        assertEquals("Mari", testCustomer.getFirstName());
        assertEquals("Brown", testCustomer.getLastName());
        assertEquals(12.28, testCustomer.getCustomerBalance());
    }
}
