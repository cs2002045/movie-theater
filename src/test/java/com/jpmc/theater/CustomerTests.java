package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomerTests {

    @Test
    void testEquality() {
        Customer a = new Customer("John Doe", "1");
        Customer b = new Customer("John Doe", "1");
        assertTrue(a.equals(b) && b.equals(a));
        assertTrue(a.hashCode() == b.hashCode());
    }

    @Test
    void testEquality_negative() {
        Customer a = new Customer("John Doe", "1");
        Object b = new Object();
        assertFalse(a.equals(b) && b.equals(a));
        assertFalse(a.hashCode() == b.hashCode());
    }

    @Test
    void testToString() {
        Customer customer = new Customer("John Doe", "1");
        String expected = "name: John Doe";
        assertEquals(expected, customer.toString());
    }

    @Test
    void testSameObject() {
        Customer a = new Customer("John Doe", "1");
        Customer b = a;
        assertTrue(a.equals(b) && b.equals(a));
        assertTrue(a.hashCode() == b.hashCode());
    }
    
}
