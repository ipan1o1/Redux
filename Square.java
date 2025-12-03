public abstract class Square {

    //Atributes
    protected final int lig, col;

    // Constructor
    public Square(int l, int c){
        this.lig = l;
        this.col = c;
    }
    
    // Methods
    public abstract boolean isEmpty();
    public abstract void enter(Ball b);
    public abstract void leave(Ball b);
    public abstract void touch(Ball b); 
}
