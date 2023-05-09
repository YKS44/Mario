import Utils.MathUtil;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void addXBy(int amount)
    {
        x += amount;
        x = MathUtil.clamp(x, 0, 10);
    }
}
