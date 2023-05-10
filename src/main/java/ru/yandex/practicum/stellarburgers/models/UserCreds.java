package ru.yandex.practicum.stellarburgers.models;

public class UserCreds {
    private String email;
    private String password;

    public UserCreds(UserModel user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public static UserCreds from (UserModel user){
        return new UserCreds(user);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}