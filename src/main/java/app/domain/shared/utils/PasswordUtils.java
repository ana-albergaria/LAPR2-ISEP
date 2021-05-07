package app.domain.shared.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PasswordUtils {

    public static String generateRandomPassword(){
        Random rnd = new Random();
        StringBuilder salt = new StringBuilder();
        String saltChars = "abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    public static boolean writePassword(String generatedPassword, String email){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("emailAndSMSMessages.txt"));
            bw.write(String.format("Employee email: %s%nEmplooye Password: %s", email, generatedPassword));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
