package za.ac.cput.util;

public class Helper {
    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean intIsNull(long i) {
        if (i <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public static boolean doubleIsInvalid(double value) {
        return value <= 0 || Double.isNaN(value) || Double.isInfinite(value);
    }

    public static boolean isAngleValid(double angle) {
        return angle >= -360 && angle <= 360; // Fixed logic
    }

    public static boolean areAllObjectsNotNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) return false;
        }
        return true;
    }
}