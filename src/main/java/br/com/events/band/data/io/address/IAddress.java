package br.com.events.band.data.io.address;

public interface IAddress {

    String getStreet();
    String getNeighbour();
    String getComplement();

    Long getCityId();
    String getStateIso();
    String getCountryIso();

    String getZipCode();
}
