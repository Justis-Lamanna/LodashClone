/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Chain string methods.
 * @author Justis
 */
public class StringChain
{
    private final String string;
    
    /**
     * Starts a chain with a String.
     * @param string The string to chain.
     */
    public StringChain(String string){
        this.string = string;
    }
    
    /**
     * Retrieve the value of the chain.
     * @return The string.
     */
    public String value(){
        return string;
    }
    
    /**
     * Replace all matches to regex with replacement.
     * @param regex The regex to match.
     * @param replacement The replacement.
     * @return The StringChain to continue chaining.
     */
    public StringChain replace(String regex, String replacement){
        Objects.requireNonNull(regex);
        Objects.requireNonNull(replacement);
        return new StringChain(string.replaceAll(regex, replacement));
    }
    
    /**
     * Replace all matches to regex with replacement.
     * @param regex The regex to match.
     * @param replacement The replacement.
     * @return The StringChain to continue chaining.
     */
    public StringChain replace(Pattern regex, String replacement){
        Objects.requireNonNull(regex);
        Objects.requireNonNull(replacement);
        return new StringChain(regex.matcher(string).replaceAll(replacement));
    }
    
    /**
     * Splits a string up to limit times.
     * @param regex The regex to split on.
     * @param limit The maximum number of times to split.
     * @return A ListChain to continue chaining.
     */
    public ListChain<String> split(String regex, int limit){
        Objects.requireNonNull(regex);
        return new ListChain<>(
                java.util.Arrays.asList(string.split(regex, limit))
        );
    }
    
    /**
     * Splits a string.
     * @param regex The regex to split on.
     * @return A ListChain to continue chaining.
     */
    public ListChain<String> split(Pattern regex){
        Objects.requireNonNull(regex);
        return new ListChain<>(
                java.util.Arrays.asList(regex.split(string))
        );
    }
    
    /**
     * Splits a string up to limit times.
     * @param regex The regex to split on.
     * @param limit The maximum number of times to split.
     * @return A ListChain to continue chaining.
     */
    public ListChain<String> split(Pattern regex, int limit){
        Objects.requireNonNull(regex);
        return new ListChain<>(
                java.util.Arrays.asList(regex.split(string, limit))
        );
    }
    
    /**
     * Splits a string.
     * @param regex The regex to split on.
     * @return A ListChain to continue chaining.
     */
    public ListChain<String> split(String regex){
        Objects.requireNonNull(regex);
        return new ListChain<>(
                java.util.Arrays.asList(string.split(regex))
        );
    }
    
    /**
     * Allows tapping into a chain sequence to do some logic.
     * @param consumer The function that does work on the source string.
     * @return The StringChain to continue chaining.
     */
    public StringChain tap(Consumer<String> consumer){
        consumer.accept(string);
        return this;
    }
    
    /**
     * Allows tapping into a chain sequence to convert the source string in some way.
     * @param function The function to convert the source list into a new list.
     * @return The StringChain wrapping the results of applying function with the source string.
     */
    public StringChain thru(Function<String, String> function){
        return new StringChain(function.apply(string));
    }
}
