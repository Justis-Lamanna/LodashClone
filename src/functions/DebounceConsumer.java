/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/**
 * The Consumer used in the Debounce function.
 * @author Justis
 */
public class DebounceConsumer<T> implements Consumer<T> {

    private final Consumer<T> function;
    private TimerTask task;
    private Timer timer;
    private final long delay;
    private final boolean trailingEdge;
    private final boolean throttle;
    
    /**
     * Creates a debounce or throttle consumer from a regular one.
     * @param function The function to wrap.
     * @param delay The number of milliseconds to wait between before invocation.
     * @param trailingEdge True if this should activate on the trailing edge.
     * @param throttle True if this debounce should act as a throttle.
     */
    public DebounceConsumer(Consumer<T> function, long delay, boolean trailingEdge, boolean throttle){
        this.function = function;
        this.task = null;
        this.timer = new Timer();
        this.delay = delay;
        this.trailingEdge = trailingEdge;
        this.throttle = throttle;
    }
    
    /**
     * Creates a debounce consumer from a regular one.
     * @param function The function to wrap.
     * @param delay The number of milliseconds to wait between before invocation.
     * @param trailingEdge True if this should activate on the trailing edge.
     */
    public DebounceConsumer(Consumer<T> function, long delay, boolean trailingEdge){
        this(function, delay, trailingEdge, false);
    }
    
    @Override
    public void accept(T t) {
        if(timer == null){
           function.accept(t); 
        } else if(task == null){
            task = createTask(t);
            timer.schedule(task, delay);
            if(!trailingEdge){
                function.accept(t);
            }
        } else if(!throttle) {
            task.cancel();
            task = createTask(t);
            timer.schedule(task, delay);
        }
    }
    
    private TimerTask createTask(T t){
        return new TimerTask(){
            @Override
            public void run() {
                task = null;
                if(trailingEdge){
                    function.accept(t);
                }
            }
        };
    }
    
    /**
     * Cancel the debouncing.
     */
    public void cancel(){
        timer.cancel();
        timer = null;
    }
    
    /**
     * Immediately execute the wrapped function.
     * @param t The parameter to execute with.
     */
    public void flush(T t){
        function.accept(t);
    }
}
