//import javax.swing.*;
//
//public class Graphic {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("FrameDemo");
//    }
//}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

import javax.swing.*;
import javax.swing.border.Border;

public class Graphic extends JPanel {
    private JTextField textField;
    private JLabel label;
    String[] petStrings = {
            "May your life be full of love and laughter and your shelves be full of books.",
            "Wishing you many nights of bedtime stories and sweet dreams.",
            "May your life be filled with beautiful stories. ",
            "Given with love and the hope that this book will open the world of literature to you.",
            "A book is a gift you can open again and again. You are a gift your family will love more and more." };


//    // this is start listeners which actualy starts thread from 0
    private static JPanel startListeners() {
        JButton start = new JButton("Sumbit");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("losik");

            }
        });
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
        pane.add(start);
        pane.add(Box.createHorizontalGlue());
        return pane;
    }
//
//    //this stopp listeners just stopps thread.
//    private void stopListeners(Box box) {
//        JButton stop = new JButton("end");
//        stop.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        box.add(stop);
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
//        startListeners(box);
//        stopListeners(box);
        add(box);
    }

    private void ComboBox(Box box) {
        JPanel panel1 =new JPanel(new GridLayout(3,1));

        JComboBox comboBox = new JComboBox(petStrings);
        comboBox.setRenderer(new MyComboBoxRenderer("Choose a dedication"));
        comboBox.setSelectedIndex(-1); //By default it selects first item, we don't want any selection
        panel1.add(comboBox);
        box.add(comboBox);
    }

    //this initializes only textField and our label.
    private void upPart(Box box) {
        JPanel panel =new JPanel(new GridLayout(3,1));
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

        Graphic count1 = new Graphic();
        frame.add(count1);
        // BOXIS DAMATEBAA MAGLAI
        // AGIA BUTTON
        JPanel pane = startListeners();
        frame.add(pane, BorderLayout.EAST);

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



    class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}