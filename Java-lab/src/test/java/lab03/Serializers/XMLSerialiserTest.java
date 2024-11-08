package lab03.Serializers;

import lab03.Payment;
import lab03.PaymentMethod;
import lab03.Car;
import lab03.Renter;
import lab03.Agreement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLSerializerTest {

    private XMLSerializer<Car> carSerializer;
    private XMLSerializer<Renter> renterSerializer;
    private XMLSerializer<Agreement> agreementSerializer;

    private Car car;
    private Renter renter;
    private Agreement agreement;
    private Payment payment;

    @BeforeEach
    void setUp() {
        carSerializer = new XMLSerializer<>(Car.class);
        renterSerializer = new XMLSerializer<>(Renter.class);
        agreementSerializer = new XMLSerializer<>(Agreement.class);

        car = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        renter = new Renter("John", "Doe", "ID123", "DL123");
        payment = new Payment(PaymentMethod.CREDIT_CARD, 250.0);
        agreement = new Agreement(car, renter, LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 20), payment);
    }

    @Test
    void serializeCar() throws IOException {
        String xmlString = carSerializer.serialize(car);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<manufacturer>Toyota</manufacturer>"));
            assertTrue(xmlString.contains("<brand>Corolla</brand>"));
            assertTrue(xmlString.contains("<costPerDay>50.0</costPerDay>"));
        });
    }

    @Test
    void serializeRenter() throws IOException {
        String xmlString = renterSerializer.serialize(renter);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<firstName>John</firstName>"));
            assertTrue(xmlString.contains("<lastName>Doe</lastName>"));
            assertTrue(xmlString.contains("<identityDocument>ID123</identityDocument>"));
        });
    }

    @Test
    void serializeAgreement() throws IOException {
        String xmlString = agreementSerializer.serialize(agreement);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<manufacturer>Toyota</manufacturer>"));  // Car's manufacturer
            assertTrue(xmlString.contains("<firstName>John</firstName>"));        // Renter's first name
            assertTrue(xmlString.contains("<amount>250.0</amount>"));         // Payment amount
        });
    }

    @Test
    void serializeListOfCars() throws IOException {
        List<Car> cars = List.of(
                new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020),
                new Car("Honda", "Civic", "Sedan", "White", 50.0, "VIN004", 2023),
                car
        );
        String xmlString = carSerializer.serialize(cars);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<brand>Corolla</brand>"));
            assertTrue(xmlString.contains("<brand>Focus</brand>"));
            assertTrue(xmlString.contains("<brand>Civic</brand>"));
        });
    }

    @Test
    void deserializeSingleObject() throws IOException {
        String xmlString = """
            <car>
                <manufacturer>Toyota</manufacturer>
                <brand>Corolla</brand>
                <type>Sedan</type>
                <color>Red</color>
                <costPerDay>100.0</costPerDay>
                <vinCode>ABC123XYZ</vinCode>
                <releaseYear>2020</releaseYear>
            </car>""";

        XMLSerializer<Car> xmlSerializer = new XMLSerializer<>(Car.class);

        Car deserializedCar = xmlSerializer.deserialize(xmlString);

        assertAll(() -> {
            assertNotNull(deserializedCar);
            assertEquals("Toyota", deserializedCar.getManufacturer());
            assertEquals("Corolla", deserializedCar.getBrand());
            assertEquals("Sedan", deserializedCar.getType());
            assertEquals("Red", deserializedCar.getColor());
            assertEquals(100.0, deserializedCar.getCostPerDay());
            assertEquals("ABC123XYZ", deserializedCar.getVinCode());
            assertEquals(2020, deserializedCar.getReleaseYear());
        });
    }

    @Test
    void deserializeRenter() throws IOException {
        String xmlString = """
                <renter>
                    <firstName>John</firstName>
                    <lastName>Doe</lastName>
                    <identityDocument>ID123</identityDocument>
                    <driversLicense>DL123</driversLicense>
                </renter>""";

        XMLSerializer<Renter> xmlSerializer = new XMLSerializer<>(Renter.class);
        Renter deserializedRenter = renterSerializer.deserialize(xmlString);

        assertAll(() -> {
            assertNotNull(deserializedRenter);
            assertEquals("John", deserializedRenter.getFirstName());
            assertEquals("Doe", deserializedRenter.getLastName());
            assertEquals("ID123", deserializedRenter.getIdentityDocument());
        });
    }

    @Test
    void deserializeAgreement() throws IOException {
        String xmlString = """
                <agreement>
                    <car>
                        <manufacturer>Toyota</manufacturer>
                        <brand>Corolla</brand>
                        <type>Sedan</type>
                        <color>Red</color>
                        <costPerDay>50.0</costPerDay>
                        <vinCode>VIN123</vinCode>
                        <releaseYear>2022</releaseYear>
                    </car>
                    <renter>
                        <firstName>John</firstName>
                        <lastName>Doe</lastName>
                        <identityDocument>ID123</identityDocument>
                        <driversLicense>DL123</driversLicense>
                    </renter>
                    <startDate>2024-01-15</startDate>
                    <endDate>2024-01-20</endDate>
                    <payment>
                        <paymentMethod>CREDIT_CARD</paymentMethod>
                        <amount>250.0</amount>
                    </payment>
                </agreement>""";
        Agreement deserializedAgreement = agreementSerializer.deserialize(xmlString);

        assertAll(() -> {
            assertNotNull(deserializedAgreement);
            assertEquals("Corolla", deserializedAgreement.getCar().getBrand());
            assertEquals("John", deserializedAgreement.getRenter().getFirstName());
            assertEquals(250.0, deserializedAgreement.getTotalPrice());
        });
    }

    @Test
    void writeCarToFile() throws IOException {
        String fileName = "src/test/java/resources/write-car.xml";
        carSerializer.writeToFile(car, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("<brand>Corolla</brand>"));
        });
    }

    @Test
    void writeAgreementToFile() throws IOException {
        String fileName = "src/test/java/resources/write-agreement.xml";
        agreementSerializer.writeToFile(agreement, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("<amount>250.0</amount>"));
        });
    }

    @Test
    void readFromFileAgreement() throws IOException {
        String fileName = "src/test/java/resources/agreement.xml";

        XMLSerializer<Agreement> agreementSerializer = new XMLSerializer<>(Agreement.class);

        List<Agreement> deserializedAgreements = agreementSerializer.readFromFile(fileName);

        assertAll(() -> {
            assertNotNull(deserializedAgreements);
            assertEquals(2, deserializedAgreements.size());

            assertTrue(deserializedAgreements.stream().anyMatch(agreement ->
                    "Toyota".equals(agreement.getCar().getManufacturer()) &&
                            "John".equals(agreement.getRenter().getFirstName()) &&
                            250.0 == agreement.getTotalPrice()
            ), "Expected agreement with Toyota and John with $250 not found");

            assertTrue(deserializedAgreements.stream().anyMatch(agreement ->
                    "Honda".equals(agreement.getCar().getManufacturer()) &&
                            "Jane".equals(agreement.getRenter().getFirstName()) &&
                            270.0 == agreement.getTotalPrice()
            ), "Expected agreement with Honda and Jane with $225 not found");
        });
    }
}
