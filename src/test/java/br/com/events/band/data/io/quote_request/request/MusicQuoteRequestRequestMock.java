package br.com.events.band.data.io.quote_request.request;


import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicQuoteRequestRequestMock {
    public static MusicQuoteRequestRequest build() {
        return new MusicQuoteRequestRequest(
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.INTEGER
        );
    }
}
