/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import interfaces.ArrayPredicate;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
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
}
