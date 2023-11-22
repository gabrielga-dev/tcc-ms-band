package br.com.events.band.data.io.musician.response;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import br.com.events.band.data.model.table.band.BandMusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class MusicianResponse {

    private String uuid;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long birthDay;
    private Long creationDateMilliseconds;
    private String avatarUuid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean hasStartedWithThisBand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean active;
    private List<MusicianTypeResponse> types;

    public MusicianResponse(BandMusicianTable assoc) {
        this.setBasicInformation(assoc.getMusician());

        var bandAssocUuid = assoc.getBand().getUuid();
        var musicianBand = assoc.getMusician().getBandThatInserted();
        this.hasStartedWithThisBand = Objects.nonNull(musicianBand) && bandAssocUuid.equals(musicianBand.getUuid());
        this.active = assoc.getMusician().isActive();
        this.setTypes(assoc.getMusician().getTypes());
    }

    public MusicianResponse(MusicianTable musician, Boolean hasStartedWithThisBand) {
        this.setBasicInformation(musician);

        this.hasStartedWithThisBand = hasStartedWithThisBand;
        this.active = musician.isActive();
        this.setTypes(musician.getTypes());
    }

    public void setBasicInformation(MusicianTable musician) {
        this.uuid = musician.getUuid();
        this.firstName = musician.getFirstName();
        this.lastName = musician.getLastName();
        this.age = DateUtil.calculateAgeByBirthday(musician.getBirthday());
        this.birthDay = DateUtil.localDateTimeToMilliseconds(musician.getBirthday());
        this.creationDateMilliseconds = DateUtil.localDateTimeToMilliseconds(musician.getCreationDate());
        this.avatarUuid = musician.getProfilePictureUuid();
    }

    public void setTypes(List<MusicianTypeTable> types) {
        this.types = types.stream().map(MusicianTypeResponse::new).collect(Collectors.toList());
    }
}
