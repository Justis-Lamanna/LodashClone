/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import interfaces.NilFunction;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The NilFunction used in the Debounce function.
 * @author Justis
 */
public class DebounceNilFunction implements NilFunction{
    
    private final NilFunction function;
    private TimerTask task;
    private Timer timer;
    private final long delay;
    private final boolean trailingEdge;
    private final boolean throttle;
    
    /**
     * Creates a debounce NilFunction from a regular one.
     * @param function The function to wrap.
     * @param delay The number of milliseconds to wait between before invocation.
     * @param trailingEdge True if this should activate on the trailing edge.
     * @param throttle True if this function should act as a throttle.
     */
    public DebounceNilFunction(NilFunction function, long delay, boolean trailingEdge, boolean throttle){
        this.function = function;
        this.task = null;
        this.timer = new Timer();
        this.delay = delay;
        this.trailingEdge = trailingEdge;
        this.throttle = throttle;
    }
    
    public DebounceNilFunction(NilFunction function, long delay, boolean trailingEdge){
        this(function, delay, trailingEdge, false);
    }
    
    @Override
    public void invoke() {
        if(timer == null){
            function.invoke();
        }
        if(task == null){
            task = createTask();
            timer.schedule(task, delay);
            if(!trailingEdge){
                function.invoke();
            }
        } else if(!throttle) {
            task.cancel();
            task = createTask();
            timer.schedule(task, delay);
        }
    }
    
    private TimerTask createTask(){
        return new TimerTask(){
            @Override
            public void run() {
                task = null;
                if(trailingEdge){
                    function.invoke();
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
     */
    public void flush(){
        function.invoke();
    }
}
