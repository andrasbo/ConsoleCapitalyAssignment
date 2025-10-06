package com.hyczbo.beadando_i.field;
import com.hyczbo.beadando_i.player.BankrupcyException;
import com.hyczbo.beadando_i.player.Player;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class Jackpot extends Field {

    /**
     * @param fee
     */
    public Jackpot(int fee) {this.fee = fee;}
    
    /**
     * Given player is awarded with the Jackpot's fee amount of cash.
     * BankrupcyException should not happen.
     * @param player
     * @throws BankrupcyException
     */
    @Override
    public void fieldAction(Player player) throws BankrupcyException {
        System.out.println("\t> JACKPOT: "+player.getName() + " has won " + fee);
        player.changeCashBy(fee);
    }

    /**
     * @return class name
     */
    @Override
    public String toString() {return "JACKPOT";}
}
