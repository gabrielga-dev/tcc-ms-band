package br.com.events.band.domain.mapper.contact;

import br.com.events.band.domain.entity.Contact;
import br.com.events.band.domain.io.contact.listBandContact.rest.out.ListBandContactRestResult;
import br.com.events.band.domain.io.contact.listBandContact.useCase.out.ListBandContactUseCaseResult;
import br.com.events.band.util.AuthUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class holds every needed mapping process to the listing band's contacts feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ListBandContactMapper {

    public static List<ListBandContactUseCaseResult> from(List<Contact> originals) {
        return originals.stream().map(ListBandContactMapper::from).collect(Collectors.toList());
    }

    public static ListBandContactUseCaseResult from(Contact original) {
        return ListBandContactUseCaseResult
                .builder()
                .uuid(
                        AuthUtil.isAuthenticated()
                                ? original.getUuid()
                                : null
                ).type(original.getType())
                .content(original.getContent())
                .build();
    }

    public static List<ListBandContactRestResult> fromUseCaseResult(
            List<ListBandContactUseCaseResult> originals
    ) {
        return originals.stream().map(ListBandContactMapper::fromUseCaseResult).collect(Collectors.toList());
    }

    public static ListBandContactRestResult fromUseCaseResult(ListBandContactUseCaseResult original) {
        return ListBandContactRestResult
                .builder()
                .uuid(original.getUuid())
                .type(original.getType())
                .content(original.getContent())
                .build();
    }
}
