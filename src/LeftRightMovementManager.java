public class LeftRightMovementManager {
    private static LeftRightMovementManager instance = null;

    private volatile boolean isMovingLeft = false;
    private volatile boolean isMovingRight = false;

    public synchronized boolean canMove(boolean wantsToMoveRight)
    {
        if(wantsToMoveRight)
        {
            if(!isMovingLeft)
            {
                //if it wants to move right and it is not moving left
                return true;
            }
        }
        else
        {
            if(!isMovingRight)
            {
                //if it wants to move left and it is not moving right
                return true;
            }
        }

        //otherwise dont let it move
        return false;
    }

    public void setMovingLeft(boolean value)
    {
        isMovingLeft = value;
    }

    public void setMovingRight(boolean value)
    {
        isMovingRight = value;
    }

    public static LeftRightMovementManager getInstance()
    {
        if(instance == null)
        {
            instance = new LeftRightMovementManager();
        }
        return instance;
    }
}
