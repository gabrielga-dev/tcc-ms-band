package br.com.events.band.core.config;

import br.com.events.band.core.exception.document_template.CouldNotGenerateDocumentException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;

import java.io.IOException;

public class Base64ImageProvider extends AbstractImageProvider {

    @Override
    public Image retrieve(String src) {
        try {
            return retrieveFromBytes(src);
        } catch (BadElementException | IOException ex) {
            throw new CouldNotGenerateDocumentException();
        }
    }

    private Image retrieveFromBytes(String src) throws IOException, BadElementException {
        byte[] img = Base64.decode(src);
        return Image.getInstance(img);
    }

    @Override
    public String getImageRootPath() {
        return null;
    }

}
