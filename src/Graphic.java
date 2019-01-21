//import javax.swing.*;
//
//public class Graphic {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("FrameDemo");
//    }
//}

import com.itextpdf.text.DocumentException;
import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

import javax.swing.*;
import javax.swing.border.Border;

public class Graphic extends JPanel {
    private JTextField textField, srcTextField,sendTextField;
    private JLabel label;
    private JButton start;
    private JButton source;
    private JButton dist;
    private JComboBox comboBox;

    String[] petStrings = {
            "May your life be full of love and laughter and your shelves be full of books.",
            "Wishing you many nights of bedtime stories and sweet dreams.",
            "May your life be filled with beautiful stories. ",
            "Given with love and the hope that this book will open the world of literature to you.",
            "A book is a gift you can open again and again. You are a gift your family will love more and more."};


    //    // this is start listeners which actualy starts thread from 0
    private void startListeners(Box box) {
        JPanel panel1 = new JPanel(new GridLayout(1, 0));
        start = new JButton("Sumbit");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
//                String path = srcTextField.getText();
                String from = sendTextField.getText();
//                String box_str = this.ComboBox.GetItemText(this.ComboBox.SelectedItem);
                int k = comboBox.getSelectedIndex();
                System.out.println(comboBox.getItemAt(k));
                System.out.println(name);
 //               System.out.println(path);
                System.out.println(from);
                String dedication = (String) comboBox.getItemAt(k);
//                String dst = "";
//                for(int i=path.length()-1;i>=0;i--){
//                    if(path.charAt(i)=='\\'){
//                        dst = path.substring(0,i+1);
//                        break;
//                    }
//                }
//                dst += name+".pdf";
//                System.out.println(dst);

                PDFItext sol = new PDFItext();
                sol.addTextPdf("Material\\Aries.pdf", "D:\\"+name+".pdf", name, dedication, from);


            }
        });
        panel1.add(start);
        panel1.add(new Label(" "));
        box.add(new JPanel());
        box.add(panel1);
    }

//    private void sourceListener(Box box) {
//        box.add(new JPanel());
//
//        JPanel panel1 = new JPanel(new GridLayout(1, 0));
//        source = new JButton("Source");
//        source.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("sourceeeeee");
//                //JFileChooser fileChooser = new JFileChooser();
//                //fileChooser.showOpenDialog(null);
//
//                //File file = fileChooser.getSelectedFile();
////                String str = "";
////                if (file != null) {
////                    str = file.getAbsolutePath();
////                    srcTextField.setText(str);
////                } else {
////                    srcTextField.setText("");
////                }
//            //}
//        });
//
//        panel1.add(source);
//        panel1.add(new Label(" "));
//        panel1.add(new Label(" "));
//
//        box.add(new JPanel());
//        box.add(panel1);
//        JPanel panel = new JPanel(new GridLayout(2, 1));
//        srcTextField = new JTextField(10);
//        srcTextField.setText("");
//        panel.add(srcTextField);
//        box.add(panel);
//    }


    /**
     * this is ur constructor, which initializes everything we need for this task.
     */
    public Graphic() {
        int line = BoxLayout.Y_AXIS;
        Box box = new Box(line);
        ///// agia names fielduka.
        upPart(box);
        //combo box
        ComboBox(box);
        // submit button.
        //sourceListener(box);
        downPart(box);

        startListeners(box);
        add(box);


    }

    private void ComboBox(Box box) {
        JPanel panel1 = new JPanel(new GridLayout(3, 1));

        comboBox = new JComboBox(petStrings);
        comboBox.setRenderer(new MyComboBoxRenderer("Choose a dedication"));
        comboBox.setSelectedIndex(-1); //By default it selects first item, we don't want any selection
        panel1.add(comboBox);

        box.add(comboBox);
    }

    private void downPart(Box box) {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel temp = new JLabel("Love, ");
        sendTextField = new JTextField(10);
        panel.add(temp);
//        System.out.println("test");
        sendTextField.setText("");
        panel.add(sendTextField);
        box.add(panel);
    }

    //this initializes only textField and our label.
    private void upPart(Box box) {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        label = new JLabel("Dear ");
        textField = new JTextField(10);
        textField.setText("");
        panel.add(label);
        panel.add(textField);
        box.add(panel);
    }

    //as said in task
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        //run constructor!!
        Graphic count1 = new Graphic();


        frame.add(count1);
        frame.add(Box.createRigidArea(new Dimension(400, 400)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        private String _title;

        public MyComboBoxRenderer(String title) {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus) {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}