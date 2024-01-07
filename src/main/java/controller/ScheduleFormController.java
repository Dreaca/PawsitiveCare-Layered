package controller;

import bo.BOFactory;
import bo.custom.ScheduleBo;
import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import dto.ScheduleDto;
import dto.Tm.ScheduleTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class ScheduleFormController {
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colVetName;
    public TableColumn colDuration;
    public TableColumn colMod;
    public TableView tblSchedule;

//    public  ScheduleModel model = new ScheduleModel();
    private ScheduleBo bo = (ScheduleBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.SCHEDULE);
    private Image image = new Image("/view/Assets/icon/settings.png");
    private ImageView imageView = new ImageView(image);
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadData();
    }


    public void printScheduleOnAction(ActionEvent event) {
            createJasperReport();
    }
    private void createJasperReport() {

        try {
            InputStream stream = getClass().getResourceAsStream("/report/schedule.jrxml");
            JasperDesign load = JRXmlLoader.load(stream);
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report,null, DbConnection.getInstance().getConnection());

            JasperViewer.viewReport(print);
        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
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
            List<ScheduleDto> list = bo.loadScheduleList();
            oblist.clear();
            for (ScheduleDto d: list) {
                oblist.add(
                        new ScheduleTm(
                                d.getDate(),
                                d.getTime(),
                                d.getDuration(),
                                d.getVetName(),
                                getJFXButton()
                        )
                );
                for (int i = 0; i < oblist.size(); i++) {
                    int finalI = i;
                    oblist.get(i).getModButton().setOnAction(actionEvent -> {
                        JFXButton modButton = oblist.get(finalI).getModButton();
                        double x = modButton.localToScreen(modButton.getBoundsInLocal()).getMinX();
                        double y = modButton.localToScreen(modButton.getBoundsInLocal()).getMinY();
                        try {
                            ContextMenu con = loadPopup(oblist.get(finalI).getModButton());
                            con.getItems().get(0).setOnAction(actionEvent1 -> {

                            });
                            con.getItems().get(1).setOnAction(actionEvent1 -> {
                                ScheduleTm tm = oblist.get(finalI);
                                try {
                                    boolean deleted = bo.deleteScheduleItem(tm.getVetName(),tm.getDate(),tm.getDuration(),tm.getTime());
                                    if (deleted) {
                                        loadData();
                                    }
                                } catch (SQLException e) {

                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            con.show(modButton,x,y);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
            tblSchedule.setItems(oblist);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ContextMenu loadPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Update");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);

        button2.setOnAction(actionEvent -> {
            System.out.println("delete button clicked");
        });
        return con;
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
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colVetName.setCellValueFactory(new PropertyValueFactory<>("vetName"));
        colMod.setCellValueFactory(new PropertyValueFactory<>("modButton"));
    }

}
