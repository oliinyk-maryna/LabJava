package lab03.services;

import lab03.Agreement;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AgreementService provides methods for operations on collections of Agreement objects.
 */
public class AgreementService {

    /**
     * Filters agreements by a specific renter's last name.
     *
     * @param agreements List of Agreement objects
     * @param lastName The last name of the renter
     * @return List of agreements where the renter has the specified last name
     */
    public List<Agreement> filterByRenterLastName(List<Agreement> agreements, String lastName) {
        return agreements.stream()
                .filter(agreement -> agreement.getRenter().getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    /**
     * Sorts agreements by the rental start date.
     *
     * @param agreements List of Agreement objects
     * @return List of agreements sorted by start date
     */
    public List<Agreement> sortByStartDate(List<Agreement> agreements) {
        return agreements.stream()
                .sorted(Comparator.comparing(Agreement::getStartDate))
                .collect(Collectors.toList());
    }

    /**
     * Finds the agreement with the highest total price.
     *
     * @param agreements List of Agreement objects
     * @return Agreement with the highest total price
     */
    public Agreement findAgreementWithHighestTotalPrice(List<Agreement> agreements) {
        return agreements.stream()
                .max(Comparator.comparing(Agreement::getTotalPrice))
                .orElse(null);
    }

}
