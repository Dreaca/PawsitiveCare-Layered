package controller;

import bo.BOFactory;
import bo.custom.VetBo;
import dto.Tm.VetTm;
import dto.VetDto;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

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

        var dto = new VetDto(vetId,fullname,contact,email);
        try {
            if(bo.saveVeterinarian(dto)){
                new Alert(Alert.AlertType.CONFIRMATION,"Veterinarian Saved !!").show();
                loadAllData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void initialize(){
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
