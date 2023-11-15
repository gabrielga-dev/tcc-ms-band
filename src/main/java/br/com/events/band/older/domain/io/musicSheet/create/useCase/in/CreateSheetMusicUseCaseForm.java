package br.com.events.band.older.domain.io.musicSheet.create.useCase.in;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSheetMusicUseCaseForm {

    private String musicUuid;
    private String observation;
    private MultipartFile file;
}
