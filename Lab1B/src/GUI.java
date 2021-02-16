import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private Integer semaphore = 0;
    private CustomThread thread1;
    private CustomThread thread2;
    public GUI() {
        JFrame frame = new JFrame("Lab 1 b");
        JLabel message = new JLabel("", JLabel.CENTER);
        JButton start1 = new JButton("Пуск 1");
        JButton start2 = new JButton("Пуск 2");
        JButton stop1 = new JButton("Стоп 1");
        JButton stop2 = new JButton("Стоп 2");
        CustomSlider countSlider = new CustomSlider(10, 90, 50);

        start1.setBounds(85, 100, 80, 30);
        start2.setBounds(175, 100, 80, 30);
        stop1.setBounds(85, 140, 80, 30);
        stop2.setBounds(175, 140, 80, 30);
        countSlider.setBounds(30, 30, 280, 50);
        countSlider.setMajorTickSpacing(10);
        countSlider.setPaintTicks(true);
        countSlider.setPaintLabels(true);

        message.setBounds(85, 160, 170, 40);
        frame.add(message);
        frame.add(countSlider);
        frame.add(start1);
        frame.add(start2);
        frame.add(stop1);
        frame.add(stop2);
        frame.add(message);
        frame.setSize(340, 240);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);


        start1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (semaphore) {
                    if (semaphore == 0) {
                        semaphore = 1;
                        thread1 = new CustomThread(10, countSlider);
                        start1.setEnabled(false);
                        stop2.setEnabled(false);
                        thread1.setPriority(1);
                        thread1.setDaemon(true);
                        thread1.start();
                        message.setText("");
                    }
                    else{
                        message.setText("Critical section is occupied!");
                        System.out.println("Critical section is occupied!");
                    }
                }

            }
        });
        stop1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (semaphore) {
                    start1.setEnabled(true);
                    stop2.setEnabled(true);
                    thread1.setInterrupted();
                    message.setText("Critical section is free!");
                    semaphore = 0;
                }
            }
        });
        start2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (semaphore) {
                    if (semaphore == 0) {
                        semaphore = 1;
                        thread2 = new CustomThread(-10, countSlider);
                        start2.setEnabled(false);
                        stop1.setEnabled(false);
                        thread2.setPriority(10);
                        thread2.setDaemon(true);
                        thread2.start();
                        message.setText("");
                    }
                    else{
                        message.setText("Critical section is occupied!");
                        System.out.println("Critical section is occupied!");
                    }
                }

            }
        });
        stop2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (semaphore) {
                    thread2.setInterrupted();
                    start2.setEnabled(true);
                    stop1.setEnabled(true);
                    semaphore = 0;
                    message.setText("Critical section is free!");
                }
            }
        });
    }
}
