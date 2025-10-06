package com.hyczbo.beadando_i.field;
import com.hyczbo.beadando_i.player.BankrupcyException;
import com.hyczbo.beadando_i.player.Player;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class Tax extends Field {

    /**
     * @param fee
     */
    public Tax(int fee) {this.fee = fee;}
    
    /**
     * Given player loses the Tax's fee amount of cash.
     * @param player
     * @throws BankrupcyException
     */
    @Override
    public void fieldAction(Player player) throws BankrupcyException {
        System.out.println("\t> TAX: "+player.getName() + " has to pay " + fee);
        player.changeCashBy(-fee);
    }

    /**
     * @return class name
     */
    @Override
    public String toString() {return "TAX";}
}
