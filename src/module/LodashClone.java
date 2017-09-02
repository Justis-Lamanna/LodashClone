/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lodash.Maths;
import lodash.Numbers;

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
        for(int index = 0; index < 10; index++){
            System.out.println(Numbers.random());
        }
    }
}
