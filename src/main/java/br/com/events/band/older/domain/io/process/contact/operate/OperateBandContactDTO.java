package br.com.events.band.older.domain.io.process.contact.operate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperateBandContactDTO {

    private String bandUuid;
    private String contactUuid;
}
