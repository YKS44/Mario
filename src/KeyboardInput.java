import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardInput implements KeyListener{
    private HashMap<Integer, VoidAction> keyMap = new HashMap<>();

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
        keyMap.put(KeyEvent.VK_W, ()->System.out.println("up"));
        keyMap.put(KeyEvent.VK_S, ()->System.out.println("down"));
        keyMap.put(KeyEvent.VK_A, ()->System.out.println("left"));
        keyMap.put(KeyEvent.VK_D, ()->System.out.println("right"));
        keyMap.put(KeyEvent.VK_ESCAPE, ()->System.exit(0));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyMap.containsKey(keyCode)){
            keyMap.get(keyCode).execute();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
