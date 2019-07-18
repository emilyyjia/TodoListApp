package tests;

import model.Items;
import model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TestItems {
    private Items testItems;

    @BeforeEach
    private void runBefore() {
        testItems = new Items();

        Calendar testDate = Calendar.getInstance();

        for (int i = 0; i < 10; i++) {
            String name = "todo" + i;
            Todo testTodo = new Todo(name, testDate);
            testItems.add(testTodo);
        }
    }

    @Test
    private void testContains() {
        assertTrue(testItems.contains("todo0"));
        assertTrue(testItems.contains("todo5"));
        assertTrue(testItems.contains("todo9"));
        assertFalse(testItems.contains("todo10"));
        assertFalse(testItems.contains("todo20"));
    }

    @Test
    private void testRemove() {
        assertTrue(testItems.contains("todo5"));
        testItems.remove("todo5");
        assertFalse(testItems.contains("todo5"));

        Items copy = new Items(testItems);

        testItems.remove("todo20");
        assertEquals(copy, testItems);
    }

    @Test
    private void testClear() {
        testItems.clearTodos();
        assertTrue(testItems.isEmpty());
    }
}
