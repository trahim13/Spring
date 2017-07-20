package org.trahim.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.trahim.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by root on 19.07.2017.
 */
public class PdfBuilder extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) model.get("user");

        document.add(new Paragraph(user.getName()));
        document.add(new Paragraph(user.getPassword()));

    }
}
