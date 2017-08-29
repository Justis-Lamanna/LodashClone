/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        Function<Integer, Integer> function = Functions.before(4, i -> i + 1);
        for(int index = 0; index < 4; index++){
            System.out.println(function.apply(index));
        }
    }
}
