package br.com.events.band.data.io.band.response;

import br.com.events.band.core.util.AuthUtil;
import br.com.events.band.data.io.address.response.AddressResponse;
import br.com.events.band.data.io.contact.response.ContactResponse;
import br.com.events.band.data.io.music.response.MusicResponse;
import br.com.events.band.data.io.musician.response.MusicianResponse;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BandProfileResponse {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private Long creationDateMilliseconds;
    private String profilePictureUuid;
    private String ownerUuid;
    private AddressResponse address;
    private List<ContactResponse> contacts;
    private List<MusicianResponse> musicians;
    private List<MusicResponse> contributedMusics;

    public BandProfileResponse(BandTable band, AddressResponse address) {
        this.uuid = band.getUuid();
        this.name = band.getName();
        this.description = band.getDescription();
        this.active = band.getActive();
        this.creationDateMilliseconds = band.getCreationDateMilliseconds();
        this.profilePictureUuid = band.getProfilePictureUuid();
        this.ownerUuid = band.getOwnerUuid();
        this.address = address;

        this.contacts = band.getContacts().stream().map(ContactResponse::new).collect(Collectors.toList());

        var isBandOwner = AuthUtil.isAuthenticated() &&
                AuthUtil.getAuthenticatedPersonUuid().equals(band.getOwnerUuid());

        this.musicians = band.getInsertedMusicians()
                .stream()
                .filter(m -> {
                    if (!isBandOwner) {
                        return m.isActive();
                    }
                    return true;
                })
                .map(m -> new MusicianResponse(m, Boolean.TRUE))
                .collect(Collectors.toList());
        var addedMusicians = this.musicians
                .stream()
                .map(MusicianResponse::getUuid)
                .collect(Collectors.toSet());
        band.getAssociatedMusicians().forEach(
                musicianAssoc -> {
                    if (!addedMusicians.contains(musicianAssoc.getMusician().getUuid())) {
                        this.musicians.add(new MusicianResponse(musicianAssoc));
                        addedMusicians.add(musicianAssoc.getMusician().getUuid());
                    }
                }
        );

        this.contributedMusics = band.getContributedMusics()
                .stream()
                .map(MusicResponse::new)
                .collect(Collectors.toList());
    }
}
