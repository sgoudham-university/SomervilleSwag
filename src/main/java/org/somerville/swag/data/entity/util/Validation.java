package org.somerville.swag.data.entity.util;

public class Validation {

    /**
     * Returns True/False based on ReGex validation of Customer Sign Up Information
     *
     * @param forename // Only Characters [1-15]
     * @param surname // Only Characters [1-15]
     * @param email // Accepts Most Emails - No Numbers in Domain Name, Doesn't handle multiple &quot;.&quot; in the domain (joe@abc.co.uk).
     * @param password // First Character Must Be Letter. Must be [4-15] Characters Long. Numbers & Underscores Allowed
     * @param passwordConfirm // First Character Must Be Letter. Must be [4-15] Characters Long. Numbers & Underscores Allowed
     * @param addressLineOne // No Special Characters. Whitespace is Permitted
     * @param city // Only characters. Whitespace Permitted. Will accept empty string
     * @param postcode // Only characters. Whitespace Permitted. Will accept empty string
     * @param phoneNumber // Only Numbers, [1-11] Characters Only
     * @return True/False
     */
    public static boolean isCustomerInfoInvalid(String forename, String surname, String email, String password,
                                       String passwordConfirm, String addressLineOne, String city, String postcode,
                                       String phoneNumber) {
        return !forename.strip().matches("[a-zA-Z]{1,15}") ||
                !surname.strip().matches("[a-zA-Z]{1,15}") ||
                !email.strip().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$") ||
                !password.matches("^[a-zA-Z]\\w{3,14}$") ||
                !passwordConfirm.matches("^[a-zA-Z]\\w{3,14}$") ||
                !addressLineOne.matches("^[a-zA-Z0-9_ ]*$") ||
                !city.matches("^[a-zA-Z_ ]*$") ||
                !postcode.matches("^[a-zA-Z0-9_ ]{6,7}$") ||
                !phoneNumber.matches("[0-9]{11}") ||
                !password.equals(passwordConfirm);
    }

    /**
     * Returns True/False based on ReGex validation of the Customer Credit/Debit card
     *
     * @param txtCardNo The Card Number Entered
     * @param txtCvv The 3 Digits At the Back of the Card
     * @return True/False
     */
    public static boolean isCardInvalid(String txtCardNo, String txtCvv) {
        return !txtCardNo.strip().matches("[0-9]{16}") || !txtCvv.strip().matches("[0-9]{3}");
    }
}
