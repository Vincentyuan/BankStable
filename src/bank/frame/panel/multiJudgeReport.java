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

		header = new JLabel("银行综合稳定性报告");
		bankl = new JLabel("    银行:");
		bank = new JLabel(multi.getBankname());
		yearl = new JLabel("    年份:");
		year = new JLabel(String.valueOf(multi.getYear()));
		shortJLabel1 = new JLabel("短期稳定性评级");
		shortJLabel = new JLabel(multi.getShortrate());
		depositl = new JLabel("综合存款量:");
		deposit = new JLabel(multi.getMultideposit());
		loanl = new JLabel("综合贷款量:");
		loan = new JLabel(multi.getMultiloan());
		judgerl = new JLabel("    评定人:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    时间:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		tendency = new JButton("趋势图");
		save = new JButton("保存");

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
							JOptionPane.showMessageDialog(getObject(), "保存成功");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(getObject(), "保存失败");
							e1.printStackTrace();
						}
					} else {
						JOptionPane
								.showMessageDialog(getObject(), "您已经保存过该记录了");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getObject(), "数据库连接问题");
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
