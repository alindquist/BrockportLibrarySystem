package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.StudentBorrower;
import utilities.Core;
import java.net.URL;;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ders on 3/27/2017.
 */
public class ModifyStudentViewController implements Initializable {

    private ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    private ObservableList<String> borrowerStatusList = FXCollections.observableArrayList("Good", "Delinquent");
    private Core core = Core.getInstance();
    Properties language = core.getLang();

    @FXML
    private TextField ContactPhone;
    @FXML private ComboBox<String> Status;
    @FXML private TextField Email;
    @FXML private Button submit;
    @FXML private TextField DateOfRegistration;
    @FXML private TextField FirstName;
    @FXML private TextField BannerId;
    @FXML private TextField LastName;
    @FXML private TextField DateOfLatestBorrowerStatus;
    @FXML private ComboBox<String> BorrowerStatus;
    @FXML private TextField Notes;
    @FXML private Text alertMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BannerId.setText(core.getModStudentBorrower().getBannerId());
        BannerId.setDisable(true);
        Notes.setText(core.getModStudentBorrower().getNotes());
        FirstName.setText(core.getModStudentBorrower().getFirstName());
        LastName.setText(core.getModStudentBorrower().getLastName());
        ContactPhone.setText(core.getModStudentBorrower().getContactPhone());
        Email.setText(core.getModStudentBorrower().getEmail());
        BorrowerStatus.setValue(core.getModStudentBorrower().getBorrowerStatus());
        DateOfLatestBorrowerStatus.setText(core.getModStudentBorrower().getDateOfLatestBorrowerStatus());
        DateOfRegistration.setText(core.getModStudentBorrower().getDateOfRegistration());
        Status.setValue(core.getModStudentBorrower().getStatus());
        BorrowerStatus.setItems(borrowerStatusList);
        Status.setItems(statusList);
        submit.setText(language.getProperty("Modify"));
    }

    public void submit(ActionEvent actionEvent) {
        alertMessage.setText("");
        StudentBorrower studentBorrower = core.getModStudentBorrower();
        studentBorrower.setNotes(Notes.getText());
        studentBorrower.setFirstName(FirstName.getText());
        studentBorrower.setLastName(LastName.getText());
        studentBorrower.setContactPhone(ContactPhone.getText());
        studentBorrower.setEmail(Email.getText());
        studentBorrower.setBorrowerStatus(BorrowerStatus.getValue());
        studentBorrower.setDateOfLatestBorrowerStatus(DateOfLatestBorrowerStatus.getText());
        studentBorrower.setDateOfRegistration(DateOfRegistration.getText());
        studentBorrower.setStatus(Status.getValue());
        studentBorrower.update();
        System.out.println(studentBorrower.toString());
        alertMessage.setText("Student Borrower successfully updated");
    }
}