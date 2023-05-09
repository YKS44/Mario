public class ScreenManager {
    private static ScreenManager instance = null;

    /*
     * Positive x is this way →	
     * Positive y is this way ↓
     * 
     */

     private final String[] map = {
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛",
        "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜"
    };

    private String[] currentScreen = map;

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
        if(currentScreen != cachedScreen)
        {
            clearScreen();
            for(int i = 0; i < cachedScreen.length; i++)
            {
                System.out.println(cachedScreen[i]);
            }
            currentScreen = cachedScreen;
        }
    }

    public synchronized void moveObject(Coordinate target, Coordinate prev, String sprite)
    {
        
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
