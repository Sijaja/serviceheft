package dev.sijaja.serviceheft.config;

public class PasswordGenerator {
    public static void main(String[] args) {
        String password = "password123";
        String hashedPassword = org.springframework.security.crypto.bcrypt.BCrypt
                .hashpw(password, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
