package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class baseball extends JFrame implements ActionListener
{
	TTS TTS = new TTS();
	BGM2 bgm2 = new BGM2();
	
	ImageIcon icon, exit, answer, newgame, clear, nameicon;
	JButton button[] = new JButton [4];
	JPanel set1;
	JLabel label, name;
	JTextField field = new JTextField(30);
	JTextArea area;
	JScrollPane scroll;
	
	int cnt, ballCnt, strikeCnt;
	int [] baseBallRandNum, baseBallAnswer;
	
	SecureRandom rand;
	
	public baseball()
	{
		baseBallRandNum = new int[4];
		baseBallAnswer = new int[4];
		cnt = ballCnt = strikeCnt = 0;
		rand = new SecureRandom();
		
		icon= new ImageIcon("./BaseBall2.jpg");
		exit = new ImageIcon("./exit.png");
		newgame = new ImageIcon("./newgame.png");
		answer = new ImageIcon("./answer.png");
		clear = new ImageIcon("./clear.png");
		nameicon = new ImageIcon("./name.png");
		
		JPanel background = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);				
			}
		};
		background.setLayout(null);
		
		button[0] = new JButton(newgame);
		button[1] = new JButton(clear);
		button[2] = new JButton(answer);
		button[3] = new JButton(exit);
		button[0].setBounds(1050, 80, 200, 100);
		button[1].setBounds(1050, 200, 200, 100);
		button[2].setBounds(1050, 320, 200, 100);
		button[3].setBounds(1050, 440, 200, 150);
		
		for(int i=0; i<button.length; i++)
		{
			button[i].setBorderPainted(false);
			button[i].setFocusPainted(false);
			button[i].setContentAreaFilled(false);
			background.add(button[i]);
		}
		
		label = new JLabel("  input :");
		name = new JLabel(nameicon);
		label.setOpaque(true);
		label.setBackground(Color.green);
		label.setFont(new Font("Viner Hand ITC", Font.PLAIN, 17));
		label.setBounds(150, 600, 70, 50);
		name.setBounds(350, 20, 600, 100);
		
		field.setEditable(false);
		field.setForeground(Color.black);
		field.setBackground(Color.white);
		field.setFont(new Font("바탕", Font.BOLD, 17));
		field.setBounds(220, 600, 800, 50);
		
		area = new JTextArea();
		area.setEditable(false);
		area.setOpaque(false);
		area.setForeground(Color.white);
		area.setBounds(150, 130, 850, 400);
		
		scroll = new JScrollPane(area);
		scroll.setBorder(new BevelBorder(BevelBorder.LOWERED));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBounds(150, 130, 850, 400);
		
		
		
		
		background.add(scroll);
		background.add(name);
		background.add(label);
		background.add(field);
		
		setContentPane(background);
		defaultState();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		if (obj instanceof JButton)
			customButtonAction(obj);
		else
			customTextAction(obj);
	}
	
	public void defaultState()
	{
		for(int i=0; i<button.length; i++)
		{
			button[i].addActionListener(this);
		}
		
		field.addActionListener(this);
		button[1].setEnabled(false);
		button[2].setEnabled(false);
	}

	public void customButtonAction(Object obj) {
		if (obj == button[0]) {
			field.setEditable(true);
			button[1].setEnabled(true);
			button[2].setEnabled(true);
			cnt = 0;
			for (int i = 0; i < baseBallRandNum.length; i++) {
				int randNum = rand.nextInt(10);
				if (i == 1 && randNum == baseBallRandNum[i - 1]
					|| i == 2 && randNum == baseBallRandNum[i - 1]
					|| i == 2 && randNum == baseBallRandNum[i - 2]
					|| i == 3 && randNum == baseBallRandNum[i - 1]
					|| i == 3 && randNum == baseBallRandNum[i - 2]
					|| i == 3 && randNum == baseBallRandNum[i - 3]) {
					--i;
					continue;
				}
				baseBallRandNum[i] = randNum;
			}
		} else if (obj == button[1]) {
			button[1].setEnabled(false);
			area.setText("");
		} else if (obj == button[2]) {
			area.append("Answer is " + baseBallRandNum[0] + baseBallRandNum[1] + baseBallRandNum[2] + baseBallRandNum[3]+"!!\n");
			TTS.TTS("Answer is " + baseBallAnswer[0] + baseBallAnswer[1] + baseBallAnswer[2] + baseBallAnswer[3]);
			TTS.TTS("Try it again");
			button[2].setEnabled(false);
			field.setEditable(false);
			return;
		} else if (obj == button[3]) {
			System.exit(0);
		}
		area.setFont(new Font("맑은고딕", Font.BOLD, 22));
	}

	public void customTextAction(Object obj) {
		if (obj == field) {
			button[1].setEnabled(true);
			ballCnt = 0;
			strikeCnt = 0;

			String answerStr = field.getText();
			if (!answerStr.matches("[\\d]{4}")) {
				JOptionPane.showMessageDialog(this, "네자리 숫자를 입력해주세요.");
				field.setText("");
				return;
			}

			int answerNum = Integer.parseInt(answerStr);
			baseBallAnswer[0] = answerNum / 1000;
			baseBallAnswer[1] = (answerNum % 1000) / 100;
			baseBallAnswer[2] = (answerNum % 100) / 10;
			baseBallAnswer[3] = answerNum % 10;

			for (int i = 0; i < baseBallAnswer.length - 1; i++) {
				for (int j = i + 1; j < baseBallAnswer.length; j++) {
					if (baseBallAnswer[i] == baseBallAnswer[j]) {
						JOptionPane.showMessageDialog(this, "중복되지 않는 숫자를 입력해주세요.");
						field.setText(null);
						return;
					}
				}
			}

			cnt++;
			
			for (int i = 0; i < baseBallRandNum.length; i++) {
				for (int j = 0; j < baseBallAnswer.length; j++) {
					if (baseBallRandNum[i] == baseBallAnswer[j]) {
						if (i == j)
							strikeCnt++;
						else
							ballCnt++;
					}
				}
			}

			if (strikeCnt == 4) {
				area.append("Congratulation Homerun!\n");
				area.append("Answer is " + baseBallAnswer[0] + baseBallAnswer[1] + baseBallAnswer[2] + baseBallAnswer[3] +"!!\n");
				TTS.TTS("Congratulation Homerun!");
				TTS.TTS("Answer is " + baseBallAnswer[0] + baseBallAnswer[1] + baseBallAnswer[2] + baseBallAnswer[3]);
				
				bgm2.bgm2();			
				
				field.setEditable(false);
				button[2].setEnabled(false);
				field.setText("");
				return;
			}
			area.append(cnt + "회 : " + baseBallAnswer[0] + baseBallAnswer[1] + baseBallAnswer[2] + baseBallAnswer[3]);
			area.append(" : " + strikeCnt + "Strike " + ballCnt + "Ball\n");
			area.setFont(new Font("맑은고딕", Font.BOLD, 22));
			TTS.TTS(strikeCnt + "Strike" + ballCnt + "Ball");

			field.setText("");
		}
	}
}
