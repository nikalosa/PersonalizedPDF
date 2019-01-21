import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.text.pdf.PdfContentByte.ALIGN_CENTER;
import static com.itextpdf.text.pdf.PdfContentByte.ALIGN_LEFT;

public class PDFItext {

    private String harse(String s){
        String newS = "";
        for(int i=0;i<s.length();i++){
            newS += new String(s.charAt(i)+" ");

        }
        System.out.println(newS);
        return newS;

    }

    private List<String> getParts(String dedication){
        List<String> dedParts = new ArrayList<>();
        int ind = 0, L = 0, R = dedication.length();
        boolean dot = false;
        for(int i=0;i<dedication.length();i++){
            if(i<dedication.length()-2 && (dedication.charAt(i)=='.' || dedication.charAt(i)==',')){
                dot = true;
                R = i+1;
            }else if(dot == false && ind>=30 && ind<=50 && dedication.charAt(i)==' '){
                R = i;
            }else if(ind > 50 || dot){
                ind = i-R-1;
                dot = false;
                dedParts.add(dedication.substring(L,R));
                L = R + 1;
            }
            ind++;
        }
        dedParts.add(dedication.substring(L));
        return dedParts;
    }

    public void addTextPdf(String src, String dest, String txt, String dedication, String from){
        try {
            //Create PdfReader instance.
            PdfReader pdfReader =
                    new PdfReader(src);

            //Create PdfStamper instance.
            PdfStamper pdfStamper = new PdfStamper(pdfReader,
                    new FileOutputStream(dest));

            //Create BaseFont instance.
            BaseFont baseFont = BaseFont.createFont(
                    "Material\\futura\\Futura Bold Italic font.ttf",
                    BaseFont.CP1252, BaseFont.EMBEDDED);

            BaseFont dedFont = BaseFont.createFont(
                    "Material\\Futuramano Light Regular\\Futuramano Light Regular.ttf",
                    BaseFont.CP1252, BaseFont.EMBEDDED);

            //Get the number of pages in pdf.
            //int pages = pdfReader.getNumberOfPages();

            //Iterate the pdf through pages.

            Rectangle ps = PageSize.A4;

            List<Integer> pages = new ArrayList<>();
            pages.add(4);
            pages.add(5);
            pages.add(10);
            pages.add(14);
            pages.add(20);

            List<String> dedicationParts = getParts(dedication);
            //System.out.println(dedicationParts);
            for(int i : pages) {

                //Contain the pdf data.
                PdfContentByte pageContentByte =
                        pdfStamper.getOverContent(i);

                pageContentByte.beginText();
                //Set text font and size.
                pageContentByte.setFontAndSize(baseFont, 37);
                pageContentByte.setColorFill(new BaseColor(147,61,119));

                pageContentByte.showTextAligned(ALIGN_CENTER,txt+"'s", ps.getWidth()/2,525,0);

                pageContentByte.endText();
            }

            PdfContentByte pageContentByte = pdfStamper.getOverContent(1);
            pageContentByte.beginText();
            pageContentByte.setFontAndSize(dedFont,24);
            pageContentByte.setColorFill(new BaseColor(147, 61, 119));
            pageContentByte.showTextAligned(ALIGN_LEFT, "Dear "+txt+",", 100, ps.getHeight()/3,0);
            pageContentByte.endText();

            float bla = ps.getHeight() / 3 - 60;
            for(String ded : dedicationParts) {
                pageContentByte.beginText();
                pageContentByte.setFontAndSize(dedFont, 15);
                pageContentByte.setColorFill(new BaseColor(147, 61, 119));
                pageContentByte.showTextAligned(ALIGN_LEFT, ded, 100, bla, 0);
                bla -= 20;
                pageContentByte.endText();
            }

            pageContentByte.beginText();
            pageContentByte.setFontAndSize(dedFont,15);
            pageContentByte.setColorFill(new BaseColor(147, 61, 119));
            pageContentByte.showTextAligned(ALIGN_LEFT, "Love, ", 100, ps.getHeight()/10+20,0);
            pageContentByte.showTextAligned(ALIGN_LEFT, from,100,ps.getHeight()/10,0);
            pageContentByte.endText();
            //Close the pdfStamper.
            pdfStamper.close();

            System.out.println("PDF modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manipulatePdf(String src, String dest, String txt, String dedication, String from) throws IOException, IOException, DocumentException {
       // append(src,dest);
        PdfReader reader = new PdfReader(src);
        for(int i=1;i<=reader.getNumberOfPages();i++) {
            PdfDictionary dict = reader.getPageN(i);
            PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
            if (object instanceof PRStream) {
                PRStream stream = (PRStream) object;
                byte[] data = PdfReader.getStreamBytes(stream);


                String eredeti = txt;
                //final String s = new String(eredeti.getBytes());

                //stream.setData(new String(data, "UTF8").replace("*****", s).getBytes("UTF8"));
                if(i == 1){
                    stream.setData(new String(data).replace("*****", harse("Hello "+txt)).getBytes("UTF-8"));
                    stream.setData(new String(data).replace("#####", dedication).getBytes("UTF-8"));
                    //stream.setData(new String(data).replace("@@@@@", "Love, "+from).getBytes("UTF-8"));
                }else {
                    stream.setData(new String(data).replace("*****", txt+"'s").getBytes("UTF-8"));
                }
            }

        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();

        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_CENTER);
        reader.close();
    }
}
