package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * inventory class for storing and managing Part and Product Objects
 */
public class Inventory {

/*--------------------------------------------------------------------------------------------------------------------*/

                                       /* Variable Declarations */

/*--------------------------------------------------------------------------------------------------------------------*/
    /**
     * static integer counter incremented to generate unique Part IDs
     */
    private static int partIDCounter = 0;

    /**
     * static integer counter incremented to generate unique Product IDs
     */
    private static int productIDCounter = 999;

    /**
     * ObservableList used to display all Parts in the main menu Parts pane TableView
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * ObservableList used to display the Part search results of user entered keywords
     */
    private static final ObservableList<Part> partSearchResults = FXCollections.observableArrayList();

    /**
     * ObservableList used to display all Products in the main menu Products pane TableView
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * ObservableList used to display the Product search results of user entered keywords
     */
    private static final ObservableList<Product> productSearchResults = FXCollections.observableArrayList();

    /**
     * ObservableList used to store the Parts that will be assigned to a Product's associated Parts list as a Product
     * is being created
     */
    private static final ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();

    /**
     * defines a new Instance of the Inventory Class for Singleton implementation
     */
    private static final Inventory instance = new Inventory();

/*--------------------------------------------------------------------------------------------------------------------*/

                                                 /* Constructors */

/*--------------------------------------------------------------------------------------------------------------------*/
    /**
     * private constructor ensures this class can only be instantiated once internally
     */
    private Inventory(){
    }

    /**
     * returns the sole instance of this class. A new Inventory Object is only created the first time this method is
     * called - subsequent calls will point to the same Object initially created.
     *
     * @return the sole instance of the Inventory class. A new Inventory Object can only be created once.
     */
    public static Inventory getInstance(){
        return instance;
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                        /* Add, Update, Delete Parts & Products */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * adds a new Part Object to the Inventory's Parts list
     *
     * @param newPart the Part Object to be added to the Inventory
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * adds a new Product Object to the Inventory's Products list
     *
     * @param newProduct the Product Object to be added to the Inventory
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * updates the Inventory's Part list with an updated Part Object @param newPart at @param index
     *
     * @param index the index position in the Inventory's Parts list of the Part to be updated
     * @param selectedPart the Part Object to be updated
     */
    public static void updatePart(int index, Part selectedPart){
        getAllParts().remove(index);
        getAllParts().add(index, selectedPart);
    }

    /**
     * updates the Inventory's Product list with an updated Product Object @param newProduct at @param index
     *
     * @param index the index position in the Inventory's Products list of the Product to be updated
     * @param newProduct the Product Object to be updated
     */
    public static void updateProduct(int index, Product newProduct){
        getAllProducts().remove(index);
        getAllProducts().add(index, newProduct);
    }

    /**
     * returns true if @param selectedPart exists in the Inventory's list of Parts and is successfully deleted
     *
     * @param selectedPart the Part Object to be deleted from the Inventory's Parts list
     * @return true if @param selectedPart exists in the Inventory and is successfully deleted
     */
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            for (Product product : getAllProducts())
                product.deleteAssociatedPart(selectedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * returns true if @param selectedProduct exists in the Inventory's list of Products and is successfully deleted
     *
     * @param selectedProduct the Product Object to be deleted from the Inventory's Products list
     * @return true if @param selectedProduct exists in the Inventory and is successfully deleted
     */
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else
            return false;
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                     /* Part & Product Searching */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * returns the Part Object from the Inventory's Part list whose ID matches @param partId, and throws a
     * NullPointerException if such a Part does not exist
     *
     * @param partId the ID number of the Part to locate
     * @return the Part Object whose ID matches @param partId
     */
    public static Part lookupPart(int partId) throws NullPointerException{
        for (Part p : getAllParts()){
            if (p.getId() == partId)
                return p;
        }
        throw new NullPointerException();
    }

    /**
     * returns an ObservableList containing all Parts with a name starting with or matching @param partName
     *
     * @param partName the user entered keyword to conduct a search of the Inventory's Parts
     * @return an ObservableList containing all Parts with a name starting with or matching @param partName
     */
    public static ObservableList<Part> lookupPart(String partName){
        for (Part p : allParts){
            if (partName.length() <= p.getName().length() &&
                    (p.getName().toLowerCase().contains(partName.toLowerCase())))
                partSearchResults.add(p);
        }
        return partSearchResults;
    }

    /**
     * returns the Product Object from the Inventory's Product list whose ID matches @param productId, and throws a
     * NullPointerException if such a Product does not exist
     *
     * @param productId the ID number of the Product to locate
     * @return the Product Object whose ID matches @param productId
     */
    public static Product lookupProduct(int productId) throws NullPointerException{
        for (Product p : getAllProducts()){
            if (p.getId() == productId)
                return p;
        }
        throw new NullPointerException();
    }

    /**
     * returns an ObservableList containing all Products with a name starting with or matching @param productName
     *
     * @param productName the user entered keyword to conduct a search of the Inventory's Products
     * @return an ObservableList containing all Products with a name starting with or matching @param productName
     */
    public static ObservableList<Product> lookupProduct(String productName){
        for (Product p : allProducts){
            if (productName.length() <= p.getName().length() &&
                    p.getName().trim().substring(0,(productName.length())).equalsIgnoreCase(productName))
                productSearchResults.add(p);
        }
        return productSearchResults;
    }

    /**
     * Checks the Inventory's Part list for any matches to @param searchBar's text, displays those matches to @param
     * partsView if any are found, and displays an error message using @param errorLabel if no matches are found.
     *
     * @param partsView The TableView Object whose display will be updated based on search results
     * @param searchBar The TextField into which search arguments are being entered
     * @param errorLabel The Label which will display an error message if no search matches are found
     */
    public static void searchInputForPartResults(TableView<Part> partsView, TextField searchBar, Label errorLabel){
        if (searchBar.getText().isEmpty()){
            partsView.setItems(Inventory.getAllParts());
            partsView.refresh();
            errorLabel.setText("");
        }
        else{
            partSearchResults.clear();
            String searchInput = searchBar.getText().trim();
            if (Validator.isInteger(searchInput)) {
                try {
                    partSearchResults.add(Inventory.lookupPart(Integer.parseInt(searchInput)));
                }
                catch (NullPointerException e){
                    errorLabel.setText("");
                }
            }

            partsView.setItems(lookupPart(searchInput));
            partsView.refresh();

            if (partSearchResults.size() >= 1){
                errorLabel.setText("");
                if (partSearchResults.size() == 1)
                    partsView.getSelectionModel().select(0);
            }
            else
                errorLabel.setText("No matches found.");
        }
    }

    /**
     * Checks the Inventory's Product list for any matches to @param searchBar's text, displays those matches to @param
     * partsView if any are found, and displays an error using @param errorLabel if no matches are found.
     *
     * @param productsView The TableView Object whose display will be updated based on search results
     * @param searchBar The TextField into which search arguments are being entered
     * @param errorLabel The Label which will display an error message if no search matches are found
     */
    public static void searchInputForProductResults(TableView<Product> productsView,
                                              TextField searchBar, Label errorLabel){
        if (searchBar.getText().isEmpty()){
            productsView.setItems(Inventory.getAllProducts());
            productsView.refresh();
            errorLabel.setText("");
        }
        else{
            productSearchResults.clear();
            String searchInput = searchBar.getText().trim();
            if (Validator.isInteger(searchInput)) {
                try {
                    productSearchResults.add(Inventory.lookupProduct(Integer.parseInt(searchInput)));
                }
                catch (NullPointerException e){
                    errorLabel.setText("");
                }
            }

            productsView.setItems(lookupProduct(searchInput));
            productsView.refresh();

            if (productSearchResults.size() >= 1){
                errorLabel.setText("");
                if (productSearchResults.size() == 1)
                    productsView.getSelectionModel().select(0);
            }
            else
                errorLabel.setText("No matches found.");
        }
    }

    /**
     * returns an ObservableList of Parts matching with user entered search keywords
     *
     * @return an ObservableList of Parts matching with user entered search keywords
     */
    public static ObservableList<Part> getPartSearchResults(){
        return partSearchResults;
    }

    /**
     * returns an ObservableList of Products matching with user entered search keywords
     *
     * @return an ObservableList of Products matching with user entered search keywords
     */
    public static ObservableList<Product> getProductSearchResults(){
        return productSearchResults;
    }

    /**
     * removes @param searchResultToRemove Object from the list of currently displayed Part search results
     *
     * @param searchResultToRemove the Part Object to be removed from the List of Inventory Part search results
     */
    public static void deletePartFromSearchResults(Part searchResultToRemove){
        partSearchResults.remove(searchResultToRemove);
    }

    /**
     * removes @param searchResultToRemove Object from the list of currently displayed Product search results
     *
     * @param searchResultToRemove the Product Object to be removed from the List of Inventory Product search results
     */
    public static void deleteProductFromSearchResults(Product searchResultToRemove){
        productSearchResults.remove(searchResultToRemove);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                             /* Master & Temporary Part / Product Access */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * returns ObservableList of all Parts currently in the Inventory
     *
     * @return the ObservableList of all Parts currently in the Inventory
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * returns the ObservableList of all Products currently in the Inventory
     *
     * @return the ObservableList of all Products currently in the Inventory
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * returns the ObservableList of associated Parts temporarily being stored to create a new Product Object
     *
     * @return the ObservableList of associated Parts temporarily being stored to create a new Product Object
     */
    public static ObservableList<Part> getTempAssociatedParts(){
        return tempAssociatedParts;
    }

    /**
     * clears the Temporary Associated Parts list to refresh for new Object creation
     */
    public static void clearTempAssociatedParts(){
        tempAssociatedParts.clear();
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                     /* Part & Product ID Generation */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * increments the Inventory's Part ID counter and returns the value to allow for unique ID generation
     *
     * @return a unique, never-before-used ID number for a newly created Part Object
     */
    public int generateNewPartId(){
        return ++partIDCounter;
    }

    /**
     * increments the Inventory's Product ID counter and returns the value to allow for unique ID generation
     *
     * @return a unique, never-before-used ID number for a newly created Product Object
     */
    public int generateNewProdId(){
        return ++productIDCounter;
    }
}