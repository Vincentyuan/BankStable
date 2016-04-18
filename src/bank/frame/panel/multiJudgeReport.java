package bank.frame.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.entity.JudgeRecord;
import bank.frame.ClientContext;
import bank.frame.MainFrame;

public class multiJudgeReport extends workPanel {

	private JLabel header, bankl, bank, yearl, year, shortJLabel1, shortJLabel,
			depositl, deposit, loanl, loan, judgerl, judger, timel, time;

	private JButton tendency, save;

	private JPanel head, mid, bottom, white;

	private JudgeRecord multi;

	public multiJudgeReport(JudgeRecord multi) {
		// TODO Auto-generated constructor stub
		// this.frame=frame;
		this.multi = multi;

		header = new JLabel("�����ۺ��ȶ��Ա���");
		bankl = new JLabel("    ����:");
		bank = new JLabel(multi.getBankname());
		yearl = new JLabel("    ���:");
		year = new JLabel(String.valueOf(multi.getYear()));
		shortJLabel1 = new JLabel("�����ȶ�������");
		shortJLabel = new JLabel(multi.getShortrate());
		depositl = new JLabel("�ۺϴ����:");
		deposit = new JLabel(multi.getMultideposit());
		loanl = new JLabel("�ۺϴ�����:");
		loan = new JLabel(multi.getMultiloan());
		judgerl = new JLabel("    ������:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    ʱ��:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		tendency = new JButton("����ͼ");
		save = new JButton("����");

		this.setOpaque(false);

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		white = new JPanel();
		white.setOpaque(false);

	}

	public void init() {
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new GridLayout(4, 1));
		this.add(head);
		this.add(mid);
		this.add(bottom);
		this.add(white);

		head.add(header);

		mid.setLayout(new GridLayout(4, 4));
		mid.add(bankl);
		mid.add(bank);
		mid.add(yearl);
		mid.add(year);
		mid.add(shortJLabel1);
		mid.add(shortJLabel);
		mid.add(new JLabel());
		mid.add(new JLabel());
		mid.add(depositl);
		mid.add(deposit);
		mid.add(loanl);
		mid.add(loan);
		mid.add(judgerl);
		mid.add(judger);
		mid.add(timel);
		mid.add(time);

	//	bottom.add(tendency);
		bottom.add(save);
		addListener();
	}

	public void addListener() {
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JudgeRecordDao dao = new JudgeRecordDaoImpl();
				try {
					if (!checkExist()) {
						try {
							dao.insertRecord(toJudgeRecord());
							JOptionPane.showMessageDialog(getObject(), "����ɹ�");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(getObject(), "����ʧ��");
							e1.printStackTrace();
						}
					} else {
						JOptionPane
								.showMessageDialog(getObject(), "���Ѿ�������ü�¼��");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getObject(), "���ݿ���������");
				}

			}
		});
	}

	public JudgeRecord toJudgeRecord() {
		return this.multi;
	}

	public Component getObject() {
		return this;
	}

	public boolean checkExist() throws SQLException {
		JudgeRecordDao tmpDao = new JudgeRecordDaoImpl();

		return tmpDao
				.checkExist(multi.getBankname(), multi.getYear(), "multi");
	}

}
