package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private ApiClient apiClient;
    private Stage primaryStage;
    private ObservableList<Flat> items = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        final HBox hb = new HBox();
        Scene scene = new Scene(new Group());
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);
        primaryStage.setTitle("Real Estate Scanner");
        primaryStage.setScene(scene);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hb);

        TableView mainTable = createTable();

        final Button nextButton = new Button("Next");
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                items.clear();
                requestFlats(true);
            }
        });

        hb.getChildren().addAll(nextButton);
        hb.setSpacing(3);
        vbox.getChildren().addAll(mainTable);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

//        grid.prefWidthProperty().bind(primaryStage.widthProperty());
//        grid.prefHeightProperty().bind(primaryStage.heightProperty());
//        grid.setMaxWidth(Double.MAX_VALUE);
//        grid.setMaxHeight(Double.MAX_VALUE);
//        grid.setHgap(10);
//        grid.setVgap(12);

        primaryStage.show();
    }

    private final TableView createTable() {
        TableView table = new TableView();
        table.setEditable(false);
        TableColumn idNameCol = new TableColumn("Id");
        idNameCol.setCellValueFactory(
                new PropertyValueFactory<Flat, Integer>("id"));
        idNameCol.setMinWidth(100);
        TableColumn addressNameCol = new TableColumn("Address");
        addressNameCol.setCellValueFactory(
                new PropertyValueFactory<Flat, String>("address"));
        addressNameCol.setMinWidth(300);
        TableColumn priceNameCol = new TableColumn("Price");
        priceNameCol.setCellValueFactory(
                new PropertyValueFactory<Flat, Integer>("price"));
        priceNameCol.setMinWidth(100);

        table.getColumns().addAll(idNameCol, addressNameCol, priceNameCol);
        requestFlats(true);
        table.setItems(items);
        table.setMaxWidth(Double.MAX_VALUE);
        table.setMaxHeight(Double.MAX_VALUE);
//        table.prefWidthProperty().bind(primaryStage.widthProperty());
//        table.prefHeightProperty().bind(primaryStage.heightProperty());
        return table;
    }

    private void requestFlats(boolean nextPage) {
        FlatList flatsList = null;
        try {
            flatsList = getApiClient().getFlats(1, 25);
        } catch(IOException iex){
            System.out.println("Error when requesting flats: " + iex.getMessage());
            return;
        }
        for(Flat flat: flatsList.entries){
            items.add(flat);
        }
    }

    private final ApiClient getApiClient() {
        if(apiClient != null)
            return apiClient;
        apiClient = new ApiClient();
        return apiClient;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
