package br.com.events.band.business.command.document.impl;

import br.com.events.band.business.command.document.GeneratePdfDocumentCommand;
import br.com.events.band.core.config.StaticTemplateExecutor;
import br.com.events.band.core.exception.document_template.CouldNotGenerateDocumentException;
import br.com.events.band.core.exception.document_template.WaterMarkDTO;
import br.com.events.band.data.io.document.PdfConfigurationDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeneratePdfDocumentCommandImpl implements GeneratePdfDocumentCommand {

    private final ImageProvider imageProvider;
    private final CSSResolver cssResolver;

    @Override
    public byte[] execute(PdfConfigurationDTO configuration, Map<String, Object> params) {
        try {
            var document = new Document(PageSize.A4);

            var context = getContext(params);
            var messageResolver = new StandardMessageResolver();
            var executor = new StaticTemplateExecutor(
                    context,
                    messageResolver,
                    StandardTemplateModeHandlers.HTML5.getTemplateModeName()
            );
            var processedTemplate = executor.processTemplateCode(configuration.getTemplate());


            var documentStream = new ByteArrayOutputStream();
            var writer = PdfWriter.getInstance(document, documentStream);

            var templateStream = new ByteArrayInputStream(processedTemplate.getBytes(StandardCharsets.UTF_8));

            writer.setPageEvent(new WaterMarkDTO(
                    "My Events",
                    Font.FontFamily.HELVETICA,
                    70,
                    Font.NORMAL,
                    new BaseColor(0.058f, 0.44f, 0.70f, 0.45f)
            ));

            document.open();

            var htmlContext = new HtmlPipelineContext(null);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            htmlContext.setImageProvider(imageProvider);

            var pdf = new PdfWriterPipeline(document, writer);
            var html = new HtmlPipeline(htmlContext, pdf);
            var css = new CssResolverPipeline(cssResolver, html);

            var worker = new XMLWorker(css, true);
            var p = new XMLParser(worker);
            p.parse(templateStream);


            document.close();

            return documentStream.toByteArray();
        } catch (Exception e) {
            throw new CouldNotGenerateDocumentException(e);
        }
    }

    private Context getContext(Map<String, Object> params) {
        Context ctx = new Context();
        params.forEach(ctx::setVariable);
        return ctx;
    }
}
