package game.controller;

import game.model.MailInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MailController implements Initializable {

    MailInfo mailinfo = new MailInfo();

    @FXML
    private Label mailFrom;

    @FXML
    private Label mailTo;

    @FXML
    private TextArea mailMassage;

    @FXML
    private TextArea mailReplay;

    @FXML
    private Label mailSubject;

    @FXML
    void onActionSend(ActionEvent event) {
        if (mailinfo.getCount() == 2 && !mailReplay.getText().isEmpty()) {
            mailinfo.setSentmail(mailReplay.getText());
            mailFrom.setText("From : " + mailinfo.getReciver());
            mailTo.setText("To : " + mailinfo.getSender());
            mailMassage.setText(mailinfo.getSentmail());
            mailSubject.setText("Subject : " + mailinfo.getSubject());
            mailinfo.setCount(3);
            mailinfo.getMailStage().close();
        } else if (mailinfo.getCount() == 3 && !mailReplay.getText().isEmpty()){
            mailinfo.setSentmail(mailReplay.getText());
            mailFrom.setText("From : " + mailinfo.getSender());
            mailTo.setText("To : " + mailinfo.getReciver());
            mailMassage.setText(mailinfo.getSentmail());
            mailSubject.setText("Subject : " + mailinfo.getSubject());
            mailinfo.setCount(2);
            mailinfo.getMailStage().close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailFrom.setText("From : " + mailinfo.getSender());
        mailTo.setText("To : " + mailinfo.getReciver());
        mailMassage.setText(mailinfo.getSentmail());
        mailSubject.setText("Subject : " + mailinfo.getSubject());
    }

}
