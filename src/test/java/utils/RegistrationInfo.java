package utils;

import lombok.Data;

@Data
public class RegistrationInfo {
    private final String name;
    private final String phoneNumber;
    private final String city;

    public RegistrationInfo(String name, String phoneNumber, String city) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }
}
