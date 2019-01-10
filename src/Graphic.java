//import javax.swing.*;
//
//public class Graphic {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("FrameDemo");
//    }
//}
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Graphic extends JPanel {
    private JTextField textField;
    private JLabel label;
    private final static int max_num =4;

    // this is start listeners which actualy starts thread from 0
    private void startListeners(Box box) {
        JButton start = new JButton("start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        box.add(start);
    }

    //this stopp listeners just stopps thread.
    private void stopListeners(Box box) {
        JButton stop = new JButton("end");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        box.add(stop);
    }

    /**
     * this is ur constructor, which initializes everything we need for this task.
     */
    public Graphic() {
        int line = BoxLayout.Y_AXIS;
        Box box = new Box(line);
        label = new JLabel("Name");
        textField = new JTextField(10);
        textField.setText("");
        box.add(label);
        box.add(textField);

//        upPart(box);
//        startListeners(box);
//        stopListeners(box);
        add(box);
    }


    //this initializes only textField and our label.
    private void upPart(Box box) {
        label = new JLabel("0");
        textField = new JTextField(10);
        textField.setText("");

        box.add(textField);
        box.add(label);
    }

    //as said in task
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

//        for(int i =0 ; i < max_num;i++) {
//            Graphic count1 = new Graphic();
//            frame.add(count1);
//            frame.add(Box.createRigidArea(new Dimension(0, 40)));
//        }
//
        Graphic count1 = new Graphic();
        frame.add(count1);
        frame.add(Box.createRigidArea(new Dimension(0, 40)));




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
}