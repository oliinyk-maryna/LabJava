package lab05.comparators;

import lab05.Models.Agreement;

import java.util.Comparator;

/**
 * The {@code AgreementComparator} class provides several static methods for comparing
 * {@link Agreement} objects based on different fields such as start date, total price,
 * and customer name.
 */
public class AgreementComparator {
    /**
     * Returns a comparator that compares {@link Agreement} objects by their start date.
     *
     * @return a comparator that compares {@link Agreement} objects by start date
     */
    public static Comparator<Agreement> byStartDate() {
        return Comparator.comparing(Agreement::getStartDate);
    }

    /**
     * Returns a comparator that compares {@link Agreement} objects by their total price.
     * If two agreements have the same total price, they are compared by start date.
     *
     * @return a comparator that compares {@link Agreement} objects by total price
     *         and then by start date if the prices are the same
     */
    public static Comparator<Agreement> byTotalPrice() {
        return Comparator
                .comparing(Agreement::getTotalPrice)
                .thenComparing(Agreement::getStartDate);
    }

    /**
     * Returns a comparator that compares {@link Agreement} objects by the last name of the customer.
     * If two agreements have the same last name, they are compared by the first name.
     *
     * @return a comparator that compares {@link Agreement} objects by customer's last name
     *         and then by first name if the last names are the same
     */
    public static Comparator<Agreement> byCustomerName() {
        return Comparator
                .comparing((Agreement agreement) -> agreement.getRenter().getLastName(), Comparator.nullsLast(String::compareTo))
                .thenComparing(agreement -> agreement.getRenter().getFirstName(), Comparator.nullsLast(String::compareTo));
    }
}
