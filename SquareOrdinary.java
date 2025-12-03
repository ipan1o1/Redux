public class SquareOrdinary extends SquareWalk {

    // Constructor
    public SquareOrdinary(int lig, int col){
        super(lig,col);
    }

    // Methods
    @Override
    public boolean isEmpty(){
        return content == null;
    }

    @Override
    public void enter(Ball b){}

    @Override
    public void leave(Ball b){}

    @Override
    public void touch(Ball b){}
}
