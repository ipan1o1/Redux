public class SquareSolid extends Square{

    // Constructor
    public SquareSolid(int c, int l){
        super(c, l);
    }

    // Methods
    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public void enter(Ball b){
        // Does nothing
    }

    @Override
    public void leave(Ball b){
        // Does nothing
    }

    @Override
    public void touch(Ball b){
        // Does something I guess ?
    }

}