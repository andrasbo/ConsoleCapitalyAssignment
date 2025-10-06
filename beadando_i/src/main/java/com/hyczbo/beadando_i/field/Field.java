package com.hyczbo.beadando_i.field;
import com.hyczbo.beadando_i.player.BankrupcyException;
import com.hyczbo.beadando_i.player.Player;

/**
 * @author Andras Boromissza [hyczbo]
 */
public abstract class Field {
    protected int fee;

    /**
     * @param player
     * @throws BankrupcyException
     */
    public abstract void fieldAction(Player player) throws BankrupcyException;
}
