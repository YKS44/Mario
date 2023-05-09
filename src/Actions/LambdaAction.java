package Actions;
public class LambdaAction implements Action{

    private VoidInterface v;
    public LambdaAction(VoidInterface v)
    {
        this.v = v;
    }
    
    @Override
    public void onPressed()
    {
        v.execute();
    }

    @Override
    public void onReleased(){}

}
