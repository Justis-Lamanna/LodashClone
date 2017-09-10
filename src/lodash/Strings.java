/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.text.Normalizer;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String methods
 * @author Justis
 */
public class Strings {
    
    /**A regex that matches beginning and ending non-alphanumerics*/
    private static final Pattern NON_ALPHANUM_AT_ENDS = Pattern.compile("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$");
    /**A regex that matches modifiers (Umlauts, accent marks, etc)*/
    private static final Pattern MODIFIERS = Pattern.compile("\\p{M}");
    /**A regex that matches regex special characters*/
    private static final Pattern REGEX = Pattern.compile("[\\^\\$\\.\\*\\+\\?\\(\\)\\[\\]\\{\\}\\|]");
    /**A regex used to split for word boundaries
     * This means:
     * Lower or uppercase to uppercase (fooBar -> foo Bar, ABC -> A B C)
     * A non-alphanumeric (foo-bar -> foo bar)
     * Changing from a letter to a number (foo5 -> foo 5)
     * Changing from a number to a litter (5bar -> 5 bar)
     * Used in converting words to certain cases, such as camel case.
     */
    private static final Pattern SPLIT_ON_BOUNDARY = Pattern.compile(
            "(?<=[a-z])(?=[A-Z])|" //Lowercase to uppercase.
            + "[^A-Za-z0-9]+|" //A non-alphanumeric.
            + "(?<=[a-zA-Z])(?=[0-9])|" //Letter to number.
            + "(?<=[0-9])(?=[a-zA-Z])"); //Number to letter.
    
    /**A regex that matches whitespace at the end of a string*/
    private static final Pattern WHITESPACE_AT_END = Pattern.compile("\\s+$");
    /**A regex that matches whitespace at the start of a string*/
    private static final Pattern WHITESPACE_AT_START = Pattern.compile("^\\s+");
    
    private Strings(){
        throw new IllegalStateException("Cannot instantiate Strings");
    }
    
    /**
     * Internal function to trim unwanted characters from the beginning and ends.
     * @param string The string to pull from.
     * @param regex The regex to trim.
     * @return The trimmed string.
     */
    private static String iTrim(String string, Pattern regex){
        return regex.matcher(string).replaceAll("");
    }
    
    /**
     * Camel-cases a string.
     * The string is deburred, and all non-alphanumeric characters are removed. 
     * The first word is all lowercase, while the remainder are capitalized.
     * @param string The string to camel-case.
     * @return The camel-cased string.
     */
    public static String camelCase(String string){
        Objects.requireNonNull(string);
        string = iSplitJoin(string, "", Strings::capitalize);
        return lowerFirst(string);
    }
    
    /**
     * Capitalizes the first character in a string, and lowercases the rest.
     * @param word The word to capitalize.
     * @return The capitalized word.
     */
    public static String capitalize(String word){
        Objects.requireNonNull(word);
        switch(word.length()){
            case 0: return "";
            case 1: return word.toUpperCase();
            default: return word.substring(0, 1).toUpperCase() + 
                    word.substring(1).toLowerCase();
        }
    }
    
    /**
     * Removes all accent marks from a string.
     * @param string The string to deburr.
     * @return The deburred string.
     */
    public static String deburr(String string){
        Objects.requireNonNull(string);
        String normalized = Normalizer.normalize(string, Normalizer.Form.NFD);
        return MODIFIERS.matcher(normalized).replaceAll("");
    }
    
    /**
     * Check if a string ends with some target string.
     * The string is sliced from 0 to length, and is then checked
     * if the sliced string ends in target.
     * @param string The string to search.
     * @param target The target string.
     * @param length The index of the 'end'.
     * @return True if the string ends with target.
     */
    public static boolean endsWith(String string, String target, int length){
        Objects.requireNonNull(string);
        Objects.requireNonNull(target);
        if(length < 0 || length > string.length()){
            throw new IndexOutOfBoundsException("length must be between 0 and " + string.length());
        }
        return string.substring(0, length).endsWith(target);
    }
    
    /**
     * Check if a string ends with some target string.
     * @param string The string to search.
     * @param target The target string.
     * @return True if the string ends with target.
     */
    public static boolean endsWith(String string, String target){
        Objects.requireNonNull(string);
        Objects.requireNonNull(target);
        return string.endsWith(target);
    }
    
    /**A regex that matches &amp;**/
    private static final Pattern AMPERSAND = Pattern.compile("&");
    /**A regex that matches &lt;**/
    private static final Pattern LESS_THAN = Pattern.compile("<");
    /**A regex that matches &gt;**/
    private static final Pattern GREATER_THAN = Pattern.compile(">");
    /**A regex that matches "**/
    private static final Pattern QUOTATION_MARK = Pattern.compile("\"");
    /**A regex that matches '**/
    private static final Pattern APOSTROPHE = Pattern.compile("'");
    
    /**
     * Replaces &amp;, &lt;, &gt;, ", and ' with HTML-friendly versions.
     * @param string The string to escape.
     * @return The escaped string.
     */
    public static String escape(String string){
        Objects.requireNonNull(string);
        string = AMPERSAND.matcher(string).replaceAll("&amp;");
        string = LESS_THAN.matcher(string).replaceAll("&lt;");
        string = GREATER_THAN.matcher(string).replaceAll("&gt;");
        string = QUOTATION_MARK.matcher(string).replaceAll("&quot;");
        string = APOSTROPHE.matcher(string).replaceAll("&apos;");
        return string;
    }
    
    /**
     * Escapes all regex-specific characters in a string.
     * Escapes ^, $, ., *, +, ?, (, ), [, ], {, }, and |.
     * @param string The string to escape.
     * @return The escaped string.
     */
    public static String escapeRegExp(String string){
        Objects.requireNonNull(string);
        Matcher m = REGEX.matcher(string);
        return m.replaceAll("\\\\$0");
    }
    
    /**
     * Splits a string along boundaries, and joins again.
     * @param string The string to convert.
     * @param separator The string to place between splits.
     * @param transform Transform the string before appending (upper/lowercase)
     * @return The converted string.
     */
    public static String iSplitJoin(String string, String separator, Function<String, String> transform){
        string = iTrim(deburr(string), NON_ALPHANUM_AT_ENDS);
        String[] split = SPLIT_ON_BOUNDARY.split(string);
        StringBuilder builder = new StringBuilder();
        for(int index = 0; index < split.length; index++){
            if(index != 0){
                builder.append(separator);
            }
            builder.append(transform.apply(split[index]));
        }
        return builder.toString();
    }
    
    /**
     * The string is deburred, and split along non-alphanumeric characters,
     * changes from lower to upper case, or changes from letter to number.
     * Hyphens are inserted between each split.
     * @param string The string to kebab-case.
     * @return The kebabed string.
     */
    public static String kebabCase(String string){
        Objects.requireNonNull(string);
        return iSplitJoin(string, "-", String::toLowerCase);
    }
    
    /**
     * Converts string, as space separated words, to lowercase.
     * The string is deburred, and non alphanumeric
     * characters are removed. Changes from letter to number, or changes in case,
     * have spaces placed between them. All words are converted to all lowercase.
     * @param string The string to lowercase.
     * @return The lowercased string.
     */
    public static String lowerCase(String string){
        Objects.requireNonNull(string);
        return iSplitJoin(string, " ", String::toLowerCase);
    }
    
    /**
     * Converts the first character of a string to lowercase.
     * @param string The string to modify.
     * @return The modified string.
     */
    public static String lowerFirst(String string){
        Objects.requireNonNull(string);
        switch(string.length()){
            case 0: return "";
            case 1: return string.toLowerCase();
            default: return string.substring(0, 1).toLowerCase() + string.substring(1);
        }
    }
    
    /**
     * Internal function to perform padding.
     * @param string The string to pad.
     * @param padding The padding string to use.
     * @param leftPaddingLength The number of characters on the left side.
     * @param rightPaddingLength The number of characters on the right side.
     * @return The padded string.
     */
    private static String iPad(String string, String padding, int leftPaddingLength, int rightPaddingLength){
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < leftPaddingLength; index++){
            sb.append(padding.charAt(index % padding.length()));
        }
        sb.append(string);
        for(int index = 0; index < rightPaddingLength; index++){
            sb.append(padding.charAt(index % padding.length()));
        }
        return sb.toString();
    }
    
    /**
     * Pads the start and end of a string with another string.
     * @param string The string to pad.
     * @param length The length the return string should be.
     * @param padding The padding string, repeated or truncated as necessary.
     * @return The padded string.
     */
    public static String pad(String string, int length, String padding){
        Objects.requireNonNull(string);
        Objects.requireNonNull(padding);
        int paddingLength = length - string.length();
        if(paddingLength <= 0){
            return string;
        }
        int leftPaddingLength = paddingLength / 2;
        int rightPaddingLength = paddingLength - leftPaddingLength;
        return iPad(string, padding, leftPaddingLength, rightPaddingLength);
    }
    
    /**
     * Pads the start and end of a string with spaces.
     * @param string The string to pad.
     * @param length The length the final string should be.
     * @return The padded string.
     */
    public static String pad(String string, int length){
        return pad(string, length, " ");
    }
    
    /**
     * Pads the end of a string with another string.
     * @param string The string to pad.
     * @param length The length the final string should be.
     * @param padding The padding string, repeated or truncated as necessary.
     * @return The padded string.
     */
    public static String padEnd(String string, int length, String padding){
        Objects.requireNonNull(string);
        Objects.requireNonNull(padding);
        int paddingLength = length - string.length();
        if(paddingLength <= 0){
            return string;
        }
        return iPad(string, padding, 0, paddingLength);
    }
    
    /**
     * Pads the end of a string with spaces
     * @param string The string to pad.
     * @param length The length the final string should be.
     * @return The padded string.
     */
    public static String padEnd(String string, int length){
        return padEnd(string, length, " ");
    }
    
    /**
     * Pads the start of a string with another string.
     * @param string The string to pad.
     * @param length The length the final string should be.
     * @param padding The padding string, repeated or truncated as necessary.
     * @return The padded string.
     */
    public static String padStart(String string, int length, String padding){
        Objects.requireNonNull(string);
        Objects.requireNonNull(padding);
        int paddingLength = length - string.length();
        if(paddingLength <= 0){
            return string;
        }
        return iPad(string, padding, paddingLength, 0);
    }
    
    /**
     * Pads the start of a string with spaces
     * @param string The string to pad.
     * @param length The length the final string should be.
     * @return The padded string.
     */
    public static String padStart(String string, int length){
        return padStart(string, length, " ");
    }
    
    /**
     * Converts a string into an integer.
     * @param string The string to convert.
     * @param radix The radix of the number.
     * @return The converted number.
     */
    public static int parseInt(String string, int radix){
        return Integer.parseInt(string, radix);
    }
    
    /**
     * Converts a string into an integer.
     * @param string The string to convert.
     * @return The converted number.
     */
    public static int parseInt(String string){
        return Integer.parseInt(string);
    }
    
    /**
     * Repeat a string some amount of times.
     * @param string The string to repeat.
     * @param n The number of times to repeat.
     * @return The string, repeated n times.
     */
    public static String repeat(String string, int n){
        Objects.requireNonNull(string);
        if(n <= 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int count = 0; count < n; count++){
            sb.append(string);
        }
        return sb.toString();
    }
    
    /**
     * Replaces the first match of a pattern in a string with a replacement.
     * @param string The string to modify.
     * @param pattern The regex to match.
     * @param replacement The replacement string.
     * @return The string, with the first match replaced.
     */
    public static String replace(String string, String pattern, String replacement){
        Objects.requireNonNull(string);
        Objects.requireNonNull(pattern);
        Objects.requireNonNull(replacement);
        return string.replaceFirst(pattern, replacement);
    }
    
    /**
     * Replaces the first match of a pattern in a string with a replacement.
     * @param string The string to modify.
     * @param pattern The regex to match.
     * @param replacement The replacement string.
     * @return The string, with the first match replaced.
     */
    public static String replace(String string, Pattern pattern, String replacement){
        Objects.requireNonNull(string);
        Objects.requireNonNull(pattern);
        Objects.requireNonNull(replacement);
        return pattern.matcher(string).replaceFirst(replacement);
    }
    
    /**
     * Converts a string to snake_case.
     * The string is deburred, and non alphanumeric characters are removed. 
     * Changes from letter to number, or changes in case,
     * have underscores placed between them.
     * @param string The string to convert.
     * @return The snake_case string.
     */
    public static String snakeCase(String string){
        Objects.requireNonNull(string);
        return iSplitJoin(string, "_", String::toLowerCase);
    }
    
    /**
     * Splits a string along a regex, up to limit times.
     * @param string The string to split.
     * @param regex The regex to split along.
     * @param limit The length to truncate results to.
     * @return The split string.
     */
    public static String[] split(String string, String regex, int limit){
        Objects.requireNonNull(string);
        Objects.requireNonNull(regex);
        return java.util.Arrays.copyOf(string.split(regex), limit);
    }
    
    /**
     * Splits a string along a regex, up to limit times.
     * @param string The string to split.
     * @param regex The regex to split along.
     * @param limit The length to truncate results to.
     * @return The split string.
     */
    public static String[] split(String string, Pattern regex, int limit){
        Objects.requireNonNull(string);
        Objects.requireNonNull(regex);
        return java.util.Arrays.copyOf(regex.split(string), limit);
    }
    
    /**
     * Splits a string along a regex.
     * @param string The string to split.
     * @param regex The regex to split along.
     * @return The split string.
     */
    public static String[] split(String string, String regex){
        Objects.requireNonNull(string);
        Objects.requireNonNull(regex);
        return string.split(regex);
    }
    
    /**
     * Splits a string along a regex.
     * @param string The string to split.
     * @param regex The regex to split along.
     * @return The split string.
     */
    public static String[] split(String string, Pattern regex){
        Objects.requireNonNull(string);
        Objects.requireNonNull(regex);
        return regex.split(string);
    }
    
    /**
     * Converts a string to Start Case
     * The string is deburred, and non alphanumeric characters are removed. 
     * Changes from letter to number, or changes in case,
     * have spaces placed between them. Each word then has the first letter capitalized.
     * @param string The string to modify.
     * @return The modified string.
     */
    public static String startCase(String string){
        Objects.requireNonNull(string);
        return iSplitJoin(string, " ", Strings::upperFirst);
    }
    
    /**
     * Checks if the string starts with the target.
     * @param string The string to check.
     * @param target The string to search for.
     * @param length The index to consider the 'start'.
     * @return True if the string starts with the specified target.
     */
    public static boolean startsWith(String string, String target, int length){
        Objects.requireNonNull(string);
        Objects.requireNonNull(target);
        if(length < 0 || length > string.length()){
            throw new IndexOutOfBoundsException("length must be between 0 and " + string.length());
        }
        return string.substring(length).startsWith(target);
    }
    
    /**
     * Checks if the string starts with the target.
     * @param string The string to check.
     * @param target The string to search for.
     * @return True if the string starts with the specified target.
     */
    public static boolean startsWith(String string, String target){
        Objects.requireNonNull(string);
        Objects.requireNonNull(target);
        return string.startsWith(target);
    }
    
    /**
     * Converts a string to studlyCase.
     * Characters are randomly converted to upper or lower case.
     * @param string The string to convert.
     * @return The studly string.
     */
    public static String studlyCase(String string){
        Objects.requireNonNull(string);
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for(int index = 0; index < string.length(); index++){
            if(rnd.nextBoolean()){
                sb.append(Character.toUpperCase(string.charAt(index)));
            } else {
                sb.append(Character.toLowerCase(string.charAt(index)));
            }
        }
        return sb.toString();
    }
    
    /**
     * Converts an entire string to lowercase.
     * @param string The string to convert.
     * @return The lowercase string.
     */
    public static String toLower(String string){
        Objects.requireNonNull(string);
        return string.toLowerCase();
    }
    
    /**
     * Converts an entire string to uppercase.
     * @param string The string to convert.
     * @return The uppercase string.
     */
    public static String toUpper(String string){
        Objects.requireNonNull(string);
        return string.toLowerCase();
    }
    
    /**
     * Remove the specified characters from the beginning and ends of the string.
     * @param string The string to trim.
     * @param charsToRemove The characters to remove.
     * @return The trimmed string.
     */
    public static String trim(String string, String charsToRemove){
        Objects.requireNonNull(string);
        Objects.requireNonNull(charsToRemove);
        Pattern charRegex = Pattern.compile(
                "^[" + escapeRegExp(charsToRemove) + "]+|" +
                "[" + escapeRegExp(charsToRemove) + "]+$"
        );
        return charRegex.matcher(string).replaceAll("");
    }
    
    /**
     * Remove whitespace from the beginning and ends of the string.
     * @param string The string to trim.
     * @return The trimmed string.
     */
    public static String trim(String string){
        Objects.requireNonNull(string);
        return string.trim();
    }
    
    /**
     * Remove the specified characters from the end of the string.
     * @param string The string to trim.
     * @param charsToRemove The characters to remove.
     * @return The trimmed string.
     */
    public static String trimEnd(String string, String charsToRemove){
        Objects.requireNonNull(string);
        Objects.requireNonNull(charsToRemove);
        Pattern charRegex = Pattern.compile(
                "[" + escapeRegExp(charsToRemove) + "]+$"
        );
        return charRegex.matcher(string).replaceAll("");
    }
    
    /**
     * Removes whitespace from the end of a string.
     * @param string The string to trim.
     * @return The trimmed string.
     */
    public static String trimEnd(String string){
        Objects.requireNonNull(string);
        return WHITESPACE_AT_END.matcher(string).replaceAll("");
    }
    
    /**
     * Remove the specified characters from the beginning of the string.
     * @param string The string to trim.
     * @param charsToRemove The characters to remove.
     * @return The trimmed string.
     */
    public static String trimStart(String string, String charsToRemove){
        Objects.requireNonNull(string);
        Objects.requireNonNull(charsToRemove);
        Pattern charRegex = Pattern.compile(
                "^[" + escapeRegExp(charsToRemove) + "]+"
        );
        return charRegex.matcher(string).replaceAll("");
    }
    
    /**
     * Removes whitespace from the start of a string.
     * @param string The string to trim.
     * @return The trimmed string.
     */
    public static String trimStart(String string){
        Objects.requireNonNull(string);
        return WHITESPACE_AT_START.matcher(string).replaceAll("");
    }
    
    /**
     * Truncates a string.
     * The string is truncated to be at least length characters long, including
     * the ommission string. If a separator is specified, The string is truncated
     * further, to the point where the separator matches.
     * @param string The string to truncate.
     * @param length The length of the final string, including ommission.
     * @param ommission The string to append to the end of the truncation.
     * @param separator A regex to specify a further truncation point.
     * @return The truncated string.
     */
    public static String truncate(String string, int length, String ommission, String separator){
        Objects.requireNonNull(string);
        Objects.requireNonNull(ommission);
        int stringLength = length - ommission.length();
        String truncated = string.substring(0, stringLength);
        if(separator != null){
            Matcher m = Pattern.compile(separator + "(?!.*" + separator + ")")
                    .matcher(truncated);
            if(m.find()){
                truncated = truncated.substring(0, m.start());
            }
        }
        return truncated + ommission;
    }
    
    /**
     * Truncates a string.
     * @param string The string to truncate.
     * @param length The length of the final string, including ommission.
     * @param ommission The string to append to the end of the truncation.
     * @return The truncated string.
     */
    public static String truncate(String string, int length, String ommission){
        return truncate(string, length, ommission, null);
    }
    
    /**
     * Truncates a string.
     * @param string The string to truncate.
     * @param length The length of the final string, including the string "...".
     * @return The truncated string.
     */
    public static String truncate(String string, int length){
        return truncate(string, length, "...", null);
    }
    
    /**A regex that matches &amp;amp;**/
    private static final Pattern ESC_AMPERSAND = Pattern.compile("&amp;");
    /**A regex that matches &amp;lt;**/
    private static final Pattern ESC_LESS_THAN = Pattern.compile("&lt;");
    /**A regex that matches &amp;gt;**/
    private static final Pattern ESC_GREATER_THAN = Pattern.compile("&gt;");
    /**A regex that matches &amp;quot;**/
    private static final Pattern ESC_QUOTATION_MARK = Pattern.compile("&quot;");
    /**A regex that matches &amp;apos;**/
    private static final Pattern ESC_APOSTROPHE = Pattern.compile("&apos;");
    
    /**
     * Replaces &amp;amp;, &amp;lt;, &amp;gt;, &amp;quot;, and &amp;apos;
     * with their normal versions.
     * @param string The string to unescape.
     * @return The unescaped string.
     */
    public static String unescape(String string){
        Objects.requireNonNull(string);
        string = ESC_AMPERSAND.matcher(string).replaceAll("&");
        string = ESC_LESS_THAN.matcher(string).replaceAll("<");
        string = ESC_GREATER_THAN.matcher(string).replaceAll(">");
        string = ESC_QUOTATION_MARK.matcher(string).replaceAll("\"");
        string = ESC_APOSTROPHE.matcher(string).replaceAll("'");
        return string;
    }
    
    /**
     * Converts string, as space separated words, to uppercase.
     * The string is deburred, and non alphanumeric
     * characters are removed. Changes from letter to number, or changes in case,
     * have spaces placed between them. All words are converted to all uppercase.
     * @param string The string to modify.
     * @return The modified string.
     */
    public static String upperCase(String string){
        Objects.requireNonNull(string);
        return iSplitJoin(string, " ", String::toUpperCase);
    }
            
    /**
     * Converts the first character of a string to uppercase.
     * @param string The string to modify.
     * @return The modified string.
     */
    public static String upperFirst(String string){
        Objects.requireNonNull(string);
        switch(string.length()){
            case 0: return "";
            case 1: return string.toUpperCase();
            default: return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }
}
