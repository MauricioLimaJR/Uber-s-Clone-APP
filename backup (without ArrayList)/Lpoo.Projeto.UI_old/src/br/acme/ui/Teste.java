package br.acme.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Teste extends Application {

@Override
public void start(Stage primaryStage) {
    CustomPane root = new CustomPane();
    root.setPadding(new Insets(20));
    root.setHgap(10);
    root.setVgap(10);
    for (int i = 0; i < 5; i++) {
        RectangleBox recB = new RectangleBox();
        root.getChildren().add(recB);
    }

    Scene scene = new Scene(root, 300, 250);

    primaryStage.setTitle("Hello World!");
    primaryStage.setScene(scene);
    primaryStage.show();
}


public static void main(String[] args) {
    launch(args);
}

}

class RectangleBox extends VBox {

static int index = 0;

public RectangleBox() {
    Rectangle rec = new Rectangle(150, 150);
    rec.setFill(Color.GREEN);

    StackPane sp = new StackPane();
    sp.getChildren().add(rec);
    Label label = new Label(Integer.toString(index));
    index++;
    sp.getChildren().add(label);
    getChildren().add(sp);
    final ContextMenu cm = new ContextMenu();
    MenuItem removeRec = new MenuItem("Remove Rectangle");
    removeRec.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {

        	((CustomPane) RectangleBox.this.getParent()).getChildren().remove(RectangleBox.this);
        	//((CustomPane) getParent()).getPane().getChildren().remove(0); //here is the problem, I want this line to remove the rectangle I clicked on(now it's removing first element in the pane).

        }
    });

    cm.getItems().add(removeRec);
    createContextMenuEvent(cm, rec);

}

private void createContextMenuEvent(final ContextMenu cm, final Rectangle rec) {
    addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(rec, t.getScreenX(), t.getScreenY());
            }
        }
    });
}

}

class CustomPane extends FlowPane {

public CustomPane() {
    //setAlignment(Pos.CENTER);
    setHgap(25);
    setVgap(25);
    setPadding(new Insets(20));
}

public CustomPane getPane() {
    return this;
}
}