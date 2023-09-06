package Model.UserModels;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder(PasswordSecrets.saltRounds);
    }

    public static String encryptPassword(String password){
        BCryptPasswordEncoder encoder = encoder();
        return encoder.encode(password);
    }

    public static boolean matchPassword(String password, String encoded){
        BCryptPasswordEncoder encoder = encoder();
        return encoder.matches(password, encoded);
    }
}
