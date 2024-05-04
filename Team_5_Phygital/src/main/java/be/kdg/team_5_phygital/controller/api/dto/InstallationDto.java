package be.kdg.team_5_phygital.controller.api.dto;

public class InstallationDto {
    private int id;
    private String name;
    private String province;
    private String city;
    private String street;
    private int streetNumber;

    public InstallationDto() {
    }

    public InstallationDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public InstallationDto(int id, String name, String province, String city, String street, int streetNumber) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}
