/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lodash.Collections;
import lodash.Dates;
import lodash.Functions;

/**
 *
 * @author justislamanna
 */
public class LodashClone {
    private LodashClone(){
        throw new IllegalStateException("Cannot instantiate Lodash");
    }
    
    public static void main(String[] args){
        Consumer<Integer> function = Functions.debounce((Integer t) -> System.out.println(t), 1000);
        for(int index = 0; index < 7; index++){
            function.accept(index);
        }
    }
}
