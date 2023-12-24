package controller;

import Dto.ScheduleDto;
import Dto.Tm.ScheduleTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ScheduleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ScheduleFormController {
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colVetName;
    public TableColumn colDuration;
    public TableColumn colMod;
    public TableView tblSchedule;

    public  ScheduleModel model = new ScheduleModel();
    private Image image = new Image("/view/Assets/icon/settings.png");
    private ImageView imageView = new ImageView(image);
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadData();
    }


    public void printScheduleOnAction(ActionEvent event) {
    }

    public void mailVetsOnAction(ActionEvent event) {

    }

    public void refreshOnAction(ActionEvent event) {
        loadData();
    }

    public void addScheduleOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/EmployeeDash/addNewScheduleItem.fxml"));
        AddNewScheduleItemController controller = loader.getController();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void loadData(){
        ObservableList<ScheduleTm> oblist = FXCollections.observableArrayList();
        try {
            List<ScheduleDto> list = model.getScheduleData();
            for (ScheduleDto d: list) {
                oblist.add(
                        new ScheduleTm(
                                d.getDate(),
                                d.getTime(),
                                d.getVetName(),
                                d.getDuration(),
                                getJFXButton()
                        )
                );
            }
            tblSchedule.setItems(oblist);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private JFXButton getJFXButton() {
        JFXButton bt = new JFXButton();
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            bt.setGraphic(imageView);
            return bt;
    }

    public void setCellValueFactory(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
//        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colVetName.setCellValueFactory(new PropertyValueFactory<>("vetName"));
        colMod.setCellValueFactory(new PropertyValueFactory<>("modButton"));
    }

}
