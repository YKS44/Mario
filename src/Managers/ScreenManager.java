package Managers;
import java.util.Arrays;

import Utils.Coordinate;

public class ScreenManager {
    private static ScreenManager instance = null;

    /*
     * Positive x is this way →	
     * Positive y is this way ↓
     * 
     */

    private String[] currentScreen = {
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜"
    };

    private volatile String[] cachedScreen = {
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "🟩⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜"
    };

    private ScreenManager()
    {
        Thread screenThread = new Thread(()->{
            while(true)
            {
                updateScreen();

                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        screenThread.start();
    }

    private void updateScreen()
    {
        if(!Arrays.equals(currentScreen, cachedScreen))
        {
            clearScreen();
            currentScreen = Arrays.copyOf(cachedScreen, cachedScreen.length);
            for(int i = 0; i < currentScreen.length; i++)
            {
                System.out.println(currentScreen[i]);
            }
        }
    }

    public synchronized void moveObject(Coordinate target, Coordinate prev, String sprite)
    {
        String newColumn = cachedScreen[target.getY()];

        if(target.getY() == prev.getY())
        {
            newColumn = newColumn.replace(sprite, "⬛");
            newColumn = newColumn.substring(0,target.getX()) + sprite + newColumn.substring(target.getX()+1);

            cachedScreen[target.getY()] = newColumn;  
        }
        else
        {

            String removeTrace = cachedScreen[prev.getY()];
            removeTrace = removeTrace.replace(sprite, "⬛");
            
            newColumn = newColumn.substring(0,target.getX()) + sprite + newColumn.substring(target.getX()+1);

            cachedScreen[prev.getY()] = removeTrace;
            cachedScreen[target.getY()] = newColumn;
        }

    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ScreenManager getInstance()
    {
        if(instance == null)
        {
            instance = new ScreenManager();
        }
        return instance;
    }
}
