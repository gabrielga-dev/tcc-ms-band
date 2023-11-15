package br.com.events.band.newer.data.io.musician.response;

import br.com.events.band.newer.data.table.BandMusicianTable;
import br.com.events.band.newer.data.table.MusicianTable;
import br.com.events.band.older.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    private boolean hasStartedWithThisBand;

    public MusicianResponse(BandMusicianTable assoc) {
        this.uuid = assoc.getMusician().getUuid();
        this.firstName = assoc.getMusician().getFirstName();
        this.lastName = assoc.getMusician().getLastName();
        this.age = DateUtil.calculateAgeByBirthday(assoc.getMusician().getBirthday());
        this.birthDay = DateUtil.localDateTimeToMilliseconds(assoc.getMusician().getBirthday());
        this.creationDateMilliseconds = DateUtil.localDateTimeToMilliseconds(assoc.getMusician().getCreationDate());
        this.avatarUuid = assoc.getMusician().getAvatarUuid();

        var bandAssocUuid = assoc.getBand().getUuid();
        var bandThatInsertedUuid = assoc.getMusician().getBandThatInserted().getUuid();
        this.hasStartedWithThisBand = bandAssocUuid.equals(bandThatInsertedUuid);
    }

    public MusicianResponse(MusicianTable musician) {
        this.uuid = musician.getUuid();
        this.firstName = musician.getFirstName();
        this.lastName = musician.getLastName();
        this.age = DateUtil.calculateAgeByBirthday(musician.getBirthday());
        this.birthDay = DateUtil.localDateTimeToMilliseconds(musician.getBirthday());
        this.creationDateMilliseconds = DateUtil.localDateTimeToMilliseconds(musician.getCreationDate());
        this.avatarUuid = musician.getAvatarUuid();

        this.hasStartedWithThisBand = true;
    }
}
