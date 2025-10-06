package com.hyczbo.beadando_i.player;
import com.hyczbo.beadando_i.field.Field;
import com.hyczbo.beadando_i.field.Estate;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Andras Boromissza [hyczbo]
 */
public abstract class Player {
    protected final String name;
    protected int cash;
    protected int position;
    protected final ArrayList<Estate> estates;
    
    /**
     * @param name
     */
    public Player(String name) {
        this.name = name;
        cash = 10000;
        position = 0;
        estates = new ArrayList<>();
    }
    
    /**
     * Player moves their piece to a new Field using a random dice throw.
     * The Field applies it's action on the Player. Then, if the Field is an
     * Estate and the Player didn't go bankrupt,
     * they apply their action on the Field.
     * @param board
     * @throws BankrupcyException
     */
    public void takeRandomTurn(ArrayList<Field> board) throws BankrupcyException {
        System.out.println("\n------------------------------------------\n"
            + name+"["+this.toString()+"]"+" takes turn\n"
            + "starting cash: "+cash);

        Field field = movePiece(board, position, throwDice());
        if (field.getClass() == Estate.class) {
            Estate e = (Estate) field;
            System.out.println("\t> enters "+e.getName());
            e.fieldAction(this); // src of BankrupcyException
            playerAction(e);
        }
        else {
            field.fieldAction(this); // src of BankrupcyException
        }
        System.out.println("ending cash: " + cash);
    }
    
    /**
     * Player moves their piece to a new Field using the given diceThrow.
     * The Field applies it's action on the Player. Then, if the Field is an
     * Estate and the Player didn't go bankrupt,
     * they apply their action on the Field.
     * @param board
     * @param diceThrow
     * @throws BankrupcyException
     */
    public void takeSimulationTurn(ArrayList<Field> board, int diceThrow) throws BankrupcyException {
        System.out.println("\n------------------------------------------\n"
            + name+"["+this.toString()+"]"+" takes turn\n"
            + "starting cash: "+cash);

        Field field = movePiece(board, position, diceThrow);
        if (field.getClass() == Estate.class) {
            Estate e = (Estate) field;
            System.out.println("\t> enters "+e.getName());
            
            e.fieldAction(this); // src of BankrupcyException
            playerAction(e);
        }
        else {
            field.fieldAction(this); // src of BankrupcyException
        }
        System.out.println("ending cash: " + cash);
    }

    /**
     * Player adds the given positive or negative amount to it's cash.
     * Declares bankupcy if their cash dropps negative.
     * @param amount
     * @throws BankrupcyException
     */
    public void changeCashBy(int amount) throws BankrupcyException {
        cash += amount;
        if (cash < 0) {declareBankrupcy(); throw new BankrupcyException();}
    }    

    /**
     * @return random number between 2 and 12
     */
    protected int throwDice() {
        Random r = new Random();
        return r.nextInt(2,13);
    }

    /**
     * @param board
     * @param position
     * @param diceThrow
     * @return field that the player jumps to
     */
    protected Field movePiece(ArrayList<Field> board, int position, int diceThrow) {
        this.position = (position + diceThrow) % board.size();
        System.out.println("\t> ("+diceThrow+") "
                +position+" -> "+this.position);
        return board.get(this.position);
    }   

    /**
     * Player's estates remove their owner reference, become Free,
     * player cash is set to 0 and player estates are cleared
     */
    protected void declareBankrupcy() {
        for (Estate h : estates) {
            h.foreclose(this);
        }
        estates.clear();
        cash = 0;
    }   

    /**
     * @param estate
     */
    protected abstract void playerAction(Estate estate);
    
    /**
     * @return player name
     */
    public String getName() {return name;}

    /**
     * @return player cash
     */
    public int getCash() {return cash;}

    /**
     * @return list of player estates
     */
    public ArrayList<Estate> getEstates() {return estates;}
}
