package br.com.events.band.data.io.band.response;

import br.com.events.band.data.model.table.BandTable;
import br.com.events.band.data.io.address.response.AddressResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BandResponse {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private Long creationDateMilliseconds;
    private Long updateDateMilliseconds;
    private String profilePictureUuid;
    private AddressResponse address;

    public BandResponse(BandTable band) {
        this.uuid = band.getUuid();
        this.name = band.getName();
        this.description = band.getDescription();
        this.active = band.getActive();
        this.creationDateMilliseconds = band.getCreationDateMilliseconds();
        this.updateDateMilliseconds = band.getUpdateDateMilliseconds();
        this.profilePictureUuid = band.getProfilePictureUuid();
    }
}
