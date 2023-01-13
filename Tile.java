package test;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;

public class Tile {

    final public char letter;
    final public int score;
    private Tile(char letter, int score)
    {
        this.letter= letter;
        this.score= score;
    }
    private char getLetter()
    {
        return this.letter;
    }
    private int getScore()
    {
        return this.score;
    }
    @Override
    public boolean equals(Object o){return super.equals(o);}
    @Override
    public int hashCode(){return super.hashCode();}


    public static class Bag{
        private static Bag bag= null;
        private static int numOfTile= 98;

        int[] arr_Tile_Amount= {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        static int[] max_Tile_Amount={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};

        Tile[] tile_arr= {new Tile('A',1), new Tile('B',3), 
        new Tile('C',3), new Tile('D',2), new Tile('E',1),
        new Tile('F',4), new Tile('G',2), new Tile('H',4),
        new Tile('I',1),new Tile('J',8),new Tile('K',5),
        new Tile('L',1),new Tile('M',3),new Tile('N',1),
        new Tile('O',1), new Tile('P',3), new Tile('Q',10),
        new Tile('R',1),new Tile('S',1),new Tile('T',1),
        new Tile('U',1),new Tile('V',4), new Tile('W',4),
        new Tile('X',8), new Tile('Y',4),new Tile('Z',10)};

        int getSize(){ return numOfTile;}
        Tile getRand()
        {
            int index;
            if(this.getSize()==0){return null;}
            int[] arr = FindIndex();
            Random r= new Random();
            index= r.nextInt(arr.length);
            numOfTile--;
            this.arr_Tile_Amount[arr[index]]--;
            return this.tile_arr[arr[index]];
        }
        //helper methods- find exsisting items in index array
        int[] FindIndex()
        {
            int size=0;
            int[] arr1= getQuantities();
            for(int i: arr1)
            {
                if(i>0){size++;}
            }
            int[] arr_exsist= new int[size];
            for(int i=0, j=0; i<arr_Tile_Amount.length; i++)
            {
                if(this.arr_Tile_Amount[i]>0)
                {
                    arr_exsist[j]= this.arr_Tile_Amount[i];
                    j++;
                }
            }
            return arr_exsist;
            
        }

        Tile getTile(char letter)
        {
            if(letter<'A'||letter>'Z'){return null;}
            for(int i=0; i< this.tile_arr.length; i++){
                if(this.tile_arr[i].getLetter()==letter && this.arr_Tile_Amount[i]>0)
                {
                    this.arr_Tile_Amount[i]--;
                    return this.tile_arr[i];
                }
            }
            return null;
        }

        void put(Tile tile)
        {
            if(numOfTile==98)
                    return;
            for(int i=0; i< this.tile_arr.length; i++)
            {
                if(this.tile_arr[i]==tile)
                {
                    this.arr_Tile_Amount[i]++;
                    numOfTile++;
                }
            }
        }


        int[] getQuantities()
        {
            int[] newArr= new int[26];
            for(int i=0; i<this.arr_Tile_Amount.length; i++)
            {
                newArr[i]= arr_Tile_Amount[i];
            }
            return newArr;
        }

        public static Bag getBag()
        {
            if(bag==null)
            {
                bag=new Bag();
            }
            return bag;
        }


     }

    }

	

