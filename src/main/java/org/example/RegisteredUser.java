package org.example;

public class RegisteredUser extends User {
    public RegisteredUser() {
        this.name = "name";
        this.password = "password";
    }

    RegisteredUser(String name, String password) {
        setName(name);
        setPassword(password);
    }

    @Override
    public void setName(String name) {
        if (PasswordManager.checkText(name, "^[A-Za-z]{3,20}$")) {
            this.name = name;

            return;
        }

        System.out.println("ტექსტი უნდა შეიცავდეს 3 დან 20 სიმბოლოს და შედგებოდეს მხოლოდ ალფავიტიდან");

    }

    @Override
    public void setPassword(String password) {
        if (PasswordManager.checkText(password, "^[A-Za-z]{3,20}$")) {
            this.password = PasswordManager.hashPassword(password);

            return;
        }

        System.out.println("ტექსტი უნდა შეიცავდეს 4 დან 10 სიმბოლოს ასევე მინუმუმ ერთ დიდ ლათინურ ასოს მასში უნდა შედიოდის სიმბოლო და ციფრი");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}