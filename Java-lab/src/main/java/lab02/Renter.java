package lab02;

import java.util.Objects;

/**
 * Class representing a Renter in the rental system.
 */
public class Renter implements Comparable<Renter> {
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String driversLicense;

    /**
     * Constructs a Renter with the specified personal information.
     *
     * @param firstName The first name of the renter.
     * @param lastName The last name of the renter.
     * @param identityDocument The identity document of the renter.
     * @param driversLicense The driver's license of the renter.
     */
    public Renter(String firstName, String lastName, String identityDocument, String driversLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.driversLicense = driversLicense;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public String getDriversLicense() {
        return driversLicense;
    }

    /**
     * Compares this renter to the specified object.
     *
     * @param o the object to compare this Renter against
     * @return true if the given object represents a Renter with the same attributes, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renter renter = (Renter) o;
        return Objects.equals(firstName, renter.firstName) &&
                Objects.equals(lastName, renter.lastName) &&
                Objects.equals(identityDocument, renter.identityDocument) &&
                Objects.equals(driversLicense, renter.driversLicense);
    }

    /**
     * Returns a hash code value for the object based on the renter's attributes.
     *
     * @return a hash code value for this Renter
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, identityDocument, driversLicense);
    }

    /**
     * Compares this Renter to another Renter based on multiple criteria.
     *
     * @param other the other Renter to compare to
     * @return a negative integer, zero, or a positive integer as this Renter is less than, equal to, or greater than the specified Renter
     */
    @Override
    public int compareTo(Renter other) {
        // Спочатку порівнюємо за прізвищем
        int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison; // Якщо прізвища різні, повертаємо результат порівняння
        }

        // Якщо прізвища однакові, порівнюємо за ім'ям
        return this.firstName.compareToIgnoreCase(other.firstName);
    }
}
