package model;

/**
 * blueprint for Outsourced Objects
 */
public class Outsourced extends Part{

    /**
     * the name of the company this Outsourced is associated with
     */
    private String companyName = "";

    /**
     * constructs a new Outsourced Object
     *
     * @param id the Outsourced Part's unique ID number
     * @param name  the Outsourced Part's name
     * @param price the Outsourced Part's price
     * @param stock the Outsourced Part's inventory level, or amount of this Product on hand
     * @param min   the minimum amount of this Outsourced Part that should be kept on hand
     * @param max   the maximum amount of this Outsourced Part that should be kept on hand
     * @param cName the company name of this Outsourced Part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String cName) {
        super(id, name, Double.parseDouble(String.format("%.2f", price)), stock, min, max);
        setCompanyName(cName);
    }

    /**
     * assigns @param cName to the company name field of this Outsourced Object
     *
     * @param cName the name of the company to be assigned to this Outsourced Object
     */
    public void setCompanyName(String cName){
        this.companyName = cName;
    }

    /**
     * returns the company name of this Outsourced Object
     *
     * @return the name of the company associated with this Outsourced Object
     */
    public String getCompanyName(){
        return this.companyName;
    }
}