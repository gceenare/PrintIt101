package za.ac.cput.util;

public class Helper {
    public static boolean isNullOrEmpty(String s){
        if(s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean intIsNull(int i){
        if(i<=0){
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email){
        //todo
        return true;
    }
}
