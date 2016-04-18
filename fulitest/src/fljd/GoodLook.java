package fljd;

import java.applet.*;
import javax.swing.border.*;    //这个 border 包里包含的是 swing 元件的边框类，界面修饰边框。
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
	
	//显示文本
	static JLabel Ltitle = new JLabel("复利计算器");
	static JLabel Lw = new JLabel("— 欢迎使用复利计算器,请选择计算类型! —");
	static JLabel LP = new JLabel("本金");
	static JLabel LR = new JLabel("利率");
	static JLabel LY = new JLabel("年限");
	static JLabel LFA = new JLabel("年金终值");
	static JLabel LC = new JLabel("复利");
	static JLabel LS = new JLabel("单利");
	static JLabel LRP = new JLabel("定期付息");
	static JLabel LM = new JLabel("次数");
	static JLabel Lresult = new JLabel("结果");
	//单行文本输入框
	static JTextField jtfC = new JTextField();
	static JTextField jtfS = new JTextField();
	static JTextField jtfFA = new JTextField();
	static JTextField jtfP = new JTextField();
	static JTextField jtfR = new JTextField();
	static JTextField jtfRP = new JTextField();
	static JTextField jtfY = new JTextField();
	static JTextField jtfM = new JTextField();
	static JTextField jtfresult = new JTextField();
	
	static Button b1 = new Button("确定");
	static Button b2 =new Button("清除");
	
	static Demo_test dt = new Demo_test();//算法
	
	Checkbox cb[] = new Checkbox[10]; //复选框
	CheckboxGroup cb1 = new CheckboxGroup();
	
	public void init() {
		
		setLayout(null); 
		setBackground(new Color(0, 50, 100));	
	
		addMouseListener(this);//将本类作为鼠标事件的接口响应鼠标动作
		
		add(Ltitle);
		Ltitle.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 30));
		Ltitle.setBounds(300, 0, 200,50);
		Ltitle.setForeground(Color.ORANGE);
		add(b1);
		b1.setBounds(350, 350, 50, 25);
		b1.addActionListener(this);	
		b1.setEnabled(true); //使能控件
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
		jtfresult.setEditable(false);//禁用操作功能
		
		add(Lw);
		Lw.setBounds(415, 40, 300, 40);
		Lw.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
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
		
		cb[0] = new Checkbox("复利终值",cb1,false);
		cb[0].setBounds(0, 80, 100, 40);
		add(cb[0]);
		cb[0].addItemListener(this);
		
		cb[1] = new Checkbox("单利终值",cb1,false);
		cb[1].setBounds(100, 80, 100, 40);
		add(cb[1]);
		cb[1].addItemListener(this);
		
		cb[2] = new Checkbox("本金",cb1,false);
		cb[2].setBounds(200, 80, 100, 40);
		add(cb[2]);
		cb[2].addItemListener(this);
		
		cb[3] = new Checkbox("年限",cb1,false);
		cb[3].setBounds(300, 80, 100, 40);
		add(cb[3]);
		cb[3].addItemListener(this);
		
		cb[4] = new Checkbox("利率(年)",cb1,false);
		cb[4].setBounds(400, 80, 100, 40);
		add(cb[4]);
		cb[4].addItemListener(this);
		
		cb[5] = new Checkbox("年金终值",cb1,false);
		cb[5].setBounds(500, 80, 100, 40);
		add(cb[5]);
		cb[5].addItemListener(this);
		
		cb[6] = new Checkbox("定期付息",cb1,false);
		cb[6].setBounds(600, 80, 100, 40);
		add(cb[6]);
		cb[6].addItemListener(this);
			
		this.resize(new Dimension(750,750));//窗口大小
		
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
		//清除文本框和文本的值
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
	//item的状态发生改变时触发该事件
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	//显示结果
	public void actionPerformed(ActionEvent e){

		if(e.getSource()==b1)
		{
			double P =0; //本金
			double F=0; //复利终值
			double D =0;//单利
			int N =0;//年限
			double R=0;//利率
			int M=0;//次数
			double fa = 0;//年金终值
			double rp = 0;//定期付息
			
			
			try {
				
				jtfresult.setText("");//清除置空
				if(cb[0].getState()){//复利终值
			
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
				    M = Integer.valueOf(jtfM.getText().toString()).intValue();
					F = dt.Compound(P, R, M, N);
				
					jtfresult.setText(Double.toString(F));	
					dmysql(P, N, R, M, F);
				}
				
				if(cb[1].getState()){//单利终值
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();			 
					D = dt.Single(P, R, N);
					jtfresult.setText(Double.toString(D));	
					
				}
				if(cb[2].getState()){//本金
					
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
				    F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					P = dt.BenJin(F, R, N);
					jtfresult.setText(Double.toString(P));	
					
				}
				if(cb[3].getState()){//年限
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					N = (int) dt.NianXian(P, F, R);
					jtfresult.setText(Double.toString(N));
					
				}
				if(cb[4].getState()){//利率
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					F = Double.valueOf(jtfC.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();				
					R = dt.LiLv(P, F, N);
					jtfresult.setText(Double.toString(R));	
					
				}
				if(cb[5].getState()){//年金终值
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();				
					fa = dt.NRate(P, R, N);
					DecimalFormat df1 = new DecimalFormat("#.00");
					df1.format(fa);
					jtfresult.setText(Double.toString(fa));	
					
				}
				if(cb[6].getState()){//定期付息
					P=Double.valueOf(jtfP.getText().toString()).doubleValue();
					R=Double.valueOf(jtfR.getText().toString()).doubleValue();
					N = Integer.valueOf(jtfY.getText().toString()).intValue();
					rp = dt.pay(P, R, N);
					jtfresult.setText(Double.toString(rp));		
					
				}	
			} catch (Exception e2) {
				// TODO: handle exception
				//JOptionPane.showMessageDialog(null, "请输入正确的数据！", "提示",
						//JOptionPane.ERROR_MESSAGE);
						System.out.println(e2);
			}
		}
		//清除置空
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