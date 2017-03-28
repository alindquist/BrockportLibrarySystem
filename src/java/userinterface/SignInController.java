package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Worker;
import models.WorkerCollection;
import utilities.Core;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Kevin, modified by Andeezy on 3/8/2017.
 */
public class SignInController implements Initializable {

    @FXML ComboBox<String> language;
    @FXML TextField bannerId;
    @FXML TextField password;
    @FXML private Text alertMessage;
    @FXML private Text signInHeader;
    @FXML private Text banner;
    @FXML private Text pw;
    @FXML private Button signIn;
    private ObservableList<String> languages = FXCollections.observableArrayList("en_US", "fr_FR");
    Core core = Core.getInstance();
    Properties lang = core.getLang();

    public SignInController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        language.setItems(languages);
        language.setValue("en_US");
        signInHeader.setText(lang.getProperty("signIn"));
        banner.setText(lang.getProperty("PromptBannerId"));
        pw.setText(lang.getProperty("PromptPassword"));
        signIn.setText(lang.getProperty("SignIn"));
    }

    public void signIn(ActionEvent actionEvent) throws IOException {
        if (bannerId.getText().equals("") || password.getText().equals(""))
            alertMessage.setText("Please enter BannerID/Password");
        else {
            // Query DB to create worker object.
            WorkerCollection workerCollection = new WorkerCollection();
            Worker worker = (Worker)workerCollection.signInWorker(bannerId.getText()).elementAt(0);
            String pw = (String)worker.getState("Password");
            if (!pw.equals(password.getText()))
                alertMessage.setText("Password Invalid");
            if (pw.equals(password.getText())) {
                try {
                    core.setUser(worker);
                    core.setLanguage(language.getSelectionModel().getSelectedItem());
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Brockport Library System");
                    primaryStage.setResizable(false);
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println("Can't open new window.");
                }
            }
        }
    }

    public void skipAsAdmin(ActionEvent actionEvent) throws IOException {
        dummyWorker(1);
        core.setLanguage(language.getSelectionModel().getSelectedItem());
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System!");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Can't open new window.");
        }
    }

    public void skipAsOrdinary(ActionEvent actionEvent) {
        dummyWorker(2);
        core.setLanguage(language.getSelectionModel().getSelectedItem());
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainview.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/e/ef/Brockp_Gold_Eagles_logo.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Brockport Library System!");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Can't open new window.");
        }
    }


    private void dummyWorker(int type) {
        String[] workerArray = {"BannerId", "Password", "FirstName", "LastName", "ContactPhone",
                "Email", "Credentials", "DateOfLatestCredentialsStatus", "DateOfHire", "Status"};
        String[] workerData;
        if (type == 1) {
            workerData = new String[]{"800123456", "123456", "John", "Doe", "555-666-1234", "DaMan@gmail.com",
                    "Administrator", "3-21-2017", "4-17-2007", "Active"};
            Properties props = new Properties();
            for (int i = 0; i < workerArray.length; i++) {
                props.put(workerArray[i], workerData[i]);
            }
            Worker worker = new Worker(props);
            core.setUser(worker);
        } else if (type == 2) {
            workerData = new String[]{"800123456", "123456", "John", "Doe", "555-666-1234", "DaMan@gmail.com",
                    "Ordinary", "3-21-2017", "4-17-2007", "Active"};
            Properties props = new Properties();
            for (int i = 0; i < workerArray.length; i++) {
                props.put(workerArray[i], workerData[i]);
            }
            Worker worker = new Worker(props);
            core.setUser(worker);
        }
    }
}