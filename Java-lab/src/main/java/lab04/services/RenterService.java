package lab04.services;

import lab05.Renter;

import java.util.List;
import java.util.stream.Collectors;

public class RenterService {

    private List<Renter> renters;

    public RenterService(List<Renter> renters) {
        this.renters = renters;
    }

    // Filter by identity document and driver's license
    public List<Renter> filterByIdentityDocumentAndLicense(String identityDocument, String driversLicense) {
        return renters.stream()
                .filter(renter -> renter.getIdentityDocument().equalsIgnoreCase(identityDocument))
                .filter(renter -> renter.getDriversLicense().equalsIgnoreCase(driversLicense))
                .collect(Collectors.toList());
    }

    // Filter by last name and first name
    public List<Renter> filterByName(String lastName, String firstName) {
        return renters.stream()
                .filter(renter -> renter.getLastName().equalsIgnoreCase(lastName))
                .filter(renter -> renter.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }
}
