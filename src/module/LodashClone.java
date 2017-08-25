/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
        List<Integer> testList = new ArrayList<>(Arrays.asList(0, 2, 4, 6, 7, 8));
        List<Integer> testList2 = Arrays.asList(3, 5, 7, 9);
        BiPredicate<Integer, Integer> testFunction = (i, j) -> i%2 == j%2;
        System.out.println(pull(testList, 1, 2, 3, 4));
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
     * Note that the array passed into the predicate cannot be modified, only read.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param predicate The predicate to test with.
     * @return A list with the elements removed from the beginning that didn't pass the predicate.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> dropWhile(List<T> array, ArrayPredicate<T> predicate){
        array = Collections.unmodifiableList(Objects.requireNonNull(array));
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
     * Drop elements from the beginning until a non-null value is reached.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @return A list with the elements removed from the beginning that weren't valid.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> dropWhile(List<T> array){
        return dropWhile(array, (value, index, list) -> value != null);
    }
    
    /**
     * Drop elements from the end until a predicate returns true.
     * Note that the array supplied to the predicate cannot be modified.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @param predicate The predicate to test against.
     * @return A list with the elements removed from the end that didn't pass the predicate.
     * @throws IllegalArgumentException Array or predicate is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array, ArrayPredicate<T> predicate){
        array = Collections.unmodifiableList(Objects.requireNonNull(array));
        Objects.requireNonNull(predicate);
        int cutoff;
        for(cutoff = array.size() - 1; cutoff >= 0; cutoff--){
            if(predicate.test(array.get(cutoff), cutoff, array)){
                break;
            }
        }
        return array.subList(0, cutoff + 1);
    }
    
    /**
     * Drop elements from the end until a predicate returns true.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @param predicate The predicate to test against.
     * @return A list with the elements removed from the end that didn't pass the predicate.
     * @throws IllegalArgumentException Array or predicate is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return dropRightWhile(array, (value, index, list) -> predicate.test(value));
    }
    
    /**
     * Drop elements from the end until reaching a valid value.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @param invalidValues A list of invalid values.
     * @return A list with the elements removed from the end that were invalid.
     * @throws IllegalArgumentException Array or invalidValues is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array, List<T> invalidValues){
        return dropRightWhile(array, (value, index, list) -> !iIsInvalidValue(value, invalidValues));
    }
    
    /**
     * Drop elements from the end until reaching a non-null value.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @return A list with the elements removed from the end that were invalid.
     * @throws IllegalArgumentException Array or invalidValues is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array){
        return dropRightWhile(array, (value, index, list) -> value != null);
    }
    
    /**
     * Fill an array with a value.
     * This method mutates the supplied list.
     * @param <T> The type contained in the list.
     * @param array The list to fill.
     * @param value The value to fill with.
     * @param start The index to start the fill.
     * @param end The index to stop filling (exclusive).
     * @return The modified array.
     * @throws IllegalArgumentException Start is greater than end.
     * @throws ArrayIndexOutOfBoundsException Start or end exceed bounds.
     * @throws NullPointerException Provided list is null.
     */
    public static <T> List<T> fill(List<T> array, T value, int start, int end){
        Objects.requireNonNull(array);
        if(start < 0){
            throw new ArrayIndexOutOfBoundsException("Start must be non-negative");
        }
        if(end > array.size()){
            throw new ArrayIndexOutOfBoundsException("End must be less than or equal to list size");
        }
        if(start > end){
            throw new IllegalArgumentException("Start must be less than end.");
        }
        for(int index = start; index < end; index++){
            array.set(index, value);
        }
        return array;
    }
    
    /**
     * Fill an array with a value.
     * This method mutates the supplied list.
     * @param <T> The type contained in the list.
     * @param array The list to fill.
     * @param value The value to fill with.
     * @param start The index to start the fill.
     * @return The modified array.
     * @throws IllegalArgumentException Start is less than zero, or greater than list size.
     * @throws NullPointerException Provided list is null.
     */
    public static <T> List<T> fill(List<T> array, T value, int start){
        return fill(array, value, start, array.size());
    }
    
    /**
     * Fill an array with a value.
     * This method mutates the supplied list.
     * @param <T> The type contained in the list.
     * @param array The list to fill.
     * @param value The value to fill with.
     * @return The modified array.
     * @throws NullPointerException Provided list is null.
     */
    public static <T> List<T> fill(List<T> array, T value){
        return fill(array, value, 0, array.size());
    }
    
    /**
     * Find the first item in the array that passes the predicate, and returns its index.
     * @param <T> The type in the list.
     * @param array The array to test.
     * @param predicate The predicate to use to test.
     * @param start The index to start from.
     * @return The index of the passing value, or -1 if not found.
     * @throws ArrayIndexOutOfBoundsException Start is less than zero, or exceeds array size.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> int findIndex(List<T> array, Predicate<T> predicate, int start){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        if(start < 0 || start >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Start must lie in array bounds");
        }
        for(int index = start; index < array.size(); index++){
            if(predicate.test(array.get(index))){
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Find the first item in the array that passes the predicate, and returns its index.
     * @param <T> The type in the list.
     * @param array The array to test.
     * @param predicate The predicate to use to test.
     * @return The index of the passing value, or -1 if not found.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> int findIndex(List<T> array, Predicate<T> predicate){
        return findIndex(array, predicate, 0);
    }
    
    /**
     * Find the first item in the array that isn't null.
     * @param <T> The type in the list.
     * @param array The array to test.
     * @return The index of the passing value, or -1 if not found.
     * @throws NullPointerException Array is null.
     */
    public static <T> int findIndex(List<T> array){
        return findIndex(array, el -> el != null, 0);
    }
    
    /**
     * Find the last item in the array that matches a predicate.
     * @param <T> The type in the array.
     * @param array The array to search.
     * @param predicate The predicate to determine if the value was found.
     * @param start The index to start searching, inclusive.
     * @return The index of the last array to match the predicate, or -1 if not found.
     * @throws ArrayIndexOutOfBoundsException start is not within array bounds.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> int findLastIndex(List<T> array, Predicate<T> predicate, int start){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        if(start < 0 || start >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Start must lie in array bounds");
        }
        for(int index = start; index >= 0; index--){
            if(predicate.test(array.get(index))){
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Find the last item in the array that matches a predicate.
     * @param <T> The type in the array.
     * @param array The array to search.
     * @param predicate The predicate to determine if the value was found.
     * @return The index of the last array to match the predicate, or -1 if not found.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> int findLastIndex(List<T> array, Predicate<T> predicate){
        return findLastIndex(array, predicate, array.size() - 1);
    }
    
    /**
     * Find the last item in the array that is non-null.
     * @param <T> The type in the array.
     * @param array The array to search.
     * @return The index of the last array to match the predicate, or -1 if not found.
     * @throws NullPointerException Array is null.
     */
    public static <T> int findLastIndex(List<T> array){
        return findLastIndex(array, el -> el != null, array.size() - 1);
    }
    
    /**
     * Get the first element of an array.
     * Alias for head()
     * @param <T> The type in the list.
     * @param array The array to retrieve from, or null if the list is empty.
     * @return The first element in the list.
     * @throws NullPointerException Array is null.
     */
    public static <T> T first(List<T> array){
        Objects.requireNonNull(array);
        return array.isEmpty() ? null : array.get(0);
    }
    
    /**
     * Get the first element of an array.
     * @param <T> The type in the list.
     * @param array The array to retrieve from, or null if the list is empty.
     * @return The first element in the list.
     * @throws NullPointerException Array is null.
     */
    public static <T> T head(List<T> array){
        return first(array);
    }
    
    /**
     * Find the index of a specified element.
     * @param <T> The type contained in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @param start The index to begin searching. If negative, start becomes the
     * arrays size plus this value.
     * @return The index of the specified element, or -1 if not found.
     * @throws ArrayIndexOutOfBoundsException Start is not in bounds.
     * @throws NullPointerException Array is null.
     */
    public static <T> int indexOf(List<T> array, T value, int start){
        Objects.requireNonNull(array);
        if(start < 0){
            start = array.size() + start;
        }
        if(start < 0 || start >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Start is not in bounds");
        }
        for(int index = start; index < array.size(); index++){
            if(Objects.equals(value, array.get(index))){
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Find the index of a specified element.
     * @param <T> The type contained in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @return The index of the specified element, or -1 if not found.
     * @throws NullPointerException Array is null.
     * @throws IllegalArgumentException Array is empty.
     */
    public static <T> int indexOf(List<T> array, T value){
        return indexOf(array, value, 0);
    }
    
    /**
     * Returns a copy of the supplied array, except the last value.
     * @param <T> The type in the array.
     * @param array The array to slice.
     * @return A copy of the array, with the last element removed, or an
     * empty array if the supplied array was empty.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> initial(List<T> array){
        return Objects.requireNonNull(array).isEmpty() ? 
                new ArrayList<>() : 
                array.subList(0, array.size() - 1);
    }
    
    static <T> List<T> iFlatten(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        List<T> flattened = new ArrayList<>();
        for(List<T> array : arrays){
            flattened.addAll(array);
        }
        return flattened;
    }
    
    /**
     * Creates a list containing only common values between many lists.
     * @param <T> The type in the lists
     * @param arrays The arrays to check.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, or one of the sublists
     * is null.
     */
    public static <T> List<T> intersection(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        List<T> intersection = new ArrayList<>();
        for(T value : arrays.get(0)){
            boolean seen = true;
            for(int index = 1; index < arrays.size(); index++){
                if(!arrays.get(index).contains(value)){
                    seen = false;
                    break;
                }
            }
            if(seen){
                intersection.add(value);
            }
        }
        return intersection;
    }
    
    /**
     * Check if a list contains a value, after applying a mapping function.
     * @param <T> The type in the list.
     * @param <R> The type returned from the iteratee.
     * @param list The list of values to search.
     * @param value The value to compare equality with.
     * @param iteratee The mapping function.
     * @return True if the list contains a value that, when applied with the
     * iteratee, is equal to the specified value.
     * @throws NullPointerException List or iteratee is null.
     */
    static <T, R> boolean iContains(List<T> list, R value, Function<T, R> iteratee){
        Objects.requireNonNull(iteratee);
        for(T other : Objects.requireNonNull(list)){
            R otherMapped = iteratee.apply(other);
            if(Objects.equals(value, otherMapped)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if a list contains a value, after applying a mapping function.
     * @param <T> The type in the list.
     * @param list The list of values to search.
     * @param value The value to compare equality with.
     * @param comparator The comparing function.
     * @return True if the list contains a value that the comparator marks as equal.
     * @throws NullPointerException List or comparator is null.
     */
    static <T> boolean iContains(List<T> list, T value, BiPredicate<T, T> comparator){
        Objects.requireNonNull(comparator);
        for(T other : Objects.requireNonNull(list)){
            if(comparator.test(value, other)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Creates a list containing only common values between lists.
     * An iteratee is used to convert each value before comparison
     * @param <T> The type contained in the list of lists.
     * @param <R> The type converted to during comparison.
     * @param arrays The list of arrays to check.
     * @param iteratee An iteratee function that converts each value into another.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, or one of the sublists
     * is null, or the iteratee is null.
     */
    public static <T, R> List<T> intersectionBy(List<List<T>> arrays, Function<T, R> iteratee){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        Objects.requireNonNull(iteratee);
        List<T> intersection = new ArrayList<>();
        for(T value : arrays.get(0)){
            R valueMapped = iteratee.apply(value);
            boolean seen = true;
            for(int index = 1; index < arrays.size(); index++){
                if(!iContains(arrays.get(index), valueMapped, iteratee)){
                    seen = false;
                    break;
                }
            }
            if(seen){
                intersection.add(value);
            }
        }
        return intersection;
    }
    
    /**
     * Creates a list containing only common values between many lists.
     * @param <T> The type in the lists
     * @param arrays The arrays to check.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, or one of the sublists
     * is null.
     */
    public static <T> List<T> intersectionBy(List<List<T>> arrays){
        return intersection(arrays);
    }
    
    /**
     * Creates a list containing only common values between many lists.
     * @param <T> The type in the lists.
     * @param arrays The arrays to check.
     * @param comparator The function which checks for equality.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, one of the sublists
     * is null, or the comparator is null.
     */
    public static <T> List<T> intersectionWith(List<List<T>> arrays, BiPredicate<T, T> comparator){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        Objects.requireNonNull(comparator);
        List<T> intersection = new ArrayList<>();
        for(T value : arrays.get(0)){
            boolean seen = true;
            for(int index = 1; index < arrays.size(); index++){
                if(!iContains(arrays.get(index), value, comparator)){
                    seen = false;
                    break;
                }
            }
            if(seen){
                intersection.add(value);
            }
        }
        return intersection;
    }
    
    /**
     * Creates a list containing only common values between many lists.
     * @param <T> The type in the lists.
     * @param arrays The arrays to check.
     * @param comparator The function which checks for equality.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, one of the sublists
     * is null, or the comparator is null.
     */
    public static <T> List<T> intersectionWith(List<List<T>> arrays, Comparator<T> comparator){
        return intersectionWith(arrays, iBiPredicateFromComparator(comparator));
    }
    
    /**
     * Creates a list containing only common values between many lists.
     * @param <T> The type in the lists
     * @param arrays The arrays to check.
     * @return A list of values each list contains.
     * @throws NullPointerException The list of lists is null, or one of the sublists
     * is null.
     */
    public static <T> List<T> intersectionWith(List<List<T>> arrays){
        return intersection(arrays);
    }
    
    /**
     * Converts a list into a string, separated by a specific separator.
     * @param <T> The type in the array.
     * @param array The array to turn into a string.
     * @param separator The separator string, used between elements.
     * @return A string containing each element and the separator between them.
     * @throws NullPointerException Array or separator is null.
     */
    public static <T> String join(List<T> array, String separator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(separator);
        String joined = "";
        for(int index = 0; index < array.size(); index++){
            if(index != 0){
                joined += separator;
            }
            joined += Objects.toString(array.get(index));
        }
        return joined;
    }
    
    /**
     * Converts a list into a string, separated by a comma.
     * @param <T> The type in the array.
     * @param array The array to turn into a string.
     * @return A string containing each element and the separator between them.
     * @throws NullPointerException Array is null.
     */
    public static <T> String join(List<T> array){
        return join(array, ",");
    }
    
    /**
     * Retrieves the last element in an array.
     * @param <T> The type in the array.
     * @param array The array to retrieve from.
     * @return The last element, or null if the list is empty.
     * @throws NullPointerException array is null.
     */
    public static <T> T last(List<T> array){
        Objects.requireNonNull(array);
        return array.isEmpty() ? null : array.get(array.size() - 1);
    }
    
    /**
     * Find the index of a specified element.
     * @param <T> The type contained in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @param start The index to begin searching. If negative, start becomes the
     * arrays size plus this value.
     * @return The index of the specified element, or -1 if not found.
     * @throws ArrayIndexOutOfBoundsException Start is not in bounds.
     * @throws NullPointerException Array is null.
     */
    public static <T> int lastIndexOf(List<T> array, T value, int start){
        Objects.requireNonNull(array);
        if(start < 0){
            start = array.size() + start;
        }
        if(start < 0 || start >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Start is not in bounds");
        }
        for(int index = start; index >= 0; index--){
            if(Objects.equals(value, array.get(index))){
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Find the index of a specified element.
     * @param <T> The type contained in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @return The index of the specified element, or -1 if not found.
     * @throws NullPointerException Array is null.
     * @throws IllegalArgumentException Array is empty.
     */
    public static <T> int lastIndexOf(List<T> array, T value){
        return lastIndexOf(array, value, array.size() - 1);
    }
    
    /**
     * Get the element at index n of the array.
     * If the supplied index is negative, the nth element from the end is
     * returned.
     * @param <T> The type in the array.
     * @param array The array to retrieve from.
     * @param index The index of the element to retrieve.
     * @return The specified element.
     * @throws NullPointerException Array is null.
     * @throws ArrayIndexOutOfBoundsException Index is out of bounds.
     */
    public static <T> T nth(List<T> array, int index){
        Objects.requireNonNull(array);
        if(index < 0){
            index = array.size() + index;
        }
        if(index < 0 || index >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Index specified is out of bounds.");
        }
        return array.get(index);
    }
    
    /**
     * Removes all values specified from the list.
     * This operation mutates the list. Use without() for non-mutation.
     * @param <T> The type in the list.
     * @param array The array to remove from.
     * @param values The values to remove.
     * @return The instance of the provided array.
     * @throws NullPointerException array or values is null.
     */
    public static <T> List<T> pull(List<T> array, List<T> values){
        Objects.requireNonNull(array).removeAll(Objects.requireNonNull(values));
        return array;
    }
    
    /**
     * Removes all values specified from the list.
     * This operation mutates the list. Use without() for non-mutation.
     * @param <T> The type in the list.
     * @param array The array to remove from.
     * @param values The values to remove.
     * @return The instance of the provided array.
     * @throws NullPointerException array or values is null.
     */
    public static <T> List<T> pull(List<T> array, T... values){
        return pull(array, Arrays.asList(values));
    }
}
