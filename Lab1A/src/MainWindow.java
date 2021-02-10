import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Lab 1a");
        JButton start = new JButton("Пуск");
        JSlider threadSlider1 = new JSlider(1,10,5);
        JSlider threadSlider2 = new JSlider(1,10,5);
        JLabel t1 = new JLabel("Thread 1 Priority");
        JLabel t2 = new JLabel("Thread 2 Priority");
        CustomSlider countSlider = new CustomSlider(10,90,50);

        start.setBounds(170,150,100, 30);

        countSlider.setBounds(80, 90,280, 50);
        countSlider.setMajorTickSpacing(10);
        countSlider.setPaintTicks(true);
        countSlider.setPaintLabels(true);

        threadSlider1.setBounds(80, 30,130, 50);
        threadSlider1.setMajorTickSpacing(1);
        threadSlider1.setPaintTicks(true);
        threadSlider1.setPaintLabels(true);

        threadSlider2.setBounds(230, 30,130, 50);
        threadSlider2.setMajorTickSpacing(1);
        threadSlider2.setPaintTicks(true);
        threadSlider2.setPaintLabels(true);


        t1.setBounds(85,10,130, 15);
        t2.setBounds(235,10,130, 15);

        frame.add(countSlider);
        frame.add(start);
        frame.add(threadSlider1);
        frame.add(threadSlider2);
        frame.add(t1);
        frame.add(t2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(440,240);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        CustomThread thread1 = new CustomThread(10,countSlider);
        CustomThread thread2 = new CustomThread(-10,countSlider);

        thread1.setPriority(5);
        thread2.setPriority(5);

        threadSlider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                thread1.setPriority(threadSlider1.getValue());
            }
            });

        threadSlider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                thread2.setPriority(threadSlider2.getValue());
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thread1.start();
                thread2.start();
            }
        });

    }
}
