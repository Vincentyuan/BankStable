package bank.frame.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import bank.frame.BaseFrame;
import bank.frame.MyPanel;

public class longJudgeTendency extends BaseFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem rangeDeposit, gradeDeposit, resultDeposit;// �������ȼ�����һ�������
	private JMenuItem rangeLoan, gradeLoan, resultLoan;
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel������panel����ͬ�İ�ť���²�ͬ��panel��

	public longJudgeTendency() {
		// TODO Auto-generated constructor stub
	}

	public longJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();

		rangeDeposit = new JMenuItem("����ȶ�������");
		gradeDeposit = new JMenuItem("����ȶ��Եȼ�");
		resultDeposit = new JMenuItem("����ȶ���");
		rangeLoan = new JMenuItem("�����ȶ�������");
		gradeLoan = new JMenuItem("�����ȶ��Եȼ�");
		resultLoan = new JMenuItem("�����ȶ���");

		work = new MyPanel[6];
		// System.out.println("test/"+bankname+"���ڴ���һ��ֵ"+".jpg");
		work[0] = new MyPanel("test/" + bankname + "���ڴ���ȶ���" + ".jpg");
		work[1] = new MyPanel("test/" + bankname + "���ڴ����ȶ���" + ".jpg");
		work[2] = new MyPanel("test/" + bankname + "���ڴ���ȶ�������" + ".jpg");
		work[3] = new MyPanel("test/" + bankname + "���ڴ����ȶ�������" + ".jpg");
		work[4] = new MyPanel("test/" + bankname + "���ڴ���ȶ��Եȼ�" + ".jpg");
		work[5] = new MyPanel("test/" + bankname + "���ڴ����ȶ��Եȼ�" + ".jpg");
		
	

	}

	public void init(int center) {

		frame.setTitle("�����ȶ���");
		
		

		menuBar.add(rangeDeposit);
		menuBar.add(gradeDeposit);
		menuBar.add(resultDeposit);
		menuBar.add(rangeLoan);
		menuBar.add(gradeLoan);
		menuBar.add(resultLoan);

		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		// JPanel initPanel=new MyPanel("test/"+bankname+"��һ��ֵ"+".jpg");
		container.add(work[center], BorderLayout.CENTER);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 700;
		int height = 600;
		
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2,
				700,400);
		
		frame.setSize(720, 420);
		frame.setVisible(true);

		// container.add(panel,BorderLayout.CENTER);
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("init method has been called");
	
		init(2);
		addActionListener();
	}

	public void addActionListener() {
		resultDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(0);
				frame.revalidate();
				frame.repaint();

			}
		});
		resultLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);

				init(1);
			}
		});
		rangeDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);

				init(2);
			}
		});
		rangeLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(3);
			}
		});
		gradeDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(4);
			}
		});
		gradeLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(5);
			}
		});

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		longJudgeTendency s = new longJudgeTendency("�й���������",
				String.valueOf(2006));
		s.init();
	}

	
}
