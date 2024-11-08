package lab04.services;

import lab05.Agreement;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AgreementService provides methods for operations on collections of Agreement objects.
 */
public class AgreementService {

    private List<Agreement> agreements;

    public AgreementService(List<Agreement> agreements) {
        this.agreements = agreements;
    }

    /**
     * Filters agreements by a specific renter's last name and rental start date.
     *
     * @param lastName The last name of the renter
     * @param startDate The rental start date
     * @return List of agreements where the renter has the specified last name and the rental starts after the given date
     */
    public List<Agreement> filterByRenterLastNameAndStartDate(String lastName, String startDate) {
        return agreements.stream()
                .filter(agreement -> agreement.getRenter().getLastName().equalsIgnoreCase(lastName))
                .filter(agreement -> agreement.getStartDate().isAfter(java.time.LocalDate.parse(startDate)))
                .collect(Collectors.toList());
    }

    /**
     * Filters agreements by a range of total price.
     *
     * @param minPrice The minimum total price
     * @param maxPrice The maximum total price
     * @return List of agreements where the total price is within the specified range
     */
    public List<Agreement> filterByTotalPriceRange(double minPrice, double maxPrice) {
        return agreements.stream()
                .filter(agreement -> agreement.getTotalPrice() >= minPrice && agreement.getTotalPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Sorts agreements by rental duration (end date - start date).
     *
     * @return List of agreements sorted by rental duration
     */
    public List<Agreement> sortByRentalDuration() {
        return agreements.stream()
                .sorted(Comparator.comparingLong(agreement -> ChronoUnit.DAYS.between(agreement.getStartDate(), agreement.getEndDate())))
                .collect(Collectors.toList());
    }


    public List<Agreement> getAllAgreements() {
        return agreements;
    }
}
