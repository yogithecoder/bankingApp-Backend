package com.project.OnlineBanking.Helpers;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Method to hash a password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Method to verify a password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "mySecretPassword";
        
        // Hash the password
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);
        
        // Verify the password
        boolean isPasswordCorrect = checkPassword(password, hashedPassword);
        System.out.println("Password is correct: " + isPasswordCorrect);
        
        // Try an incorrect password
        boolean isPasswordIncorrect = checkPassword("wrongPassword", hashedPassword);
        System.out.println("Password is incorrect: " + isPasswordIncorrect);
    }
}
