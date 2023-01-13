package test;


public class Word {
    Tile[] arr_word;
    int row;
    int col;
    boolean vertical;

    public Word(Tile[] arr_word, int row, int col, boolean vertical) {
        this.arr_word = arr_word;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    
    public Tile[] getWord()
    {
        return arr_word;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public boolean Vertical()
    {
        return vertical;
    }
    @Override
    public boolean equals(Object obj){return super.equals(obj);}
    
        

	
}

