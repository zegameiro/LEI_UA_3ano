package pt.deti.ua;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class AppTest {

    @SuppressWarnings("rawtypes")
    TqsStack stack;

    @BeforeEach
    public void setup() {
        stack = new TqsStack<>();
    }

    @DisplayName("A stack is empty on construction")
    @Test
    public void checkEmptyness() {
        Assertions.assertTrue(stack.isEmpty());
    }

    @DisplayName("A stack has size zero on construction")
    @Test
    public void checkSize() {
        Assertions.assertTrue(stack.size() == 0);
    }

    @SuppressWarnings("unchecked")
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void checkPushElement() {
        int n = 2;
        stack.push(1);
        stack.push(3);
        
        Assertions.assertAll(
            () -> assertFalse(stack.isEmpty()),
            () -> assertEquals(n, stack.size())
        );      
    }

    @SuppressWarnings("unchecked")
    @DisplayName("If one pushes x then pops, the value popped is x.")
    @Test
    public void checkPushPop() {
        stack.push(2);
        stack.push("a");
        Assertions.assertEquals("a", stack.pop());
    }

    @SuppressWarnings("unchecked")
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void checkPushPeek() {
        stack.push("x");
        Assertions.assertAll(
            () -> assertEquals(1, stack.size()),
            () -> assertEquals("x", stack.peek()),
            () -> assertEquals(1, stack.size())
        );
    }

    @SuppressWarnings("unchecked")
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")   
    @Test
    public void checkSizePop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assertions.assertAll(
            () -> assertEquals(3, stack.size()),
            () -> assertEquals(3, stack.pop()),
            () -> assertEquals(2, stack.pop()),
            () -> assertEquals(1, stack.pop()),
            () -> assertEquals(0, stack.size()),
            () -> assertTrue(stack.isEmpty())
        );

    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void checkPopEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void checkPeekEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    public void checkPushBoundStack() {
        Stack<Integer> bStack = new BoundedStack<>(2);
        bStack.push(1);
        bStack.push(2);
        Assertions.assertThrows(IllegalStateException.class, () -> bStack.push(3));
    }
}
