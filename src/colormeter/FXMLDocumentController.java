/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colormeter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author HoldenHc
 */
public class FXMLDocumentController implements Initializable {
    
    private static final long SLEEP_DELAY = 10L;
    private static final int SLIDER_MAX = 20;
    private static final int SLIDER_MIN = 0;
    @FXML
    Rectangle pane_color = new Rectangle();
    @FXML
    Label redLabel = new Label();
    @FXML
    Label greenLabel = new Label();
    @FXML
    Label blueLabel = new Label();
    @FXML
    ImageView imageView = new ImageView();
    @FXML
    Slider slider = new Slider();
    PixelColor pixelColor;
    
    private int sliderValue = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        slider.setMax(SLIDER_MAX);
        slider.setMin(SLIDER_MIN);
        //slider.setShowTickLabels(true);
        //slider.setShowTickMarks(true);
        
        slider.valueProperty().addListener(ov->{
            pixelColor.setSectionReduction((int)slider.getValue());
            //System.out.println();
        });
        
        pixelColor = new PixelColor();
        Thread getPixel = new Thread(pixelColor);
        getPixel.start();
        
        int b = 9;
        
//        
        new Thread(new Runnable() {           
            @Override
            public void run() { 
                try {
                    while (true) {
                        Platform.runLater(() -> {
                            //test.setText("" + r.();
                            int[] rgvq = pixelColor.getRGV();
                            Color color = Color.rgb(rgvq[0], rgvq[1], rgvq[2]);
                            
                            pane_color.setFill(color);
                            redLabel.setText("R:" + rgvq[0]);
                            greenLabel.setText("G:" + rgvq[1]);
                            blueLabel.setText("B:" + rgvq[2]);
                            
                            redLabel.setFont(Font.font("Consolas",
                            FontWeight.BOLD, 24));
                            greenLabel.setFont(Font.font("Consolas",
                            FontWeight.BOLD, 24));
                            blueLabel.setFont(Font.font("Consolas",
                            FontWeight.BOLD, 24));

                            
                            Image image = SwingFXUtils.toFXImage(pixelColor.getImage50x50(), null);; //getWr();
                            //PixelReader pr = image.getPixelReader();
                            //Image im2 = new WritableImage(pr, 20, 20, 10, 10);
                            imageView.setImage(image);
                            
                        } // Run from JavaFX GUI
                        );

                        Thread.sleep(SLEEP_DELAY);
                    }
                }
                catch (InterruptedException ex) {
                }
            }
        }).start();
       
    }    
    
}
