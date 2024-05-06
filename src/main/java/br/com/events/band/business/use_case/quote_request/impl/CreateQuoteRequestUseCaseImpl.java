package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SendQuoteCreationErrorMessageCommand;
import br.com.events.band.business.command.quote_request.SendQuoteRequestEmailMessageCommand;
import br.com.events.band.business.use_case.quote_request.CreateQuoteRequestUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.data.model.table.musician.MusicianTypeTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateQuoteRequestUseCaseImpl implements CreateQuoteRequestUseCase {

    private final FindBandCommand findBandCommand;
    private final FindMusicCommand findMusicCommand;
    private final FindMusicianTypeCommand findMusicianTypeCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final MsEventFeign msEventFeign;
    private final SendQuoteRequestEmailMessageCommand sendQuoteRequestEmailMessageCommand;
    private final SendQuoteCreationErrorMessageCommand sendQuoteRequestEmailMessage;

    @Override
    @Transactional
    public void execute(QuoteRequestRequest request) {
        try {
            var band = findBandCommand.byUuid(request.getBandUuid()).orElseThrow(BandNonExistenceException::new);
            if (!band.getActive()) {
                throw new BandNonExistenceException();
            }

            var musicsMap = findMusicCommand.byUuids(request.getMusicUuids())
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    MusicTable::getUuid,
                                    music -> music
                            )
                    );
            var musicianTypesMap = findMusicianTypeCommand.byUuid(request.getMusicianTypeUuids())
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    MusicianTypeTable::getUuid,
                                    musicianType -> musicianType
                            )
                    );
            var newQuoteRequest = new QuoteRequestTable(request, musicsMap, musicianTypesMap, band);
            saveQuoteRequestCommand.execute(newQuoteRequest);

            var event = msEventFeign.findProfile(request.getEventUuid());

            sendQuoteRequestEmailMessageCommand.execute(request, event, band);
        } catch (Exception e) {
            sendQuoteRequestEmailMessage.execute(request);
            log.info(
                    "[ERROR] Error while creating quote request, sending message to delete it." +
                            " Quote request uuid: {}; {}",
                    request.getQuoteRequestUuid(), e.getLocalizedMessage()
            );
        }
    }
}
