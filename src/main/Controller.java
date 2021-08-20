package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML private Label categoryLabel;
    @FXML private Label header;
    @FXML private Label header1;
    @FXML private Label header2;
    @FXML private Label header3;
    @FXML private Label header4;
    @FXML private Label header5;
    @FXML private Label header6;
    @FXML private Label header7;
    @FXML private Label header8;
    @FXML private Label header9;

    @FXML private Label time;
    @FXML private Label time1;
    @FXML private Label time2;
    @FXML private Label time3;
    @FXML private Label time4;
    @FXML private Label time5;
    @FXML private Label time6;
    @FXML private Label time7;
    @FXML private Label time8;
    @FXML private Label time9;

    @FXML private ImageView image;
    @FXML private ImageView image1;
    @FXML private ImageView image2;
    @FXML private ImageView image3;
    @FXML private ImageView image4;
    @FXML private ImageView image5;
    @FXML private ImageView image6;
    @FXML private ImageView image7;
    @FXML private ImageView image8;
    @FXML private ImageView image9;

    @FXML private ImageView icon;
    @FXML private ImageView icon1;
    @FXML private ImageView icon2;
    @FXML private ImageView icon3;
    @FXML private ImageView icon4;
    @FXML private ImageView icon5;
    @FXML private ImageView icon6;
    @FXML private ImageView icon7;
    @FXML private ImageView icon8;
    @FXML private ImageView icon9;

    @FXML private Button button;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;
    @FXML private Button button6;
    @FXML private Button button7;
    @FXML private Button button8;
    @FXML private Button button9;

    @FXML private Button page1;
    @FXML private Button page2;
    @FXML private Button page3;
    @FXML private Button page4;
    @FXML private Button page5;
    @FXML private AnchorPane anchorPane;

    private ArrayList<ImageView> images = new ArrayList<>();
    private ArrayList<ImageView> icons = new ArrayList<>();
    private ArrayList<Label> timeLabels = new ArrayList<>();
    private ArrayList<Label> labels = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Button> pages = new ArrayList<>();
    private ArrayList<Item> items;

    private String[] categories = {"NEW", "COVID", "POLITICS", "BUSINESS", "TECHNOLOGY", "HEALTH", "SPORTS", "ENTERTAINMENT", "WORLD", "OTHERS"};
    private int categoryIndex = 0, currentPage = 1;

    public void setCategoryIndex(int index) {
        categoryIndex = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLabel.setText(categories[categoryIndex]);
        items = new NewsController(categoryIndex).getItems();

        addNodeToList();
        changePage(0);

        for (int i = 0; i < pages.size(); i++){
            int idx = i;
            pages.get(i).setOnAction(e -> changePage(idx));
        }
    }

    private void addNodeToList(){
        for (Label l : Arrays.asList(header, header1, header2, header3, header4, header5, header6, header7, header8, header9))
            labels.add(l);

        for (Button b : Arrays.asList(button, button1, button2, button3, button4, button5, button6, button7, button8, button9))
            buttons.add(b);

        for (Button b : Arrays.asList(page1, page2, page3, page4, page5))
            pages.add(b);

        for (ImageView i : Arrays.asList(image, image1, image2, image3, image4, image5, image6, image7, image8, image9))
            images.add(i);

        for (ImageView i : Arrays.asList(icon, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9))
            icons.add(i);

        for (Label l : Arrays.asList(time, time1, time2, time3, time4, time5, time6, time7, time8, time9))
            timeLabels.add(l);
    }

    public void changePage(int page){
        if (currentPage != page){
            currentPage = page;

            final int ITEMCOUNT = 10;
            for (int i = 0; i < ITEMCOUNT; i++){
                int idx = i + (page * ITEMCOUNT);

                try{
                    labels.get(i).setText(items.get(idx).getTitle());
                    timeLabels.get(i).setText(items.get(idx).durationToString());
                    buttons.get(i).setDisable(false);
                    buttons.get(i).setOnAction(e -> article(e, idx));

                    if (!items.get(idx).getImgSrc().equals("")){
                        images.get(i).setImage(new Image(items.get(idx).getImgSrc()));
                    }else
                        images.get(i).setImage(null);
                }
                catch (IndexOutOfBoundsException e){
                    labels.get(i).setText("Empty");
                    buttons.get(i).setDisable(true);
                    images.get(i).setImage(null);
                }
            }
        }
    }

    public void menuCategories(ActionEvent event) {
        new SceneSwitch().menuCategories(event);
    }

    public void article(ActionEvent event, int index) {
        new SceneSwitch().article(event, items, index);
        System.out.println(items.get(index).getLink());
    }
}
