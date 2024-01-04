package controller;

import bo.BOFactory;
import bo.custom.VetBo;
import dto.Tm.VetTm;
import dto.VetDto;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class VetFormController {
    public AnchorPane root;
    public TextField txtLastName;
    public TextField txtFirstName;
    public TextField txtContact;
    public TableColumn tblColVetId;
    public TableColumn tblColName;
    public TableColumn tblColContact;
    public TableColumn tblColSchedule;
    public JFXButton btnClear;
    public TextField txtID;
    public TableColumn colEmail;
    public TextField txtEmail;
    public TableView tblVet;
    public AnchorPane sidepane;
    public Label idnotValid;
    public Label fnameNotValid;
    public Label lastNameNotValid;
    public Label contactNotValid;
    public Label emailNotValid;
    public Label datelbl;
    public Label timelbl;

    //    private VetModel model = new VetModel();
    private VetBo bo = (VetBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.VET);
    public void clearOnAction(ActionEvent actionEvent) {
        txtEmail.clear();
        txtLastName.clear();
        txtContact.clear();
        txtFirstName.clear();
        txtID.clear();
    }

    public void searchVetOnAction() {
        String id = txtID.getText();
        VetDto dto = null;
        try {
            dto = bo.searchVet(id);

        txtID.setText(dto.getVetId());
        String[]name = dto.getVetName().split(" ");
        txtFirstName.setText(name[0]);
        txtLastName.setText(name[1]);
        txtContact.setText(dto.getContact());
        txtEmail.setText(dto.getEmail());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        try {
            if(bo.deleteVet(id)){
                new Alert(Alert.AlertType.CONFIRMATION,"Veterianrian Deleted !!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String vetId = txtID.getText();
        String fullname = txtFirstName.getText() +" "+ txtLastName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        if(!txtID.getText().isEmpty()||!txtFirstName.getText().isEmpty()||!txtEmail.getText().isEmpty()|!txtLastName.getText().isEmpty()||!txtContact.getText().isEmpty()) {
            if(checkReg()) {
                var dto = new VetDto(vetId, fullname, contact, email);
                try {
                    if (bo.saveVeterinarian(dto)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Veterinarian Saved !!").show();
                        loadAllData();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    private boolean checkReg() {
        if (Pattern.matches("!|@|#|\'$\'|%|^|_|\'*\'|",txtFirstName.getText())){
            fnameNotValid.setVisible(true);
            return false;
        } else if (Pattern.matches("!|@|#|\'$\'|%|^|_|\'*\'|", txtLastName.getText())) {
            lastNameNotValid.setVisible(true);
            return false;
        } else if (!Pattern.matches("(0|94|\\+94)([1-7]([0-9]))[0-9]{7}",txtContact.getText())) {
            contactNotValid.setVisible(true);
            return false;
        } else if (!Pattern.matches("^(?=.{1,256}$)[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$", txtEmail.getText())) {
            emailNotValid.setVisible(true);
            return false;
        } else return true;
    }
    public void initialize(){
        timelbl.setText((LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))));
        datelbl.setText(String.valueOf(LocalDate.now()));
        setValueCellFactory();
        loadAllData();
    }

    private void loadAllData() {
        ObservableList<VetTm> oblist = FXCollections.observableArrayList();

        try {
            List<VetDto> dto = bo.loadAllVets();
            for (VetDto d:dto) {
                oblist.add(
                       new VetTm(
                               d.getVetId(),
                               d.getVetName(),
                               d.getContact(),
                               d.getEmail(),
                               new JFXButton("Schedule")
                       )
                );
            }
            tblVet.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setValueCellFactory() {
        tblColVetId.setCellValueFactory(new PropertyValueFactory<>("vetId"));
        tblColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblColSchedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
