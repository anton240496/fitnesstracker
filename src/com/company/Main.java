package com.company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalTime;
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

    private int counter, countero, counter1, counters, counter2, counterp; // время на разные виды трененеровок
    // счетчик срабатываний секундомера
    private int timerCount;
    // период срабатывания секундомером  для всех упражненй
    private int timerDel = 0;
    private int timerDel1 = 0;
    private int timerDel2 = 0;
    private float K;//калорие в  один час
    private float cal, calo, cal1, cals, cal2, calp;//подсчет калорий
    private float k; // в одну секунду
//----------------------------------------------------------------------
// создание объектов

    private JTextArea input = new JTextArea("начните тренировку");
    private JScrollPane scrollPaneInput = new JScrollPane(input);
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panelob = new JPanel();
    private JButton start = new JButton("Старт отжимание");
    private JButton start1 = new JButton("Старт скакалка");
    private JButton start2 = new JButton("Старт приседание");
    private JButton stop = new JButton("Стоп");
    private JLabel labelo = new JLabel("");
    private JLabel labels = new JLabel("");
    private JLabel labelp = new JLabel("");
    private JLabel label1 = new JLabel("");
    private JLabel label2 = new JLabel("");
    private JLabel label3 = new JLabel("");
    private JLabel label4 = new JLabel("");
    private DecimalFormat df = new DecimalFormat("#####.##"); // округление калорий до сотых

    private Timer timer;
    private Timer timer1;// секундомеры для трех кнопок
    private Timer timer2;


    // конструктор
    public Main() {

        // инициализация компонентов
        initComponents();
        // объект класса счетчика
        TimeClass tc = new TimeClass(timerDel);
        // объект секундомера
        // период секундомера 1000мс = 1 сек
        int timerStep = 1000;
        timer = new Timer(timerStep, tc);

        TimeClass1 tc1 = new TimeClass1(timerDel1);
        // объект секундомера
        timer1 = new Timer(timerStep, tc1);

        TimeClass2 tc2 = new TimeClass2(timerDel2);
        // объект секундомера

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
        input.setPreferredSize(new Dimension(350, 20));
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

        panel1.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("текущея тренировка")));
        panel1.setPreferredSize(new Dimension(200, 200));
        panel1.add(labelo);
        panel1.add(labels);
        panel1.add(labelp);



        // панель 2
        panel2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("вид тренровки")));
        panel2.setPreferredSize(new Dimension(200, 200));
        panel2.add(start);
        panel2.add(start1);
        panel2.add(start2);
        panel2.add(stop);

        // панель 3

        panelob.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("все тренировки")));
        panelob.setPreferredSize(new Dimension(200, 200));
        panelob.add(label1);
        panelob.add(label2);
        panelob.add(label3);
        panelob.add(label4);

        // размещение панелей
        container.add(BorderLayout.CENTER, panel1);
        container.add(BorderLayout.EAST, panel2);
        container.add(BorderLayout.SOUTH, panelob);

    }
//----------------------------------------------------------------------

    // класс имплементации события нажатия старт
    class startEventListener implements ActionListener {

        @Override
        // обработка события нажатия на button start отжимание
        public void actionPerformed(ActionEvent e) {
            input.setText("отжимание");
            // старт секундомера с повторением
            timer.start();
            timer.setRepeats(true);
            // остановка других секундомеров, если они включены
            timer1.stop();
            timer2.stop();

        }
    }
    class startEventListener1 implements ActionListener {

        @Override
        // обработка события нажатия на button start скакалка
        public void actionPerformed(ActionEvent e) {
            input.setText("скакалка");
            // старт секундомера с повторением
            timer1.start();
            timer1.setRepeats(true);
            // остановка других секундомеров, если они включены
            timer.stop();
            timer2.stop();

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
            // остановка других секундомеров, если они включены
            timer1.stop();
            timer.stop();

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

            K = 30;// за один час
            k = K / 3600; // в одну секунду
            countero++; // секундомер текущий
            counter++; // обший
            calo++; // текущий подсчет
            cal++; // общий подсчет
            cal = counter * k;
            calo=countero*k;// формулы для подсчета
            if (countero > 0) {
              // если время пошло, появляется надпись
                // не забудем про формат времени и округление калорий до сотых
                labelo.setText("отжимание: время: " + LocalTime.ofSecondOfDay(countero) +  " затрат калорий " + df.format(calo));
            }
        }
    }
//----------------------------------------------------------------------
// остальные два класса построенны аналогичным образом
    public class TimeClass1 implements ActionListener {
        // время пошло , скакалка
        public TimeClass1(int count) {
            counter1 = count;
        }
        @Override
        public void actionPerformed(ActionEvent ts1) {

            K = 100;
            k = K / 3600;
            counters++;
            counter1++;
            cal1++;
            cals++;
            cal1 = counter1 * k;
            cals=counters*k;
            if (counters > 0) {
                labels.setText("скакалка: время: " + LocalTime.ofSecondOfDay(counters) + " затрат калорий " + df.format(cals));
            }
        }

    }

    // время пошло , приседание
    public class TimeClass2 implements ActionListener {

        // конструктор
        public TimeClass2(int count) {
            counter2 = count;
        }

        // время пошло приседание
        @Override
        public void actionPerformed(ActionEvent ts) {

            K = 200;
            k = K / 3600;
            counter2++;
            counterp++;
            cal2++;
            calp++;
            cal2 = counter2 * k;
            calp = counterp * k;
            if (counterp >= -1) {

                labelp.setText("приседание: время: "+ LocalTime.ofSecondOfDay(counterp) + " затрат калорий " + df.format(calp));
            }
        }

    }

    // клас имплементации события нажатия стоп
    class stopEventListener implements ActionListener {

        @Override
        // обработка события нажатия на button start
        public void actionPerformed(ActionEvent e) {

            float calsymm; // общий затрат трех тренировок калорий
            long countersymm; // времени

            timer.stop();
            timer1.stop();
            timer2.stop(); // остановить все секундомеры
            calsymm = cal + cal2 +cal1;
            countersymm = counter1+counter2+counter; // формулы для общего затрата

            label1.setText("время потрачено на отжимание: " + LocalTime.ofSecondOfDay(counter) + " затрат калорий " + df.format(cal) );
            label2.setText("время потрачено на скакалку: "+ LocalTime.ofSecondOfDay(counter1) +" затрат калорий " + df.format(cal1) );
            label3.setText("время потрачено на приседание: "  + LocalTime.ofSecondOfDay(counter2) +  " затрат калорий " + df.format(cal2) );
            label4.setText("общий затрат на тренировки" + LocalTime.ofSecondOfDay(countersymm) + " затрат калорий " + df.format(calsymm));



            input.setText("тренировка закончена, выйдите или начните новую");

            timerCount = 0;
            calo=0;
            cals=0;
            calp=0;
            countero=0;
            counters=0;
            counterp=0; // обнуление текущих секундомеров, чтобы можно было начать тренировку заново, при этом данные продолжают
            // суммироваться, так как для общих данных предусмотрены другие без обнуления аналогичные переменные


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