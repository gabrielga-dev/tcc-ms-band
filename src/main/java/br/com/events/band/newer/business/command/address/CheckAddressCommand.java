package br.com.events.band.newer.business.command.address;

import br.com.events.band.newer.adapter.feign.MsLocationFeign;
import br.com.events.band.newer.data.io.address.IAddress;
import br.com.events.band.newer.core.exception.address.LocationDoesntExistsException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckAddressCommand {

    private final MsLocationFeign locationFeign;

    public void execute(IAddress address){
        try{
            locationFeign.validateIfAddressExists(
                    address.getCityId(),
                    address.getStateIso(),
                    address.getCountryIso()
            );
        } catch (FeignException fe){
            throw new LocationDoesntExistsException();
        }
    }
}
