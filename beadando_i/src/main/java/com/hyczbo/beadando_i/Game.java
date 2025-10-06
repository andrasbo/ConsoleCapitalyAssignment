package com.hyczbo.beadando_i;
import com.hyczbo.beadando_i.field.Field;
import com.hyczbo.beadando_i.player.Player;
import java.util.ArrayList;

/**
 * @author Andras Boromissza [hyczbo]
 */
public abstract class Game {
    protected final ArrayList<Player> players;
    protected final ArrayList<Field> board;  
    
    /**
     * @param players
     * @param board
     * @throws InvalidGameParametersException
     */
    public Game(ArrayList<Player> players, ArrayList<Field> board)
            throws InvalidGameParametersException {
        if (players.size() < 2 || board.size() < 2) {
            throw new InvalidGameParametersException();
        }
        this.players = players;
        this.board = board;
    }   
    
    /**
     * @return winner player object
     */
    public abstract Player play();
    
    /**
     * @return list of players in game
     */
    public ArrayList<Player> getPlayers() {return players;}
    
    /**
     * @return list of fields on the board
     */
    public ArrayList<Field> getBoard() {return board;}    
}
