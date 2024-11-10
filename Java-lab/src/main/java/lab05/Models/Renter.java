package lab05.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a Renter in the rental system.
 */
public class Renter implements Comparable<Renter> {
    private final UUID id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String driversLicense;

    public Renter() {
        this.id = UUID.randomUUID(); // Generate a random UUID by default
    }

    @JsonCreator
    public Renter(
            @JsonProperty("id") UUID id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("identityDocument") String identityDocument,
            @JsonProperty("driversLicense") String driversLicense) {
        this.id = id != null ? id : UUID.randomUUID(); // Use provided id or generate a new one if null
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.driversLicense = driversLicense;
    }

    public UUID getId() {
        return id;
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

    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return Objects.equals(id, renter.id) &&
                Objects.equals(firstName, renter.firstName) &&
                Objects.equals(lastName, renter.lastName) &&
                Objects.equals(identityDocument, renter.identityDocument) &&
                Objects.equals(driversLicense, renter.driversLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, identityDocument, driversLicense);
    }


    /**
     * Compares this Renter to another Renter based on multiple criteria.
     *
     * @param other the other Renter to compare to
     * @return a negative integer, zero, or a positive integer as this Renter is less than, equal to, or greater than the specified Renter
     */
    @Override
    public int compareTo(Renter other) {
        int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        return this.firstName.compareToIgnoreCase(other.firstName);
    }
}
