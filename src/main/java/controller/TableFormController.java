package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerTM;

import java.io.Serializable;
import java.util.Optional;

public class TableFormController implements Serializable {

    public TableView<CustomerTM> tblCustomers;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSaveCustomer;
    public Button btnNewCustomer;
    public Button BtnDeleteCustomer;

    public void initialize(){

        BtnDeleteCustomer.setDisable(true);

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observableValue, CustomerTM oldCustomer, CustomerTM newCustomer) {
                if (newCustomer ==null) {
                    BtnDeleteCustomer.setDisable(true);
                    btnSaveCustomer.setText("Save Customer");
                    txtId.setEditable(true);
                    return;
                }

                btnSaveCustomer.setText("Update Customer");
                BtnDeleteCustomer.setDisable(false);
                txtId.setText(newCustomer.getId());
                //txtId.setDisable(true);
                txtId.setEditable(false);
                txtName.setText(newCustomer.getName());
                txtAddress.setText(newCustomer.getAddress());

            }
        });

        BtnDeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ObservableList<CustomerTM> olCustomers = tblCustomers.getItems();
                CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();

                if (selectedCustomer==null) {

                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtId.requestFocus();
                    return;
                }

                Optional<ButtonType> selectedOption = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to Delete customer",ButtonType.YES,ButtonType.NO).showAndWait();

                if (selectedOption.get()==ButtonType.YES) olCustomers.remove(selectedCustomer);


            }
        });

        btnNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tblCustomers.getSelectionModel().clearSelection();
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtId.requestFocus();
            }
        });
        //Lets map Column names
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        //Customer table Observable List
        ObservableList<CustomerTM> oList = tblCustomers.getItems();

        btnSaveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (txtId.getText().isBlank()){
                    new Alert(Alert.AlertType.ERROR,"Customer ID cannot be empty").showAndWait();
                    txtId.requestFocus();
                    return;
                }
                else if (txtName.getText().isBlank()) {
                    new Alert(Alert.AlertType.ERROR,"Customer Name cannot be empty").showAndWait();
                    txtName.requestFocus();
                    return;
                } else if (txtAddress.getText().isBlank()) {
                    new Alert(Alert.AlertType.ERROR,"Customer Address cannot be empty").showAndWait();
                    txtAddress.requestFocus();

                    return;
                }

                for (CustomerTM customer:oList) {
                    if (customer.getId().equalsIgnoreCase(txtId.getText())){
                        new Alert(Alert.AlertType.ERROR,"Duplicate customer ID's are not allowed").showAndWait();
                        txtId.requestFocus();
                        return;
                    }
                }

                ObservableList<CustomerTM> oList = tblCustomers.getItems();

                String id = txtId.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();

                CustomerTM newCustomer = new CustomerTM(id, name, address);
                oList.add(newCustomer);
                txtId.clear();
                txtAddress.clear();
                txtName.clear();
                txtId.requestFocus();

            }
        });




    }
}
