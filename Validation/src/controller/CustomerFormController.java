package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import view.tdm.CustomerTM;

import java.net.URL;
import java.util.ResourceBundle;

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





        //set click event for the Save Comment
        btnSaveCustomer.setOnMouseClicked(event->{

            //Gather information from inputs
            String customerID = txtCusID.getText();
            String customerName = txtCusName.getText();
            String customerAddress = txtCusAddress.getText();
            double customerSalary = Double.parseDouble(txtCusSalary.getText());

            // Add to the Table
            CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, customerSalary);
            tblCustomer.getItems().add(customerTM);


        });
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }



}
