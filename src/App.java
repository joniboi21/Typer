import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    static final String NAME = "Typer";
    private int position = 0;

    @Override
    public void start(Stage primaryStage) {

        // Get screen dimensions
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D bounds = primaryScreen.getVisualBounds();

        // Max width and height
        final double MAX_WIDTH = bounds.getWidth();
        final double MAX_HEIGHT = bounds.getHeight();

        // Create a root node and add the textflow
        StackPane root = new StackPane();

        // Background
        Color backgroundColor = Color.rgb(27, 27, 27);
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);

        // Main string
        StringBuilder mainTextString = new StringBuilder("I really like to eat blueberries. They are my favorite fruit! My second favorite fruit would have to be raspberries.");
        
        // Textflow to contain all characters of main string
        TextFlow textFlow = new TextFlow();
        Font textFlowFont = Font.font("Menlo", FontWeight.SEMI_BOLD, 40);
        Color goodColor = Color.rgb(215, 21, 220);
        Color neutralColor = Color.rgb(215, 220, 21);
        Color badColor = Color.rgb(215, 21, 21);
        Color overflowColor = Color.rgb(200, 150, 21);

        // Create new Text objects for each character and add to textFlow
        for (char c : mainTextString.toString().toCharArray()) {
            Text cText = new Text(Character.toString(c));
            cText.setFont(textFlowFont);
            cText.setFill(neutralColor);

            textFlow.getChildren().add(cText);
        }
        textFlow.setPrefWidth(MAX_WIDTH * 0.80);
        root.getChildren().add(textFlow);
        textFlow.setPadding(new Insets(30, 60, 30, 60));

        // Create the scene
        Scene scene = new Scene(root, MAX_WIDTH, MAX_HEIGHT);

        // Close window event
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> {
            if (keyEvent.isMetaDown() && keyEvent.getCode() == KeyCode.W) {
                primaryStage.close();
            }
        });

        // Key pressed event
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> {

            if (!keyEvent.getText().isEmpty()) {
                char pressedKey = keyEvent.getText().charAt(0);
                char currentChar = mainTextString.charAt(position);

                if (pressedKey == currentChar) {
                    Text good = (Text) textFlow.getChildren().get(position);
                    good.setFill(goodColor);

                    // Test complete
                    if (position == mainTextString.length()-1) {
                        
                    }

                } else {
                    if (currentChar == ' ') {
                        mainTextString.insert(position, pressedKey);

                        Text overflow = new Text(Character.toString(pressedKey));
                        overflow.setFill(overflowColor);
                        overflow.setFont(textFlowFont);
                        textFlow.getChildren().add(position, overflow);


                    } else {
                        Text bad = (Text) textFlow.getChildren().get(position);
                        bad.setFill(badColor);
                    }
                }

                position++;

            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                position = (position != 0) ? position - 1 : position;
                Text current = (Text) textFlow.getChildren().get(position);

                if (current.getFill().equals(overflowColor)) {
                    mainTextString.deleteCharAt(position);
                    textFlow.getChildren().remove(position);

                } else {
                    Text prev = (Text) textFlow.getChildren().get(position);
                    prev.setFill(neutralColor);
                }
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
