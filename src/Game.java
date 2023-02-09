import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class Game {

   Board board;
   Dice dice;
   Deque<player> playerlist = new LinkedList<>();
   player winner;

   public Game ()
   {
      initilizegame();
   }

   private void initilizegame()
   {
      board = new Board(10,5,4);
      dice = new Dice(1);
      winner = null;
      addplayer();
   }

   private void addplayer()
   {
      player p1 = new player("p1" , 0);
      player p2 = new player ("p2", 0);

      playerlist.add(p1);
      playerlist.add(p2);
   }

   public void startgame()
   {
      while (winner == null)
      {
         // Checking Player turn
         player playerTurn = findPlayerTurn();
         System.out.println("Player turn is " + playerTurn.id + " and current position is " + playerTurn.currposition );

         // roll the dice
         int diceNumbers =  dice.rolldice();

         //get the new position
         int playerNewPosition = playerTurn.currposition + diceNumbers;
         playerNewPosition = jumpCheck(playerNewPosition);
         playerTurn.currposition = playerNewPosition;

         System.out.println("player turn is:" + playerTurn.id + " new Position is: " + playerNewPosition);
         //check for winning condition
         if(playerNewPosition >= board.cells.length * board.cells.length-1){

            winner = playerTurn;
         }

      }

      System.out.println("WINNER IS:" + winner.id);
   }

   private player findPlayerTurn() {

      player playerTurns = playerlist.removeFirst();
      playerlist.addLast(playerTurns);
      return playerTurns;
   }

   private int jumpCheck (int playerNewPosition) {

      if(playerNewPosition > board.cells.length * board.cells.length-1 ){
         return playerNewPosition;
      }

      Cell cell = board.getcell(playerNewPosition);
      if(cell.jump != null && cell.jump.start == playerNewPosition) {
         String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
         System.out.println("jump done by: " + jumpBy);
         return cell.jump.end;
      }
      return playerNewPosition;
   }



}
