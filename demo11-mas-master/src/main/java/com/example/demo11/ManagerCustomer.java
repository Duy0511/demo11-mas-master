package com.example.demo11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ManagerCustomer implements Initializable {
        @FXML
        private TableView table;
        @FXML
        private TextField search;
        @FXML
        private TableColumn<Customer, Integer> id;
        @FXML
        private TableColumn<Customer, String> name;
        @FXML
        private TableColumn<Customer, Integer> identycard ;
        @FXML
        private TableColumn<Customer, Integer> point;
        @FXML
        private TableColumn<Customer, String> phonenumber;
        @FXML
        private TableColumn<Customer, String> adress;
        @FXML
        private TextField nameText;
        @FXML
        private TextField idText;
        @FXML
        private TextField phonenumberText;
        @FXML
        private TextField addressText;
        @FXML
        private TextField identycardText;
        @FXML
        private TextField pointText;

        public ObservableList<Customer> customers = FXCollections.observableArrayList(
                new Customer("Duy",23,100,1234534123,"Ha Noi","092312341234"),
                 new Customer("Minh",63,10,1999999999,"Ha Noi","082341334"),
                 new Customer("Chien",29,160,45243423,"Ha Noi","0239123112"),
                 new Customer("Khanh",73,190,333213333,"Ha Noi","084124124"),
                 new Customer("Tu",53,120,234534123,"Ha Noi","0145138991")
        );

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            table.setEditable(true);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        point.setCellValueFactory(new PropertyValueFactory<>("point"));
        identycard.setCellValueFactory(new PropertyValueFactory<>("identyCard"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        table.setItems(customers);


    }

        public void AddCustomer(ActionEvent e) throws IOException {
         Customer customer = new Customer();
         customer.setName(nameText.getText());
         customer.setAddress(addressText.getText());
         customer.setId(Integer.parseInt(idText.getText()));
         customer.setPoint(Integer.parseInt(pointText.getText()));
         customer.setPhoneNumber(phonenumberText.getText());
         customer.setIdentyCard(Integer.parseInt(identycardText.getText()));
         customers.add(customer);
         FindCustomer();
    }
        public void FindCustomer(){
            FilteredList<Customer > filteredData = new FilteredList(customers,b->true);
            search.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate(Customer ->{
                    if(newValue.isEmpty() || newValue.isBlank()|| newValue == null){
                        return true;
                    }
                    String searchKey = newValue.toLowerCase();
                    if(Customer.getName().toLowerCase().indexOf(searchKey) > -1){
                        return true;
                    } else if(Customer.getAddress().toLowerCase().indexOf(searchKey) >-1 ){
                        return true;
                    } else if(Customer.getPhoneNumber().toLowerCase().indexOf(searchKey) >-1) {
                        return true;
                    } else if(String.valueOf(Customer.getId()).toLowerCase().indexOf(searchKey) >-1) {
                        return true;
                    } else if(String.valueOf(Customer.getIdentyCard()).toLowerCase().indexOf(searchKey)>-1){
                        return true;
                    } else if(String.valueOf(Customer.getPoint()).toLowerCase().indexOf(searchKey)>-1){
                        return true;
                    } else{
                        return false;
                    }
                }
                );
                SortedList<Customer> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            });
        }




        public void Delete(ActionEvent e) {
        Customer selected = (Customer) table.getSelectionModel().getSelectedItem();
        customers.remove(selected);


    }
}