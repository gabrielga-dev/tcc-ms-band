package br.com.events.band.newer.data.io.band;

import br.com.events.band.newer.data.io.address.IAddress;
import br.com.events.band.older.util.DateUtil;

import java.time.LocalDateTime;

public interface IBandResponse {
    String getUuid();
    String getName();
    String getDescription();
    Boolean getActive();

    default Long getCreationDateMilliseconds(){
        return DateUtil.localDateTimeToMilliseconds(this.getCreationDate());
    }

    default Long getUpdateDateMilliseconds(){
        return DateUtil.localDateTimeToMilliseconds(this.getUpdateDate());
    }
    default LocalDateTime getCreationDate(){
        return DateUtil.millisecondsToLocalDateTime(this.getCreationDateMilliseconds());
    }
    default LocalDateTime getUpdateDate(){
        return DateUtil.millisecondsToLocalDateTime(this.getUpdateDateMilliseconds());
    }

    String getProfilePictureUuid();
    IAddress getAddress();
}
