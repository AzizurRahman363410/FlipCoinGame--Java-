package game.model;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MailInfo {
    
    private static String webName;
    private static AnchorPane tabPane;
    private static int count = 0;
    private static Stage mailStage;
    private static int page ;
    private static String sentmail;
    private static String replymail;
    private static String sender = null;
    private static String reciver = null;
    private static String subject;

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        MailInfo.webName = webName;
    }
    
    
    public  AnchorPane getTabPane() {
        return tabPane;
    }

    public  void setTabPane(AnchorPane tabPane) {
        MailInfo.tabPane = tabPane;
    }

    
    public int getCount() {
        return count;
    }

    public Stage getMailStage() {
        return mailStage;
    }

    public void setMailStage(Stage mailStage) {
        MailInfo.mailStage = mailStage;
    }
    
    public void setCount(int count) {
        MailInfo.count = count;
    }

    public  String getSentmail() {
        return sentmail;
    }

    public String getReplymail() {
        return replymail;
    }

    public void setReplymail(String replymail) {
        MailInfo.replymail = replymail;
    }

    public  void setSentmail(String sentmail) {
        MailInfo.sentmail = sentmail;
    }

    public  String getSender() {
        return sender;
    }

    public  void setSender(String sender) {
        MailInfo.sender = sender;
    }

    public  String getReciver() {
        return reciver;
    }

    public  void setReciver(String reciver) {
        MailInfo.reciver = reciver;
    }

    public  String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        MailInfo.subject = subject;
    }
    
    
    
    public void setPage(int page) {
        MailInfo.page = page;
    }
    
    public int getPage() {
        return page;
    }
}
