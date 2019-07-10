package tests;

import model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestTodo {
    private Todo testTodo;

    @BeforeEach
    void runBefore() {
        Calendar testDate = Calendar.getInstance();
        testDate.set(2019,9,3);
        testTodo = new Todo("test", testDate);
    }

    @Test
    void testIsOverDue() {
        assertFalse(testTodo.isOverdue());

        Calendar testDate = Calendar.getInstance();
        testDate.set(2018, 8,29);
        testTodo.setDueDate(testDate);

        assertTrue(testTodo.isOverdue());

    }

    @Test
    void testGetName() {
        assertEquals("test", testTodo.getName());
    }

}
