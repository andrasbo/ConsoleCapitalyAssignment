package com.hyczbo.beadando_i.player;
import com.hyczbo.beadando_i.field.FullyDevelopedEstateException;
import com.hyczbo.beadando_i.field.Estate;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class Greedy extends Player {

    /**
     * @param name
     */
    public Greedy(String name) {super(name);}

    /**
     * Greedy players take every purchase opportunity.
     * <p>
     * If the estate is Free or it's the player's own Taken estate,
     * they will try to buy/upgrade it. Upon success it will be added to their
     * estates.
     * <p>
     * If the estate is fully developed or they are going bankrupt, no
     * transaction happens.
     * @param estate
     */
    @Override
    protected void playerAction(Estate estate) {
        boolean wantsToBuy = cash - estate.getPrice() > 0;
        boolean freeEstate = !estates.contains(estate) && estate.isFree();
        boolean ownEstate = estates.contains(estate) && estate.isTaken();
        
        if (wantsToBuy) {
            try {
                if (freeEstate) {
                    estate.upgrade(this);
                    estates.add(estate);
                    
                    System.out.println("\tthey now have: ");
                    for (Estate e : estates) {
                        System.out.println("\t\t- "+e.getName()
                                +"["+e.getLevel().toString()+"]");
                    }
                }
                if (ownEstate) {
                    estate.upgrade(this);

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
    }
    
    /**
     * @return class name
     */
    @Override
    public String toString() {return "Greedy";}
}
