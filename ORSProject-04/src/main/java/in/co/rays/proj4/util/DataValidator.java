package in.co.rays.proj4.util;

import java.util.Calendar;
import java.util.Date;

/**
 * DataValidator class provides utility methods to validate 
 * various types of data such as String, numbers, email, name, 
 * roll number, password, phone number, and date.
 * 
 * @author Krati
 */
public class DataValidator {

    /**
     * Checks if a string is null or empty.
     * 
     * @param val input string
     * @return true if null or empty, false otherwise
     */
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a string is not null and not empty.
     * 
     * @param val input string
     * @return true if not null or empty, false otherwise
     */
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    /**
     * Checks if a string is a valid integer.
     * 
     * @param val input string
     * @return true if valid integer, false otherwise
     */
    public static boolean isInteger(String val) {
        if (isNotNull(val)) {
            try {
                Integer.parseInt(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if a string is a valid long number.
     * 
     * @param val input string
     * @return true if valid long, false otherwise
     */
    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Validates if a string is in valid email format.
     * 
     * @param val input email
     * @return true if valid email, false otherwise
     */
    public static boolean isEmail(String val) {
        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Validates if a string is a proper name.
     * 
     * @param val input name
     * @return true if valid name, false otherwise
     */
    public static boolean isName(String val) {
        String namereg = "^[^-\\s][\\p{L} .'-]+$";
        if (isNotNull(val)) {
            try {
                return val.matches(namereg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Validates roll number format (2 letters followed by 3 digits).
     * 
     * @param val input roll number
     * @return true if valid, false otherwise
     */
    public static boolean isRollNo(String val) {
        String rollreg = "[a-zA-Z]{2}[0-9]{3}";
        if (isNotNull(val)) {
            try {
                return val.matches(rollreg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Validates password format (includes uppercase, lowercase, number, special character, 8-12 length).
     * 
     * @param val input password
     * @return true if valid password, false otherwise
     */
    public static boolean isPassword(String val) {
        String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";
        if (isNotNull(val)) {
            try {
                return val.matches(passreg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if password length is between 8 and 12 characters.
     * 
     * @param val input password
     * @return true if length valid, false otherwise
     */
    public static boolean isPasswordLength(String val) {
        if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates phone number format (10 digits, starts with 6-9).
     * 
     * @param val input phone number
     * @return true if valid phone number, false otherwise
     */
    public static boolean isPhoneNo(String val) {
        String phonereg = "^[6-9][0-9]{9}$";
        if (isNotNull(val)) {
            try {
                return val.matches(phonereg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public static boolean isNotificationCode(String val) {
        String notificationReg = "^NT-\\d{3}$";
        if (isNotNull(val)) {
            try {
                return val.matches(notificationReg);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if phone number length is exactly 10 digits.
     * 
     * @param val input phone number
     * @return true if length is 10, false otherwise
     */
    public static boolean isPhoneLength(String val) {
        if (isNotNull(val) && val.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a string represents a valid date.
     * 
     * @param val input date string
     * @return true if valid date, false otherwise
     */
    public static boolean isDate(String val) {
        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

    /**
     * Checks if a given date string falls on a Sunday.
     * 
     * @param val input date string
     * @return true if Sunday, false otherwise
     */
    public static boolean isSunday(String val) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DataUtility.getDate(val));
        int i = cal.get(Calendar.DAY_OF_WEEK);
        return i == Calendar.SUNDAY;
    }

    /**
     * Main method to test validation methods.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Tests included in original code
    }
    
 // General Code Validation (AB-101, NT-102, XY-999 ...)
    public static boolean isCode(String val) {
        String codeReg = "^[A-Z]{2}-\\d{3}$";
        if (isNotNull(val)) {
            try {
                return val.matches(codeReg);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
