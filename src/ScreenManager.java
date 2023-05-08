public class ScreenManager {
    private static ScreenManager instance = null;

    private char[][] currentScreen;
    private char[][] cachedScreen;

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
            for(int x = 0; x < currentScreen.length; x++)
            {
                for(int y = 0; y < currentScreen[x].length; y++){
                    System.out.println(cachedScreen[x][y]);
                }
                System.out.println();
            }
            currentScreen = cachedScreen;
        }
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
