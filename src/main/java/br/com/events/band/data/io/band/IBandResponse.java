package br.com.events.band.data.io.band;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.address.IAddress;

import java.time.LocalDateTime;
import java.util.Objects;

public interface IBandResponse {
    String getUuid();

    String getName();

    String getDescription();

    Boolean getActive();

    default Long getCreationDateMilliseconds() {
        return DateUtil.localDateTimeToMilliseconds(this.getCreationDate());
    }

    default Long getUpdateDateMilliseconds() {
        if (Objects.isNull(this.getUpdateDate())) {
            return null;
        }
        return DateUtil.localDateTimeToMilliseconds(this.getUpdateDate());
    }

    default LocalDateTime getCreationDate() {
        return DateUtil.millisecondsToLocalDateTime(this.getCreationDateMilliseconds());
    }

    default LocalDateTime getUpdateDate() {
        return DateUtil.millisecondsToLocalDateTime(this.getUpdateDateMilliseconds());
    }

    String getProfilePictureUuid();

    IAddress getAddress();
}
