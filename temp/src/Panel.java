import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Panel extends JPanel {
    private Timer timerDraw;
    private Image background, ship, dead, injure, end1, end2, bomb;
    private JButton button1, button2;
    private Logic myLogic;
    private int mouseX, mouseY;

    public class myMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
                mouseX = e.getX();
                mouseY = e.getY();

                if ((mouseX > 100) && (mouseY > 100) && (mouseX < 400) && (mouseY < 400)) {
                    if ((myLogic.endGame == 0) && (myLogic.botTurn == false)) {
                        int i = (mouseY - 100) / 30;
                        int j = (mouseX - 100) / 30;
                        if (myLogic.arrayBot[i][j] <= 4) {
                           // myLogic.arrayBot(i, j);
                        }
                    }
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public Panel() {
        addMouseListener(new myMouse());
        setFocusable(true);

        myLogic = new Logic();
        myLogic.start();

        try {
            background = ImageIO.read(new File(""));
            ship = ImageIO.read(new File(""));
            dead = ImageIO.read(new File(""));
            injure = ImageIO.read(new File(""));
            end1 = ImageIO.read(new File(""));
            end2 = ImageIO.read(new File(""));
            bomb = ImageIO.read(new File(""));
        } catch (IOException ex) {

        }
        timerDraw = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();

            }
        });
        timerDraw.start();

        setLayout(null);

        button1 = new JButton();
        button1.setText("Новая игра");
        button1.setForeground(Color.BLACK);
        button1.setFont(new Font("serif", 0, 30));
        button1.setBounds(130, 450, 200, 80);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myLogic.start();
            }
        });
        add(button1);

        button2 = new JButton();
        button2.setText("Выход");
        button2.setForeground(Color.BLACK);
        button2.setFont(new Font("serif", 0, 30));
        button2.setBounds(550, 450, 200, 80);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myLogic.start();
            }
        });
        add(button2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background, 0, 0, 900, 600, null);
        gr.setFont(new Font("serif", 3, 40));
        gr.setColor(Color.blue);
        gr.drawString("Бот", 150, 50);
        gr.drawString("Игрок", 590, 50);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //System.out.println(myLogic.arrayPlayer[i][j]);
                //if ((myLogic.arrayPlayer[i][j] >= 1) && (myLogic.arrayPlayer[i][j] <= 4)) {
                 //   gr.drawImage(ship, 500 + j * 30, 100 + i * 30, 30, 30, null);
                //}
                if (myLogic.arrayBot[i][j]!=0) {
                    if ((myLogic.arrayBot[i][j]>=8) && (myLogic.arrayBot[i][j]<=11)) {
                        gr.drawImage(injure,100+j*30,100+i*30,30,30,null);
                    } 
                    else if (myLogic.arrayBot[i][j]>=15) {
                        gr.drawImage(dead,100+j*30,100+i*30,30,30,null);
                    }
                    if (myLogic.arrayBot[i][j]>=5) {
                        gr.drawImage(bomb,100+j*30,100+i*30,30,30,null);
                    }
                }
                if (myLogic.arrayPlayer[i][j]!=0) {
                if ((myLogic.arrayPlayer[i][j]>=1) && (myLogic.arrayPlayer[i][j]<=4)) {
                    gr.drawImage(ship,500+j*30,100+i*30,30,30,null);
                }
                else if ((myLogic.arrayPlayer[i][j]>=8) && (myLogic.arrayPlayer[i][j]<=11)) {
                        gr.drawImage(injure,500+j*30,100+i*30,30,30,null);
                } 
                else if (myLogic.arrayPlayer[i][j]>=15) {
                        gr.drawImage(dead,500+j*30,100+i*30,30,30,null);
                }
                if (myLogic.arrayPlayer[i][j]>=5) {
                        gr.drawImage(bomb,500+j*30,100+i*30,30,30,null);
                }
                }
            }
        }

        gr.setColor(Color.RED);
        if ((mouseX > 100) && (mouseY > 100) && (mouseX < 400) && (mouseY < 400)) {
            if ((myLogic.endGame == 0) && (myLogic.botTurn == false)) {
                int i = (mouseY - 100) / 30; // вычисляем строку
                int j = (mouseX - 100) / 30; // вычисляем столбец
                if (myLogic.arrayBot[i][j] <= 4)
                    gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
            }
        }

        gr.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) {
            gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
            gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
            gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
            gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
        }
        gr.setFont(new Font("serif", 0, 20));
        gr.setColor(Color.BLACK);
        for (int i = 1; i <= 10; i++) {
            gr.drawString("" + i, 73, 93 + i * 30);
            gr.drawString("" + i, 473, 93 + i * 30);

            gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
            gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
        }

        if (myLogic.endGame == 1) {
            gr.drawImage(end1, 300, 200, 300, 100, null);
        } else if (myLogic.endGame == 2) {
            gr.drawImage(end2, 300, 200, 300, 100, null);
        }
    }
}