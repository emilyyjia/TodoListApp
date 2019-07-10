package tests;

import model.Todo;
import model.Todos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TestTodos {
    private Todos testTodos;

    @BeforeEach
    private void runBefore() {
        testTodos = new Todos();

        Calendar testDate = Calendar.getInstance();

        for (int i = 0; i < 10; i++) {
            String name = "todo" + i;
            Todo testTodo = new Todo(name, testDate);
            testTodos.add(testTodo);
        }
    }

    @Test
    private void testContains() {
        assertTrue(testTodos.contains("todo0"));
        assertTrue(testTodos.contains("todo5"));
        assertTrue(testTodos.contains("todo9"));
        assertFalse(testTodos.contains("todo10"));
        assertFalse(testTodos.contains("todo20"));
    }

    @Test
    private void testRemove() {
        assertTrue(testTodos.contains("todo5"));
        testTodos.remove("todo5");
        assertFalse(testTodos.contains("todo5"));

        Todos copy = new Todos(testTodos);

        testTodos.remove("todo20");
        assertEquals(copy,testTodos);
    }

    @Test
    private void testClear() {
        testTodos.clearTodos();
        assertTrue(testTodos.isEmpty());
    }
}
