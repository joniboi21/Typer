import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    static final String NAME = "Typer";
    @Override
    public void start(Stage primaryStage) {

        System.out.println("Multiple Texts Branch");

        // Get screen dimensions
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D bounds = primaryScreen.getVisualBounds();

        // WIDTH AND HEIGHT
        final double MAX_WIDTH = bounds.getWidth();
        final double MAX_HEIGHT = bounds.getHeight();

        // String mainTextString = "I really like to eat blueberries.";
        String mainTextString = "I really like to eat blueberries. They are my favorite fruit! My second favorite fruit would have to be raspberries.";
        int position = 7;

        // Left Text
        Text leftText = new Text(mainTextString.substring(0, position));
        leftText.setFill(Color.RED);
        leftText.setFont(Font.font("Menlo", 40));
        
        // Right Text
        Text rightText = new Text(mainTextString.substring(position));
        rightText.setFill(Color.BLUE);
        rightText.setFont(Font.font("Menlo", 40));

        // Textflow for Left and Right Text
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(leftText, rightText);

        // Create a root node and add the textflow
        StackPane root = new StackPane();
        root.getChildren().add(textFlow);

        // Create the scene
        Scene scene = new Scene(root, MAX_WIDTH, MAX_HEIGHT);

        // Close window event
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> {
            if (keyEvent.isMetaDown() && keyEvent.getCode() == KeyCode.W) {
                primaryStage.close();
            }
        });

        // Set the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle(App.NAME);
        primaryStage.show();
    }

    public static void mainText(String[] args) {
        launch(args);
    }
}
