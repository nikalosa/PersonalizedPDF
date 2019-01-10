import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Controller {
    public static void main(String[] args) {
        String txt = "";
        for(int i=0;i<args[0].length();i++){
            txt += ""+args[0].charAt(i)+" ";

        }
        String sourcePath = args[1];
        PDFItext sol = new PDFItext();
        try {
            sol.manipulatePdf("D:\\Freelancer\\PersonalizedPDF\\Material\\"+sourcePath,
                    "D:\\Freelancer\\PersonalizedPDF\\Result\\Aries-"+txt+".pdf", txt);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
