package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * GUI controller that handles GUI component action events such as mouse clicks and typed keys
 */
public class GuiForm implements Initializable, Validator{

/*--------------------------------------------------------------------------------------------------------------------*/

                                        /* Variable Declarations */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * the Pane used for displaying Parts in the main menu
     */
    @FXML
    private Pane partsMainPane;

    /**
     * the Pane used for displaying Products in the main menu
     */
    @FXML
    private Pane productsMainPane;

    /**
     * the form for creating or modifying Parts
     */
    @FXML
    private Pane partPane;

    /**
     * the form for creating or modifying Products
     */
    @FXML
    private Pane productPane;

    /**
     * the confirmation window that appears to confirm program operations such as creation, modification and deletion of
     * Parts and Products
     */
    @FXML
    private Pane validationPane;

    /**
     * the TextField label in the Parts form that displays either as 'Machine ID' or 'Company Name' based on the type
     * (InHouse or Outsourced) the user selects
     */
    @FXML
    private Label machineIdOrCompanyNameLabel;

    /**
     * the label that displays at the top of the Products form to indicate either Add or Modify mode
     */
    @FXML
    private Label addOrModProductLabel;

    /**
     * the label that displays at the top of the Parts form to indicate either Add or Modify mode
     */
    @FXML
    private Label addOrModPartLabel;

    /**
     * the label in the validation pane that displays the current action to be confirmed (ex. confirm add Part)
     */
    @FXML
    private Label confirmLabel;

    /**
     * the label that will display error messages in the main menu Parts pane
     */
    @FXML
    private Label partsMainPaneErrorLabel;

    /**
     * the label that will display error messages in the Parts creation / modification pane if input is invalid
     */
    @FXML
    private Label partInputErrorLabel;

    /**
     * the label that will display error messages in the main menu Products pane
     */
    @FXML
    private Label productsMainErrorLabel;

    /**
     * the label that will display error messages in the Products creation / modification pane if input is invalid
     */
    @FXML
    private Label productInputErrorLabel;

    /**
     * the textfield where a Part's ID number is displayed - this field is disabled and cannot be modified
     */
    @FXML
    private TextField partIDField;

    /**
     * the textfield where a Part's name may be entered in the Part creation/modification form
     */
    @FXML
    private TextField partNameField;

    /**
     * the textfield where a Part's inventory stock may be entered in the Part creation/modification form
     */
    @FXML
    private TextField partInvField;

    /**
     * the textfield where a Part's price may be entered in the Part creation/modification form
     */
    @FXML
    private TextField partPriceField;

    /**
     * the textfield where a Part's maximum allowed stock may be entered in the Part creation/modification form
     */
    @FXML
    private TextField partMaxField;

    /**
     * the textfield where a Part's minimum allowed stock may be entered in the Part creation/modification form
     */
    @FXML
    private TextField partMinField;

    /**
     * the textfield used to enter either a Part's Machine ID number or Company name, depending on the type of Part
     * being created/modified
     */
    @FXML
    private TextField idOrCompanyNameField;

    /**
     * the main menu search bar used to search the Parts tableview for matching results to keywords
     */
    @FXML
    private TextField partSearchField;

    /**
     * the main menu search bar used to search the Products tableview for matching results to keywords
     */
    @FXML
    private TextField productSearchBar;

    /**
     * the search bar within the Products creation/modification pane to search the list of all Parts for keyword matches
     */
    @FXML
    private TextField productPartSearchField;

    /**
     * the textfield where a Product's ID number is displayed - this field is disabled and cannot be modified
     */
    @FXML
    private TextField prodIDField;

    /**
     * the textfield where a Product's name may be entered in the Product creation/modification form
     */
    @FXML
    private TextField prodNameField;

    /**
     * the textfield where a Product's inventory stock may be entered in the Product creation/modification form
     */
    @FXML
    private TextField prodInvField;

    /**
     * the textfield where a Product's price may be entered in the Product creation/modification form
     */
    @FXML
    private TextField prodPriceField;

    /**
     * the textfield where a Product's minimum allowed stock may be entered in the Product creation/modification form
     */
    @FXML
    private TextField prodMinField;

    /**
     * the textfield where a Product's maximum allowed stock may be entered in the Product creation/modification form
     */
    @FXML
    private TextField prodMaxField;

    /**
     * the main menu tableview used to display all created Parts
     */
    @FXML
    private TableView<Part> allPartsTableView;

    /**
     * the Product creation/modification form tableview used to display all created Parts
     */
    @FXML
    private TableView<Part> productAllPartsView;

    /**
     * the Product creation/modification form tableview used to display all Parts associated with the current Product
     */
    @FXML
    private TableView<Part> associatedPartsView;

    /**
     * the main menu tableview used to display all created Products
     */
    @FXML
    private TableView<Product> allProductsView;

    /**
     * the column used to display a Part's ID number in the main menu Parts tableview
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * the column used to display a Part's inventory stock in the main menu Parts tableview
     */
    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    /**
     * the column used to display a Product's inventory stock in the main menu Products tableview
     */
    @FXML
    private TableColumn<Part, Integer> productInvCol;

    /**
     * the column used to display a Product's ID number in the main menu Products tableview
     */
    @FXML
    private TableColumn<Part, Integer> productIDCol;

    /**
     * the column used to display a Part's ID number in the Product creation/modification all-Parts tableview
     */
    @FXML
    private TableColumn<Part, Integer> productAllPartsIDCol;

    /**
     * the column used to display a Part's inventory stock in the Product creation/modification all-Parts tableview
     */
    @FXML
    private TableColumn<Part, Integer> productAllPartsInvCol;

    /**
     * the column used to display a Part's ID number in the Product creation/modification associated-Parts tableview
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDCol;

    /**
     * the column used to display a Part's inventory stock in the Product creation/modification associated-Parts
     * tableview
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartsInvCol;


    /**
     * the column used to display a Part's name in the main menu Parts tableview
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * the column used to display a Product's name in the main menu Products tableview
     */
    @FXML
    private TableColumn<Part, String> productNameCol;

    /**
     * the column used to display a Part's name in the Product creation/modification all-Parts tableview
     */
    @FXML
    private TableColumn<Part, String> productAllPartsNameCol;

    /**
     * the column used to display a Part's name in the Product creation/modification associated-Parts tableview
     */
    @FXML
    private TableColumn<Part, String> associatedPartsNameCol;

    /**
     * the column used to display a Part's price in the main menu Parts tableview
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * the column used to display a Product's price in the main menu Products tableview
     */
    @FXML
    private TableColumn<Part, Double> productPriceCol;

    /**
     * the column used to display a Part's price in the Product creation/modification all-Parts tableview
     */
    @FXML
    private TableColumn<Part, Double> productAllPartsPriceCol;

    /**
     * the column used to display a Part's price in the Product creation/modification associated-Parts tableview
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;

    /**
     * the radio button in the Parts creation/modification pane used to specify that the resulting Object will be of
     * type InHouse
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * the radio button in the Parts creation/modification pane used to specify that the resulting Object will be of
     * type Outsourced
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Inventory Object to manage created Parts and Products
     */
    Inventory inventory = Inventory.getInstance();

    /**
     * String variable that will change to represent different program modes and enable proper functionality
     * using only one 'confirm' button and FXML form
     */
    private String programMode = "";

/*--------------------------------------------------------------------------------------------------------------------*/

                                        /* TableView Initialization */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Initializes TableViews and their corresponding Columns
     *
     * @param url the url address to be used
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableViews();
    }

    /**
     * Initializes each TableView's Columns to the proper corresponding Object data
     */
    private void initTableViews(){
        allPartsTableView.setItems(Inventory.getAllParts());
        initTableColumns(partIdColumn, partPriceColumn, partNameColumn, partInvColumn);

        allProductsView.setItems(Inventory.getAllProducts());
        initTableColumns(productIDCol, productPriceCol, productNameCol, productInvCol);

        productAllPartsView.setItems(Inventory.getAllParts());
        initTableColumns(productAllPartsIDCol, productAllPartsPriceCol, productAllPartsNameCol, productAllPartsInvCol);

        associatedPartsView.setItems(Inventory.getTempAssociatedParts());
        initTableColumns(associatedPartsIDCol, associatedPartPriceCol, associatedPartsNameCol, associatedPartsInvCol);
    }

    /**
     * Takes in a TableView's Columns as arguments and assigns each Column the correct Object data to display.
     * Overrides the TableCell Class's updateItem() method to enable decimal precision 2 format display.
     *
     * @param idCol The TableColumn Object which will store the ID values
     * @param priceCol The TableColumn Object which will store the price values
     * @param nameCol The TableColumn Object which will store the name values
     * @param invCol The TableColumn Object which will store the inventory amount values
     */
    private void initTableColumns(TableColumn<Part, Integer> idCol, TableColumn<Part, Double> priceCol,
                                  TableColumn<Part, String> nameCol, TableColumn<Part, Integer> invCol){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(partPriceCol -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean isEmpty) {
                super.updateItem(price, isEmpty);
                if (isEmpty)
                    setText("");
                else
                    setText(String.format("%.2f", price));
            }
        });
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                              /* Add, Modify, and Delete Part / Product Controls */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Switches from the main menu to the 'Add Part' form when the 'Add Part' button is clicked.
     * Clears the Part form TextFields and sets the ID field to autogenerated and disabled.
     */
    @FXML
    private void partsAddButtonOnClick(){
        programMode = "AddPart";
        partsMainPaneErrorLabel.setText("");
        hideMainMenu();
        partPane.setDisable(false);
        partPane.setOpacity(1);
        idOrCompanyNameField.clear();
        TextField[] fieldsToClear = {partNameField, partInvField, partPriceField, partMinField, partMaxField};
        Validator.clearTextFields(fieldsToClear);

        addOrModPartLabel.setText("Add Part");
        partIDField.setPromptText("Auto gen- disabled");
    }

    /**
     * Switches from the main menu to the 'Add Product' form when the 'Add Product' button is clicked.
     * Clears Product form TextFields and resets the associated parts TableView items for a new Product.
     */
    @FXML
    private void productsAddButtonOnClick() {
        programMode = "AddProduct";
        hideMainMenu();
        TextField[] fieldsToClear = {prodNameField, prodInvField, prodPriceField, prodMinField, prodMaxField};
        Validator.clearTextFields(fieldsToClear);
        productPane.setDisable(false);
        productPane.setOpacity(1);
        addOrModProductLabel.setText("Add Product");
        prodIDField.setPromptText("Auto gen- disabled");
        Inventory.clearTempAssociatedParts();
        associatedPartsView.setItems(Inventory.getTempAssociatedParts());
        associatedPartsView.refresh();
        productAllPartsView.refresh();
    }

    /**
     * Switches from main menu to 'Modify Part' form when 'Modify Part' button is clicked.
     * Ensures that a Part has been selected from the Parts TableView to be modified.
     * Sets the 'Modify Part' form by setting TextFields to corresponding values for the selected Part.
     */
    @FXML
    private void partsModButtonOnClick(){
        programMode = "ModPart";

        Part userSelection = allPartsTableView.getSelectionModel().getSelectedItem();
        if (userSelection == null)
            partsMainPaneErrorLabel.setText("Error - Please select a part to modify.");
        else {
            partsMainPaneErrorLabel.setText("");
            hideMainMenu();
            partPane.setDisable(false);
            partPane.setOpacity(1);
            initModifyPartScreen(userSelection);
            addOrModPartLabel.setText("Modify Part");
        }
    }

    /**
     * Switches from main menu to 'Modify Product' form when 'Modify Product' button is clicked.
     * Ensures that a Product has been selected from the Products TableView to be modified.
     * Sets the 'Modify Product' form by setting TextFields to corresponding values for the selected Product.
     */
    @FXML
    private void productsModButtonOnClick(){
        Product productToModify = allProductsView.getSelectionModel().getSelectedItem();
        if (productToModify == null)
            productsMainErrorLabel.setText("Error - Please select a product to modify.");
        else {
            programMode = "ModProduct";
            hideMainMenu();
            productPane.setDisable(false);
            productPane.setOpacity(1);
            addOrModProductLabel.setText("Modify Product");
            initModifyProductFields(productToModify);
            associatedPartsView.setItems(productToModify.getAllAssociatedParts());
            productAllPartsView.refresh();
            associatedPartsView.refresh();
        }
    }

    /**
     * Ensures a Part for deletion has been selected, and if it has, fades the main menu and displays the validation
     * pane to confirm deletion of that Part.
     */
    @FXML
    private void partDeleteOnClick(){
        Part partToBeDeleted = allPartsTableView.getSelectionModel().getSelectedItem();
        if (partToBeDeleted == null)
            partsMainPaneErrorLabel.setText("Error - Please select a part to delete.");
        else{
            partsMainPaneErrorLabel.setText("");
            programMode = "DeletePart";
            confirmLabel.setText("Confirm Part Delete");
            fadeMainMenu();
            showValidationPane();
            validationPane.setLayoutX(240);
            validationPane.setLayoutY(110);
        }
    }

    /**
     * Ensures that a Product selected for deletion is not null and does not have any associated Parts with it.
     * If the Product selected for deletion meets these requirements, the validation Pane is displayed for confirmation.
     */
    @FXML
    private void productDeleteOnClick(){
        Product productToBeDeleted = allProductsView.getSelectionModel().getSelectedItem();
        if (productToBeDeleted == null)
            productsMainErrorLabel.setText("Error - please select a product to delete.");
        else if (!productToBeDeleted.getAllAssociatedParts().isEmpty())
            productsMainErrorLabel.setText("Error - this product has associated part(s) and cannot be deleted.");
        else{
            programMode = "DeleteProduct";
            productsMainErrorLabel.setText("");
            confirmLabel.setText("Confirm Product Delete");
            fadeMainMenu();
            showValidationPane();
            validationPane.setLayoutX(560);
            validationPane.setLayoutY(130);
        }
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                               /* Search Bars */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Triggers a search for any matching Parts after a key has been entered into the main menu Parts search bar
     */
    @FXML
    private void partSearchOnKeyTyped(){
        Inventory.searchInputForPartResults(allPartsTableView, partSearchField, partsMainPaneErrorLabel);
    }
    /**
     * Triggers a search for any matching Products after a key has been entered into the main menu Products search bar
     */
    @FXML
    private void productSearchOnKeyTyped(){
        Inventory.searchInputForProductResults(allProductsView, productSearchBar, productsMainErrorLabel);
    }
    /**
     * Triggers a search for any matching Parts after a key has been entered into the Product Form Parts search bar
     */
    @FXML
    private void productPartSearchFieldOnKeyTyped(){
        Inventory.searchInputForPartResults(productAllPartsView, productPartSearchField,
                productInputErrorLabel);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                            /* Part Pane Controls */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * resets the Parts Pane's error label and enables the 'Machine ID' field
     */
    @FXML
    private void inHouseRadioButtonOnClick(){
        partInputErrorLabel.setText("");
        idOrCompanyNameField.clear();
        machineIdOrCompanyNameLabel.setText("Machine ID");
    }

    /**
     * resets the Parts Pane's error label and enables the 'Company Name' field
     */
    @FXML
    private void outsourcedRadioButtonOnClick(){
        partInputErrorLabel.setText("");
        idOrCompanyNameField.clear();
        machineIdOrCompanyNameLabel.setText("Company Name");
    }

    /**
     * Ensures all Part form TextFields contain valid data to create a new Part, and if they do, displays the
     * validation Pane for confirmation when the 'Save Part' button is clicked.
     */
    @FXML
    private void partSaveButtonOnClick(){
        TextField[] numericFields = {partInvField, partPriceField, partMinField, partMaxField};
        Validator.removeWhiteSpaceFromNumericFields(numericFields);
        if (inHouseRadioButton.isSelected()) {
            idOrCompanyNameField.setText(idOrCompanyNameField.getText().replace(" ", ""));
            if (Validator.isNotPosInt(idOrCompanyNameField.getText())) {
                partInputErrorLabel.setText("Error - please ensure 'Machine ID' is a positive integer.");
                return;
            }
        }
        if (Validator.allInputFieldsAreValid(partInvField, partMinField, partMaxField, partNameField, partPriceField,
                partInputErrorLabel)) {
            partInputErrorLabel.setText("");
            partPane.setDisable(true);
            validationPane.setLayoutX(240);
            validationPane.setLayoutY(110);
            showValidationPane();


            if (programMode.equals("AddPart"))
                confirmLabel.setText("Confirm Add Part");
            else if (programMode.equals("ModPart"))
                confirmLabel.setText("Confirm Modify Part");
        }
    }

    /**
     * Switches from the Parts Pane to the main menu if user cancels current Part operation
     */
    @FXML
    private void cancelPartOnClick(){
        partInputErrorLabel.setText("");
        productsMainErrorLabel.setText("");
        partPane.setOpacity(0);
        partPane.setDisable(true);
        showMainMenu();
        allPartsTableView.refresh();
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                            /* Product Pane Controls  */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Ensures all Product form TextFields contain valid data to create a new Product, and if they do, displays the
     * validation Pane for confirmation when the 'Save Product' button is clicked.
     */
    @FXML
    private void productSaveButtonOnClick(){
        TextField[] numericFields = {prodInvField, prodPriceField, prodMinField, prodMaxField};
        Validator.removeWhiteSpaceFromNumericFields(numericFields);
        if (Validator.allInputFieldsAreValid(prodInvField, prodMinField, prodMaxField, prodNameField,
                prodPriceField, productInputErrorLabel)){
            productInputErrorLabel.setText("");
            productPane.setDisable(true);
            showValidationPane();
            if (programMode.equals("AddProduct"))
                confirmLabel.setText("Confirm Add Product");
            else if (programMode.equals("ModProduct"))
                confirmLabel.setText("Confirm Modify Product");
            validationPane.setLayoutX(290);
            validationPane.setLayoutY(150);
        }
    }

    /**
     * Ensures a Part to be associated with the current Product is not null and has not already been associated.
     * If the Part meets these criteria, it is added to the Product's associated Parts list.
     */
    @FXML
    private void addAssociatedPartButtonOnClick(){
        productInputErrorLabel.setText("");
        Part selectedPart = productAllPartsView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            if (programMode.equals("AddProduct")){
                if (!Inventory.getTempAssociatedParts().contains(selectedPart))
                    Inventory.getTempAssociatedParts().add(selectedPart);
                else
                    productInputErrorLabel.setText("Error - the selected part is already associated.");
            }
            else if (programMode.equals("ModProduct")){
                Product productBeingModified = allProductsView.getSelectionModel().getSelectedItem();
                if (!productBeingModified.getAllAssociatedParts().contains(selectedPart))
                    productBeingModified.addAssociatedPart(selectedPart);
                else
                    productInputErrorLabel.setText("Error - the selected part is already associated.");
            }
        }
        else
            productInputErrorLabel.setText("Error - please select a part to associate.");
    }

    /**
     * Ensures that a Part to be disassociated from a Product is not null, and if it is not, displays the validation
     * Pane for confirmation.
     */
    @FXML
    private void removeAssociatedOnClick(){
        Part associatedPart = associatedPartsView.getSelectionModel().getSelectedItem();
        if (associatedPart == null)
            productInputErrorLabel.setText("Error - please select a part to disassociate.");
        else {
            if (programMode.equals("AddProduct"))
                programMode = "RemovePartAddProduct";
            else if (programMode.equals("ModProduct"))
                programMode = "RemovePartModProduct";

            productInputErrorLabel.setText("");
            productPane.setDisable(true);
            showValidationPane();
            confirmLabel.setText("Confirm Remove Part");
            validationPane.setLayoutX(290);
            validationPane.setLayoutY(150);
        }
    }

    /**
     * switches from the Products Pane to the main menu if user cancels current Product operation
     */
    @FXML
    private void cancelProductOnClick(){
        productInputErrorLabel.setText("");
        productsMainErrorLabel.setText("");
        partsMainPaneErrorLabel.setText("");
        productPane.setOpacity(0);
        productPane.setDisable(true);
        showMainMenu();
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                    /* TextField manipulation / initialization */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Initializes the Modify Part screen by setting the TextFields to the corresponding data in the selected Part
     *
     * @param modPart the Part selected to be modified
     */
    private void initModifyPartScreen(Part modPart){
        partIDField.setPromptText(Integer.toString(modPart.getId()));
        partNameField.setText(modPart.getName());
        partPriceField.setText(Double.toString(modPart.getPrice()));
        partMaxField.setText(Integer.toString(modPart.getMax()));
        partMinField.setText(Integer.toString(modPart.getMin()));
        partInvField.setText(Integer.toString(modPart.getStock()));

        if (modPart instanceof InHouse){
            inHouseRadioButton.setSelected(true);
            outsourcedRadioButton.setSelected(false);
            machineIdOrCompanyNameLabel.setText("Machine ID");
            idOrCompanyNameField.setText(Integer.toString(((InHouse) modPart).getMachineId()));
        }
        else if (modPart instanceof Outsourced){
            inHouseRadioButton.setSelected(false);
            outsourcedRadioButton.setSelected(true);
            machineIdOrCompanyNameLabel.setText("Company Name");
            idOrCompanyNameField.setText(((Outsourced) modPart).getCompanyName());
        }
        else
            idOrCompanyNameField.clear();
    }

    /**
     * Sets the Product pane's TextFields to the corresponding Product Object data
     *
     * @param modProduct the selected Product to be modified
     */
    private void initModifyProductFields(Product modProduct){
        prodIDField.setPromptText(Integer.toString(modProduct.getId()));
        prodNameField.setText(modProduct.getName());
        prodPriceField.setText(Double.toString(modProduct.getPrice()));
        prodMaxField.setText(Integer.toString(modProduct.getMax()));
        prodMinField.setText(Integer.toString(modProduct.getMin()));
        prodInvField.setText(Integer.toString(modProduct.getStock()));
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                            /* Pane Changing */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * displays the main menu comprised of the main Part and Product panes
     */
    private void showMainMenu(){
        partsMainPane.setDisable(false);
        partsMainPane.setOpacity(1);
        productsMainPane.setDisable(false);
        productsMainPane.setOpacity(1);
        partSearchField.clear();
        productSearchBar.clear();
        allPartsTableView.refresh();
        allProductsView.refresh();
        partsMainPaneErrorLabel.setText("");
        productsMainErrorLabel.setText("");
    }

    /**
     * hides the main menu comprised of the main Part and Product panes
     */
    private void hideMainMenu(){
        partsMainPane.setOpacity(0);
        partsMainPane.setDisable(true);
        productsMainPane.setOpacity(0);
        productsMainPane.setDisable(true);
    }

    /**
     * disables the main menu without removing all opacity to verify a Part/Product deletion
     */
    private void fadeMainMenu(){
        productsMainPane.setDisable(true);
        productsMainPane.setOpacity(0.25);
        partsMainPane.setDisable(true);
        partsMainPane.setOpacity(0.25);
    }

    /**
     * displays the confirmation Pane to verify add, modify and delete actions
     */
    private void showValidationPane(){
        validationPane.setDisable(false);
        validationPane.setOpacity(1);
    }

    /**
     * hides the confirmation pane
     */
    private void hideValidationPane(){
        validationPane.setOpacity(0);
        validationPane.setDisable(true);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                        /* Confirmation Yes/No buttons */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Determines the appropriate execution following user confirmation (in the validation pane) based on the program's
     * current mode. Possible program modes are Add/Modify/Delete Part, Add/Modify/Delete Product, and RemovePart from
     * a Product's associated Parts list.
     */
    @FXML
    private void confirmYesButtonOnClick(){
        switch (programMode) {
            case "AddPart" ->  executeAddPart();
            case "ModPart" -> executeModPart();
            case "DeletePart" -> executeDelPart();
            case "AddProduct" -> executeAddProd();
            case "ModProduct" -> executeModProd();
            case "DeleteProduct" -> executeDelProd();
            case "RemovePartAddProduct", "RemovePartModProduct" -> executeRemoveAssociatedPart();
        }
        confirmLabel.setText("");
        hideValidationPane();
    }

    /**
     * current program mode is used to determine action after user chooses 'no' in confirmation pane
     */
    @FXML
    private void confirmNoButtonOnClick() {
        switch (programMode) {
            case "AddPart", "ModPart" -> partPane.setDisable(false);
            case "AddProduct", "ModProduct" -> productPane.setDisable(false);
            case "DeletePart", "DeleteProduct" -> showMainMenu();
            case "RemovePartAddProduct" -> {
                programMode = "AddProduct";
                productPane.setDisable(false);
            }
            case "RemovePartModProduct" -> {
                programMode = "ModProduct";
                productPane.setDisable(false);
            }
        }
        partsMainPaneErrorLabel.setText("");
        hideValidationPane();
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                        /* Program Mode Executions */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * creates a new Part Object based on user input and adds it to the Inventory's list of all Parts
     */
    private void executeAddPart(){
        Part newPart;
        if (inHouseRadioButton.isSelected()){
            newPart = new InHouse(inventory.generateNewPartId(), partNameField.getText(),
                    Double.parseDouble(partPriceField.getText()), Integer.parseInt(partInvField.getText()),
                    Integer.parseInt(partMinField.getText()), Integer.parseInt(partMaxField.getText()),
                    Integer.parseInt(idOrCompanyNameField.getText()));
        }
        else{
            newPart = new Outsourced(inventory.generateNewPartId(), partNameField.getText().trim(),
                    Double.parseDouble(partPriceField.getText()), Integer.parseInt(partInvField.getText()),
                    Integer.parseInt(partMinField.getText()), Integer.parseInt(partMaxField.getText()),
                    idOrCompanyNameField.getText());
        }
        Inventory.addPart(newPart);
        partPane.setOpacity(0);
        partPane.setDisable(true);
        showMainMenu();
    }

    /**
     * RUNTIME ERROR:
     *
     * I am using the same TextField to take in both Machine ID (for InHouse Parts) and Company Name
     * (for Outsourced Parts). The text in the field is read in differently based on the type (either parsed as an
     * integer or simply read in as a String) and is stored to either the created / modified Object's machineID or
     * companyName instance variable.
     *
     * What determines the type (and thus the handling of the data) are the Part form radio buttons. The selected button
     * is used as a condition for Adding a new Part. I was not using the selected button, however, for the 'Modify Part'
     * logic. Rather, I was checking for the type of current Object to be modified using 'instanceof'. Until I went back
     * and began testing the program, I did not realize this posed a problem. If a Part of type 'InHouse' is modified
     * within the Part pane to now be an 'Outsourced' and then saved, not only did I have no logic to replace the old
     * Object with a replacement Object of the updated type, but I also encountered a NumberFormatException when the
     * method entered into the wrong Object type's TextField parsing condition and tried to parse a companyName String
     * value where a machineID integer was expected.
     *
     * To solve this problem, I implemented a condition at the time of Part modification data assignment to check if the
     * Object's type to be modified corresponds with the current selected radio button in the Part pane. If it does not,
     * the Object must be flushed and replaced with a new Object of the intended type. Before this switch can happen
     * however, any Products to which the Part is associated must remove the Part from their associated Parts lists to
     * avoid the 'ghost' part falsely showing up in the Product pane's tableviews after it is replaced. Those same
     * products must then add the new Part Object to their associated parts lists.
     *
     * Upon making these changes, modifying the type of an existing part no longer results in a thrown exception, and
     * all TableView displays in the Products pane are working correctly.
     */
    private void executeModPart(){
        Part partToModify = allPartsTableView.getSelectionModel().getSelectedItem();
        if ((partToModify instanceof InHouse && outsourcedRadioButton.isSelected()) ||
                (partToModify instanceof Outsourced && inHouseRadioButton.isSelected())) {
            List<Product> relatedProducts = new ArrayList<>();
            for (Product p : Inventory.getAllProducts())
                if (p.getAllAssociatedParts().contains(partToModify))
                    relatedProducts.add(p);
            Inventory.deletePart(partToModify);
            if (partToModify instanceof InHouse)
                partToModify = new Outsourced(0, null, 0, 0, 0, 0, null);
            else
                partToModify = new InHouse(0, null, 0, 0, 0, 0, 0);
            Inventory.addPart(partToModify);
            for (Product p : relatedProducts)
                p.addAssociatedPart(partToModify);
            partToModify.setId(Integer.parseInt(partIDField.getPromptText()));
        }
        partToModify.setName(partNameField.getText().trim());
        partToModify.setPrice(Double.parseDouble(partPriceField.getText()));
        partToModify.setStock(Integer.parseInt(partInvField.getText()));
        partToModify.setMax(Integer.parseInt(partMaxField.getText()));
        partToModify.setMin(Integer.parseInt(partMinField.getText()));
        if (partToModify instanceof InHouse)
            ((InHouse) partToModify).setMachineId(Integer.parseInt(idOrCompanyNameField.getText().trim()));
        else if (partToModify instanceof Outsourced)
            ((Outsourced) partToModify).setCompanyName(idOrCompanyNameField.getText().trim());
        Inventory.updatePart(Inventory.getAllParts().indexOf(partToModify), partToModify);
        partPane.setOpacity(0);
        partPane.setDisable(true);
        showMainMenu();
        allPartsTableView.refresh();
    }

    /**
     * Removes user selected Part from Inventory's list of all Parts. If the Parts TableView is currently displaying
     * the Part in a search result, the Part is also deleted from the current list of search results so that it will
     * not falsely continue to appear after deletion.
     */
    private void executeDelPart(){
        Part partToBeDeleted = allPartsTableView.getSelectionModel().getSelectedItem();
        if (Inventory.deletePart(partToBeDeleted)) {
            if (!partSearchField.getText().isEmpty()) {
                Inventory.deletePartFromSearchResults(partToBeDeleted);
                if (Inventory.getPartSearchResults().isEmpty())
                    partsMainPaneErrorLabel.setText("No matches found.");
            }
            allPartsTableView.refresh();
        }
        showMainMenu();
    }

    /**
     * Creates a new Product Object with user input data and stores it in the Inventory's list of all Products
     */
    private void executeAddProd(){
        Product newProduct = new Product(inventory.generateNewProdId(), prodNameField.getText().trim(),
                Double.parseDouble(prodPriceField.getText()), Integer.parseInt(prodInvField.getText()),
                Integer.parseInt(prodMinField.getText()), Integer.parseInt(prodMaxField.getText()));
        for (Part p : associatedPartsView.getItems())
            newProduct.addAssociatedPart(p);
        Inventory.addProduct(newProduct);
        productPane.setOpacity(0);
        productPane.setDisable(true);
        showMainMenu();
    }

    /**
     * Updates user selected Product with new data from Product form TextFields, and updates the Inventory's Product
     * list to reflect the changes
     */
    private void executeModProd(){
        Product productToModify = allProductsView.getSelectionModel().getSelectedItem();
        productToModify.setName(prodNameField.getText().trim());
        productToModify.setPrice(Double.parseDouble(prodPriceField.getText()));
        productToModify.setStock(Integer.parseInt(prodInvField.getText()));
        productToModify.setMax(Integer.parseInt(prodMaxField.getText()));
        productToModify.setMin(Integer.parseInt(prodMinField.getText()));

        Inventory.updateProduct(Inventory.getAllProducts().indexOf(productToModify), productToModify);
        allProductsView.refresh();
        productPane.setOpacity(0);
        productPane.setDisable(true);
        showMainMenu();
    }

    /**
     * Removes user selected Product from Inventory's list of all Products. If the Products TableView is currently
     * displaying the Product in a search result, the Product is also deleted from the current list of search results so
     * that it will not falsely continue to appear after deletion.
     */
    private void executeDelProd(){
        Product productToBeDeleted = allProductsView.getSelectionModel().getSelectedItem();
        if (Inventory.deleteProduct(productToBeDeleted)) {
            if (!productSearchBar.getText().isEmpty()) {
                Inventory.deleteProductFromSearchResults(productToBeDeleted);
                if (Inventory.getProductSearchResults().isEmpty())
                    productsMainErrorLabel.setText("No matches found.");
            }
            allProductsView.refresh();
        }
        showMainMenu();
    }

    /**
     * Removes user selected Part from the associated Parts for the current Product
     */
    private void executeRemoveAssociatedPart(){
        if (programMode.equals("RemovePartAddProduct")){
            programMode = "AddProduct";
            Inventory.getTempAssociatedParts().remove(associatedPartsView.getSelectionModel().getSelectedItem());
        }
        else if (programMode.equals("RemovePartModProduct")){
            programMode = "ModProduct";
            if (allProductsView.getSelectionModel().getSelectedItem().deleteAssociatedPart(associatedPartsView.
                    getSelectionModel().getSelectedItem()))
                associatedPartsView.refresh();
        }
        productPane.setDisable(false);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                      /* Error Label Refreshing */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Refreshes the Parts panel error label when a Parts form TextField is clicked
     */
    @FXML
    private void partFieldOnClick(){
        partInputErrorLabel.setText("");
    }

    /**
     * Refreshes the Products panel error label when a Products form TextField is clicked
     */
    @FXML
    private void productFieldOnClick() {
        productInputErrorLabel.setText("");
    }

    /**
     * Refreshes the main menu Parts error label when the Parts search bar is clicked
     */
    @FXML
    private void partSearchOnClick(){
        partsMainPaneErrorLabel.setText("");
    }
    /**
     * Refreshes the main menu Products error label when Product pane components are clicked
     */
    @FXML
    private void productsMainPaneOnClick() {
        productsMainErrorLabel.setText("");
    }

/*--------------------------------------------------------------------------------------------------------------------*/

                                           /* Program Exit */

/*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Closes the software when the exit button is clicked
     */
    @FXML
    private void onExitButtonClicked(){
        System.exit(0);
    }
}