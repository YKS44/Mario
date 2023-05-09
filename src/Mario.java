import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Actions.Action;
import Actions.HoldAction;
import Actions.LambdaAction;

public class Mario {
    private final String sprite = "🟩";
    private Coordinate pos = new Coordinate(0, 5);
    private Coordinate prev = pos;

    private final KeyboardInput keyboardInput;
    
    public Mario()
    {
        keyboardInput = new KeyboardInput();
    }

    private void moveLeft()
    {
        pos.addXBy(-1);
        ScreenManager.getInstance().moveObject(pos, prev, sprite);
        prev = pos;
    }

    private void moveRight()
    {
        pos.addXBy(1);
        ScreenManager.getInstance().moveObject(pos, prev, sprite);
        prev = pos;
    }

    private class KeyboardInput implements KeyListener{
        private HashMap<Integer, Action> keyMap = new HashMap<>();
    
        public KeyboardInput() {
            // Create a new Frame to listen for keyboard events
            Frame frame = new Frame();
    
            // Add the KeyListener to the Frame
            frame.addKeyListener(this);
    
            // Set the size and visibility of the Frame
            frame.setSize(180, 0);
            frame.setTitle("Mario Game");
            frame.setVisible(true);
    
            // Request focus for the Frame so that it can receive keyboard events
            frame.requestFocus();
    
            registerKeyMaps();
        }
       
        private void registerKeyMaps()
        {
            keyMap.put(KeyEvent.VK_A, new HoldAction(()->System.out.println("left")));
            keyMap.put(KeyEvent.VK_D, new HoldAction(()->System.out.println("right")));
            keyMap.put(KeyEvent.VK_SPACE, new LambdaAction(()->System.out.println("jump")));
            keyMap.put(KeyEvent.VK_ESCAPE, new LambdaAction(()->System.exit(0)));
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
    
            if(keyMap.containsKey(keyCode)){
                keyMap.get(keyCode).onPressed();
            }
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
    
            if(keyMap.containsKey(keyCode))
            {
                keyMap.get(keyCode).onReleased();
            }
        }
    
        @Override
        public void keyTyped(KeyEvent e) {}
    }
    
}
