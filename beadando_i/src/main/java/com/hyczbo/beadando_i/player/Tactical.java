package com.hyczbo.beadando_i.player;
import com.hyczbo.beadando_i.field.FullyDevelopedEstateException;
import com.hyczbo.beadando_i.field.Estate;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class Tactical extends Player {
    boolean wantsToBuy;

    /**
     * @param name
     */
    public Tactical(String name) {
        super(name);
        wantsToBuy = true;
    }
    
    /**
     * Tactical players skip every second purchase opportunity.
     * <p>
     * If the estate is Free or it's the player's own Taken estate,
     * they will try to buy/upgrade it. Upon success it will be added to their
     * estates and their class level wantsToBuy variable will be set to False.
     * <p>
     * If the estate is fully developed or they are going bankrupt, no
     * transaction happens.
     * <p>
     * If their class level wantsToBuy variable is False, it will be
     * set to True.
     * @param estate
     */
    @Override
    protected void playerAction(Estate estate) {
        boolean freeEstate = !estates.contains(estate) && estate.isFree();
        boolean ownEstate = estates.contains(estate) && estate.isTaken();
        
        if (wantsToBuy) {
            try {
                if (freeEstate || ownEstate) {
                    estate.upgrade(this);
                    wantsToBuy = false;
                    if (freeEstate) {estates.add(estate);} 
                    
                    System.out.println("\tthey now have: ");
                    for (Estate e : estates) {
                        System.out.println("\t\t- "+e.getName()
                                +"["+e.getLevel().toString()+"]");
                    }
                }
            }
            catch(FullyDevelopedEstateException e) {
                System.out.println("Fully upgraded estate");
            }
            catch(BankrupcyException e) {}
        }
        else {wantsToBuy = true;}
    }
    
    /**
     * @return class name
     */
    @Override
    public String toString() {return "Tactical";}
}
