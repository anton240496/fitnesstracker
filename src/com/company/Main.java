package com.company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import javax.swing.Timer;// у нас он будет секундомером
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


class Main extends JFrame {

    private int counter;
    // счетчик срабатываний секундомера
    private int timerCount;
    // период срабатывания секундомером мс для всех упражненй
    private int timerDel = 0;
    private int timerDel1 = 0;
    private int timerDel2 = 0;
    private float K;//калорие в час
    private float cal, cal1, cal2;//подсчет калорий
    private float k; // в секунду
//----------------------------------------------------------------------
// создание объектов

    private JTextArea input = new JTextArea("input");
    private JScrollPane scrollPaneInput = new JScrollPane(input);
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton start = new JButton("Старт отжимание");
    private JButton start1 = new JButton("Старт скакалка");
    private JButton start2 = new JButton("Старт приседание");
    private JButton stop = new JButton("Стоп");
    private JLabel label1 = new JLabel("label1");
    private JLabel label3 = new JLabel("label3");

    private Timer timer;
    private Timer timer1;
    private Timer timer2;


    // конструктор
    public Main() {

        // инициализация компонентов
        initComponents();
        // объект класса счетчика
        TimeClass tc = new TimeClass(timerDel);
        // объект секундомера
        // период одного импульса счета в мс
        int timerStep = 1000;
        timer = new Timer(timerStep, tc);

        TimeClass1 tc1 = new TimeClass1(timerDel1);
        // объект секундомера
        // период одного импульса счета в мс

        timer1 = new Timer(timerStep, tc1);

      TimeClass2 tc2 = new TimeClass2(timerDel2);
        // объект секундомера
        // период одного импульса счета в мс

        timer2 = new Timer(timerStep, tc2);



    }

    // метод инициализации компонентов формы
    private void initComponents() {

        // положение на экране
        setBounds(15, 30, 500, 250);
        // размер формы
        setSize(600, 480);
        // контейнер для размещения компонентов формы
        Container container = getContentPane();

        // окно вывода текстовой информации
        input.setPreferredSize(new Dimension(350, 100));
        input.setSize(20, 20);
        // кнопка старт
        start.setPreferredSize(new Dimension(150, 20));
        start1.setPreferredSize(new Dimension(150, 20));
        start2.setPreferredSize(new Dimension(150, 20));
        // зарегистрировать экземпляр класса обработчика события start
        start.addActionListener(new startEventListener());
        start1.addActionListener(new startEventListener1());
       start2.addActionListener(new startEventListener2());

        // кнопка стоп
        stop.setPreferredSize(new Dimension(100, 20));
        // зарегистрировать экземпляр класса обработчика события stop
        stop.addActionListener(new stopEventListener());

        // панель 1
        panel1.add(BorderLayout.CENTER, scrollPaneInput);
        panel1.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("процесс")));
        panel1.setPreferredSize(new Dimension(200, 200));
        panel1.add(label1);
        panel1.add(label3);

        // панель 2
        panel2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("вид тренровки")));
        panel2.setPreferredSize(new Dimension(200, 200));
        panel2.add(start);
        panel2.add(start1);
        panel2.add(start2);

        panel2.add(stop);



        // размещение панелей
        container.add(BorderLayout.CENTER, panel1);
        container.add(BorderLayout.EAST, panel2);

    }
//----------------------------------------------------------------------

    // клас имплементации события нажатия старт
    class startEventListener implements ActionListener {

        @Override
        // обработка события нажатия на button start отжимание  (работает)
        public void actionPerformed(ActionEvent e) {
            input.setText("отжимание");
            // старт таймера с повторением
            timer.start();
            timer.setRepeats(true);


        }
    }

    class startEventListener1 implements ActionListener {

        @Override
        // обработка события нажатия на button start скакалка
        public void actionPerformed(ActionEvent e) {
            input.setText("скакалка");
            // старт таймера с повторением
            timer1.start();
            timer1.setRepeats(true);


        }
    }

    class startEventListener2 implements ActionListener {

        @Override
        // обработка события нажатия на button start приседание
        public void actionPerformed(ActionEvent e) {
            input.setText("приседание");
            // старт секундомера с повторением
            timer2.start();
            timer2.setRepeats(true);


        }
    }

    //  класс пререзагружаемого счетчика секундомера
    public class TimeClass implements ActionListener {

        // конструктор
        public TimeClass(int count) {
            counter = count;
        }

        // время пошло , отжимание
        @Override
        public void actionPerformed(ActionEvent ts) {
            K = 30;
            k = K / 3600;


            K++;

            cal++;


            counter++;
            cal = counter * k;
            if (counter > 0) {


                label1.setText("время: " + counter +  "калорий в час=" + cal);


            } else {
                // счет срабатываний секундомера
                input.setText("Цикл секундомера N " + ++timerCount);
                // звук beep
                Toolkit.getDefaultToolkit().beep();
                // перезагрузка счетчика времени
                counter = timerDel;

            }
        }
    }
//----------------------------------------------------------------------

    public class TimeClass1 implements ActionListener {
        // время пошло , скакалка
        public TimeClass1(int count) {
            counter = count;
        }
        @Override
        public void actionPerformed(ActionEvent ts1) {
            K = 100;
            k = K / 3600;




            cal1++;


           counter++;
            cal1 = counter * k;
            if (counter > 0) {


                label1.setText("время: " + counter + "калорий в час=" + cal1);


            } else {
                // счет срабатываний секундомера
                input.setText("Цикл секундомера N " + ++timerCount);
                // звук beep
                Toolkit.getDefaultToolkit().beep();
                // перезагрузка счетчика времени
                counter = timerDel1;

            }
        }

    }

    // время пошло , приседание
    public class TimeClass2 implements ActionListener {

        // конструктор
        public TimeClass2(int count) {
            counter = count;
        }

        // время пошло , не работает все время срабатывает следующий класс
        @Override
        public void actionPerformed(ActionEvent ts) {
            K = 200;
            k = K / 3600;




            cal2++;


          counter++;
            cal2 = counter * k;
            if (counter >= -1) {

             //   cal = counter* K;
                label1.setText("время: " + counter +  "калорий в час=" + cal2);

            } else {
                // счет срабатываний секундомера
                input.setText("Цикл секундомера N " + ++timerCount);
                // звук beep
                Toolkit.getDefaultToolkit().beep();
                // перезагрузка счетчика времени
                counter = timerDel2;

            }
        }
    }

    // клас имплементации события нажатия стоп
    class stopEventListener implements ActionListener {

        @Override
        // обработка события нажатия на button start
        public void actionPerformed(ActionEvent e) {
            float calsymm;
           calsymm =  cal +cal1 + cal2;
            //остановка секундомера
            //counter++;

            timer.stop();
            timer1.stop();
            timer2.stop();


            label1.setText("секундомер остановлен, время: " + counter + "калорий в час=" + calsymm);
            calsymm++;

//            label3.setText("время: " + counter + "калорий в час=" + cal);
            //label1.setText("df");
          //  counter++;
          //  cal++;


            input.setText("секундомер остановлен");

             counter = 0;
           timerCount = 0;
        }
    }




    public static void main(String[] args) {

        // объект графической формы
        Main graphic = new Main();
        // закрытие формы
        graphic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // визуализация формы
        graphic.setVisible(true);
    }
}