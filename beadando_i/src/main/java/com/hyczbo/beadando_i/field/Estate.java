package com.hyczbo.beadando_i.field;
import com.hyczbo.beadando_i.player.BankrupcyException;
import com.hyczbo.beadando_i.player.Player;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class Estate extends Field {
    private final String name;
    private Level level;
    private int price;
    private Player owner;

    /**
     * @param name
     */
    public Estate(String name) {
        this.name = name;
        level = Level.Free;
        fee = 0;
        price = 1000;
        owner = null;
    }
    
    /**
     * If the estate has an owner and it's not the given player, they have to 
     * pay the estate's fee to the owner. Given player may go bankrupt, in that
     * case, no cash is given to the owner.
     * @param player
     * @throws BankrupcyException
     */
    @Override
    public void fieldAction(Player player) throws BankrupcyException {
        if (player != this.owner && level != Level.Free){
            System.out.println("\t> "
                + player.getName() + " has to pay " + fee
                + " to " + this.owner.getName());
            player.changeCashBy(-fee); // src of BankrupcyException
            this.owner.changeCashBy(fee); // in case of BankrupcyException owner gets nothing
        }
    }

    /**
     * If the estate is Free, the given player tries to buy it. If no
     * BankrupcyException is thrown, the estate becomes Taken, price and fee
     * increases, the given player becomes the owner.
     * <p>
     * If the estate is Taken, the given player tries to upgrade it. If no
     * BankrupcyException is thrown, the estate becomes Developed, price and fee
     * increases.
     * <p>
     * If the estate is Developed, it cannot be upgraded.
     * @param player
     * @throws BankrupcyException
     * @throws FullyDevelopedEstateException
     */
    public void upgrade(Player player)
        throws BankrupcyException, FullyDevelopedEstateException {
        switch(level) {
            case Free -> { 
                try {
                    player.changeCashBy(-price);
                    owner = player;
                    level = Level.Taken;
                    fee = 500;
                    price = 4000;
                    System.out.println("\t> "+name + " bought by "+player.getName());
                    break;
                } catch(BankrupcyException e){}
            }
            case Taken -> {
                try {
                    player.changeCashBy(-price);
                    level = Level.Developed;
                    fee = 2000;
                    System.out.println("\t> "+name + " upgraded by "+player.getName());
                    break;
                } catch(BankrupcyException e){}
            }
            case Developed -> throw new FullyDevelopedEstateException();
        }
    }

    /**
     * Estate level becomes Free, owner becomes null.
     * @param player
     */
    public void foreclose(Player player) {
        level = Level.Free;
        owner = null;
    }
    
    /**
     * @return true if estate level is Free
     */
    public boolean isFree() {return level == Level.Free;}

    /**
     * @return true if estate level is Taken
     */
    public boolean isTaken() {return level == Level.Taken;}

    /**
     * @return true if estate level is Developed
     */
    public boolean isDeveloped() {return level == Level.Developed;}    

    /**
     * @return estate name
     */
    public String getName() {return name;}

    /**
     * @return estate level
     */
    public Level getLevel() {return level;}

    /**
     * @return estate price
     */
    public int getPrice() {return price;}    

    /**
     * @return estate owner
     */
    public Player getOwner() {return owner;}
}
