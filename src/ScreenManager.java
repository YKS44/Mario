import java.util.Arrays;

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
                }catch(InterruptedException e){}
            }
        });
        screenThread.start();
    }

    private void updateScreen()
    {
        if(!Arrays.equals(currentScreen, cachedScreen))
        {
            clearScreen();
            for(int i = 0; i < cachedScreen.length; i++)
            {
                System.out.println(cachedScreen[i]);
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
            // System.out.println(newColumn);

            cachedScreen[target.getY()] = newColumn;  
        }
        else
        {

            String removeTrace = cachedScreen[prev.getY()];
    
            removeTrace = removeTrace.substring(0,prev.getX()) + "⬛" + newColumn.substring(prev.getX()+1);
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
