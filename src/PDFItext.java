import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFItext {
    public static void manipulatePdf(String src, String dest, String txt) throws IOException, IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        for(int i=1;i<=reader.getNumberOfPages();i++) {
            PdfDictionary dict = reader.getPageN(i);
            PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
            if (object instanceof PRStream) {
                PRStream stream = (PRStream) object;
                byte[] data = PdfReader.getStreamBytes(stream);


                String eredeti = txt;
                final String s = new String(eredeti.getBytes());

                stream.setData(new String(data).replace("************", s).getBytes("ISO-8859-2"));
            }

        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();

        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_CENTER);
        reader.close();
    }
}
