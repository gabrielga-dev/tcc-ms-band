package br.com.events.band.older.domain.mapper.musician;

import br.com.events.band.newer.data.model.table.MusicianTable;
import br.com.events.band.older.domain.io.musician.list.rest.out.ListMusiciansRestResult;
import br.com.events.band.older.domain.io.musician.list.useCase.out.ListMusiciansUseCaseResult;
import br.com.events.band.older.util.DateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ListMusicianMapper {

    public static List<ListMusiciansUseCaseResult> from(List<MusicianTable> musicians) {
        return musicians.stream().map(ListMusicianMapper::from).collect(Collectors.toList());
    }

    public static ListMusiciansUseCaseResult from(MusicianTable musician) {
        return ListMusiciansUseCaseResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .age(DateUtil.calculateAgeByBirthday(musician.getBirthday()))
                .creationDateMilliseconds(DateUtil.localDateTimeToMilliseconds(musician.getCreationDate()))
                .avatarUuid(musician.getProfilePictureUuid())
                .build();
    }

    public static List<ListMusiciansRestResult> fromUseCaseResult(List<ListMusiciansUseCaseResult> musicians) {
        return musicians.stream().map(ListMusicianMapper::from).collect(Collectors.toList());
    }

    public static ListMusiciansRestResult from(ListMusiciansUseCaseResult musician) {
        return ListMusiciansRestResult
                .builder()
                .uuid(musician.getUuid())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .age(musician.getAge())
                .creationDateMilliseconds(musician.getCreationDateMilliseconds())
                .avatarUuid(musician.getAvatarUuid())
                .build();
    }
}
