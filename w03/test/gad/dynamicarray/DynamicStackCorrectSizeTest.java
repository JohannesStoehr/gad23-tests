package gad.dynamicarray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicStackCorrectSizeTest {

    @Test
    @DisplayName("DynamicStackCorrectSize")
    void TestSizeAfterPush(){
        DynamicStack dynamicStack = new DynamicStack(3, 4, new StudentResult());
        dynamicStack.pushBack(10);
        assertEquals(1,dynamicStack.size());
        dynamicStack.pushBack(20);
        assertEquals(2,dynamicStack.size());
        dynamicStack.pushBack(30);
        assertEquals(3,dynamicStack.size());
        dynamicStack.pushBack(40);
        assertEquals(4,dynamicStack.size());
    }

    @Test
    @DisplayName("DynamicStackCorrectSize")
    void TestSizeAfterPop(){
        DynamicStack dynamicStack = new DynamicStack(3, 4, new StudentResult());
        dynamicStack.pushBack(10);
        dynamicStack.pushBack(20);
        dynamicStack.pushBack(30);
        dynamicStack.pushBack(40);
        dynamicStack.popBack();
        assertEquals(3,dynamicStack.size());
        dynamicStack.popBack();
        assertEquals(2,dynamicStack.size());
    }
}
