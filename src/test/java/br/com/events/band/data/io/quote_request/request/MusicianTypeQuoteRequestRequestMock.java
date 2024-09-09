package br.com.events.band.data.io.quote_request.request;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTypeQuoteRequestRequestMock {
    public static MusicianTypeQuoteRequestRequest build() {
        return new MusicianTypeQuoteRequestRequest(
                MockConstants.STRING,
                MockConstants.INTEGER,
                MockConstants.STRING
        );
    }
}
