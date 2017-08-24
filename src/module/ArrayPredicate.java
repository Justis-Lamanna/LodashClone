/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.List;

/**
 * A predicate used in array testing.
 * @author justislamanna
 * @param <T> The type in the list.
 */
public interface ArrayPredicate<T> {
    
    /**
     * Test a value.
     * @param value The value to test.
     * @param index The index of the value to test.
     * @param array The array that is being tested.
     * @return True if the test passes, false if it fails.
     */
    boolean test(T value, int index, List<T> array);
}
