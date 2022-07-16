package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * blueprint for Product Objects
 */
public class Product extends Part {

    /**
     * Observable List used to display a Product's associated Parts
     */
    private final ObservableList<Part> associatedParts;

    /**
     * Constructs a new Product Object
     *
     * @param id The Product's unique ID number
     * @param name  The Product's name
     * @param price The Product's price
     * @param stock The Product's inventory level, or amount of this Product on hand
     * @param min   The minimum amount of this Product that should be kept on hand
     * @param max   The maximum amount of this Product that should be kept on hand
     *
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     * adds @param part to the list of this Product's associated Parts
     *
     * @param part The Part Object to be associated with this Product
     */
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    /**
     * returns true if @param selectedAssociatedProduct exists in this Product's associated Parts list and is
     *         deleted successfully
     *
     * @param selectedAssociatedPart The Part to be disassociated from this Product
     * @return true if @param selectedAssociatedProduct exists in this Product's associated Parts list and is
     *         deleted successfully
     *
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (this.getAllAssociatedParts().contains(selectedAssociatedPart)) {
            this.associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * returns an ObservableList of all Parts associated with this Product
     *
     * @return an ObservableList of all Parts associated with this Product
     *
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}