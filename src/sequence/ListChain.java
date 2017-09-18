/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import interfaces.ArrayFunction;
import interfaces.ArrayPredicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lodash.Arrays;
import lodash.Collections;

/**
 * A chaining class to make calling a sequence of methods easier.
 * All methods which return a new List return a different ListChain than
 * the current one. All methods which mutate the current List return the
 * same ListChain.
 * @author Justis
 * @param <T> The type in the list.
 */
public class ListChain<T> {
    
    private final List<T> list;
    
    /**
     * Start a chain with a list.
     * @param list The list to chain.
     */
    public ListChain(List<T> list){
        this.list = list;
    }
    
    /**
     * Retrieve the value in the chain.
     * @return The current list.
     */
    public List<T> value(){
        return list;
    }
    
    /**
     * Retrieve the value in the chain, as an array.
     * @param array The template array.
     * @return The current list as an array.
     */
    public T[] valueAsArray(T[] array){
        return list.toArray(array);
    }
    
    /**
     * Retrieve the value in the chain, as an array.
     * @return The current list as an array.
     */
    public Object[] valueAsArray(){
        return list.toArray();
    }
    
    /**
     * Concatenates the contained list with the provided list.
     * @param toConcat The list to concatenate with the internal list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> concat(List<T> toConcat){
        return new ListChain(Arrays.concat(list, toConcat));
    }
    
    /**
     * Completes the chain by combining the array into a string.
     * @param separator The separator to use.
     * @return The joined array.
     */
    public String join(String separator){
        return Arrays.join(list, separator);
    }
    
    /**
     * Completes the chain by combining the array into a string.
     * @return The joined array.
     */
    public String join(){
        return Arrays.join(list);
    }
    
    /**
     * Removes the last element from the list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pop(){
        if(!list.isEmpty()){
            list.remove(list.size() - 1);
        }
        return this;
    }
    
    /**
     * Adds an element to the end of a list.
     * @param value The value to add.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> push(T value){
        list.add(value);
        return this;
    }
    
    /**
     * Remove the first element from the list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> shift(){
        if(!list.isEmpty()){
            list.remove(0);
        }
        return this;
    }
    
    /**
     * Adds an element to the beginning of the list.
     * @param value The value to add.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> unshift(T value){
        list.add(0, value);
        return this;
    }
    
    /**
     * Sorts the list.
     * @param comparator The comparator to use during sorting.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> sort(Comparator<T> comparator){
        java.util.Collections.sort(list, comparator);
        return this;
    }
    
    /**
     * Insert a value at a certain index.
     * @param index The index to insert.
     * @param value The value to insert.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> insert(int index, T value){
        list.add(index, value);
        return this;
    }
    
    /**
     * Insert a value at a certain index.
     * @param index The index to insert.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> remove(int index){
        list.remove(index);
        return this;
    }
    
    /**
     * Turns a list into a list of lists, with each sublist the specified size.
     * @param size The size to chunk into.
     * @return The ListChain to continue chaining.
     */
    public ListChain<List<T>> chunk(int size){
        return new ListChain<>(Arrays.chunk(list, size));
    }
    
    /**
     * Removes invalid values from the source list.
     * @param invalid The list of invalid values.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> compact(List<T> invalid){
        return new ListChain<>(Arrays.compact(list, invalid));
    }
    
    //TODO: countBy
    
    /**
     * Removes values from the internal list in the list of values.
     * @param values The values to remove from the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> difference(List<T>... values){
        return new ListChain<>(Arrays.difference(list, java.util.Arrays.asList(values)));
    }
    
    /**
     * Removes the specified values, after going through an iterator before comparison.
     * @param <R> The type each value is converted to before comparison.
     * @param values The values to exclude.
     * @param iteratee The iteratee to do conversion before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> differenceBy(List<List<T>> values, Function<T, R> iteratee){
        return new ListChain<>(Arrays.differenceBy(list, values, iteratee));
    }
    
    /**
     * Removes the specified values, using a BiPredicate to determine equality.
     * @param <R> The type of the list of lists of values.
     * @param values The values to remove from the source list.
     * @param predicate The predicate to determine equality.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> differenceWith(List<List<R>> values, BiPredicate<T, R> predicate){
        return new ListChain<>(Arrays.differenceWith(list, values, predicate));
    }
    
    /**
     * Removes the specified values, using a Comparator to determine equality.
     * @param values The values to remove from the soruce list.
     * @param predicate The comparator to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> differenceWith(List<List<T>> values, Comparator<T> predicate){
        return new ListChain<>(Arrays.differenceWith(list, values, predicate));
    }
    
    /**
     * Removes the first n elements from the source list.
     * @param n The number of elements to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> drop(int n){
        return new ListChain<>(Arrays.drop(list, n));
    }
    
    /**
     * Remove the first element from the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> drop(){
        return new ListChain<>(Arrays.drop(list));
    }
    
    /**
     * Remove the last n elements from the source list.
     * @param n The number of elements to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropRight(int n){
        return new ListChain<>(Arrays.dropRight(list, n));
    }
    
    /**
     * Remove the last element from the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropRight(){
        return new ListChain<>(Arrays.dropRight(list));
    }
    
    /**
     * Remove elements from the front of the list until the predicate returns false.
     * @param predicate The predicate to determine when to stop.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropWhile(ArrayPredicate<T> predicate){
        return new ListChain<>(Arrays.dropWhile(list, predicate));
    }
    
    /**
     * Remove elements from the front of the list until the predicate returns false.
     * @param predicate The predicate to determine when to stop.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropWhile(Predicate<T> predicate){
        return new ListChain<>(Arrays.dropWhile(list, predicate));
    }
    
    /**
     * Remove elements from the end of the list until the predicate returns false.
     * @param predicate The predicate to determine when to stop.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropRightWhile(ArrayPredicate<T> predicate){
        return new ListChain<>(Arrays.dropRightWhile(list, predicate));
    }
    
    /**
     * Remove elements from the end of the list until the predicate returns false.
     * @param predicate The predicate to determine when to stop.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> dropRightWhile(Predicate<T> predicate){
        return new ListChain<>(Arrays.dropRightWhile(list, predicate));
    }
    
    /**
     * Fill the source array with a value, from start to end.
     * @param value The value to fill with.
     * @param start The index to start filling (inclusive)
     * @param end The index to stop filling (exclusive)
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> fill(T value, int start, int end){
        Arrays.fill(list, value, start, end);
        return this;
    }
    
    /**
     * Fill the source array with a value, from start to the end of the list.
     * @param value The value to fill with.
     * @param start The index to start filling (inclusive)
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> fill(T value, int start){
        Arrays.fill(list, value, start);
        return this;
    }
    
    /**
     * Fill the source array with a value.
     * @param value The value to fill with.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> fill(T value){
        Arrays.fill(list, value);
        return this;
    }
    
    /**
     * Removes all elements that fail the predicate.
     * @param predicate The predicate to determine if a value should be kept.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> filter(ArrayPredicate<T> predicate){
        return new ListChain<>(Collections.filter(list, predicate));
    }
    
    /**
     * Removes all elements that fail the predicate.
     * @param predicate The predicate to determine if a value should be kept.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> filter(Predicate<T> predicate){
        return new ListChain<>(Collections.filter(list, predicate));
    }
    
    //TODO: GroupBy
    
    /**
     * Remove the last element from the list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> initial(){
        return new ListChain<>(Arrays.initial(list));
    }
    
    /**
     * Create an array composed only of values in the source array and all provided arrays.
     * @param arrays The arrays to intersect with.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> intersection(List<T>... arrays){
        List<List<T>> arraysCopy = java.util.Arrays.asList(arrays);
        arraysCopy.add(0, list);
        return new ListChain<>(Arrays.intersection(arraysCopy));
    }
    
    /**
     * Create an array composed only of values in the source array and all provided arrays.
     * All values are run through the provided iteratee before comparison.
     * @param <R> The type converted to before checking equality.
     * @param arrays The arrays to intersect with.
     * @param iteratee The function to convert each value before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> intersectionBy(List<List<T>> arrays, Function<T, R> iteratee){
        List<List<T>> arraysCopy = new ArrayList<>(arrays);
        arraysCopy.add(0, list);
        return new ListChain<>(Arrays.intersectionBy(arraysCopy, iteratee));
    }
    
    /**
     * Create an array composed only of values in the source array and all provided arrays.
     * The provided BiPredicate is used to determine equality.
     * @param arrays The arrays to intersect with.
     * @param predicate The predicate to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> intersectionWith(List<List<T>> arrays, BiPredicate<T, T> predicate){
        List<List<T>> arraysCopy = new ArrayList<>(arrays);
        arraysCopy.add(0, list);
        return new ListChain<>(Arrays.intersectionWith(arraysCopy, predicate));
    }
    
    /**
     * Create an array composed only of values in the source array and all provided arrays.
     * The provided Comparator is used to determine equality.
     * @param arrays The arrays to intersect with.
     * @param comparator The comparator to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> intersectionWith(List<List<T>> arrays, Comparator<T> comparator){
        List<List<T>> arraysCopy = new ArrayList<>(arrays);
        arraysCopy.add(0, list);
        return new ListChain<>(Arrays.intersectionWith(arraysCopy, comparator));
    }
    
    /**
     * Creates an array by running each element in the source list through iteratee.
     * @param <R> The type converting to.
     * @param iteratee The mapping function.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<R> map(ArrayFunction<T, R> iteratee){
        return new ListChain<>(Collections.map(list, iteratee));
    }
    
    /**
     * Creates an array by running each element in the source list through iteratee.
     * @param <R> The type converting to.
     * @param iteratee The mapping function.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<R> map(Function<T, R> iteratee){
        return new ListChain<>(Collections.map(list, iteratee));
    }
    
    /**
     * Divides the list into values that passed a predicate, and those that failed.
     * @param predicate The dividing predicate
     * @return The ListChain to continue chaining.
     */
    public ListChain<List<T>> partition(Predicate<T> predicate){
        return new ListChain<>(Collections.partition(list, predicate));
    }
    
    /**
     * Removes all supplied values from the source list.
     * @param values The values to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pull(T... values){
        Arrays.pull(list, values);
        return this;
    }
    
    /**
     * Removes all supplied values from the source list.
     * @param values The values to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pullAll(List<T> values){
        Arrays.pullAll(list, values);
        return this;
    }
    
    /**
     * Removes all supplied values from the source list.
     * All values are run through the supplied iteratee before testing equality.
     * @param <R> The type converted to before equality checking.
     * @param values The values to remove.
     * @param iteratee The function to convert values before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> pullAllBy(List<T> values, Function<T, R> iteratee){
        Arrays.pullAllBy(list, values, iteratee);
        return this;
    }
    
    /**
     * Removes all supplied values from the source list.
     * Equality is tested by using the provided predicate.
     * @param <R> The type of the values list.
     * @param values The values to remove.
     * @param predicate The function to determine equality.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> pullAllWith(List<R> values, BiPredicate<T, R> predicate){
        Arrays.pullAllWith(list, values, predicate);
        return this;
    }
    
    /**
     * Removes all supplied values from the source list.
     * Equality is tested by using the provided comparator.
     * @param values The values to remove.
     * @param comparator The function to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pullAllWith(List<T> values, Comparator<T> comparator){
        Arrays.pullAllWith(list, values, comparator);
        return this;
    }
    
    /**
     * Remove the values at the specified indexes from the source list.
     * @param indexes The indexes to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pullAt(List<Integer> indexes){
        Arrays.pullAt(list, indexes);
        return this;
    }
    
    /**
     * Remove the values at the specified index from the source list.
     * @param index The index to removed.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> pullAt(int index){
        Arrays.pullAt(list, index);
        return this;
    }
    
    /**
     * Remove all elements that predicate tests as true.
     * @param predicate The predicate to determine removal.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> reject(ArrayPredicate<T> predicate){
        return new ListChain<>(Collections.reject(list, predicate));
    }
    
    /**
     * Remove all elements that predicate tests as true.
     * @param predicate The predicate to determine removal.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> reject(Predicate<T> predicate){
        return new ListChain<>(Collections.reject(list, predicate));
    }
    
    /**
     * Reverses the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> reverse(){
        Arrays.reverse(list);
        return this;
    }
    
    /**
     * Retrieves n random elements from the source list.
     * @param n The number of elements to get.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> sampleSize(int n){
        return new ListChain<>(Collections.sampleSize(list, n));
    }
    
    /**
     * Shuffles the values in the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> shuffle(){
        return new ListChain<>(Collections.shuffle(list));
    }
    
    /**
     * Creates a slice of the source list from start (inc) to end (exc)
     * @param start The starting index.
     * @param end The ending index.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> slice(int start, int end){
        return new ListChain<>(Arrays.slice(list, start, end));
    }
    
    /**
     * Retrieve the unique values of a sorted list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> sortedUniq(){
        return new ListChain<>(Arrays.sortedUniq(list));
    }
    
    /**
     * Retrieves the unique values of a sorted list.
     * Elements are passed through the iteratee before being evaluated for equality.
     * @param <R> The type converted to before conversion.
     * @param iteratee The iteratee to convert values before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> sortedUniqBy(Function<T, R> iteratee){
        return new ListChain<>(Arrays.sortedUniqBy(list, iteratee));
    }
    
    /**
     * Retrieve all but the first element of the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> tail(){
        return new ListChain<>(Arrays.tail(list));
    }
    
    /**
     * Creates a slice of the first n elements in the source list.
     * @param n The number of elements to retrieve.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> take(int n){
        return new ListChain<>(Arrays.take(list, n));
    }
    
    /**
     * Create a slice of the last n element in the source list.
     * @param n The number of elements to retrieve.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> takeRight(int n){
        return new ListChain<>(Arrays.takeRight(list, n));
    }
    
    /**
     * Retrieves elements from the beginning of the source list, until predicate returns false.
     * @param predicate The predicate to determine when to stop taking.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> takeWhile(ArrayPredicate<T> predicate){
        return new ListChain<>(Arrays.takeWhile(list, predicate));
    }
    
    /**
     * Retrieves elements from the beginning of the source list, until predicate returns false.
     * @param predicate The predicate to determine when to stop taking.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> takeWhile(Predicate<T> predicate){
        return new ListChain<>(Arrays.takeWhile(list, predicate));
    }
    
    /**
     * Retrieves elements from the end of the source list, until predicate returns false.
     * @param predicate The predicate to determine when to stop taking.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> takeRightWhile(ArrayPredicate<T> predicate){
        return new ListChain<>(Arrays.takeRightWhile(list, predicate));
    }
    
    /**
     * Retrieves elements from the end of the source list, until predicate returns false.
     * @param predicate The predicate to determine when to stop taking.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> takeRightWhile(Predicate<T> predicate){
        return new ListChain<>(Arrays.takeRightWhile(list, predicate));
    }
    
    /**
     * Allows tapping into a chain sequence to do some logic.
     * @param consumer The function that does work on the source list.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> tap(Consumer<List<T>> consumer){
        consumer.accept(list);
        return this;
    }
    
    /**
     * Allows tapping into a chain sequence to convert the source list in some way.
     * This is like tap, except the chain continues with the results of applying
     * the function, rather than modifying the source list directly.
     * @param <R> The type contained in the new list.
     * @param function The function to convert the source list into a new list.
     * @return The ListChain wrapping the results of applying function with the source list.
     */
    public <R> ListChain<R> thru(Function<List<T>, List<R>> function){
        return new ListChain<>(function.apply(list));
    }
    
    /**
     * Creates a list containing all values in the source and provided lists, removing duplicates.
     * @param arrays The arrays to union with.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> union(List<T>... arrays){
        List<List<T>> copy = java.util.Arrays.asList(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.union(copy));
    }
    
    /**
     * Creates a list containing all values in the source and provided lists, removing duplicates.
     * All values are run through iteratee before comparison.
     * @param arrays The arrays to union.
     * @param iteratee The mapping function called before comparison.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> unionBy(List<List<T>> arrays, Function<T, T> iteratee){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.unionBy(arrays, iteratee));
    }
    
    /**
     * Creates a list containing all values in the source and provided lists, removing duplicates.
     * Equality is tested by running each comparison through the supplied BiPredicate.
     * @param arrays The arrays to union.
     * @param predicate The comparison function.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> unionWith(List<List<T>> arrays, BiPredicate<T, T> predicate){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.unionWith(arrays, predicate));
    }
    
    /**
     * Creates a list containing all values in the source and provided lists, removing duplicates.
     * Equality is tested by running each comparison through the supplied Comparator.
     * @param arrays The arrays to union.
     * @param comparator The comparison function.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> unionWith(List<List<T>> arrays, Comparator<T> comparator){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.unionWith(arrays, comparator));
    }
    
    /**
     * Reduce the source list to only unique entries.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> uniq(){
        return new ListChain<>(Arrays.uniq(list));
    }
    
    /**
     * Reduce the source list to only unique entries.
     * Each entry is run through iteratee before comparison.
     * @param <R> The type mapped into before comparison.
     * @param iteratee The function to map values before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> uniqBy(Function<T, R> iteratee){
        return new ListChain<>(Arrays.uniqBy(list, iteratee));
    }
    
    /**
     * Reduce the source list to only unique entries.
     * Equality is determined using the provided comparator.
     * @param comparator The comparator to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> uniqWith(BiPredicate<T, T> comparator){
        return new ListChain<>(Arrays.uniqWith(list, comparator));
    }
    
    /**
     * Reduce the source list to only unique entries.
     * Equality is determined using the provided comparator.
     * @param comparator The comparator to determine equality.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> uniqWith(Comparator<T> comparator){
        return new ListChain<>(Arrays.uniqWith(list, comparator));
    }
    
    /**
     * Remove the specified value from the list.
     * @param values The values to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> without(List<T> values){
        return new ListChain<>(Arrays.without(list, values));
    }
    
    /**
     * Remove the specified value from the list.
     * @param values The values to remove.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> without(T... values){
        return new ListChain<>(Arrays.without(list, values));
    }
    
    /**
     * Create an array of unique values that are the symmetric difference of the given arrays.
     * Note that this is not a true xor, like the original. More than one
     * occurrence of a value results in its exclusion.
     * @param arrays The arrays to xor.
     * @return The ListChain to continue chaining.
     */
    public ListChain<T> xor(List<T>... arrays){
        List<List<T>> copy = java.util.Arrays.asList(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.xor(copy));
    }
    
    /**
     * Create an array of unique values that are the symmetric difference of the given arrays.
     * Note that this is not a true xor, like the original. More than one
     * occurrence of a value results in its exclusion.
     * @param <R> The type to convert to before comparison.
     * @param arrays The arrays to xor.
     * @param iteratee The mapping function invoked before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> xorBy(List<List<T>> arrays, Function<T, R> iteratee){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.xorBy(copy, iteratee));
    }
    
    /**
     * Create an array of unique values that are the symmetric difference of the given arrays.
     * Note that this is not a true xor, like the original. More than one
     * occurrence of a value results in its exclusion.
     * @param <R> The type to convert to before comparison.
     * @param arrays The arrays to xor.
     * @param comparator The mapping function invoked before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> xorWith(List<List<T>> arrays, BiPredicate<T, T> comparator){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.xorWith(copy, comparator));
    }
    
    /**
     * Create an array of unique values that are the symmetric difference of the given arrays.
     * Note that this is not a true xor, like the original. More than one
     * occurrence of a value results in its exclusion.
     * @param <R> The type to convert to before comparison.
     * @param arrays The arrays to xor.
     * @param comparator The mapping function invoked before comparison.
     * @return The ListChain to continue chaining.
     */
    public <R> ListChain<T> xorWith(List<List<T>> arrays,Comparator<T> comparator){
        List<List<T>> copy = new ArrayList<>(arrays);
        copy.add(0, list);
        return new ListChain<>(Arrays.xorWith(copy, comparator));
    }
}
