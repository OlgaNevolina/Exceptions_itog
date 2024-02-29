import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class PasswordVerifier extends JFrame
{
	private Container contents;
	private JTextArea instructions, brokenRules;
	private JLabel strength;
	private JTextField usrPWord;
	
	public PasswordVerifier()
	{
		super("Password Strength Tester");
		contents = getContentPane();
		contents.setLayout( new FlowLayout(FlowLayout.LEFT) );

		
		instructions = new JTextArea("Please type your desired password.\n"
			+ "\nYour Password must follow these conditions:\n"
			+ "Rule1: It must have at least 8 characters, and it must not contain any space character.\n"
			+ "Rule2: It must contain at least one special character, which is not a letter or digit.\n"
			+ "Rule3: It must contain at least one upper-case letter.\n"
			+ "Rule4: It must contain at least one lower-case letter.\n"
			+ "Rule5: It must contain at least one digit.");
		instructions.setOpaque(false);
		instructions.setEditable(false);
		
		strength = new JLabel("");
		
		brokenRules = new JTextArea("");
		brokenRules.setEditable(false);
		brokenRules.setOpaque(false);
		brokenRules.setForeground( Color.RED);
		
		usrPWord = new JTextField( "", 15 );
		
		contents.add( instructions );
		contents.add( usrPWord );
		contents.add( strength );
		contents.add( brokenRules );
		
		TextFieldHandler tfh = new TextFieldHandler();
		
		usrPWord.addActionListener( tfh );	
		
		setSize( 600, 300 );
		setVisible( true );	
	}
	
	private class TextFieldHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			String password = usrPWord.getText();
			int length, spChar, lowCase, upCase, digit;
			length = lengthChecker(password);
			spChar = specialCharacter(password);
			upCase = upperCase(password);
			lowCase = lowerCase(password);
			digit = digitize(password);
			
			int pStrength = 0;
			pStrength = length + spChar + upCase + lowCase + digit;
			
	
			strength.setText("Your password has a strength of: " + pStrength
				+ " out of 5.");
			
			if (pStrength == 5)
			{
				brokenRules.setForeground( Color.BLUE );
				brokenRules.setText("Congratulations, you are a password pro!");
			}

			else if (pStrength == 0 )
			{
				brokenRules.setForeground( Color.RED );
				brokenRules.setText("Are you serious?! you violated every single rule!");
			}
			else
			{
				brokenRules.setForeground( Color.RED );
				brokenRules.setText("Your password violated the following rule(s):\n");
				
				if (length == 0)
				{
					brokenRules.append("  Rule1: It must have at least 8 characters, "
						+ "and it must not contain any space character.\n");
				}
				if (spChar == 0)
				{
					brokenRules.append("  Rule2: It must contain at least one special character, "
						+ "which is not a letter or digit.\n");
				}
				if (upCase == 0)
					brokenRules.append("  Rule3: It must contain at least one upper-case letter.\n");
				if (lowCase == 0)
					brokenRules.append("  Rule4: It must contain at least one lower-case letter.\n");
				if (digit == 0)
					brokenRules.append("  Rule5: It must contain at least one digit.\n");
			}	
		}
	}
	
	public static void main( String [] args )
	{
		PasswordVerifier passVerifier = new PasswordVerifier( );
		passVerifier.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	
	public static int lengthChecker(String s)
	{
		if (s.contains(" "))
		{
			return 0;
		}
		else 
		{
			if (s.length() >= 8)
				return 1;
			else
				return 0;
		}	
	}
	
	public static int specialCharacter(String s)
	{
		Pattern spChar = Pattern.compile("[^A-Za-z0-9 ]");
		Matcher m = spChar.matcher(s);
		if (m.find())
			return 1;
		else 
			return 0;
		
	}	
	
	public static int upperCase(String s)
	{
		Pattern upper = Pattern.compile("[A-Z]");
		Matcher m = upper.matcher(s);
		if (m.find())
			return 1;
		else 
			return 0;
	}
	
	public static int lowerCase(String s)
	{
		Pattern lower = Pattern.compile("[a-z]");
		Matcher m = lower.matcher(s);
		if (m.find())
			return 1;
		else
			return 0;
	}
	
	public static int digitize(String s)
	{
		Pattern digit = Pattern.compile("[0-9]");
		Matcher m = digit.matcher(s);
		if (m.find())
			return 1;
		else
			return 0;
	}
}
