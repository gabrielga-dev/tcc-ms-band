package br.com.events.band.newer.data.io.address;

public interface IAddressSettable extends IAddress {

    void setCity(String city);
    void setState(String state);
    void setCountry(String country);
}
