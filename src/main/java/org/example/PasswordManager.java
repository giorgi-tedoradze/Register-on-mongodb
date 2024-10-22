package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class PasswordManager {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }

    public static boolean checkText(String text, String condition) {
        return Pattern.compile(condition)
                .matcher(text)
                .matches();
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static int temporaryPassword() {
        int verificationCode = (int) (Math.random() * 1000);

        try (FileWriter myWriter = new FileWriter("./Password.txt")) {
            myWriter.write(verificationCode);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return verificationCode;
    }

}
