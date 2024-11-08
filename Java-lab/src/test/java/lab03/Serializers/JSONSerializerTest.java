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

class JSONSerializerTest {

    private JSONSerializer<Car> carSerializer;
    private JSONSerializer<Renter> renterSerializer;
    private JSONSerializer<Agreement> agreementSerializer;

    private Car car;
    private Renter renter;
    private Agreement agreement;
    private Payment payment;

    @BeforeEach
    void setUp() {
        carSerializer = new JSONSerializer<>(Car.class);
        renterSerializer = new JSONSerializer<>(Renter.class);
        agreementSerializer = new JSONSerializer<>(Agreement.class);

        car = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        renter = new Renter("John", "Doe", "ID123", "DL123");
        payment = new Payment(PaymentMethod.CREDIT_CARD, 250.0);
        agreement = new Agreement(car, renter, LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 20), payment);
    }

    @Test
    void serializeCar() throws IOException {
        String jsonString = carSerializer.serialize(car);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("\"manufacturer\":\"Toyota\""));
            assertTrue(jsonString.contains("\"brand\":\"Corolla\""));
            assertTrue(jsonString.contains("\"costPerDay\":50.0"));
        });
    }

    @Test
    void serializeRenter() throws IOException {
        String jsonString = renterSerializer.serialize(renter);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("\"firstName\":\"John\""));
            assertTrue(jsonString.contains("\"lastName\":\"Doe\""));
            assertTrue(jsonString.contains("\"identityDocument\":\"ID123\""));
        });
    }

    @Test
    void serializeAgreement() throws IOException {
        String jsonString = agreementSerializer.serialize(agreement);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("\"manufacturer\":\"Toyota\""));  // Car's manufacturer
            assertTrue(jsonString.contains("\"firstName\":\"John\""));       // Renter's first name
            assertTrue(jsonString.contains("\"amount\":250.0"));         // payment amount
        });
    }

    @Test
    void serializeListOfCars() throws IOException {
        List<Car> cars = List.of(
                new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020),
                new Car("Honda", "Civic", "Sedan", "White", 50.0, "VIN004", 2023),
                car
        );
        String jsonString = carSerializer.serialize(cars);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("\"brand\":\"Corolla\""));
            assertTrue(jsonString.contains("\"brand\":\"Focus\""));
            assertTrue(jsonString.contains("\"brand\":\"Civic\""));
        });
    }

    @Test
    void deserializeSingleObject() throws IOException {
        String jsonString = """
            {
                "manufacturer":"Toyota",
                "brand":"Corolla",
                "type":"Sedan",
                "color":"Red",
                "costPerDay":100.0,
                "vinCode":"ABC123XYZ",
                "releaseYear":2020
            }""";

        JSONSerializer<Car> jsonSerializer = new JSONSerializer<>(Car.class);

        Car deserializedCar = jsonSerializer.deserialize(jsonString);

        assertAll(() -> {
            assertNotNull(deserializedCar);
            assertEquals("Toyota", deserializedCar.getManufacturer());
            assertEquals("Corolla", deserializedCar.getBrand()); // змінили getModel на getBrand
            assertEquals("Sedan", deserializedCar.getType());
            assertEquals("Red", deserializedCar.getColor());
            assertEquals(100.0, deserializedCar.getCostPerDay());
            assertEquals("ABC123XYZ", deserializedCar.getVinCode());
            assertEquals(2020, deserializedCar.getReleaseYear());
        });
    }


    @Test
    void deserializeRenter() throws IOException {
        String jsonString = """
                {
                    "firstName":"John",
                    "lastName":"Doe",
                    "identityDocument":"ID123",
                    "driversLicense":"DL123"
                }""";

        JSONSerializer<Renter> jsonSerializer = new JSONSerializer<>(Renter.class);
        Renter deserializedRenter = renterSerializer.deserialize(jsonString);

        assertAll(() -> {
            assertNotNull(deserializedRenter);
            assertEquals("John", deserializedRenter.getFirstName());
            assertEquals("Doe", deserializedRenter.getLastName());
            assertEquals("ID123", deserializedRenter.getIdentityDocument());
        });
    }

    @Test
    void deserializeAgreement() throws IOException {
        String jsonString = """
                {
                    "car":{
                        "manufacturer":"Toyota",
                        "brand":"Corolla",
                        "type":"Sedan",
                        "color":"Red",
                        "costPerDay":50.0,
                        "vinCode":"VIN123",
                        "releaseYear":2022
                    },
                    "renter":{
                        "firstName":"John",
                        "lastName":"Doe",
                        "identityDocument":"ID123",
                        "driversLicense":"DL123"
                    },
                    "startDate":"2024-01-15",
                    "endDate":"2024-01-20",
                    "payment": {
                        "paymentMethod": "CREDIT_CARD",
                        "amount": 250.0
                               }
                }""";
        Agreement deserializedAgreement = agreementSerializer.deserialize(jsonString);

        assertAll(() -> {
            assertNotNull(deserializedAgreement);
            assertEquals("Corolla", deserializedAgreement.getCar().getBrand());
            assertEquals("John", deserializedAgreement.getRenter().getFirstName());
            //assertEquals(250.0, deserializedAgreement.getTotalPrice());
        });
    }

    @Test
    void writeCarToFile() throws IOException {
        String fileName = "src/test/java/resources/write-car.json";
        carSerializer.writeToFile(car, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("\"brand\":\"Corolla\""));
        });
    }

    @Test
    void writeAgreementToFile() throws IOException {
        String fileName = "src/test/java/resources/write-agreements.json";
        agreementSerializer.writeToFile(agreement, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("\"totalPrice\":250.0"));
        });
    }

    @Test
    void readFromFileCar() throws IOException {
        String fileName = "src/test/java/resources/agreement.json";

        JSONSerializer<Agreement> agreementSerializer = new JSONSerializer<>(Agreement.class);

        List<Agreement> deserializedAgreements = agreementSerializer.readFromFile(fileName);

        assertAll(() -> {
            assertNotNull(deserializedAgreements);
            assertEquals(2, deserializedAgreements.size());

            assertTrue(deserializedAgreements.stream().anyMatch(agreement ->
                    "Toyota".equals(agreement.getCar().getManufacturer()) &&
                            "John".equals(agreement.getRenter().getFirstName()) &&
                            //350.0 == agreement.getTotalPrice() &&
                            LocalDate.of(2024, 2, 15).isEqual(agreement.getStartDate()) &&
                            LocalDate.of(2024, 2, 20).isEqual(agreement.getEndDate())

            ), "Expected agreement with Toyota and John with $250 not found");

            // Check the second agreement (Honda)
            assertTrue(deserializedAgreements.stream().anyMatch(agreement ->
                    "Honda".equals(agreement.getCar().getManufacturer()) &&
                            "Jane".equals(agreement.getRenter().getFirstName()) &&
                            225.0 == agreement.getTotalPrice()
            ), "Expected agreement with Honda and Jane with $225 not found");
        });
    }

}
