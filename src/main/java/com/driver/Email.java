package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        if(oldPassword.equals(this.password)){
            boolean isContainMinChar = false;
            boolean isUpperCase = false;
            boolean isLowerCase = false;
            boolean isDigit = false;
            boolean isSpe_Char = false;

            // 1. It contains at least 8 characters
            if(oldPassword.length() >= 8) isContainMinChar = true;


            for(int i=0;i<oldPassword.length();i++){
                char c = oldPassword.charAt(i);
                if(c>= 'A' && c<= 'Z') isUpperCase = true;
                else if(c>= 'a' && c<= 'z') isLowerCase = true;
                else if(c>= '0' && c<= '9') isDigit = true;
                else isSpe_Char = true;
            }

            if(isContainMinChar == true && isUpperCase == true && isLowerCase == true && isDigit == true && isSpe_Char == true){
                this.password = newPassword;
            }
        }
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
