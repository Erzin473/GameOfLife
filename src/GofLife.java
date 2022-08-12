import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GofLife extends JFrame{

    int size = 50;
    boolean cellsLife[][];
    JButton cells[][];
    private Timer timer;
    JButton btn;

    public GofLife(){
        Random rnd = new Random();

        cellsLife = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(1000,1000);
        setLayout(new GridLayout(size,size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 50 ; j++) {
                cellsLife[i][j] = rnd.nextInt(100)<30;
                JButton temp = new JButton();
                if(cellsLife[i][j])
                    temp.setBackground(Color.GREEN);
                else
                    temp.setBackground(Color.BLACK);
                    add(temp);
                    cells[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean[][] temp =  new boolean[size][size];

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < 50; j++) {
                        int count =  countNeighbours(i,j);
                        if (cellsLife[i][j]){
                            if (count<2)
                                temp[i][j] = false;
                                if (count ==3 || count ==2)
                                    temp[i][j] =true;
                                    if (count >3)
                                        temp[i][j] = false;
                        }else {
                            if (count == 3)
                                temp[i][j] = true;
                        }
                    }
                }
                cellsLife = temp;

                for (int i = 0; i <size ; i++) {
                    for (int j = 0; j <50 ; j++) {
                        if (cellsLife[i][j]){
                            cells[i][j].setBackground(Color.GREEN);
                        }else
                            cells[i][j].setBackground(Color.BLACK);
                    }
                }
            }
        });

        btn = new JButton("Go");
        add(btn);
        btn.setFont(new Font("Comic Sans",Font.BOLD,25));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn){
                    timer.start();
                }
            }
        });


    }


    int countNeighbours(int x, int y){
        int count = 0;

        for (int i = x-1; i <= x+1 ; i++) {
            for (int j = y-1; j <= y+1 ; j++) {

                try {
                    if (cellsLife[i][j])
                        count++;

                }catch (Exception e){

                }
            }
        }
        if (cellsLife[x][y])
            count--;
        return count;
    }

    public static void main(String[] args) {
        new GofLife();

    }

}
