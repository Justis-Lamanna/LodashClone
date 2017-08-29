/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.Date;

/**
 * Methods for dates
 * @author Justis
 */
public class Dates {
    
    private Dates(){
        throw new IllegalStateException("Can't instantiate Dates");
    }
    
    /**
     * Get the number of milliseconds since the Unix epoch.
     * @return The number of milliseconds since the Unix epoch.
     */
    public static long now(){
        return new Date().getTime();
    }
}
