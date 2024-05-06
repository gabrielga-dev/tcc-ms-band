package br.com.events.band.data.io.event.response;

import br.com.events.band.core.util.DateUtil;
import br.com.events.band.data.io.address.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventProfileResponse implements Serializable {

    private String uuid;
    private String name;
    private String description;
    private Long dateTimeStamp;
    private AddressResponse address;

    @JsonIgnore
    public String getFormattedAddress(){
        return this.address.getFormattedAddress();
    }

    @JsonIgnore
    public LocalDateTime getDate() {
        return DateUtil.millisecondsToLocalDateTime(this.dateTimeStamp);
    }
}
