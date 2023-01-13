package test;
import java.util.ArrayList;

import javax.tools.ForwardingFileObject;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Board {
    private static Board board= null; //singelton
    static final int BOARD_SIZE= 15;// size of row
    Tile[][] tilesArr= new Tile[BOARD_SIZE][BOARD_SIZE];
    int score=0; // determines the score of the first word on the board
    int tilesOnBoard=0; // determines the number of ties on the board
    final int UP=5, DOWN=6, RIGHT=7, LEFT=8;//direction
    final int MULTYLETTER =1, TRIPLELETTER=2,MULTYWORD=3, TRIPLEWORD=4;//BONUS
    Boolean STAR= false;
    private Board(){} // default constructor
    static Board getBoard()// singelton
    {
        if(board==null)
        {
            board= new Board();
        }
        return board;
    }
    
    // class foe a single inlay on the board
    public static class SingleInlay 
    {
        //options for bonus in inlay: 1= letter*2, 2= letter*3, 3= word*2, 4=word*3, star= word*2
        final int scoreID;
        Tile tile;
        Boolean isTile;

        public SingleInlay(Tile tile, Boolean isTile, int scoreid)
        {
            this.tile = tile;
            this.isTile = isTile;
            scoreID = scoreid;
        }
    };
    //array of the board
         SingleInlay[][] full_board= { 
        new SingleInlay[]{new SingleInlay(null, FALSE, 4),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 4),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE,0),
        new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE, 1)},

        
        new SingleInlay[]{new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE, 3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,0),new SingleInlay(null, FALSE,1),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 4), new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE,1),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 1),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,4)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0)},
    
        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},

        new SingleInlay[]{new SingleInlay(null,FALSE,1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,0),new SingleInlay(null, FALSE,1),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE,1)},

        new SingleInlay[]{new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE,3),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0)},

        new SingleInlay[]{new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE, 3),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE,2),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE,3),
        new SingleInlay(null, FALSE, 0)},

        new SingleInlay[]{new SingleInlay(null, FALSE, 4),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null,FALSE, 4),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 0),new SingleInlay(null, FALSE, 0),
        new SingleInlay(null, FALSE, 1),new SingleInlay(null, FALSE,0),
        new SingleInlay(null, FALSE, 0), new SingleInlay(null, FALSE, 1)}
};
    

//return copy of tiles array from the board
Tile[][] getTiles()
{
    Tile[][] copyTiles= new Tile[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if(full_board[i][j].tile!=null)
            {
                copyTiles[i][j]= full_board[i][j].tile;
                tilesArr[i][j]= full_board[i][j].tile;
            }
            else
            {
                copyTiles[i][j]=null;
            }
            
        }
    }
    return copyTiles;
}

//hels boardLegal function- checks if the word out of bounds
private Boolean WordInBoard(Word w)
{
    if(w.getRow()<0 || w.getCol()<0||w.getRow()>=BOARD_SIZE||w.getCol()>=BOARD_SIZE)
    {
        return Boolean.FALSE;
    }
    if(w.Vertical() &&(w.getRow()+w.arr_word.length>BOARD_SIZE))
    {
        return Boolean.FALSE;
    }
    if(!w.Vertical()&&(w.getCol()+w.arr_word.length>BOARD_SIZE))
    {
        return Boolean.FALSE;
    }
    return Boolean.TRUE;
}

//helps boardLegal function-this function helps to see if the board is empty
private Boolean BoardIsEmpty()
{
    if(tilesOnBoard!=0)
    {
        return Boolean.FALSE;
    }
    else
    {
        return Boolean.TRUE;
    }
}
//helps boardLegal function-this function checks if the word is on the star
private Boolean WordOnStar(Word w)
{
    int row = w.getRow();
    int col = w.getCol();
    if(w.Vertical())
    {
        if(row<=BOARD_SIZE/2 && row+w.arr_word.length-1>= BOARD_SIZE/2
        && col== BOARD_SIZE/2)
            return true;
        return false;
    }
    else
    {
        if(col<=BOARD_SIZE/2 && col+w.arr_word.length-1>= BOARD_SIZE/2
        && row== BOARD_SIZE/2)
        return true;
    return false;
    }
}
//helps boardLegal function-this function helps to see if running over exsisting tiles on the board
private Boolean RunOverTilesInBoard(Word w)
{
    int row = w.getRow();
    int col = w.getCol();
    for(int i=0;i<w.arr_word.length;i++)
    {
        if(getTiles()[row][col]==null)
        {
            break;
        }
        if(getTiles()[row][col]!=null && w.arr_word[i]!=null)
        {
            if(getTiles()[row][col].letter!= w.arr_word[i].letter)
            {
                return Boolean.TRUE;
            }
        }
        if(w.Vertical())
        {
            row++;
        }
        else
        {
            col++;
        }
    }
    return Boolean.FALSE;
}

//helps boardLegal function -this function if there is an overlapping
private Boolean WordOverLapping(Word w)
{
    int row = w.getRow();
    int col = w.getCol();
    for(int i=0;i<w.arr_word.length;i++)
    {
        if(getTiles()[row][col]!=null && w.arr_word[i]!=null &&
        getTiles()[row][col].letter==w.arr_word[i].letter)
        {
            return Boolean.TRUE;
        }
        if(getTiles()[row][col]!=null && w.arr_word[i]==null)
        {
            return Boolean.TRUE;
        }
    }
    return Boolean.FALSE;
}
//helps boardLegal function-this function if  a new word is ajacent of overlapping an old one
private Boolean WordAjacent(Word w)
{
    int row = w.getRow();
    int col = w.getCol();
    for(int i=0;i<w.arr_word.length;i++)
    {
        if(row+1==BOARD_SIZE && col==0)
        {
            if(getTiles()[row-1][col]!=null || getTiles()[row][col+1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(row+1==BOARD_SIZE && col+1==BOARD_SIZE)
        {
            if(getTiles()[row-1][col]!=null || getTiles()[row][col-1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(row==0 && col==0)
        {
            if(getTiles()[row+1][col]!=null || getTiles()[row][col+1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(row==0 && col+1==BOARD_SIZE)
        {
            if(getTiles()[row+1][col]!=null || getTiles()[row][col-1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(row==0)
        {
            if(getTiles()[row+1][col]!=null || getTiles()[row][col+1]!=null ||
            getTiles()[row][col-1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(row+1==BOARD_SIZE)
        {
            if(getTiles()[row-1][col]!=null || getTiles()[row][col+1]!=null ||
            getTiles()[row][col-1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(col==0)
        {
            if(getTiles()[row][col+1]!=null || getTiles()[row+1][col]!=null ||
             getTiles()[row-1][col]!=null)
             {
                 return Boolean.TRUE;
             }
        }
        if(col+1==BOARD_SIZE)
        {
            if(getTiles()[row][col-1]!=null || getTiles()[row-1][col]!=null ||
             getTiles()[row+1][col]!=null)
             {
                 return Boolean.TRUE;
             }
        }
        if(row>0 && row<BOARD_SIZE && col>0 && col<BOARD_SIZE)
        {
            if(getTiles()[row+1][col]!=null || getTiles()[row-1][col]!= null ||
            getTiles()[row][col-1]!=null || getTiles()[row][col+1]!=null)
            {
                return Boolean.TRUE;
            }
        }
        if(w.Vertical())
        {
            row++;
        }
        else
        {
            col++;
        }

    }
    return Boolean.FALSE;
}

// this function returns if the given word if legal
Boolean boardLegal(Word word)
{
    if(!WordInBoard(word))
    {
        return Boolean.FALSE;
    }
    if(BoardIsEmpty())
    {
        if(!WordOnStar(word))
        {
            return Boolean.FALSE;
        }
        else
        {
          return Boolean.TRUE;
        }
    }
    if(RunOverTilesInBoard(word))
    {
        return Boolean.FALSE;
    }
    if(!WordAjacent(word) && !WordOverLapping(word))
    {
        return Boolean.FALSE;
    }
    return Boolean.TRUE;
   
}

//for now this function returns only true
 Boolean dictionaryLegal(Word word)
 {
    return Boolean.TRUE;
 }

 //this function creates an ArrayList of new words
 ArrayList<Word> getWords(Word word)
 {
    Tile[][] tmpArr = getTiles();
    ArrayList<Word> wList = new ArrayList<Word>();
    int length = word.arr_word.length;
    placeOnFictionalBoard(tmpArr, word);//placing the word on the board on tmp board
    int start=0;
    int end=0;
    if(word.Vertical())//col dont change
    {
        //426-428 gets vertical word
        start=searchWordOnBoard(tmpArr, word.getRow(),word.getCol(),UP);
        end=searchWordOnBoard(tmpArr, word.getRow()+ length-1,word.getCol(),DOWN);
       wList.add(fatchWord(tmpArr, start, word.getCol(), end-start+1, word.Vertical()));
       //this loop gets all the horizontal words- run on the vertical word
        for (int i = 0; i < word.arr_word.length; i++) {
            if(word.arr_word[i]==null)
            {
                continue;
            }
         start=searchWordOnBoard(tmpArr, word.getRow()+i,word.getCol(),LEFT);
         end= searchWordOnBoard(tmpArr, word.getRow()+i,word.getCol(),RIGHT);
         if(start!=end)
            wList.add(fatchWord(tmpArr, word.getRow()+i, start, end-start+1, !word.Vertical()));
            
        }
    }
    else// row dont change
    {
        //445-447 gets horizontal word
        start= searchWordOnBoard(tmpArr, word.getRow(),word.getCol(),LEFT);
        end= searchWordOnBoard(tmpArr, word.getRow(),word.getCol()+ length-1,RIGHT);
        wList.add(fatchWord(tmpArr, word.getRow(), start, end-start+1, word.Vertical()));
        //this loop gets all the vertical words- run on the horizontal word
         for (int i = 0; i < word.arr_word.length; i++) {
             if(word.arr_word[i]==null)
             {
                 continue;
             }
          start= searchWordOnBoard(tmpArr, word.getRow(),word.getCol()+i,UP);
          end= searchWordOnBoard(tmpArr, word.getRow(),word.getCol()+i,DOWN);
          if(start!=end)
          {
             wList.add(fatchWord(tmpArr, start, word.getCol()+i, end-start+1, !word.Vertical()));
          } 
         }
        
    }
    for (Word w : wList) {
        placeWord(w);
    }
    return wList;
    
 }

 //place the word of the real board
 private void placeWord(Word w)
 {
    int row= w.getRow();
    int col=w.getCol();
    for (int i = 0; i < w.arr_word.length; i++) {
        if(w.arr_word[i]!=null && full_board[row][col].tile==null && 
        full_board[row][col].tile!= w.arr_word[i])
        {
            full_board[row][col].tile=w.arr_word[i];
            full_board[row][col].isTile=true;
            tilesOnBoard++;
        }
        if(w.Vertical())
        {
            row++;
        }
        else
        {
            col++;
        }
    }
 }
//this function place the word on the  fictional board
 private void placeOnFictionalBoard(Tile[][] arr, Word w)
 {
    int row = w.getRow();
    int col = w.getCol();

    for (int i = 0; i < w.arr_word.length; i++) {
        if(w.arr_word[i]!=null)
        {
            arr[row][col]= w.arr_word[i];
        }
        if(w.Vertical())
        {
            row++;
        }
        else
        {
            col++;
        }
        
    }

    if(arr== tilesArr){tilesOnBoard+= w.arr_word.length;}
 }

 private int searchWordOnBoard(Tile[][] arr, int row, int col, int direction)
 {
    for (int i = 0; i < BOARD_SIZE; i++) 
    {
        if(row<0 || row>BOARD_SIZE-1 || col<0 || col > BOARD_SIZE-1 || arr[row][col]==null)
        {
            break;
        }

        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }
       
    }
//going backwards to the fist tile that is not null
    switch (direction) {
        case UP:
            row++;
            break;
        case DOWN:
            row--;
            break;
        case LEFT:
            col++;
            break;
        case RIGHT:
            col--;
            break;
    }
    if(direction== UP || direction ==DOWN)
        return row;
    else
        return col;
      
 }
//copy the full word and return the currect word without null
 private Word fatchWord(Tile[][] arr, int row, int col, int length, boolean vertical)
 {
    Word w;
    Tile[] wTiles= new Tile[length];
    if(vertical)//col dont change
    {
        for (int i = 0; i < wTiles.length; i++) {
            wTiles[i]=arr[row+i][col];
        }
    }
    else//row dont change
    {
        for (int i = 0; i < wTiles.length; i++) {
            wTiles[i]=arr[row][col+i];
        }
    }
    w= new Word(wTiles,row,col,vertical);
    return w;

 }


 int tryPlaceWord(Word w)
 {
    int score=0;
    int row= w.row;
    int col= w.col;
    if(boardLegal(w)&& dictionaryLegal(w))
    {
        ArrayList<Word> wList= getWords(w);
        for (Word word : wList) {
            if(!dictionaryLegal(word))
            {
                return 0;
            } 
            placeWord(word);
        }
        for (Word word : wList) {
            score+= getScore(word);
        }
        for (int i = 0; i < w.arr_word.length; i++) {
            if(w.Vertical())//col dont change
            {
                if(full_board[row+i][col+1]!=null || full_board[row+i][col-1]!=null)
                {
                    
                }
                
            }
            else//row dont change
            {}
            
        }
    }
    
    return score;
 }

 int getScore(Word w)
 {
    int row = w.getRow();
    int col= w.getCol();
    int length= w.arr_word.length;
    int score=0;
    int multy=1;
    if(w.Vertical())//col dont change
    {
       for (int i = 0; i < length; i++) {
           if(full_board[row+i][col].scoreID== MULTYLETTER)
           {
               score+= 2*w.arr_word[i].score;
           }
           else if(full_board[row+i][col].scoreID == TRIPLELETTER)
           {
               score+= 3*w.arr_word[i].score;
           }
           else if(full_board[row +i][col].scoreID ==MULTYWORD)
           {
            if(STAR==false && row+i==7&& col==7)
            {
                multy*=2;
                score+= w.arr_word[i].score; 
                STAR=true;
            }
            else if(STAR==true && row+i==7 && col==7)
            {
                score+= w.arr_word[i].score;
            }
            else
            {
                multy*=2;
                score+= w.arr_word[i].score;
            }
                             
           }
           else if(full_board[row +i][col].scoreID ==TRIPLEWORD)
           {
              multy*=3;
              score+= w.arr_word[i].score;
           }
           else//there isnt any bonus tile
           {
               score+= w.arr_word[i].score;
           }
        
       }
    }
    else//row dont change
    {
       for (int i = 0; i < length; i++) 
       {
        if(full_board[row][col+i].scoreID== MULTYLETTER)
        {
            score+= 2*w.arr_word[i].score;
        }
        else if(full_board[row][col+i].scoreID == TRIPLELETTER)
        {
            score+= 3*w.arr_word[i].score;
        }
        else if(full_board[row][col+i].scoreID ==MULTYWORD)
        {
            if(STAR==false && row==7&& col+i==7)
            {
                multy*=2;
                score+= w.arr_word[i].score; 
                STAR=true;
            }
            else if(STAR==true && row==7 && col+i==7)
            {
                score+= w.arr_word[i].score;
            }
            else
            {
                multy*=2;
                score+= w.arr_word[i].score;
            }
        }
        else if(full_board[row][col+i].scoreID ==TRIPLEWORD)
        {
           multy*=3;
           score+= w.arr_word[i].score;
        }
        else//there isnt any bonus tile
        {
            score+= w.arr_word[i].score;
        }
      }
    }
    return score*multy;
    
 }

}
