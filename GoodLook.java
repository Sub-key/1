package fljd;

import java.applet.*;
import javax.swing.border.*;    //��� border ����������� swing Ԫ���ı߿��࣬�������α߿�
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;

public class GoodLook extends Applet implements ActionListener,MouseListener,ItemListener{
	
	//��ʾ�ı�
	static JLabel Ltitle = new JLabel("����������");
	static JLabel Lw = new JLabel("�� ��ӭʹ�ø���������,��ѡ���������! ��");
	static JLabel LP = new JLabel("����");
	static JLabel LR = new JLabel("����");
	static JLabel LY = new JLabel("����");
	static JLabel LFA = new JLabel("�����ֵ");
	static JLabel LC = new JLabel("����");
	static JLabel LS = new JLabel("����");
	static JLabel LRP = new JLabel("���ڸ�Ϣ");
	static JLabel LM = new JLabel("����");
	static JLabel Lresult = new JLabel("���");
	//�����ı������
	static JTextField jtfC = new JTextField();
	static JTextField jtfS = new JTextField();
	static JTextField jtfFA = new JTextField();
	static JTextField jtfP = new JTextField();
	static JTextField jtfR = new JTextField();
	static JTextField jtfRP = new JTextField();
	static JTextField jtfY = new JTextField();
	static JTextField jtfM = new JTextField();
	static JTextField jtfresult = new JTextField();
	
	static Button b1 = new Button("ȷ��");
	static Button b2 =new Button("���");
	
	static Demo_test dt = new Demo_test();//�㷨
	
	Checkbox cb[] = new Checkbox[10]; //��ѡ��
	CheckboxGroup cb1 = new CheckboxGroup();
	
	public void init() {
		
		setLayout(null); 
		setBackground(new Color(0, 50, 100));	
	
		addMouseListener(this);//��������Ϊ����¼��Ľӿ���Ӧ��궯��
		
		add(Ltitle);
		Ltitle.setFont(new Font("΢���ź�", Font.CENTER_BASELINE, 30));
		Ltitle.setBounds(300, 0, 200,50);
		Ltitle.setForeground(Color.ORANGE);
		add(b1);
		b1.setBounds(350, 350, 50, 25);
		b1.addActionListener(this);	
		b1.setEnabled(true); //ʹ�ܿؼ�
		add(b2);
		b2.setBounds(350, 420, 50, 25);
		b2.addActionListener(this);	   
		b2.setEnabled(true);
		add(jtfC);
		jtfC.setBounds(70, 130, 220, 30);
		jtfC.addActionListener(this);
		jtfC.setEnabled(true);
		add(jtfP);
		jtfP.setBounds(70, 180, 220, 30);
		jtfP.addActionListener(this);
		jtfP.setEnabled(true);
		add(jtfY);
		jtfY.setBounds(70, 230, 220, 30);
		jtfY.addActionListener(this);	
		jtfY.setEnabled(true);
		add(jtfR);
		jtfR.setBounds(70, 280, 220, 30);
		jtfR.addActionListener(this);	
		jtfR.setEnabled(true);
		add(jtfM);
		jtfM.setBounds(70, 330, 220, 30);
		jtfM.setEnabled(true);
		add(jtfresult);
		jtfresult.setBounds(70, 430, 220, 30);
		jtfresult.setEditable(false);//���ò�������
		
		add(Lw);
		Lw.setBounds(415, 40, 300, 40);
		Lw.setFont(new Font("΢���ź�", Font.CENTER_BASELINE, 15));
		Lw.setForeground(Color.GREEN);
		add(LC);
		LC.setBounds(40, 130, 30, 30);
		LC.setForeground(Color.CYAN);
		add(LP);
		LP.setBounds(40, 180, 30, 30);
		LP.setForeground(Color.CYAN);
		add(LY);
		LY.setBounds(40, 230, 30, 30);
		LY.setForeground(Color.CYAN);
		add(LR);
		LR.setBounds(40, 280, 30, 30);	
		LR.setForeground(Color.CYAN);
		add(LM);
		LM.setBounds(40, 330, 30, 30);
		LM.setForeground(Color.CYAN);
		add(Lresult);
		Lresult.setBounds(40, 430, 30, 30);
		Lresult.setForeground(Color.RED);
		
		cb[0] = new Checkbox("������ֵ",cb1,false);
		cb[0].setBounds(0, 80, 100, 40);
		add(cb[0]);
		cb[0].addItemListener(this);
		
		cb[1] = new Checkbox("������ֵ",cb1,false);
		cb[1].setBounds(100, 80, 100, 40);
		add(cb[1]);
		cb[1].addItemListener(this);
		
		cb[2] = new Checkbox("����",cb1,false);
		cb[2].setBounds(200, 80, 100, 40);
		add(cb[2]);
		cb[2].addItemListener(this);
		
		cb[3] = new Checkbox("����",cb1,false);
		cb[3].setBounds(300, 80, 100, 40);
		add(cb[3]);
		cb[3].addItemListener(this);
		
		cb[4] = new Checkbox("����(��)",cb1,false);
		cb[4].setBounds(400, 80, 100, 40);
		add(cb[4]);
		cb[4].addItemListener(this);
		
		cb[5] = new Checkbox("�����ֵ",cb1,false);
		cb[5].setBounds(500, 80, 100, 40);
		add(cb[5]);
		cb[5].addItemListener(this);
		
		cb[6] = new Checkbox("���ڸ�Ϣ",cb1,false);
		cb[6].setBounds(600, 80, 100, 40);
		add(cb[6]);
		cb[6].addItemListener(this);
			
		this.resize(new Dimension(750,750));//���ڴ�С
		
	}
	public void delete(){
		jtfC.setText("");
		jtfP.setText("");
		jtfY.setText("");
		jtfR.setText("");
		jtfM.setText("");
		jtfresult.setText("");
	}
	
	public static void clean(){
		//����ı�����ı���ֵ
		jtfC.setVisible(true);
		jtfP.setVisible(true);
		jtfY.setVisible(true);
		jtfR.setVisible(true);
		jtfM.setVisible(true);
		jtfresult.setVisible(true);
		
		LC.setVisible(true);
		LP.setVisible(true);
		LY.setVisible(true);
		LR.setVisible(true);
		LM.setVisible(true);
		Lresult.setVisible(true);
		}
	

	@Override
	//item��״̬�����ı�ʱ�������¼�
	public void itemStateChanged(ItemEvent e) {
	
		if(cb[0].getState()) {
		GoodLook.clean();
		jtfC.setVisible(false);
		LC.setVisible(false);
		}
		else if(cb[1].getState()){
			GoodLook.clean();
			jtfM.setVisible(false);
			LM.setVisible(false);
			jtfC.setVisible(false);
			LC.setVisible(false);
		}
		else if(cb[2].getState()){
			GoodLook.clean();
			jtfP.setVisible(false);
			LP.setVisible(false);
			jtfM.setVisible(false);
			LM.setVisible(false);
			
		}
		else if(cb[3].getState()){
			GoodLook.clean();
			jtfY.setVisible(false);
			LY.setVisible(false);
			jtfM.setVisible(false);
			LM.setVisible(false);
			
		}
		else if(cb[4].getState()){
			GoodLook.clean();
			jtfR.setVisible(false);
			LR.setVisible(false);
			jtfM.setVisible(false);
			LM.setVisible(false);
		}
		else if(cb[5].getState()){
			GoodLook.clean();
			jtfC.setVisible(false);
			LC.setVisible(false);
			jtfM.setVisible(false);
			LM.setVisible(false);
		}
		else if(cb[6].getState()){
			GoodLook.clean();
			jtfC.setVisible(false);
			LC.setVisible(false);
			jtfM.setVisible(false);
			LM.setVisible(false);
		}	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	//��ʾ���
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==b1)
		{
			double P =0; //����
			double F=0; //������ֵ
			double D =0;//����
			int N =0;//����
			double R=0;//����
			int M=0;//����
			double fa = 0;//�����ֵ
			double rp = 0;//���ڸ�Ϣ
			
			
			try {
				
				jtfresult.setText("");//����ÿ�
				if(cb[0].getState()){//������ֵ
			
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
				    M = Integer.valueOf(jtfM.getText().toString()).intValue();
					F = dt.Compound(P, R, M, N);
				
					jtfresult.setText(Double.toString(F));	
					dmysql(P, N, R, M, F);
				}
				
				if(cb[1].getState()){//������ֵ
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();			 
					D = dt.Single(P, R, N);
					jtfresult.setText(Double.toString(D));	
					
				}
				if(cb[2].getState()){//����
					
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
				    F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					P = dt.BenJin(F, R, N);
					jtfresult.setText(Double.toString(P));	
					
				}
				if(cb[3].getState()){//����
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					N = (int) dt.NianXian(P, F, R);
					jtfresult.setText(Double.toString(N));
					
				}
				if(cb[4].getState()){//����
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();				
					R = dt.LiLv(P, F, N);
					jtfresult.setText(Double.toString(R));	
					
				}
				if(cb[5].getState()){//�����ֵ
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();				
					fa = dt.NRate(P, R, N);
					DecimalFormat df1 = new DecimalFormat("#.00");
					df1.format(fa);
					jtfresult.setText(Double.toString(fa));	
					
				}
				if(cb[6].getState()){//���ڸ�Ϣ
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
					rp = dt.pay(P, R, N);
					jtfresult.setText(Double.toString(rp));		
					
				}	
			} catch (Exception e2) {
				// TODO: handle exception
				//JOptionPane.showMessageDialog(null, "��������ȷ�����ݣ�", "��ʾ",
						//JOptionPane.ERROR_MESSAGE);
						System.out.println(e2);
			}
		}
		//����ÿ�
		if(e.getSource()==b2){
			delete();
					
		}
	
	}
	public void dmysql (double P,int N,double R,int M,double F) throws ClassNotFoundException, SQLException{
		String name = "root";
		String pwd = "123456";
		String url = "jdbc:mysql://localhost_3306/fulisql";
		try{
		Class.forName("com.mysql.jdbc.Driver");
	    //Class.forName("org.gjt.mm.mysql.Driver");
		}catch(ClassNotFoundException e)
		{	
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/fulisql", "root", "123456");
		Statement stmt = conn.createStatement();
		String sql1 = "insert into fuli(P,N,R,M,F) values(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		pstmt.setDouble(1,P);
		pstmt.setInt(2,N);
		pstmt.setDouble(3,R);
		pstmt.setInt(4,M);
		pstmt.setDouble(5,F);
		//pstmt.executeUpdate(); 
		pstmt.executeUpdate();
		stmt.close();
		conn.close();
		
	}
}