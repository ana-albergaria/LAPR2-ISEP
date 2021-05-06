package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Client {

    /**
     * The Phone Number by Omission
     */
    private static final String OMITTED_PHONE_NUMBER = "0000000000";

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
    private final Date birthDate;

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

    /**
     * Constructs an instance of Client receiving as a parameter the clients Citizen Card Number, NHS Number, Birth Date, Sex, TIN Number, E-mail, Name and Phone Number.
     *
     * @param clientsCitizenCardNumber clients Citizen Card Number.
     * @param nhsNumber                clients NHS Number.
     * @param birthDate                clients Birth Date
     * @param sex                      clients Sex.
     * @param tinNumber                clients TIN Number.
     * @param email                    clients E-mail.
     * @param name                     clients Name.
     * @param phoneNumber              clients Phone Number.
     */
    public Client(String clientsCitizenCardNumber, String nhsNumber, Date birthDate, String sex,
                  String tinNumber, String email, String name, String phoneNumber) {

        checkClientsCitizenCardNumber(clientsCitizenCardNumber);
        checknhsNumber(nhsNumber);
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
        this.psw = generatepsw(salt, rnd);
    }

    /**
     * Constructs an instance of Client receiving as a parameter the clients Citizen Card Number, NHS Number, Birth Date, Sex, TIN Number, E-mail, and Name.
     *
     * @param clientsCitizenCardNumber clients Citizen Card Number.
     * @param nhsNumber                clients NHS Number.
     * @param birthDate                clients Birth Date
     * @param sex                      clients Sex.
     * @param tinNumber                clients TIN Number.
     * @param email                    clients E-mail.
     * @param name                     clients Name.
     */
    public Client(String clientsCitizenCardNumber, String nhsNumber, Date birthDate, String sex,
                  String tinNumber, String email, String name) {
        this(clientsCitizenCardNumber, nhsNumber, birthDate, sex, tinNumber, email, name, OMITTED_PHONE_NUMBER);
    }

    public String getClientsCitizenCardNumber() {
        return clientsCitizenCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPsw() {
        return psw;
    }

    /**
     * Checks if the Clients citizan card number is correct, and if not throws an error message
     *
     * @param clientsCitizenCardNumber
     */
    private void checkClientsCitizenCardNumber(String clientsCitizenCardNumber) {
        if (StringUtils.isBlank(clientsCitizenCardNumber))
            throw new IllegalArgumentException("Clients Citizen Card Number cannot be blank");
        if ((!clientsCitizenCardNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("Clients Citizen Card Number must be only digits");
        if ((clientsCitizenCardNumber).length() != 16)
            throw new IllegalArgumentException("Clients Citizen Card Number must be 16 digits");
    }

    /**
     * Checks if the NHS number is correct, and if not throws an error message
     *
     * @param nhsNumber
     */
    private void checknhsNumber(String nhsNumber) {
        if (StringUtils.isBlank(nhsNumber))
            throw new IllegalArgumentException("NHS number cannot be blank");
        if ((!nhsNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("NHS Number must be only digits");
        if ((nhsNumber).length() != 10)
            throw new IllegalArgumentException("NHS number must be 10 digits");
    }

    /**
     * Checks if the Birth Date is correct, and if not throws an error message
     *
     * @param birthDate
     */
    private void checkBirthDate(Date birthDate) {
        if (birthDate == null)
            throw new IllegalArgumentException("Birth Date cannot be blank");
        if (birthDate.getYear() < 1870)
            throw new IllegalArgumentException("Client cannot be 150 years or older");
    }

    /**
     * Checks if the Sex is correct, and if not throws an error message
     *
     * @param sex
     */
    private void checkSex(String sex) {
        if (StringUtils.isBlank(sex))
            throw new IllegalArgumentException("Sex cannot be blank");
        if (!sex.equals("Male") && !sex.equals("Female"))
            throw new IllegalArgumentException("Sex must be Male or Female");
    }

    /**
     * Checks if the Tin number is correct, and if not throws an error message
     *
     * @param tinNumber
     */
    private void checkTinNumber(String tinNumber) {
        if (StringUtils.isBlank(tinNumber))
            throw new IllegalArgumentException("TIN number cannot be blank");
        if ((!tinNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("TIN Number must be only digits");
        if ((tinNumber).length() != 10)
            throw new IllegalArgumentException("TIN number must be 10 digits");

        // falta para o caso de ele por letras e nao numeros - tem de dar erro

    }

    /**
     * Checks if the E-mail is correct, and if not throws an error message
     *
     * @param email
     */
    private void checkEmail(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank");
    }

    /**
     * Checks if the Name is correct, and if not throws an error message
     *
     * @param name
     */
    private void checkName(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank");

    }

    /**
     * Checks if the Phone number is correct, and if not throws an error message
     *
     * @param phoneNumber
     */
    private void checkPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blank");
        if ((!phoneNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("phone number must be only digits");
        if ((phoneNumber).length() != 11)
            throw new IllegalArgumentException("Phone number must be 10 digits");
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

    private String generatepsw(StringBuilder salt, Random rnd) {
        String SALTCHARS = "abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;


        return Objects.equals(clientsCitizenCardNumber, client.clientsCitizenCardNumber) ||
                Objects.equals(nhsNumber, client.nhsNumber) ||
                Objects.equals(tinNumber, client.tinNumber) ||
                Objects.equals(email, client.email) ||
                equalsPhoneNumber(client);
    }

    private boolean equalsPhoneNumber(Client client) {
        if (!phoneNumber.equals("sem numero")) {
            return false;
        } else {
            return Objects.equals(phoneNumber, client.phoneNumber);
        }

    }

}
