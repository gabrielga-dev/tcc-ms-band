package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.business.command.document.quote_request.GenerateQuoteRequestPdfCommand;
import br.com.events.band.business.use_case.quote_request.GenerateQuoteRequestPdfUseCase;
import br.com.events.band.data.io.pdf.PdfDTO;
import br.com.events.band.data.io.pdf.PdfType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenerateQuoteRequestPdfUseCaseImpl implements GenerateQuoteRequestPdfUseCase {

    private final List<GenerateQuoteRequestPdfCommand> commands;

    @Override
    public PdfDTO execute(String quoteRequestUuid, PdfType pdfType) {
        var command = commands.stream()
                .filter(cmd -> cmd.isAccepted(pdfType))
                .findFirst()
                .orElseThrow();

        return command.execute(quoteRequestUuid);
    }
}
