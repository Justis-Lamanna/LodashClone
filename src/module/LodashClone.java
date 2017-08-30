/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import functions.DebounceConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lodash.Collections;
import lodash.Lang;

/**
 *
 * @author justislamanna
 */
public class LodashClone {
    private LodashClone(){
        throw new IllegalStateException("Cannot instantiate Lodash");
    }
    
    public static void main(String[] args){
        List<Integer> testList = new ArrayList<>(Arrays.asList(0, 2, 2, 4, 6, 7, 8));
        List<Integer> testList2 = Arrays.asList(4, 4, 4, 9);
        List<List<Integer>> testList3 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(1, 4));
        System.out.println(Lang.castArray());
    }
}
