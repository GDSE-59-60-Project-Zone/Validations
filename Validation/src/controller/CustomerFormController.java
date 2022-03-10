package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import view.tdm.CustomerTM;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class CustomerFormController implements Initializable {
    public TextField txtCusID;
    public TextField txtCusName;
    public TextField txtCusAddress;
    public TextField txtCusSalary;
    public TableView<CustomerTM> tblCustomer;
    public JFXButton btnSaveCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initializing table columns with TableTM Properties
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        //set edit and delete button from images to the table
        tblCustomer.getColumns().get(3).setCellValueFactory((param) -> {
            ImageView edit = new ImageView("/view/assets/icons/draw.png");
            ImageView delete = new ImageView("/view/assets/icons/trash.png");
            return new ReadOnlyObjectWrapper(new HBox(10, edit, delete));
        });


        //set click event for the Save Comment
        btnSaveCustomer.setOnMouseClicked(event -> {
            //Gather information from inputs
            saveCustomer();
        });

        btnSaveCustomer.setDisable(true);
    }

    private void saveCustomer() {
        String customerID = txtCusID.getText();
        String customerName = txtCusName.getText();
        String customerAddress = txtCusAddress.getText();
        double customerSalary = Double.parseDouble(txtCusSalary.getText());

        // Add to the Table
        CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, customerSalary);
        tblCustomer.getItems().add(customerTM);

        clearAllTexts();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        validate();
//        TextField = error
//        boolean // validation ok

        //if the enter key pressed
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = validate();
            //if the response is a text field
            //that means there is a error
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                saveCustomer();
            }

        }

    }

    private Object validate() {
        //Id Validation
        //C00-001
        //01. C00- this prefix is a must
        //02. after C00- only numbers allowed. minimum 3 (0-9) maximum 5
        //reg-ex ^(C00-)[0-9]{3,5}$

        //Name Validate
        //01. A-z any letter, Spaces are allowed
        //02. length min 3 max 15

        //Address Validate
        //01. A-z & 0-9 , /
        //02. Length min 4 max20

        //Salary
        //01. first number - 1-9
        //02. second 0-9 - o or more
        //03. (.34) if there is a decimal point there should have two numbers after the decimal point


        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(C00-)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");


        //get the type value
        if (!idPattern.matcher(txtCusID.getText()).matches()) {
            //if the input is not matching
            addError(txtCusID);
            return txtCusID;
        } else {
            //if the input is matching
            removeError(txtCusID);

            //if the Customer ID is ok.. then check the Customer Name
            if (!namePattern.matcher(txtCusName.getText()).matches()) {
                //if the input is not matching
                addError(txtCusName);
                return txtCusName;
            } else {
                //if the input is matching
                removeError(txtCusName);

                //if the Customer Name is ok.. then check the Customer Address
                if (!addressPattern.matcher(txtCusAddress.getText()).matches()) {
                    //if the input is not matching
                    addError(txtCusAddress);
                    return txtCusAddress;

                } else {
                    //if the input is matching
                    removeError(txtCusAddress);

                    //if the Customer Address is ok.. then check the Customer Salary
                    if (!salaryPattern.matcher(txtCusSalary.getText()).matches()) {
                        //if the input is not matching
                        addError(txtCusSalary);
                        return txtCusSalary;
                    } else {
                        //if the input is matching
                        removeError(txtCusSalary);

                    }
                }
            }
        }
        return true;
    }

    private void removeError(TextField txtField) {
        txtField.getParent().setStyle("-fx-border-color: green");
        btnSaveCustomer.setDisable(false);
    }

    private void addError(TextField txtField) {
        if (txtField.getText().length() > 0) {
            txtField.getParent().setStyle("-fx-border-color: red");
        }
        btnSaveCustomer.setDisable(true);
    }

    public void clearAllTexts() {
        txtCusID.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusSalary.clear();
        txtCusID.requestFocus();
    }


}
