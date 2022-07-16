package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * FUTURE ENHANCEMENTS:
 *
 * This program could be adapted and scaled in many ways to make it more useful and robust. Perhaps the most prominent
 * of these is the implementation of persistent data. As of now, the data entered into the program is only retained with
 * each instance. This data could be entered into a system file or internal database so that it will not be lost. This
 * functionality would make the program much more practical for real-world situations.
 *
 * Another way the program could be improved is by increasing the functionality of the 'search' and 'delete' operations.
 * Adding the ability to filter a Part search by Company name or by type (InHouse or Outsourced) would allow users to
 * better visualize and interpret Part data. Likewise, adding the ability to delete multiple Parts or Products at a time
 * might prove useful; suppose a certain company producing parts goes out of business - all their Parts should be
 * deleted.
 *
 * Finally, the program could do a much better and more thorough job of handling possible runtime exceptions. There is
 * little meaningful logic, such as try/catches, to handle unexpected conditions apart from the input fields' number
 * parsing. An example of this is the deleteAssociatedPart() Product function - the method returns false if the
 * associated Part does not exist in the Product's associated Parts list, but there is no logic to handle a false
 * case where 'true' is expected.
 *
 */
/* Please locate the folder containing generated JavaDoc files in the main directory of the submission folder. The
   folder is named 'Lucas Ross C482 JavaDoc Files'.
 */
public class Main extends Application {
    /**
     *
     * @param primaryStage the main form to be initialized and displayed
     * @throws Exception if FXML resource cannot be located
     *
     * initializes the form by loading the specified FXML resource
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/GuiForm.fxml")));
        primaryStage.setTitle("Lucas Ross C482");
        primaryStage.setScene(new Scene(root, 839, 413));
        primaryStage.show();
    }

    /**
     * program entry - launches the GUI form
     */
    public static void main(String[] args) {
        launch(args);
    }
}