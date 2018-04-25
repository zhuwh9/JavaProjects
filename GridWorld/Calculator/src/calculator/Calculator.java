package calculator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class Calculator extends JFrame {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator frame = new Calculator();
		frame.setTitle("Easy Calculator");
		frame.setSize(400, 200);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.init();
		frame.setVisible(true);
	}
	/* record the operator selected */
	private String currentOperator = new String("");
	/* first and second operand, and the operator shown on grid */
	private JTextField operand1 = new JTextField();
	private JTextField operator = new JTextField();
	private JTextField operand2 = new JTextField();
	/* some symbol in the grids, and buttons */
	private JTextField equal = new JTextField("=");
	private JTextField result = new JTextField();
	private JButton add = new JButton("+");
	private JButton sub = new JButton("-");
	private JButton mul = new JButton("*");
	private JButton div = new JButton("/");
	private JButton ok = new JButton("OK");
	
	/* bind five listeners on five buttons*/
	public void bindEvent() {
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				 operator.setText("+");
				 currentOperator = "+";
			}
		});
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				operator.setText("-");
				currentOperator = "-";
			}
		});
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				operator.setText("*");
				currentOperator = "*";
			}
		});
		div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				operator.setText("/");
				currentOperator = "/";
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String s = "";
				if (s.equals(operand1.getText()) || s.equals(operand2.getText())) {
					System.out.println("Please input both numbers!");
				} else {
					double num1 = Double.parseDouble(operand1.getText());
					double num2 = Double.parseDouble(operand2.getText());
					double num;
					if (currentOperator == "+") {
						num = num1 + num2;
						result.setText(num +"");
					} else if (currentOperator == "-") {
						num = num1 - num2;
						result.setText(num +"");
					} else if (currentOperator == "*") {
						num = num1 * num2;
						result.setText(num + "");
					} else if (currentOperator == "/") {
						if (num2 != 0) {
							num = num1 / num2;
							result.setText(num + "");
						} else if (num2 == 0) {
							System.out.println("Divide zero error!");
						}
					}
				}
			}
		});
	}
	
	/* initial the layout and set property, then bind listeners */
	private void init() {
		operator.setEditable(false);
		equal.setEditable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,5, 10, 10));
		Container container = getContentPane();
		container.add(panel, BorderLayout.CENTER);
		operand1.setHorizontalAlignment(JTextField.CENTER);
		operator.setHorizontalAlignment(JTextField.CENTER);
		operand2.setHorizontalAlignment(JTextField.CENTER);
		equal.setHorizontalAlignment(JTextField.CENTER);
		result.setHorizontalAlignment(JTextField.CENTER);
		panel.add(operand1);
		panel.add(operator);
		panel.add(operand2);
		panel.add(equal);
		panel.add(result);
		panel.add(add);
		panel.add(sub);
		panel.add(mul);
		panel.add(div);
		panel.add(ok);
		bindEvent();
	}
}
