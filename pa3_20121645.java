import java.awt.*;
import java.awt.event.*;

public class pa3_20121645 extends Frame implements ActionListener {
	private Frame f; 
	private Font font = new Font("바탕체", Font.BOLD, 15);
	private Font font2 = new Font("바탕체", Font.PLAIN, 20);
	private boolean double_dot = false;	// for double dot check.
	private boolean is_first_input = true;	// for checking input first or not.
	private TextArea Output;
	//double mem = 0;
	String mem = "0";
	double calcu_mem = 0;
	public enum operand {
		NONE, PLUS, MINUS, MULTIPLY, DIVIDE, SQUARE, ROOT, PERCENT
	}
	operand OP = operand.NONE;
	
	public pa3_20121645() {
		// frame initialize with BorderLayout.
		f = new Frame("20121645 Calculator");
		f.setResizable(false); 					// resize disable.
		f.setBackground(new Color(25, 25, 25)); // background color setting.
		f.setLayout(new BorderLayout(5,0));		// set interval two panels.
		
		// Output Panel Part -------------------------------------------------------------------------------
		Panel p1 = new Panel();
		
		Output = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		Output.setBackground(new Color(240, 240, 240));
		Output.setText(mem);
		Output.setFocusable(false);
		Output.setFont(font);
		p1.add(Output);
		f.add(p1, BorderLayout.NORTH);
		
		// Left Button Panel Part ---------------------------------------------------------------------------
		
		// button initialize and action and font setting.
		Button root = new Button("root");		root.addActionListener(this);		root.setFont(font2);
		Button square = new Button("x^2");		square.addActionListener(this);		square.setFont(font2);
		Button percent = new Button("%");		percent.addActionListener(this);	percent.setFont(font2);
		Button del = new Button("Del.");		del.addActionListener(this);		del.setFont(font2);
		Button one = new Button("1");			one.addActionListener(this);		one.setFont(font2);
		Button two = new Button("2");			two.addActionListener(this);		two.setFont(font2);
		Button three = new Button("3");			three.addActionListener(this);		three.setFont(font2);
		Button four = new Button("4");			four.addActionListener(this);		four.setFont(font2);
		Button five = new Button("5");			five.addActionListener(this);		five.setFont(font2);
		Button six = new Button("6");			six.addActionListener(this);		six.setFont(font2);
		Button seven = new Button("7");			seven.addActionListener(this);		seven.setFont(font2);
		Button eight = new Button("8");			eight.addActionListener(this);		eight.setFont(font2);
		Button nine = new Button("9");			nine.addActionListener(this);		nine.setFont(font2);
		Button zero = new Button("0");			zero.addActionListener(this);		zero.setFont(font2);
		Button plusminus = new Button("+/-");	plusminus.addActionListener(this);	plusminus.setFont(font2);
		Button dot = new Button(".");			dot.addActionListener(this);		dot.setFont(font2);
		Button divide = new Button("/");		divide.addActionListener(this);		divide.setFont(font2);
		Button multiply = new Button("*");		multiply.addActionListener(this);	multiply.setFont(font2);
		Button plus = new Button("+");			plus.addActionListener(this);		plus.setFont(font2);
		Button minus = new Button("-");			minus.addActionListener(this);		minus.setFont(font2);
		
		// button size setting and number button color setting.
		one.setPreferredSize(new Dimension(80,100));
		one.setForeground(Color.WHITE);		one.setBackground(Color.BLACK);
		two.setForeground(Color.WHITE);		two.setBackground(Color.BLACK);
		three.setForeground(Color.WHITE);	three.setBackground(Color.BLACK);
		four.setForeground(Color.WHITE);	four.setBackground(Color.BLACK);
		five.setForeground(Color.WHITE);	five.setBackground(Color.BLACK);
		six.setForeground(Color.WHITE);		six.setBackground(Color.BLACK);
		seven.setForeground(Color.WHITE);	seven.setBackground(Color.BLACK);
		eight.setForeground(Color.WHITE);	eight.setBackground(Color.BLACK);
		nine.setForeground(Color.WHITE);	nine.setBackground(Color.BLACK);
		zero.setForeground(Color.WHITE);	zero.setBackground(Color.BLACK);

		
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(5,4));
		p2.add(root); p2.add(square); p2.add(percent); p2.add(del);
		p2.add(seven); p2.add(eight); p2.add(nine); p2.add(divide);
		p2.add(four); p2.add(five); p2.add(six); p2.add(multiply);
		p2.add(one); p2.add(two); p2.add(three); p2.add(plus);
		p2.add(plusminus); p2.add(zero); p2.add(dot); p2.add(minus);
		
		f.add(p2, BorderLayout.WEST);
		
		// Right Button Panel Part --------------------------------------------------------------------------
		Button C = new Button("C");		C.addActionListener(this);		C.setFont(font2);
		Button equal = new Button("=");	equal.addActionListener(this);	equal.setFont(font2);
		
		Panel p3 = new Panel();
		
		p3.setLayout(new GridLayout(2,1));
		p3.add(C); p3.add(equal);
		f.add(p3, BorderLayout.CENTER);
		
		f.pack();
		
		
		f.setSize(400, 550); // size of the frame : 400 * 550(pixel)
		WindowDestroyer listener = new WindowDestroyer(); // window destroy button
		f.addWindowListener(listener);
		f.setResizable(false);//크기 조절 기능
		f.setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent e)
	{
		double temp; // temp variable for string to double and string length.
		String command = e.getActionCommand();
		
		// number input part---------------------------------------------------
		if(command.equals("0")) {
			if(!(mem.equals("0")))	 input_is_numbers(0);
		}
		else if(command.equals("1")) input_is_numbers(1);
		else if(command.equals("2")) input_is_numbers(2);
		else if(command.equals("3")) input_is_numbers(3);
		else if(command.equals("4")) input_is_numbers(4);
		else if(command.equals("5")) input_is_numbers(5);
		else if(command.equals("6")) input_is_numbers(6);
		else if(command.equals("7")) input_is_numbers(7);
		else if(command.equals("8")) input_is_numbers(8);
		else if(command.equals("9")) input_is_numbers(9);
		else if (command.equals(".")) {	// insert dot. and flag setting.
			if (!double_dot) {
				System.out.println(double_dot);
				mem += ".";
				double_dot = true;
				is_first_input = false;
			}
		}
		
		// operand input part---------------------------------------------------
		else {
			if (command.equals("C")) {
				mem = "0";
				calcu_mem = 0;
				double_dot = false;
				OP = operand.NONE;
			}
			// use math class and get root of the data.
			else if (command.equals("root")) {
				calcu();

				temp = Double.parseDouble(mem); // string -> double
				temp = Math.sqrt(temp);
				mem = Double.toString(temp); // double -> string
				OP = operand.ROOT;
			}
			// get rid of last integer
			else if (command.equals("Del.")) {
				temp = mem.length();
				if (temp > 1)
					mem = mem.substring(0, (int) temp - 1);
				else if (temp == 1)
					mem = "0";
			}
			// get square of the data.
			else if (command.equals("x^2")) {
				calcu();

				temp = Double.parseDouble(mem);
				temp *= temp;
				mem = Double.toString(temp);
				OP = operand.SQUARE;
			}
			// get percentage of the data.
			else if (command.equals("%")) {
				calcu();

				temp = Double.parseDouble(mem);
				temp /= 100;
				mem = Double.toString(temp);
				OP = operand.PERCENT;
			}
			// transe +/- of the data.
			else if (command.equals("+/-")) {
				temp = Double.parseDouble(mem);
				temp *= -1;
				mem = Double.toString(temp);
			}
			// operand +.
			else if (command.equals("+")) {
				calcu();
				temp = Double.parseDouble(mem);
				calcu_mem = temp;
				OP = operand.PLUS;
			}
			// operand -
			else if (command.equals("-")) {
				calcu();
				temp = Double.parseDouble(mem);
				calcu_mem = temp;
				OP = operand.MINUS;
			}
			// operand *
			else if (command.equals("*")) {
				calcu();
				temp = Double.parseDouble(mem);
				calcu_mem = temp;
				OP = operand.MULTIPLY;
			}
			// operand /
			else if (command.equals("/")) {
				calcu();
				temp = Double.parseDouble(mem);
				calcu_mem = temp;
				OP = operand.DIVIDE;
			}
			// operand =
			else if (command.equals("=")) {
				calcu();
				// if last operand is +

				double_dot = true;
				OP = operand.NONE;
				calcu_mem = 0;
			}
			is_first_input = true;
		}
		
		// update the result.
		if(is_first_input) { // if Result output can be transform to integer possible, transform. and if not result (Inputting numbers), print double.
			temp = Double.parseDouble(mem);
			if(temp == (int)temp) mem = Integer.toString((int)temp);
		}
		Output.setText(mem);
	}
	
	// calculate input data.
	public void calcu() {
		double temp; // temp value.
		if(OP == operand.PLUS) {
			temp = Double.parseDouble(mem);
			temp = calcu_mem+temp;
			mem = Double.toString(temp);
			
			OP = operand.NONE;
		}
		// if last operand is -
		else if(OP == operand.MINUS) {
			temp = Double.parseDouble(mem);
			temp = calcu_mem-temp;
			mem = Double.toString(temp);
			
			OP = operand.NONE;
		}
		// if last operand is *
		else if(OP == operand.MULTIPLY) {
			temp = Double.parseDouble(mem);
			temp = calcu_mem*temp;
			mem = Double.toString(temp);
			
			OP = operand.NONE;
		}
		// if last operand is /
		else if(OP == operand.DIVIDE) {
			temp = Double.parseDouble(mem);
			temp = calcu_mem/temp;
			mem = Double.toString(temp);
			
			//OP = operand.NONE;
		}
	}
	
	// case input is numbers. process that.
	public void input_is_numbers(int input) {
		if(is_first_input)	{ // if operand has not been done, add more input data.
			mem = Integer.toString(input);
			is_first_input = false;
			double_dot = false;
		}
		else { // if operand has done, refresh new input. operanded flag and double_dot flag initailize.
			mem += input;
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// call constructor.
		pa3_20121645 f = new pa3_20121645();
	}
	
	
}

