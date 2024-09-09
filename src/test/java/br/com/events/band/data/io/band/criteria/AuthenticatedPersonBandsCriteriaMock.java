package br.com.events.band.data.io.band.criteria;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticatedPersonBandsCriteriaMock {

    public static AuthenticatedPersonBandsCriteria build() {
        var toReturn = new AuthenticatedPersonBandsCriteria();

        toReturn.setName(MockConstants.STRING);
        toReturn.setCityId(MockConstants.LONG);
        toReturn.setStateIso(MockConstants.STRING);
        toReturn.setCountryIso(MockConstants.STRING);
        toReturn.setCreationDateStartMilliseconds(MockConstants.LONG);
        toReturn.setCreationDateEndMilliseconds(MockConstants.LONG);

        return toReturn;
    }
}
