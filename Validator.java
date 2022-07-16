package model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * interface for validating and manipulating the user data entered in GUI TextFields prior to Object creation
 */
public interface Validator {
    /**
     *
     * @param invField the TextField where inventory data was entered
     * @param minField the TextField where minimum data was entered
     * @param maxField the TextField where maximum data was entered
     * @param nameField the TextField where name data was entered
     * @param priceField the TextField where price data was entered
     * @param errorLabel the label being used to display error messages if the input data is invalid
     *
     * @return true iff the user input values for all @param fields is valid and able to be parsed correctly
     *
     * ensures that all required user-entered data for Part/Product Object creation is present and adheres to any
     * applicable restrictions
     */
    static boolean allInputFieldsAreValid(TextField invField, TextField minField, TextField maxField,
                                           TextField nameField, TextField priceField, Label errorLabel){

        TextField[] partFields = {invField, minField, maxField, nameField, priceField};
        if (anyTextFieldIsEmpty(partFields)) {
            errorLabel.setText("Error - please enter data for all fields.");
            return false;
        }
        else if (!isPosDouble(priceField.getText())) {
            errorLabel.setText("Error - please ensure 'Price' field is a non-negative numeric value.");
            return false;
        }
        else if (inputForNumericFieldsIsInvalid(invField, minField, maxField)) {
            errorLabel.setText("Error - please ensure fields 'Min', 'Max', and 'Inv' are integers >= 0.");
            return false;
        }
        else if (inventoryAmountsAreInvalid(invField, minField, maxField)) {
            errorLabel.setText("Error - please ensure 'Min' < 'Max' and 'Min <= 'Inv' <= 'Max'.");
            return false;
        }
        else
            return true;
    }

    /**
     *
     * @param fields list of TextFields where input is expected
     * @return true if any TextField in @param fields is empty
     *
     * returns true if any TextField in @param fields is empty
     */
    static boolean anyTextFieldIsEmpty(TextField[] fields){
        for (TextField tf : fields)
            if (tf.getText().isEmpty())
                return true;
        return false;
    }

    /**
     *
     * @param invField the TextField where inventory data was entered
     * @param minField the TextField where minimum data was entered
     * @param maxField the TextField where maximum data was entered
     *
     * @return true if any @param's text is not able to be parsed as a positive integer
     *
     * returns true if any @param is unable to be parsed as an integer
     */
    static boolean inputForNumericFieldsIsInvalid(TextField invField, TextField minField, TextField maxField){
        return (isNotPosInt(maxField.getText()) || isNotPosInt(minField.getText()) ||
                isNotPosInt(invField.getText()));
    }

    /**
     *
     * @param invField the TextField where inventory data was entered
     * @param minField the TextField where minimum data was entered
     * @param maxField the TextField where maximum data was entered
     *
     * @return true if @param minField is not less than @param maxField, or if @param invField is not between or equal
     * to @param minField and @param maxField
     *
     * returns true if any illegal inventory value has been entered by the user
     */
    static boolean inventoryAmountsAreInvalid(TextField invField, TextField minField, TextField maxField){
        return (Integer.parseInt(minField.getText()) >= Integer.parseInt(maxField.getText()) ||
                Integer.parseInt(invField.getText()) > Integer.parseInt(maxField.getText()) ||
                Integer.parseInt(invField.getText()) < Integer.parseInt(minField.getText()));
    }

    /**
     *
     * @param input String to be parsed as an integer
     * @return true if @param input can be parsed as an integer
     *
     * returns true if @param input is able to be parsed as an integer
     */
    static boolean isInteger(String input){
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     *
     * @param input String to be parsed as a non-negative integer
     * @return true if @param input either cannot be parsed as an integer, or has an integer value of less than 0
     *
     * returns true if @param input either cannot be parsed as an integer, or has an integer value of less than 0
     */
    static boolean isNotPosInt(String input){
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e){
            return true;
        }
        return !(Integer.parseInt(input) >= 0);
    }

    /**
     *
     * @param s String to be parsed as a non-negative double
     * @return true if @param s can be parsed as a non-negative numeric value
     *
     * returns true if @param s can be parsed as a non-negative numeric value
     */
    static boolean isPosDouble(String s){
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e){
            return false;
        }
        return Double.parseDouble(s) >= 0;
    }

    /**
     *
     * @param fieldsToRemoveSpacesFrom array of TextFields to remove white space from
     *
     * removes white space from the text of every TextField in @param fieldsToRemoveSpacesFrom
     */
    static void removeWhiteSpaceFromNumericFields(TextField[] fieldsToRemoveSpacesFrom){
        for (TextField tf : fieldsToRemoveSpacesFrom)
            tf.setText(tf.getText().replace(" ", ""));
    }

    /**
     *
     * @param fieldsToClear array of TextFields to clear
     *
     * clears the text contents of every TextField in @param fieldsToClear
     */
    static void clearTextFields(TextField[] fieldsToClear){
        for (TextField tf : fieldsToClear)
            tf.clear();
    }
}