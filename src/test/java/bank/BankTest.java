package bank;

import org.example.entity.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank testBank;

    @BeforeEach
    void setUp() {
        testBank = new Bank();
        testBank.setId(1);
        testBank.setName("Clever Bank");
    }

    @Test
    void getId() {
        assertNotNull(testBank);
        assertEquals(1, testBank.getId());
    }

    @Test
    void setId() {
        assertNotNull(testBank);
        testBank.setId(3);
        assertEquals(3, testBank.getId());
    }

    @Test
    void getName() {
        assertNotNull(testBank);
        assertEquals("Clever Bank", testBank.getName());
    }

    @Test
    void setName() {
        assertNotNull(testBank);
        testBank.setName("Another Bank");
        assertEquals("Another Bank", testBank.getName());
    }

    @Test
    void createNoArgsConstructor() {
        Bank testBank2 = new Bank();
        assertEquals(0, testBank2.getId());
        assertNull(testBank2.getName());
    }

    @Test
    void createAllArgsConstructor() {
        assertEquals(1, testBank.getId());
        assertEquals("Clever Bank", testBank.getName());
    }
}
