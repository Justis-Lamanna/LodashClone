/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author justislamanna
 */
public class LodashClone {
    private LodashClone(){
		throw new IllegalStateException("Cannot instantiate Lodash");
    }
    
    public static void main(String[] args){
        List<Integer> testList = Arrays.asList(0, 0, 2, 4, 6, 7, 8, 0);
        List<Integer> testList2 = Arrays.asList(3, 5, 7, 9);
        BiPredicate<Integer, Integer> testFunction = (i, j) -> i%2 == j%2;
        System.out.println(dropWhile(testList, LodashUtil.FALSEY_INTEGER));
    }
    
    /**
     * Creates a list of lists, with each inner list of size length.
     * If the list cannot be evenly split, the last list of list contains the
     * remaining elements.
     * @param <T> The type of list.
     * @param array The array to process.
     * @param size The length of each inner list.
     * @return The chunked list of lists.
     * @throws IllegalArgumentException Size is non-positive.
     * @throws NullPointerException The supplied array is null.
     */
    public static <T> List<List<T>> chunk(List<T> array, int size)
    {
        Objects.requireNonNull(array);
        if(size <= 0){
            throw new IllegalArgumentException("Size must be positive");
        }
        ArrayList<List<T>> returnList = new ArrayList<>();
        for(int idx = 0; idx < array.size(); idx+=size){
            int endIndex = idx + size;
            if(endIndex > array.size()){
                endIndex = array.size();
            }
            returnList.add(array.subList(idx, endIndex));
        }
        return returnList;
    }
    
    /**
     * Creates a list of list, each with a length of one.
     * @param <T> The type of list.
     * @param array The array to process.
     * @return The chunked list of lists.
     * @throws IllegalArgumentException Size is non-positive.
     * @throws NullPointerException The supplied array is null.
     */
    public static <T> List<List<T>> chunk(List<T> array){
        return chunk(array, 1);
    }
    
    /**
     * Filters an list, returning only what the test returns as true.
     * @param <T> The type of list.
     * @param array The array to filter.
     * @param predicate The test to determine if the element should remain.
     * @return The list, with only elements that pass the predicate.
     */
    static <T> List<T> iFilter(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        List<T> filtered = new ArrayList<>();
        for(T element : array){
            if(predicate.test(element)){
                filtered.add(element);
            }
        }
        return filtered;
    }
    
    /**
     * Tests if a value is invalid.
     * @param <T> The type in the list.
     * @param value The value to check.
     * @param invalid The invalid values.
     * @return True if invalid, false if not.
     */
    static <T> boolean iIsInvalidValue(T value, List<T> invalid){
        Objects.requireNonNull(value);
        Objects.requireNonNull(invalid);
        return invalid.contains(value);
    }
    
    /**
     * Tests if a value is invalid.
     * @param <T> The type in the list.
     * @param value The value to check.
     * @param invalid The invalid values.
     * @return True if invalid, false if not.
     */
    static <T> boolean iIsInvalidValue(T value, T... invalid){
        Objects.requireNonNull(value);
        List<T> invalidList = Arrays.asList(Objects.requireNonNull(invalid));
        return invalidList.contains(value);
    }
    
    /**
     * Creates a list with all invalid values removed.
     * @param <T> The type contained in the list.
     * @param array The array to compact.
     * @param invalid A list of invalid values
     * @return The new array, with invalid values removed.
     * @throws NullPointerException Supplied array is null.
     */
    public static <T> List<T> compact(List<T> array, List<T> invalid){
        return iFilter(array, el -> !iIsInvalidValue(el, invalid));
    }
    
    /**
     * Creates a list with all invalid values removed.
     * @param <T> The type contained in the list.
     * @param array The array to compact.
     * @param invalid A list of invalid values.
     * @return The new array, with invalid values removed.
     * @throws NullPointerException Supplied array is null.
     */
    public static <T> List<T> compact(List<T> array, T... invalid){
        return iFilter(array, el -> !iIsInvalidValue(el, invalid));
    }
    
    /**
     * Creates a list by concatenating two source lists.
     * @param <T> The type of each list.
     * @param array The beginning list.
     * @param values The end list.
     * @return The concatenated list.
     * @throws NullPointerException Either list is null.
     */
    public static <T> List<T> concat(List<T> array, List<T> values){
        List<T> arrayCopy = new ArrayList<>(array);
        arrayCopy.addAll(values);
        return arrayCopy;
    }
    
    /**
     * Creates a list by concatenating a source list and a list of additional values.
     * @param <T> The type of each list.
     * @param array The beginning list.
     * @param values Additional values to append to the list.
     * @return The concatenated list.
     * @throws NullPointerException Either list is null.
     */
    public static <T> List<T> concat(List<T> array, T... values){
        return concat(array, Arrays.asList(values));
    }
    
    /**
     * Creates a list of values in array that are not in values.
     * @param <T> The type of each list.
     * @param array The beginning list.
     * @param values The list of lists of values to remove from array.
     * @return A copy of array, with values removed.
     * @throws NullPointerException Either list is null, or values contains null.
     */
    public static <T> List<T> difference(List<T> array, List<List<T>> values){
        Objects.requireNonNull(values);
        for(List<T> list : values){
            Objects.requireNonNull(list);
        }
        return iFilter(array, el -> {
            for(List<T> sublist : values){
                if(sublist.contains(el)){
                    return false;
                }
            }
            return true;
        });
    }
    
    /**
     * Creates a list of values in array that are not in values.
     * @param <T> The type of each list.
     * @param array The beginning list.
     * @param values Lists of values that should be removed from array.
     * @return A copy of array, with values removed.
     * @throws NullPointerException Either list is null, or values contains null.
     */
    public static <T> List<T> difference(List<T> array, List<T>... values){
        Objects.requireNonNull(values);
        return difference(array, Arrays.asList(values));
    }
    
    /**
     * Maps a list in some way.
     * @param <T> The type of the values in the input list.
     * @param <R> The type of the values in the output list.
     * @param array The array to map.
     * @param iteratee The mapping function.
     * @return The mapped list.
     * @throws NullPointerException Supplied array or iteratee is null.
     */
    private static <T, R> List<R> iMap(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        ArrayList<R> copy = new ArrayList<>();
        for(T value : array){
            copy.add(iteratee.apply(value));
        }
        return copy;
    }
    
    /**
     * The identity function.
     * This function simply returns itself.
     * @param <T> The input and output types.
     * @return An identity function.
     */
    private static <T> Function<T, T> iIdentity(){
        return t -> t;
    }
    
    /**
     * Returns the array, with specified values removed, after going through an iterator.
     * @param <T> The type contained in the lists.
     * @param <R> The type to be mapped into during comparison.
     * @param array The initial array.
     * @param iteratee The iteratee function, which dictates the comparison.
     * @param values A list of lists of values to exclude.
     * @return The list, with values removed.
     * @throws NullPointerException The array, iteratee, values, or any sublist is null.
     */
    public static <T, R> List<T> differenceBy(List<T> array, Function<T, R> iteratee, List<List<T>> values){
        Objects.requireNonNull(values);
        values.forEach(Objects::requireNonNull);
        Objects.requireNonNull(iteratee);
        return iFilter(array, el -> {
            for(List<T> list : values){
                for(T value : list){
                    R elMapped = iteratee.apply(el);
                    R valueMapped = iteratee.apply(value);
                    if(Objects.equals(elMapped, valueMapped)){
                        return false;
                    }
                }
            }
            return true;
        });
    }
    
    /**
     * Returns the array, with specified values removed, after going through an iterator.
     * @param <T> The type contained in the lists.
     * @param <R> The type to be mapped into during comparison.
     * @param array The initial array.
     * @param iteratee The iteratee function, which dictates the comparison.
     * @param values A list of lists of values to exclude.
     * @return The list, with values removed.
     * @throws NullPointerException The array, iteratee, values, or any sublist is null.
     */
    public static <T, R> List<T> differenceBy(List<T> array, Function<T, R> iteratee, List<T>... values){
        Objects.requireNonNull(values);
        return differenceBy(array, iteratee, Arrays.asList(values));
    }
    
    /**
     * Returns the array, with specified values removed, depending on a BiPredicate.
     * @param <T> The type in the first list.
     * @param <R> The type in the list of lists.
     * @param array The inital array.
     * @param bipred The BiPredicate to use to make comparisons.
     * @param values The values to remove from the first array.
     * @return The first list, with values removed.
     * @throws NullPointerException array, bipred, values, or any sublist of values is null.
     */
    public static <T, R> List<T> differenceWith(List<T> array, BiPredicate<T, R> bipred, List<List<R>> values){
        Objects.requireNonNull(bipred);
        Objects.requireNonNull(values);
        values.forEach(Objects::requireNonNull);
        return iFilter(array, el -> {
            for(List<R> list : values){
                for(R value : list){
                    if(bipred.test(el, value)){
                        return false;
                    }
                }
            }
            return true;
        });
    }
    
    /**
     * Returns the array, with specified values removed, depending on a BiPredicate.
     * @param <T> The type in the first list.
     * @param <R> The type in the list of lists.
     * @param array The inital array.
     * @param bipred The BiPredicate to use to make comparisons.
     * @param values The values to remove from the first array.
     * @return The first list, with values removed.
     * @throws NullPointerException array, bipred, values, or any sublist of values is null.
     */
    public static <T, R> List<T> differenceWith(List<T> array, BiPredicate<T, R> bipred, List<R>... values){
        Objects.requireNonNull(values);
        return differenceWith(array, bipred, Arrays.asList(values));
    }
    
    /**
     * Creates a BiPredicate from a comparator.
     * @param <T> The type the comparator compares with.
     * @param comparator The comparator to convert.
     * @return A BiPredicate which works as the comparator did.
     */
    static <T> BiPredicate<T, T> iBiPredicateFromComparator(Comparator<T> comparator){
        return (T t, T u) -> comparator.compare(t, u) == 0;
    }
    
    /**
     * Returns the array, with specified values removed, depending on a BiPredicate.
     * @param <T> The type in the first list.
     * @param array The inital array.
     * @param comparator The Comparator to use to make comparisons.
     * @param values The values to remove from the first array.
     * @return The first list, with values removed.
     * @throws NullPointerException array, comparator, values, or any sublist of values is null.
     */
    public static <T> List<T> differenceWith(List<T> array, Comparator<T> comparator, List<List<T>> values){
        Objects.requireNonNull(comparator);
        return differenceWith(array, iBiPredicateFromComparator(comparator), values);
    }
    
    /**
     * Returns the array, with specified values removed, depending on a BiPredicate.
     * @param <T> The type in the first list.
     * @param array The inital array.
     * @param comparator The Comparator to use to make comparisons.
     * @param values The values to remove from the first array.
     * @return The first list, with values removed.
     * @throws NullPointerException array, comparator, values, or any sublist of values is null.
     */
    public static <T> List<T> differenceWith(List<T> array, Comparator<T> comparator, List<T>... values){
        Objects.requireNonNull(values);
        return differenceWith(array, iBiPredicateFromComparator(comparator), Arrays.asList(values));
    }
    
    /**
     * Creates an empty list.
     * @param <T> The type of the empty list.
     * @return An empty list.
     */
    static <T> List<T> iEmptyList(){
        return new ArrayList<>();
    }
    
    /**
     * Drops the first n elements from an array.
     * If n is greater than the list size, an empty list is returned.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @param n The number of elements to remove from the beginning.
     * @return The list, with the elements removed.
     * @throws IllegalArgumentException n is less than zero.
     * @throws NullPointerException The list is null.
     */
    public static <T> List<T> drop(List<T> array, int n){
        if(n < 0){
            throw new IllegalArgumentException("n must be non-negative");
        }
        Objects.requireNonNull(array);
        if(n >= array.size()){
            return iEmptyList();
        }
        return array.subList(n, array.size());
    }
    
    /**
     * Drops the first element from an array.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @return The list, with the first element removed.
     * @throws NullPointerException The list is null.
     */
    public static <T> List<T> drop(List<T> array){
        return drop(array, 1);
    }
    
    /**
     * Drops the last n elements from an array.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @param n The number of elements to remove from the end.
     * @return The list, with elements removed.
     * @throws IllegalArgumentException n is less than zero.
     * @throws NullPointerException The list is null.
     */
    public static <T> List<T> dropRight(List<T> array, int n){
        if(n < 0){
            throw new IllegalArgumentException("n must be non-negative");
        }
        Objects.requireNonNull(array);
        if(n >= array.size()){
            return iEmptyList();
        }
        return array.subList(0, array.size() - n);
    }
    
    /**
     * Drops the last element from an array.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @return The list, with last element removed.
     * @throws NullPointerException The list is null.
     */
    public static <T> List<T> dropRight(List<T> array){
        return dropRight(array, 1);
    }
    
    /**
     * Drop elements from the beginning until predicate returns true.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param predicate The predicate to test with.
     * @return A list with the elements removed from the beginning that didn't pass the predicate.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> dropWhile(List<T> array, ArrayPredicate<T> predicate){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        int cutoff;
        for(cutoff = 0; cutoff < array.size(); cutoff++){
            if(predicate.test(array.get(cutoff), cutoff, array)){
                break;
            }
        }
        return array.subList(cutoff, array.size());
    }
    
    /**
     * Drop elements from the beginning until predicate returns true.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param predicate The predicate to test with.
     * @return A list with the elements removed from the beginning that didn't pass the predicate.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> dropWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return dropWhile(array, (value, index, list) -> predicate.test(value));
    }
    
    /**
     * Drop elements from the beginning until a valid value is reached.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param invalidValues A list of invalid values.
     * @return A list with the elements removed from the beginning that weren't valid.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> dropWhile(List<T> array, List<T> invalidValues){
        return dropWhile(array, (value, index, list) -> !iIsInvalidValue(value, invalidValues));
    }
    
    /**
     * Drop elements from the beginning until a valid value is reached.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param invalidValues A list of invalid values.
     * @return A list with the elements removed from the beginning that weren't valid.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> dropWhile(List<T> array, T... invalidValues){
        return dropWhile(array, (value, index, list) -> !iIsInvalidValue(value, invalidValues));
    }
    
    /**
     * Drop elements from the beginning until a non-null value is reached.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @return A list with the elements removed from the beginning that weren't valid.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> dropWhile(List<T> array){
        return dropWhile(array, (value, index, list) -> value != null);
    }
    
    public static <T> List<T> dropRightWhile(List<T> array, ArrayPredicate<T> predicate){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        return null;
    }
}
