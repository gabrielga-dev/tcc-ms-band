package br.com.events.band.domain.io.feign.msFile.uploadFile.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileFileClientResult {

    private String uuid;
    private String fileName;
}
