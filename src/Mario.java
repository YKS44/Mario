import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Actions.Action;
import Actions.HoldAction;
import Actions.LambdaAction;
import Managers.LeftRightMovementManager;
import Managers.ScreenManager;
import Utils.Coordinate;

public class Mario {
    private final String sprite = "🟩";
    private Coordinate pos = new Coordinate(0, 5, 9, 0, 5, 0);
    private Coordinate prev = pos;

    private final KeyboardInput keyboardInput;

    private final LeftRightMovementManager lrMovementManager = LeftRightMovementManager.getInstance();
    
    public Mario()
    {
        keyboardInput = new KeyboardInput();
    }

    private void moveLeft()
    {
        lrMovementManager.setMovingLeft(true);

        if(lrMovementManager.canMove(false))
        {
            pos.addXBy(-1);
            ScreenManager.getInstance().moveObject(pos, prev, sprite);
            prev = pos;
        }
    }

    private void moveRight()
    {
        lrMovementManager.setMovingRight(true);

        if(lrMovementManager.canMove(true))
        {
            pos.addXBy(1);
            ScreenManager.getInstance().moveObject(pos, prev, sprite);
            prev = pos;
        }
    }

    private class KeyboardInput implements KeyListener{
        private HashMap<Integer, Action> keyMap = new HashMap<>();
    
        public KeyboardInput() {
            Frame frame = new Frame();
    
            frame.addKeyListener(this);
    
            frame.setSize(180, 0);
            frame.setTitle("Mario Game");
            frame.setVisible(true);
    
            frame.requestFocus();
    
            registerKeyMaps();
        }
       
        private void registerKeyMaps()
        {
            keyMap.put(KeyEvent.VK_A, new HoldAction(()->moveLeft(), ()->lrMovementManager.setMovingLeft(false)));
            keyMap.put(KeyEvent.VK_D, new HoldAction(()->moveRight(), ()->lrMovementManager.setMovingRight(false)));
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
