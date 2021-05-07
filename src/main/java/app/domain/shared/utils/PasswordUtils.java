package app.domain.shared.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {

    private static Random rnd = new Random();

    public static String generateRandomPassword(){
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
            File file = new File("emailAndSMSMessages.txt");
            if(!file.exists())
                file.createNewFile();
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(String.format("%n%nEmployee email: %s%nEmplooye Password: %s", email, generatedPassword));
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
