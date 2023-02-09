import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;
    Board(int boardsize, int numberofladder , int numberofsnakes)
    {
        initializecell(boardsize);
        addSnakesLadder(cells, numberofsnakes , numberofladder);
    }

    public void initializecell(int boardsize)
    {
        cells = new Cell[boardsize][boardsize];
        for(int i=0;i<boardsize;i++)
        {
            for(int j=0; j<boardsize; j++)
            {
                Cell celobj = new Cell();
                cells[i][j] = celobj;
            }
        }
    }

    public void addSnakesLadder (Cell[][] cells, int numberofsnakes,int numberofladder )
    {
     while (numberofsnakes > 0)
     {
         int snakehead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
         int snaketail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);

         if(snaketail>= snakehead)
         {
             continue;
         }

         Jump snakeobj = new Jump();
         snakeobj.start = snakehead;
         snakeobj.end= snaketail;

         System.out.println(snakeobj);
         Cell cell = getcell(snakehead);
         cell.jump = snakeobj;

         numberofsnakes--;
     }

        while (numberofladder > 0)
        {
            int ladderstart = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int ladderend = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);

            if(ladderstart>= ladderend)
            {
                continue;
            }

            Jump ladderobj = new Jump();
            ladderobj.start = ladderstart;
            ladderobj.end = ladderend;
            System.out.println(ladderobj);

            Cell cell = getcell(ladderend);
            cell.jump = ladderobj;

            numberofladder--;
        }
    }

    Cell getcell (int playrposition)
    {
        int boardrow = playrposition/ cells.length;
        int boardcolumn = playrposition% cells.length;
        return cells[boardrow][boardcolumn];
    }
}
