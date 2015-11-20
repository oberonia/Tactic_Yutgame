package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;






import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;





@SuppressWarnings("serial")
public class FrameInFrame extends JFrame{

    JDesktopPane xDesktop;        //JDesktopPane을 정의한다.

    ToolListener xToolListener = new ToolListener();



    public FrameInFrame(String title){

        super(title);

        

        /* 프레임을 닫으면 프로그램을 종료시키도록 설정한다. */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        /* 내용 패널을 BorderLayot으로 정한다. */

        Container cp = getContentPane();

        cp.setLayout(new BorderLayout());



        /* 툴바를 만든다 */

        JPanel tool_bar = new JPanel(new BorderLayout());

        JPanel tool_pnl = new JPanel(new GridLayout(1, 0));

        JButton btn;

        btn = new JButton("Maximize");

        btn.addActionListener(xToolListener);        //액션 이벤트..

        tool_pnl.add(btn);




        btn = new JButton("Minimize");

        btn.addActionListener(xToolListener);

        tool_pnl.add(btn);



        btn = new JButton("Iconify");

        btn.addActionListener(xToolListener);

        tool_pnl.add(btn);



        btn = new JButton("Close");

        btn.addActionListener(xToolListener);

        tool_pnl.add(btn);



        tool_bar.add(tool_pnl, BorderLayout.WEST);

        cp.add(tool_bar, BorderLayout.NORTH);

        

        /* 데스크탑 패널을 만든다. */

        

        xDesktop = new JDesktopPane();
        cp.add(xDesktop, BorderLayout.CENTER);

        

        /* 기본 설정의 내부 프레임을 만든다. */

        



        

        /* 모든 옵션이 켜져있는 내부 프레임을 만든다. */

        JInternalFrame ch1 = new JInternalFrame("꺾은선 차트", true, false, true, true);

        ch1.getContentPane().add(new JLabel(new ImageIcon("./sample.jpg")), BorderLayout.CENTER);
        ch1.setBounds(300, 20, 800, 600);
        xDesktop.add(ch1);
        
        
        JInternalFrame ch2 = new JInternalFrame("막대 차트", true, false, true, true);

        ch2.getContentPane().add(new JLabel(new ImageIcon("./sample.jpg")), BorderLayout.CENTER);
        ch2.setBounds(310, 30, 800, 600);
        xDesktop.add(ch2);
        
        
        
        JInternalFrame ch3 = new JInternalFrame("파이 차트", true, false, true, true);

        ch3.getContentPane().add(new JLabel(new ImageIcon("./sample.png")), BorderLayout.CENTER);
        ch3.setBounds(320, 40, 800, 600);
        xDesktop.add(ch3);
        
        JInternalFrame ch_pr = new JInternalFrame("Chart 속성");

        //fr.getContentPane().add(
            //new JLabel(new ImageIcon("./sample.jpg"))
            //, BorderLayout.CENTER);

        ch_pr.setBounds(20, 20, 200, 400);
        ch_pr.setLayout(null);

        ButtonGroup group = new ButtonGroup();
        JRadioButton chart1 = new JRadioButton("꺾은선 차트");
        JRadioButton chart2 = new JRadioButton("막대 차트");
        JRadioButton chart3 = new JRadioButton("파이 차트");
        group.add(chart1);
        group.add(chart2);
        group.add(chart3);
        chart1.setLocation(10, 10);
        chart1.setSize(100,20);
        chart2.setLocation(10, 30);
        chart2.setSize(100,20);
        chart3.setLocation(10, 50);
        chart3.setSize(100,20);
        
        ch_pr.add(chart1); chart1.addActionListener(new ChartSwitch());
        ch_pr.add(chart2); chart2.addActionListener(new ChartSwitch());
        ch_pr.add(chart3); chart3.addActionListener(new ChartSwitch());
        ch_pr.setVisible(true);



        xDesktop.add(ch_pr); 

    }
    

    class ChartSwitch implements ActionListener {

        public void actionPerformed(ActionEvent ev){

            String cmd = ev.getActionCommand();

            JInternalFrame[] fr = xDesktop.getAllFrames();           
            /* 버튼에 따라 최대화, 최소화, 닫기 등의 작업을 한다. */

            try{

            if (cmd.equals("꺾은선 차트")) {
            	for(int i=0;i<fr.length;i++) {
            		if(fr[i].getTitle().equals("꺾은선 차트")||
            				fr[i].getTitle().equals("Chart 속성"))
            			fr[i].setVisible(true);
            		else fr[i].setVisible(false);
            	}

            } else if (cmd.equals("막대 차트")) {

            	for(int i=0;i<fr.length;i++) {
            		if(fr[i].getTitle().equals("막대 차트")||
            				fr[i].getTitle().equals("Chart 속성"))
            			fr[i].setVisible(true);
            		else fr[i].setVisible(false);
            	}         

            } else if (cmd.equals("파이 차트")) {

            	for(int i=0;i<fr.length;i++) {
            		if(fr[i].getTitle().equals("파이 차트")||
            				fr[i].getTitle().equals("Chart 속성"))
            			fr[i].setVisible(true);
            		else fr[i].setVisible(false);
            	}              

            }
            
            }catch(Exception e){}         

        }

    }

    

    class ToolListener implements ActionListener {

        public void actionPerformed(ActionEvent ev){

            String cmd = ev.getActionCommand();

            JInternalFrame fr = xDesktop.getSelectedFrame();     //선택된 프레임을 가져온다.           

            /* 버튼에 따라 최대화, 최소화, 닫기 등의 작업을 한다. */

            try{

            if (cmd.equals("Maximize")) {

                fr.setMaximum(true);            //최대화 복원

            } else if (cmd.equals("Minimize")) {

                fr.setMaximum(false);            //최대화 취소

            } else if (cmd.equals("Iconify")) {

                fr.setIcon(true);                //내부프레임의 아이콘화 유무

            } else if (cmd.equals("Close")) {

                fr.setClosed(true);                //내부프레임 닫기

            }   

            }catch(Exception e){}         

        }

    }



    public static void main(String args[]){

        /* 프레임을 만든다. */

        FrameInFrame mf = new FrameInFrame("JInternalFrame 예제");



        mf.setSize(1200, 768);

        mf.setVisible(true);

    }

}