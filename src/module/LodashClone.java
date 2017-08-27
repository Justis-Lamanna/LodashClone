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
        List<Integer> testList = new ArrayList<>(Arrays.asList(0, 2, 2, 4, null, 6, 7, 8));
        List<Integer> testList2 = Arrays.asList(4, 4, 4, 9);
        List<List<Integer>> testList3 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        System.out.println(unzipWith(testList3, i -> i.stream().reduce((a, b) -> a+b).get()));
    }
    
    /**
     * An identity predicate, which returns true if item is non-null.
     * @param <T>
     * @return 
     */
    static <T> Predicate<T> iIdentityPredicate(){
        return i -> i != null;
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
     * @param values A list of lists of values to exclude.
     * @param iteratee The iteratee function, which dictates the comparison.
     * @return The list, with values removed.
     * @throws NullPointerException The array, iteratee, values, or any sublist is null.
     */
    public static <T, R> List<T> differenceBy(List<T> array, List<List<T>> values, Function<T, R> iteratee){
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
     * Drop elements from the beginning until predicate returns false.
     * Note that the array passed into the predicate cannot be modified, only read.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param predicate The predicate to test with.
     * @return A list with the elements removed from the beginning that passed the predicate.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> dropWhile(List<T> array, ArrayPredicate<T> predicate){
        array = Collections.unmodifiableList(Objects.requireNonNull(array));
        Objects.requireNonNull(predicate);
        int cutoff;
        for(cutoff = 0; cutoff < array.size(); cutoff++){
            if(!predicate.test(array.get(cutoff), cutoff, array)){
                break;
            }
        }
        return array.subList(cutoff, array.size());
    }
    
    /**
     * Drop elements from the beginning until predicate returns false.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @param predicate The predicate to test with.
     * @return A list with the elements removed from the beginning that passed the predicate.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> dropWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return dropWhile(array, (value, index, list) -> predicate.test(value));
    }
    
    /**
     * Drop elements from the beginning until a null value is reached.
     * @param <T> The type in the list.
     * @param array The list to test.
     * @return A list with the elements removed from the beginning that weren't null.
     * @throws NullPointerException Array is null.
     */
    public static <T> List<T> dropWhile(List<T> array){
        return dropWhile(array, iIdentityPredicate());
    }
    
    /**
     * Drop elements from the end until a predicate returns false.
     * Note that the array supplied to the predicate cannot be modified.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @param predicate The predicate to test against.
     * @return A list with the elements removed from the end that passed the predicate.
     * @throws IllegalArgumentException Array or predicate is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array, ArrayPredicate<T> predicate){
        array = Collections.unmodifiableList(Objects.requireNonNull(array));
        Objects.requireNonNull(predicate);
        int cutoff;
        for(cutoff = array.size() - 1; cutoff >= 0; cutoff--){
            if(!predicate.test(array.get(cutoff), cutoff, array)){
                break;
            }
        }
        return array.subList(0, cutoff + 1);
    }
    
    /**
     * Drop elements from the end until a predicate returns false.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @param predicate The predicate to test against.
     * @return A list with the elements removed from the end that passed the predicate.
     * @throws IllegalArgumentException Array or predicate is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return dropRightWhile(array, (value, index, list) -> predicate.test(value));
    }
    
    /**
     * Drop elements from the end until reaching a null value.
     * @param <T> The type contained in the list.
     * @param array The list to drop from.
     * @return A list with the elements removed from the end that were non-null.
     * @throws IllegalArgumentException Array or invalidValues is null.
     */
    public static <T> List<T> dropRightWhile(List<T> array){
        return dropRightWhile(array, iIdentityPredicate());
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
        return findIndex(array, iIdentityPredicate(), 0);
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
        return findLastIndex(array, iIdentityPredicate(), array.size() - 1);
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
    static <T, R> boolean iContains(List<T> list, T value, Function<T, R> iteratee, BiPredicate<R, R> comparator){
        Objects.requireNonNull(iteratee);
        R valueMapped = iteratee.apply(value);
        for(T other : Objects.requireNonNull(list)){
            R otherMapped = iteratee.apply(other);
            if(comparator.test(valueMapped, otherMapped)){
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
            boolean seen = true;
            for(int index = 1; index < arrays.size(); index++){
                if(!iContains(arrays.get(index), value, iteratee, (u, v) -> Objects.equals(u, v))){
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
        return intersectionBy(arrays, iIdentity());
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
                if(!iContains(arrays.get(index), value, iIdentity(), comparator)){
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
    public static <T> List<T> pullAll(List<T> array, List<T> values){
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
        return pullAll(array, Arrays.asList(values));
    }
    
    /**
     * Removes values from a list, after mapping each with an iteratee.
     * This mutates the list. Use differenceBy() for a non-mutating version.
     * @param <T> The type in each list.
     * @param <R> The type mapped to when passing through the iteratee.
     * @param array The starting array.
     * @param values The values to pull.
     * @param iteratee The mapping function applied before comparison.
     * @return The modified list.
     * @throws NullPointerException Array, values, or iteratee is null.
     */
    public static <T, R> List<T> pullAllBy(List<T> array, List<T> values, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(values);
        Objects.requireNonNull(iteratee);
        Iterator<T> iterator = array.iterator();
        while(iterator.hasNext()){
            T value = iterator.next();
            R mappedValue = iteratee.apply(value);
            for(T other : values){
                R mappedOther = iteratee.apply(other);
                if(Objects.equals(mappedValue, mappedOther)){
                    iterator.remove();
                    break;
                }
            }
        }
        return array;
    }
    
    /**
     * Removes values from a list, without specifying a mapping function
     * This mutates the list. Use differenceBy() for a non-mutating version.
     * @param <T> The type in each list.
     * @param array The starting array.
     * @param values The values to pull.
     * @return The modified list.
     * @throws NullPointerException Array or values is null.
     */
    public static <T> List<T> pullAllBy(List<T> array, List<T> values){
        return pullAllBy(array, values, iIdentity());
    }
    
    /**
     * Remove values from a list, using a BiPredicate for checking equality.
     * This mutates the list. Use differenceWith() for a non-mutating version.
     * @param <T> The type in the list.
     * @param array The array to pull from.
     * @param values The values to pull.
     * @param comparator The BiPredicate that determines equality.
     * @return The modified array.
     * @throws NullPointerException Array, values, or comparator is null.
     */
    public static <T> List<T> pullAllWith(List<T> array, List<T> values, BiPredicate<T, T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(values);
        Objects.requireNonNull(comparator);
        Iterator<T> iterator = array.iterator();
        while(iterator.hasNext()){
            T value = iterator.next();
            for(T other : values){
                if(comparator.test(other, value)){
                    iterator.remove();
                    break;
                }
            }
        }
        return array;
    }
    
    /**
     * Remove values from a list, using a Comparator for checking equality.
     * This mutates the list. Use differenceWith() for a non-mutating version.
     * @param <T> The type in the list.
     * @param array The array to pull from.
     * @param values The values to pull.
     * @param comparator The BiPredicate that determines equality.
     * @return The modified array.
     * @throws NullPointerException Array, values, or comparator is null.
     */
    public static <T> List<T> pullAllWith(List<T> array, List<T> values, Comparator<T> comparator){
        Objects.requireNonNull(comparator);
        return pullAllWith(array, values, iBiPredicateFromComparator(comparator));
    }
    
    /**
     * Removes all values specified from the list.
     * This operation mutates the list. Use differenceWith() for non-mutation.
     * @param <T> The type in the list.
     * @param array The array to remove from.
     * @param values The values to remove.
     * @return The instance of the provided array.
     * @throws NullPointerException array or values is null.
     */
    public static <T> List<T> pullAllWith(List<T> array, List<T> values){
        return pullAll(array, values);
    }
    
    /**
     * Removes the elements at the specified indexes.
     * This operation mutates the list. Use at() for non-mutation.
     * @param <T> The type contained in the list.
     * @param array The list to pull from.
     * @param indexes The indexes to pull.
     * @return An array of pulled elements.
     * @throws NullPointerException array is null, indexes, or a value in indexes is null
     * @throws ArrayIndexOutOfBoundsException An index is not in the array bounds.
     */
    public static <T> List<T> pullAt(List<T> array, List<Integer> indexes){
        Objects.requireNonNull(array);
        Objects.requireNonNull(indexes);
        for(Integer i : indexes){
            if(Objects.requireNonNull(i) < 0 || i >= array.size()){
                throw new ArrayIndexOutOfBoundsException("Index exceeds array bounds.");
            }
        }
        List<T> pulled = new ArrayList<>();
        List<Integer> copy = new ArrayList<>(indexes);
        Collections.sort(copy);
        for(int i = copy.size() - 1; i >= 0; i--){
            int indexToRemove = copy.get(i);
            pulled.add(0, array.remove(indexToRemove));
        }
        return pulled;
    }
    
    /**
     * Removes the elements at the specified indexes.
     * This operation mutates the list. Use at() for non-mutation.
     * @param <T> The type contained in the list.
     * @param array The list to pull from.
     * @param index The index to pull.
     * @return An array containing the pulled element.
     * @throws NullPointerException array is null.
     * @throws ArrayIndexOutOfBoundsException index is out of bounds.
     */
    public static <T> List<T> pullAt(List<T> array, int index){
        Objects.requireNonNull(array);
        if(index < 0 || index >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Index exceeds bounds");
        }
        List<T> removed = new ArrayList<>();
        removed.add(array.remove(index));
        return removed;
    }
    
    /**
     * Remove elements from an array that pass an ArrayPredicate.
     * This operation mutates the list. Use filter() for non-mutation.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @param predicate The predicate to determine removal.
     * @return The removed elements.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> remove(List<T> array, ArrayPredicate<T> predicate){
        Objects.requireNonNull(array);
        Objects.requireNonNull(predicate);
        int index = 0;
        List<T> immutableArray = Collections.unmodifiableList(array);
        List<T> removed = new ArrayList<>();
        Iterator<T> iterator = array.iterator();
        while(iterator.hasNext()){
            T value = iterator.next();
            if(predicate.test(value, index++, immutableArray)){
                removed.add(value);
                iterator.remove();
            }
        }
        return removed;
    }
    
    /**
     * Remove elements from an array that pass a Predicate.
     * This operation mutates the list. Use filter() for non-mutation.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @param predicate The predicate to determine removal.
     * @return The removed elements.
     * @throws NullPointerException Array or predicate is null.
     */
    public static <T> List<T> remove(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return remove(array, (v, i, a) -> predicate.test(v));
    }
    
    /**
     * Remove elements from an array that are non-null.
     * This operation mutates the list. Use filter() for non-mutation.
     * @param <T> The type in the array.
     * @param array The array to remove from.
     * @return The removed elements (nulls).
     * @throws NullPointerException Array.
     */
    public static <T> List<T> remove(List<T> array){
        return remove(array, iIdentityPredicate());
    }
    
    /**
     * Reverse the order of a list.
     * This operation mutates the list.
     * @param <T> The type in the list.
     * @param array The array to reverse.
     * @return The array, reversed.
     */
    public static <T> List<T> reverse(List<T> array){
        //No sense reinventing the wheel.
        Collections.reverse(Objects.requireNonNull(array));
        return array;
    }
    
    /**
     * Create a slice of the list.
     * @param <T> The type in the array.
     * @param array The array to slice.
     * @param start The starting index.
     * @param end The ending index (exclusive).
     * @return The slice of the array.
     * @throws NullPointerException array is null.
     * @throws ArrayIndexOutOfBoundsException Start or end is out of bounds.
     * @throws IllegalArgumentException Start is greater than end.
     */
    public static <T> List<T> slice(List<T> array, int start, int end){
        Objects.requireNonNull(array);
        if(start < 0 || end >= array.size()){
            throw new ArrayIndexOutOfBoundsException("Indexes are not in bounds.");
        }
        if(start > end){
            throw new IllegalArgumentException("Start is greater than end.");
        }
        return array.subList(start, end);
    }
    
    static <T, R> int iBinarySearch(List<T> array, T value, Function<T, R> mapper, Comparator<R> comparator){
        int start = 0;
        int end = array.size() - 1;
        R valueMapped = mapper.apply(value);
        while(start <= end){
            int mid = (start + end) / 2;
            R midValueMapped = mapper.apply(array.get(mid));
            int result = Objects.compare(midValueMapped, valueMapped, comparator);
            if(result < 0){
                start = mid + 1;
            } else if(result > 0){
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return start;
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @param comparator A comparator function.
     * @return The first index to place the value at.
     */
    public static <T> int sortedIndex(List<T> array, T value, Comparator<T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, t -> t, comparator);
        T foundValue = array.get(foundIndex);
        for(int index = foundIndex; index >= 0; index--){
            if(!Objects.equals(foundValue, array.get(index))){
                return index + 1;
            }
        }
        return 0;
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @return The first index to place the value at.
     */
    public static <T extends Comparable> int sortedIndex(List<T> array, T value){
        return sortedIndex(array, value, Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param <R> The type converted to by the iteratee.
     * @param array The array to search.
     * @param value The value to insert.
     * @param iteratee The function used to transform each object before comparing.
     * @param comparator The comparator function.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array, iteratee, or comparable are null.
     */
    public static <T, R> int sortedIndexBy(List<T> array, T value, Function<T, R> iteratee, Comparator<R> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, iteratee, comparator);
        R foundValueMapped = iteratee.apply(array.get(foundIndex));
        for(int index = foundIndex; index >= 0; index--){
            if(!Objects.equals(foundValueMapped, iteratee.apply(array.get(index)))){
                return index + 1;
            }
        }
        return 0;
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param <R> The type converted to by the iteratee.
     * @param array The array to search.
     * @param value The value to insert.
     * @param iteratee The function used to transform each object before comparing.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array, iteratee, or comparable are null.
     */
    public static <T, R extends Comparable> int sortedIndexBy(List<T> array, T value, Function<T, R> iteratee){
        return sortedIndexBy(array, value, iteratee, Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @param comparator The comparator function.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array or comparable are null.
     */
    public static <T> int sortedIndexBy(List<T> array, T value, Comparator<T> comparator){
        return sortedIndexBy(array, value, iIdentity(), comparator);
    }
    
    /**
     * Uses a binary search to determine the lowest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array or comparable are null.
     */
    public static <T extends Comparable> int sortedIndexBy(List<T> array, T value){
        return sortedIndexBy(array, value, iIdentity(), Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the first index of a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @param comparator The comparator to use during comparisons.
     * @return The index of the value, or -1 if not found.
     */
    public static <T> int sortedIndexOf(List<T> array, T value, Comparator<T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, t -> t, comparator);
        T foundValue = array.get(foundIndex);
        if(!Objects.equals(foundValue, value)){
            return -1;
        }
        for(int index = foundIndex; index >= 0; index--){
            if(!Objects.equals(array.get(index), value)){
                return index + 1;
            }
        }
        return 0;
    }
    
    /**
     * Uses a binary search to determine the first index of a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @return The index of the value, or -1 if not found.
     */
    public static <T extends Comparable> int sortedIndexOf(List<T> array, T value){
        return sortedIndexOf(array, value, Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @param comparator A comparator function.
     * @return The first index to place the value at.
     */
    public static <T> int sortedLastIndex(List<T> array, T value, Comparator<T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, t -> t, comparator);
        T foundValue = array.get(foundIndex);
        for(int index = foundIndex; index < array.size(); index++){
            if(!Objects.equals(foundValue, array.get(index))){
                return index;
            }
        }
        return array.size();
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @return The first index to place the value at.
     */
    public static <T extends Comparable> int sortedLastIndex(List<T> array, T value){
        return sortedLastIndex(array, value, Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param <R> The type converted to by the iteratee.
     * @param array The array to search.
     * @param value The value to insert.
     * @param iteratee The function used to transform each object before comparing.
     * @param comparator The comparator function.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array, iteratee, or comparable are null.
     */
    public static <T, R> int sortedLastIndexBy(List<T> array, T value, Function<T, R> iteratee, Comparator<R> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, iteratee, comparator);
        R foundValueMapped = iteratee.apply(array.get(foundIndex));
        for(int index = foundIndex; index < array.size(); index++){
            if(!Objects.equals(foundValueMapped, iteratee.apply(array.get(index)))){
                return index;
            }
        }
        return array.size();
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param <R> The type converted to by the iteratee.
     * @param array The array to search.
     * @param value The value to insert.
     * @param iteratee The function used to transform each object before comparing.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array, iteratee, or comparable are null.
     */
    public static <T, R extends Comparable> int sortedLastIndexBy(List<T> array, T value, Function<T, R> iteratee){
        return sortedLastIndexBy(array, value, iteratee, Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @param comparator The comparator function.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array or comparable are null.
     */
    public static <T> int sortedLastIndexBy(List<T> array, T value, Comparator<T> comparator){
        return sortedLastIndexBy(array, value, iIdentity(), comparator);
    }
    
    /**
     * Uses a binary search to determine the highest index to place a value in a sorted list
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to insert.
     * @return The index to insert the specified value.
     * @throws NullPointerException Array or comparable are null.
     */
    public static <T extends Comparable> int sortedLastIndexBy(List<T> array, T value){
        return sortedLastIndexBy(array, value, iIdentity(), Comparator.naturalOrder());
    }
    
    /**
     * Uses a binary search to determine the last index of a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @param comparator The comparator to use during comparisons.
     * @return The index of the value, or -1 if not found.
     */
    public static <T> int sortedLastIndexOf(List<T> array, T value, Comparator<T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        int foundIndex = iBinarySearch(array, value, t -> t, comparator);
        T foundValue = array.get(foundIndex);
        if(!Objects.equals(foundValue, value)){
            return -1;
        }
        for(int index = foundIndex; index < array.size(); index++){
            if(!Objects.equals(array.get(index), value)){
                return index - 1;
            }
        }
        return array.size() - 1;
    }
    
    /**
     * Uses a binary search to determine the last index of a value in a sorted list.
     * @param <T> The type in the list.
     * @param array The array to search.
     * @param value The value to search for.
     * @return The index of the value, or -1 if not found.
     */
    public static <T extends Comparable> int sortedLastIndexOf(List<T> array, T value){
        return sortedLastIndexOf(array, value, Comparator.naturalOrder());
    }
    
    /**
     * Determines the unique values in a sorted list.
     * @param <T> The type contained in the list.
     * @param array The list to condense.
     * @return The list of unique values in the array.
     */
    public static <T> List<T> sortedUniq(List<T> array){
        return sortedUniqBy(array, i -> i);
    }
    
    /**
     * Determines the unique values in a sorted list through a mapping function.
     * @param <T> The type contained in the list.
     * @param <R> The type output by the mapping function.
     * @param array The list to condense.
     * @param iteratee A mapping function applied before comparing
     * @return The list of unique values in the array.
     */
    public static <T, R> List<T> sortedUniqBy(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        List<T> uniqueValues = new ArrayList<>();
        if(!array.isEmpty()){
            T currentValue = array.get(0);
            R currentValueMapped = iteratee.apply(currentValue);
            uniqueValues.add(currentValue);
            for(int index = 1; index < array.size(); index++){
                T value = array.get(index);
                R valueMapped = iteratee.apply(value);
                if(!Objects.equals(currentValueMapped, valueMapped)){
                    uniqueValues.add(value);
                    currentValue = value;
                    currentValueMapped = valueMapped;
                }
            }
        }
        return uniqueValues;
    }
    
    /**
     * Get all but the first element of an array.
     * @param <T> The type in the list.
     * @param array The array to get the tail of.
     * @return The list, minus the first element.
     */
    public static <T> List<T> tail(List<T> array){
        return Objects.requireNonNull(array).subList(1, array.size());
    }
    
    /**
     * Retrieve the first n elements from a list.
     * @param <T> The type of the list.
     * @param array The array to take from.
     * @param amount The number of first elements to take.
     * @return The first amount elements.
     */
    public static <T> List<T> take(List<T> array, int amount){
        Objects.requireNonNull(array);
        if(amount < 0){
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if(amount > array.size()){
            amount = array.size();
        }
        return array.subList(0, amount);
    }
    
    /**
     * Retrieve the first element in a list.
     * @param <T> The type in the list.
     * @param array The array to take from.
     * @return A list containing the first element.
     */
    public static <T> List<T> take(List<T> array){
        return take(array, 1);
    }
    
    /**
     * Retrieve the last n elements from a list.
     * @param <T> The type of the list.
     * @param array The array to take from.
     * @param amount The number of last elements to take.
     * @return The last amount elements.
     */
    public static <T> List<T> takeRight(List<T> array, int amount){
        Objects.requireNonNull(array);
        if(amount < 0){
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if(amount > array.size()){
            amount = array.size();
        }
        return array.subList(array.size() - amount, array.size());
    }
    
    /**
     * Retrieve the last element from the list.
     * @param <T> The type in the array.
     * @param array The array to take from.
     * @return A list containing the last element in the array.
     */
    public static <T> List<T> takeRight(List<T> array){
        return takeRight(array, 1);
    }
    
    /**
     * Retrieves elements from the beginning of a list until a predicate returns false.
     * @param <T> The type in the array.
     * @param array The array to take from.
     * @param predicate The predicate to test against.
     * @return The array, with first elements removed until the predicate returned false.
     */
    public static <T> List<T> takeWhile(List<T> array, ArrayPredicate<T> predicate){
        List<T> immutableArray = Collections.unmodifiableList(Objects.requireNonNull(array));
        Objects.requireNonNull(predicate);
        int start;
        for(start = 0; start < array.size(); start++){
            if(!predicate.test(array.get(start), start, immutableArray)){
                break;
            }
        }
        return array.subList(0, start);
    }
    
    /**
     * Retrieves elements from the beginning of a list until a predicate returns false.
     * @param <T> The type in the array.
     * @param array The array to take from.
     * @param predicate The predicate to test against.
     * @return The array, with first elements removed until the predicate returned false.
     */
    public static <T> List<T> takeWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return takeWhile(array, (v, i, a) -> predicate.test(v));
    }
    
    /**
     * Retrieves elements from the beginning of the list until one returns null
     * @param <T> The type in the list.
     * @param array The array to take from
     * @return The beginning elements, until one returns null.
     */
    public static <T> List<T> takeWhile(List<T> array){
        return takeWhile(array, iIdentityPredicate());
    }
    
    /**
     * Retrieves elements from the end of the list until a predicate returns false. 
     * @param <T> The type in the list.
     * @param array The array to take from.
     * @param predicate The predicate to test against.
     * @return The elements from the end of the list that tested true.
     */
    public static <T> List<T> takeRightWhile(List<T> array, ArrayPredicate<T> predicate){
        Objects.requireNonNull(predicate);
        List<T> immutableArray = Collections.unmodifiableList(Objects.requireNonNull(array));
        int start;
        for(start = array.size() - 1; start >= 0; start--){
            if(!predicate.test(array.get(start), start, immutableArray)){
                break;
            }
        }
        return array.subList(start + 1, array.size());
    }
    
    /**
     * Retrieves elements from the end of the list until a predicate returns false. 
     * @param <T> The type in the list.
     * @param array The array to take from.
     * @param predicate The predicate to test against.
     * @return The elements from the end of the list that tested true.
     */
    public static <T> List<T> takeRightWhile(List<T> array, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return takeRightWhile(array, (v, i, a) -> predicate.test(v));
    }
    
    /**
     * Retrieves elements from the end of the list until one returns null. 
     * @param <T> The type in the list.
     * @param array The array to take from.
     * @return The elements from the end of the list that tested true.
     */
    public static <T> List<T> takeRightWhile(List<T> array){
        return takeRightWhile(array, iIdentityPredicate());
    }
    
    /**
     * Internal function to perform a union.
     * @param <T> The type in the array.
     * @param <R> The type the mapper should convert to.
     * @param arrays The arrays to union.
     * @param mapper The mapping function called before comparison.
     * @param predicate The predicate used to determine equality.
     * @return The unionized list.
     */
    static <T, R> List<T> iUnion(List<List<T>> arrays, Function<T, R> mapper, BiPredicate<R, R> predicate){
        List<T> union = new ArrayList<>();
        for(List<T> array : arrays){
            for(T item : array){
                if(!iContains(union, item, mapper, predicate)){
                    union.add(item);
                }
            }
        }
        return union;
    }
    
    /**
     * Creates an array of unique values from the given arrays.
     * @param <T> The type in the lists
     * @param arrays The list of lists to unionize.
     * @return A unionized list.
     */
    public static <T> List<T> union(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        return iUnion(arrays, iIdentity(), Objects::equals);
    }
    
    /**
     * Creates an array of unique values from the given arrays, after passing through a mapper.
     * @param <T> The type in the lists.
     * @param <R> The type converted to before comparison.
     * @param arrays The array of lists to unionize.
     * @param iteratee The mapping function.
     * @return A unionized list.
     */
    public static <T, R> List<T> unionBy(List<List<T>> arrays, Function<T, R> iteratee){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        Objects.requireNonNull(iteratee);
        return iUnion(arrays, iteratee, Objects::equals);
    }
    
    /**
     * Creates an array of unique values from the given arrays.
     * @param <T> The type in the lists.
     * @param arrays The array of lists to unionize.
     * @return A unionized list.
     */
    public static <T> List<T> unionBy(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        return iUnion(arrays, iIdentity(), Objects::equals);
    }
    
    /**
     * Creates an array of unique values from the given arrays, using a BiPredicate to determine equality.
     * @param <T> The type in the arrays.
     * @param arrays The array of arrays to unionize.
     * @param comparator The function used to determine equality.
     * @return A unionized list.
     */
    public static <T> List<T> unionWith(List<List<T>> arrays, BiPredicate<T, T> comparator){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        Objects.requireNonNull(comparator);
        return iUnion(arrays, iIdentity(), comparator);
    }
    
    /**
     * Creates an array of unique values from the given arrays, using a Comparator to determine equality.
     * @param <T> The type in the arrays.
     * @param arrays The array of arrays to unionize.
     * @param comparator The function used to determine equality.
     * @return A unionized list.
     */
    public static <T> List<T> unionWith(List<List<T>> arrays, Comparator<T> comparator){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        Objects.requireNonNull(comparator);
        return iUnion(arrays, iIdentity(), iBiPredicateFromComparator(comparator));
    }
    
    /**
     * Creates an array of unique values from the given arrays.
     * @param <T> The type in the arrays.
     * @param arrays The array of arrays to unionize.
     * @return A unionized list.
     */
    public static <T> List<T> unionWith(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        arrays.forEach(i -> Objects.requireNonNull(i));
        return iUnion(arrays, iIdentity(), Objects::equals);
    }
    
    /**
     * Creates an array of unique values from a given array.
     * @param <T> The type in the list.
     * @param array The array to get unique values from.
     * @return The unique values of the array.
     */
    public static <T> List<T> uniq(List<T> array){
        Objects.requireNonNull(array);
        return iUnion(Arrays.asList(array), iIdentity(), Objects::equals);
    }
    
    /**
     * Creates an array of unique values from a given array, after passing through a mapper.
     * @param <T> The type in the list.
     * @param array The array to get unique values from.
     * @param iteratee The mapping function called before comparison.
     * @return The unique values of the array.
     */
    public static <T, R> List<T> uniqBy(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        return iUnion(Arrays.asList(array), iteratee, Objects::equals);
    }
    
    /**
     * Creates an array of unique values from a given array.
     * @param <T> The type in the list.
     * @param array The array to get unique values from.
     * @return The unique values of the array.
     */
    public static <T> List<T> uniqBy(List<T> array){
        Objects.requireNonNull(array);
        return iUnion(Arrays.asList(array), iIdentity(), Objects::equals);
    }
    
    /**
     * Creates an array of unique values from a given array, using a BiPredicate to test equality.
     * @param <T> The type in the array.
     * @param array The array to get unique values from.
     * @param comparator The BiPredicate used to check equality
     * @return The unique values of the array.
     */
    public static <T> List<T> uniqWith(List<T> array, BiPredicate<T, T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        return iUnion(Arrays.asList(array), iIdentity(), comparator);
    }
    
    /**
     * Creates an array of unique values from a given array, using a Comparator to test equality.
     * @param <T> The type in the array.
     * @param array The array to get unique values from.
     * @param comparator The BiPredicate used to check equality
     * @return The unique values of the array.
     */
    public static <T> List<T> uniqWith(List<T> array, Comparator<T> comparator){
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);
        return iUnion(Arrays.asList(array), iIdentity(), iBiPredicateFromComparator(comparator));
    }
    
    /**
     * Creates an array of unique values from a given array.
     * @param <T> The type in the array.
     * @param array The array to get unique values from.
     * @return The unique values of the array.
     */
    public static <T> List<T> uniqWith(List<T> array){
        Objects.requireNonNull(array);
        return iUnion(Arrays.asList(array), iIdentity(), Objects::equals);
    }
    
    /**
     * Internal function to perform a zip/unzip.
     * @param <T> The type in the list of lists.
     * @param arrays The array of arrays to zip/unzip.
     * @param iteratee The mapping function to determine how the array will be combined.
     * @return The zipped/unzipped list.
     */
    static <T, R> List<R> iUnzip(List<List<T>> arrays, Function<List<T>, R> iteratee){
        List<R> unzippedArrays = new ArrayList<>();
        for(int index = 0; index < arrays.get(0).size(); index++){
            List<T> unzippedArray = new ArrayList<>();
            for(List<T> array : arrays){
                unzippedArray.add(array.get(index));
            }
            unzippedArrays.add(iteratee.apply(unzippedArray));
        }
        return unzippedArrays;
    }
    
    /**
     * Unzip a previously zipped list.
     * @param <T> The type in the array.
     * @param arrays The list of lists to unzip.
     * @return The unzipped list.
     */
    public static <T> List<List<T>> unzip(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        int size = arrays.get(0).size();
        for(List<T> array : arrays){
            if(array.size() != size){
                throw new IllegalArgumentException("All arrays must be the same size");
            }
        }
        return iUnzip(arrays, iIdentity());
    }
    
    /**
     * Unzips a previously zipped list, with an iteratee to specify how the results should combine.
     * @param <T> The type contained in the list.
     * @param <R> The type to turn the list into.
     * @param arrays The list of lists to unzip.
     * @param iteratee The function to map each unzipped list into.
     * @return The unzipped list, after being combined.
     */
    public static <T, R> List<R> unzipWith(List<List<T>> arrays, Function<List<T>, R> iteratee){
        Objects.requireNonNull(arrays);
        int size = arrays.get(0).size();
        for(List<T> array : arrays){
            if(array.size() != size){
                throw new IllegalArgumentException("All arrays must be the same size");
            }
        }
        Objects.requireNonNull(iteratee);
        return iUnzip(arrays, iteratee);
    }
    
    /**
     * Unzip a previously zipped list.
     * @param <T> The type in the array.
     * @param arrays The list of lists to unzip.
     * @return The unzipped list.
     */
    public static <T> List<List<T>> unzipWith(List<List<T>> arrays){
        Objects.requireNonNull(arrays);
        int size = arrays.get(0).size();
        for(List<T> array : arrays){
            if(array.size() != size){
                throw new IllegalArgumentException("All arrays must be the same size");
            }
        }
        return iUnzip(arrays, iIdentity());
    }
    
    /**
     * Create a list containing the original values, minus any excluded ones.
     * @param <T> The type contained in the list.
     * @param array The base array.
     * @param exclusions A list of values to exclude from the base array.
     * @return A list containing the values in array, minus the ones in exclusions.
     */
    public static <T> List<T> without(List<T> array, List<T> exclusions){
        Objects.requireNonNull(array);
        Objects.requireNonNull(exclusions);
        List<T> withoutArray = new ArrayList<>();
        for(T value : array){
            if(!exclusions.contains(value)){
                withoutArray.add(value);
            }
        }
        return withoutArray;
    }
}
