package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Random;

public class Client {


    /**
     * The Phone Number by Omission
     */
    private static final String PHONE_NUMBER_POR_OMISSAO = "sem numero";

    /**
     * The clients Citizen Card Number.
     */
    private final String clientsCitizenCardNumber;

    /**
     * The clients NHS Number.
     */
    private final String nhsNumber;

    /**
     * The clients Birth Date
     */
    private final String birthDate; // talvez seja melhorar criar um objeto do tipo data por composiçao nao ?

    /**
     * The clients Sex.
     */
    private final String sex;

    /**
     * The clients TIN Number.
     */
    private final String tinNumber;

    /**
     * The clients E-mail.
     */
    private final String email;

    /**
     * The clients Name.
     */
    private final String name;

    /**
     * The clients Phone Number.
     */
    private final String phoneNumber;

    private final String psw;


    public static String getPhoneNumberPorOmissao() {
        return PHONE_NUMBER_POR_OMISSAO;
    }

    public String getClientsCitizenCardNumber() {
        return clientsCitizenCardNumber;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPsw() {
        return psw;
    }

    /**
     * Constructs an instance of Client receiving as a parameter the clients Citizen Card Number, NHS Number, Birth Date, Sex, TIN Number, E-mail, Name and Phone Number.
     *
     * @param clientsCitizenCardNumber clients Citizen Card Number.
     * @param nhsNumber clients NHS Number.
     * @param birthDate clients Birth Date
     * @param sex clients Sex.
     * @param tinNumber clients TIN Number.
     * @param email clients E-mail.
     * @param name clients Name.
     * @param phoneNumber clients Phone Number.
     */
    public Client(String clientsCitizenCardNumber, String nhsNumber, String birthDate, String sex,
                  String tinNumber, String email, String name, String phoneNumber) {

        checkClientsCitizenCardNumber(clientsCitizenCardNumber);
        checknhsNumber(nhsNumber);
        checkBirthDate(birthDate);
        checkSex(sex);
        checkTinNumber(tinNumber);
        checkEmail(email);
        checkName(name);
        checkPhoneNumber(phoneNumber);

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        this.clientsCitizenCardNumber = clientsCitizenCardNumber;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.tinNumber = tinNumber;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.psw = generatepsw(salt,rnd);
    }


    /**
     * Constructs an instance of Client receiving as a parameter the clients Citizen Card Number, NHS Number, Birth Date, Sex, TIN Number, E-mail, and Name.
     *
     * @param clientsCitizenCardNumber clients Citizen Card Number.
     * @param nhsNumber clients NHS Number.
     * @param birthDate clients Birth Date
     * @param sex clients Sex.
     * @param tinNumber clients TIN Number.
     * @param email clients E-mail.
     * @param name clients Name.

     */
    public Client(String clientsCitizenCardNumber, String nhsNumber, String birthDate, String sex,
                  String tinNumber, String email, String name) {

        checkClientsCitizenCardNumber(clientsCitizenCardNumber);
        checknhsNumber(nhsNumber);
        checkBirthDate(birthDate);
        checkSex(sex);
        checkTinNumber(tinNumber);
        checkEmail(email);
        checkName(name);
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        this.clientsCitizenCardNumber = clientsCitizenCardNumber;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.tinNumber = tinNumber;
        this.email = email;
        this.name = name;
        this.phoneNumber = PHONE_NUMBER_POR_OMISSAO;
        this.psw = generatepsw(salt,rnd);
    }


    /**
     * Checks if the Clients citizan card number is correct, and if not throws an error message
     * @param clientsCitizenCardNumber
     *
     */
    private void checkClientsCitizenCardNumber(String clientsCitizenCardNumber) {
        if (StringUtils.isBlank(clientsCitizenCardNumber))
            throw new IllegalArgumentException("Clients Citizen Card Number cannot be blank");
        if ((clientsCitizenCardNumber).length() != 16)
            throw new IllegalArgumentException("Clients Citizen Card Number must be 16 digits");

        checkDigits(clientsCitizenCardNumber, "Clients Citizen Card Number must be only digits");
    }
    // falta para o caso de ele por letras e nao numeros - tem de dar erro


    /**
     * Checks if the NHS number is correct, and if not throws an error message
     * @param nhsNumber
     *
     */
    private void checknhsNumber(String nhsNumber) {
        if (StringUtils.isBlank(nhsNumber))
            throw new IllegalArgumentException("NHS number cannot be blank");
        if ((nhsNumber).length() != 10)
            throw new IllegalArgumentException("NHS number must be 10 digits");

        checkDigits(nhsNumber, "NHS Number must be only digits");

    }

    /**
     * Checks if the Birth Date is correct, and if not throws an error message
     * @param birthDate
     *
     */
    private void checkBirthDate(String birthDate) {
        if (StringUtils.isBlank(birthDate))
            throw new IllegalArgumentException("Birth Date cannot be blank");
        if ((birthDate).length() != 7)
            throw new IllegalArgumentException("Birth Date must be 7 digits");
        // falta para o caso de ele por letras e nao numeros - tem de dar erro

    }

    /**
     * Checks if the Sex is correct, and if not throws an error message
     * @param sex
     *
     */
    private void checkSex(String sex) {
        if (StringUtils.isBlank(sex))
            throw new IllegalArgumentException("Sex cannot be blank");
        if (!sex.equals("Male") && !sex.equals("Female"))
            throw new IllegalArgumentException("Sex must be Male or Female");
    }

    /**
     * Checks if the Tin number is correct, and if not throws an error message
     * @param tinNumber
     *
     */
    private void checkTinNumber(String tinNumber) {
        if (StringUtils.isBlank(tinNumber))
            throw new IllegalArgumentException("TIN number cannot be blank");
        if ((tinNumber).length() != 10)
            throw new IllegalArgumentException("TIN number must be 10 digits");
        // falta para o caso de ele por letras e nao numeros - tem de dar erro

        checkDigits(tinNumber, "TIN Number must be only digits");
    }

    /**
     * Checks if the E-mail is correct, and if not throws an error message
     * @param email
     *
     */
    private void checkEmail(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank");
    }

    /**
     * Checks if the Name is correct, and if not throws an error message
     * @param name
     *
     */
    private void checkName(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank");

    }

    /**
     * Checks if the Phone number is correct, and if not throws an error message
     * @param phoneNumber
     *
     */
    private void checkPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blacket");
        if ((phoneNumber).length() != 11)
            throw new IllegalArgumentException("Phone number must be 10 digits");

        checkDigits(phoneNumber, "Phone Number must be only digits");
    }

    /**
     * Returns the textual description of the Client
     *
     * @return Client characteristics
     */
    @Override
    public String toString() {
        return "Client{" +
                "clientsCitizenCardNumber='" + clientsCitizenCardNumber + '\'' +
                ", nhsNumber='" + nhsNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                ", tinNumber='" + tinNumber + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    /**
     * Checks if the String has any characters that are not digits, and if so throws an error message
     * @param str
     * @param message
     */
    private void checkDigits(String str, String message) {
        char[] c = str.toCharArray();

        for (int i = 0; i < c.length; i++)
            // verifica se o char não é um dígito
            if (!Character.isDigit(c[i])) {
                throw new IllegalArgumentException(message);
            }
    }

    private String generatepsw(StringBuilder salt, Random rnd) {
        String SALTCHARS = "abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        System.out.println(saltStr);
        return saltStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientsCitizenCardNumber, client.clientsCitizenCardNumber) &&
                Objects.equals(nhsNumber, client.nhsNumber) &&
                Objects.equals(tinNumber, client.tinNumber) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phoneNumber, client.phoneNumber);
    }

}
