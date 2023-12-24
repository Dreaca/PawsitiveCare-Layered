package controller;

import Dto.ItemDto;
import Dto.Tm.ItemTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTooltip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ItemModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageItemFormController {
    public TextField txtSearch;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQTO;
    public TableColumn colUnitPrice;
    public JFXButton btnSupplyOrder;
    public JFXButton btnUpdate;
    public JFXButton btnAdd;
    public TableColumn colDel;
    private Image image =  new Image("/view/Assets/icon/trashbin.png");
    private ImageView imgV = new ImageView(image);

    private ItemModel model = new ItemModel();
    Tooltip t1 = new Tooltip("Add item");
    Tooltip t2 = new Tooltip("Update Item");
    Tooltip t3 = new Tooltip("Create supply order");
    public void searchItemOnAction(){
    }
    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoards/AdminDash/addNewItem.fxml"))));
        stage.centerOnScreen();
        stage.show();
        loadItems();

    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoards/AdminDash/updateItemForm.fxml"))));
        stage.show();
        loadItems();
    }

    public void addSpplyOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBoards/AdminDash/suppliyOrderForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){
        setCellValueFactory();
        loadItems();
        Tooltip.install(btnAdd,t1);
        Tooltip.install(btnUpdate,t2);
        Tooltip.install(btnSupplyOrder,t3);
    }

    private void loadItems() {
        ObservableList<ItemTm> oblist  =FXCollections.observableArrayList();

        try {
            List<ItemDto> dto = model.getAllItems();
            for (ItemDto d: dto) {
                oblist.add(
                        new ItemTm(
                                d.getItemId(),
                                d.getDescription(),
                                d.getQtyOnHand(),
                                d.getUnitPrice(),
                                new JFXButton()
                        )
                );
                for (int i = 0; i < oblist.size(); i++) {
                    JFXButton deletebutton = oblist.get(i).getDeletebutton();
                    imgV.setFitHeight(20);
                    imgV.setFitWidth(20);
                    deletebutton.setGraphic(imgV);
                }
                tblItem.setItems(oblist);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTO.setCellValueFactory(new PropertyValueFactory<>("QTO"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDel.setCellValueFactory(new PropertyValueFactory<>("deletebutton"));
    }
}
