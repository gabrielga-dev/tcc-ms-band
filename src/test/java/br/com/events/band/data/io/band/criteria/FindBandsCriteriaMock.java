package br.com.events.band.data.io.band.criteria;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FindBandsCriteriaMock {

    public static FindBandsCriteria build() {
        var toReturn = new FindBandsCriteria();

        toReturn.setName(MockConstants.STRING);
        toReturn.setCityId(MockConstants.LONG);
        toReturn.setStateIso(MockConstants.STRING);
        toReturn.setCountryIso(MockConstants.STRING);

        return toReturn;
    }
}
