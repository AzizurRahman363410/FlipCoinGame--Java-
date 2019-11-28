package game.model;

public class ValidetionCheck {


    public boolean validUser(String username) {
            for (int i = 0; i < username.length(); i++) {
                if (48 > (int) (username.charAt(i)) || 122 < (int) (username.charAt(i)) || 90 < (int) (username.charAt(i)) && 97 > (int) (username.charAt(i)) || 57 < (int) (username.charAt(i)) && 65 > (int) (username.charAt(i))) {
                    return false;
                }
            }
        return true;
    }
    public boolean validName(String fname) {
         for (int i = 0; i < fname.length(); i++) {
                if (65 > (int) (fname.charAt(i)) || 122 < (int) (fname.charAt(i)) || 90 < (int) (fname.charAt(i)) && 97 > (int) (fname.charAt(i)) ) {
                    return false;
                }
            }
        return true;     
    }
    
    public boolean validMail(String mail){
        if (mail.endsWith("@gmail.com") || mail.endsWith("@yahoo.com")) {
            return true;
        }
        return false;
    }
   

}
