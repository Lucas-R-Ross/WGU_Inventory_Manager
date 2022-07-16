package model;

/**
 * blueprint for InHouse Objects
 */
public class InHouse extends Part {

    /**
     * the machine ID number for this InHouse Part
     */
    private int machineID;

    /**
     * creates a new InHouse Object
     *
     * @param id the InHouse Part's unique ID number
     * @param name the InHouse Part's name
     * @param price the InHouse Part's price
     * @param stock the InHouse Part's inventory level, or amount of this Product on hand
     * @param min the minimum amount of this InHouse Part that should be kept on hand
     * @param max the maximum amount of this InHouse Part that should be kept on hand
     * @param machineId the machine ID of this InHouse Part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /**
     * assigns @param machineId to the machine ID field of this InHouse Object
     *
     * @param machineId the integer machine ID to assign to this InHouse Object
     */
    public void setMachineId(int machineId){
        this.machineID = machineId;
    }

    /**
     * returns the machine ID of this InHouse Object
     *
     * @return the machine ID of this InHouse Object
     */
    public int getMachineId(){
        return this.machineID;
    }
}