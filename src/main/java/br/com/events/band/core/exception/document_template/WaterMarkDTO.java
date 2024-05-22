package br.com.events.band.core.exception.document_template;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WaterMarkDTO extends PdfPageEventHelper {

    private String text;
    private Font.FontFamily fontFamily;
    private int fontSize;
    private int decoration;
    private BaseColor color;

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        var canvas = writer.getDirectContentUnder();
        ColumnText
                .showTextAligned(
                        canvas,
                        Element.ALIGN_CENTER,
                        new Phrase(this.text, new Font(this.fontFamily, this.fontSize, this.decoration, this.color)),
                        PageSize.A4.getWidth() / 2,
                        PageSize.A4.getWidth() / 2,
                        0);
    }
}
