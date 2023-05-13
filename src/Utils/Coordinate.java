package Utils;
public class Coordinate {
    private int x;
    private int y;

    private final int maxX;
    private final int minX;

    private final int maxY;
    private final int minY;

    public Coordinate(int startingX, int startingY)
    {
        this.x = startingX;
        this.y = startingY;

        maxX = (int)Double.POSITIVE_INFINITY;
        minX = (int)Double.NEGATIVE_INFINITY;
        maxY = (int)Double.POSITIVE_INFINITY;
        minY = (int)Double.NEGATIVE_INFINITY;
    }

    public Coordinate(int startingX, int startingY, int maxX, int minX, int maxY, int minY)
    {
        this.x = startingX;
        this.y = startingY;

        this.maxX = maxX;
        this.minX = minX;

        this.maxY = maxY;
        this.minY = minY;
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
        x = MathUtil.clamp(x, minX, maxX);
    }

    public void addYBy(int amount)
    {
        y+= amount;
        y = MathUtil.clamp(y, minY, maxY);
    }
}
