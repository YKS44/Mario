package Actions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class HoldAction implements Action{
    private final AtomicBoolean isHolding;

    public HoldAction(Runnable action)
    {
        this.isHolding = new AtomicBoolean(false);
        CompletableFuture.runAsync(()->{
            while(true)
            {
                if(isHolding.get())
                {
                    action.run();
                    try{
                        Thread.sleep(50);
                    }catch(InterruptedException e){}
                }
            }
        });
    }

    @Override
    public void onPressed() {
        isHolding.set(true);
    }

    @Override
    public void onReleased() {
        isHolding.set(false);
    }
    
}
