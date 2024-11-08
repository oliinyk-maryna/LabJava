package lab05;

public class CarBuilder {
    private String manufacturer;
    private String brand;
    private String type;
    private String color;
    private double costPerDay;
    private String vinCode;
    private int releaseYear;

    public CarBuilder setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be null or empty");
        }
        this.manufacturer = manufacturer;
        return this;
    }

    public CarBuilder setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        this.brand = brand;
        return this;
    }

    public CarBuilder setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        this.type = type;
        return this;
    }

    public CarBuilder setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
        return this;
    }

    public CarBuilder setCostPerDay(double costPerDay) {
        if (costPerDay < 0) {
            throw new IllegalArgumentException("Cost per day must be at least 0.0");
        }
        this.costPerDay = costPerDay;
        return this;
    }

    public CarBuilder setVinCode(String vinCode) {
        if (vinCode == null || !vinCode.matches("[A-HJ-NPR-Z0-9]{17}")) {
            throw new IllegalArgumentException("VIN code must be a valid 17-character alphanumeric code");
        }
        this.vinCode = vinCode;
        return this;
    }

    public CarBuilder setReleaseYear(int releaseYear) {
        if (releaseYear < 1886 || releaseYear > 2024) {
            throw new IllegalArgumentException("Release year must be between 1886 and 2024");
        }
        this.releaseYear = releaseYear;
        return this;
    }

    public Car build() {
        validateFields();
        Car car = new Car();
        car.setManufacturer(this.manufacturer);
        car.setBrand(this.brand);
        car.setType(this.type);
        car.setColor(this.color);
        car.setCostPerDay(this.costPerDay);
        car.setVinCode(this.vinCode);
        car.setReleaseYear(this.releaseYear);
        return car;
    }

    private void validateFields() {
        StringBuilder errors = new StringBuilder();

        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            errors.append("Manufacturer cannot be null or empty.\n");
        }
        if (brand == null || brand.trim().isEmpty()) {
            errors.append("Brand cannot be null or empty.\n");
        }
        if (type == null || type.trim().isEmpty()) {
            errors.append("Type cannot be null or empty.\n");
        }
        if (color == null || color.trim().isEmpty()) {
            errors.append("Color cannot be null or empty.\n");
        }
        if (costPerDay < 0) {
            errors.append("Cost per day must be at least 0.0.\n");
        }
        if (vinCode == null || !vinCode.matches("[A-HJ-NPR-Z0-9]{17}")) {
            errors.append("VIN code must be a valid 17-character alphanumeric code.\n");
        }
        if (releaseYear < 1886 || releaseYear > 2024) {
            errors.append("Release year must be between 1886 and 2024.\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException("Invalid Car configuration:\n" + errors.toString());
        }
    }
}
