package com.hyczbo.beadando_i;
import com.hyczbo.beadando_i.field.Estate;
import com.hyczbo.beadando_i.field.Field;
import com.hyczbo.beadando_i.player.BankrupcyException;
import com.hyczbo.beadando_i.player.Player;
import java.util.ArrayList;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class GameSimulation extends Game {
    private final ArrayList<Integer> diceThrows;
    
    /**
     * @param players
     * @param board
     * @param diceThrows
     * @throws InvalidGameParametersException
     */
    public GameSimulation(ArrayList<Player> players,
                          ArrayList<Field> board,
                          ArrayList<Integer> diceThrows)
            throws InvalidGameParametersException {
        super(players, board);
        this.diceThrows = diceThrows;
    }
    
    /**
     * Iterates through players and dice throws until only one player is left.
     * Each player tries to take their turn with the given dice throws
     * and gets removed in case of bankrupcy.
     * @return winner player object
     */
    @Override
    public Player play() {
        int nthPlayer = 0;
        int nthThrow = 0;
        
        while (players.size() > 1) {
            try {
                players.get(nthPlayer)
                       .takeSimulationTurn(board, diceThrows.get(nthThrow));
                
                System.out.println("\nBOARD ["+board.size()+"]:");
                
                for (Field f : board) {
                    if (f.getClass() == Estate.class) {
                        Estate e = (Estate)f;
                        System.out.print("\t- "+((Estate)f).getName()
                            +"["+e.getLevel().toString()+"]: ");
                        if (e.getOwner() != null) {
                            System.out.print(e.getOwner().getName()+"\n");
                        }
                        else {System.out.print("-\n");}  
                    }
                    else {System.out.println("\t- "+f.toString());}
                }
            }
            catch(BankrupcyException e) {
                System.out.println("player declared bankrupcy");
                
                players.remove(nthPlayer);
            }
            nthPlayer = (nthPlayer + 1) % players.size();
            nthThrow++;
        }
        return players.get(0);
    }
}
