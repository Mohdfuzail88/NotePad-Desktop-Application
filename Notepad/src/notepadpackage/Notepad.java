package notepadpackage;
import java.awt.event.*;

import java.io.*;
import javax.swing.*;
public class Notepad extends JFileChooser implements ActionListener  {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JMenuItem jmt1;
	static JMenuItem jmt3;
	static JMenuItem jmt4;
	static JFrame jf;
	static JMenuBar jmb;
	static JTextArea jta;
	static JFileChooser jf1;
	static boolean isChanged=false;
 public static void main(String[] args) {
	
	 try {
UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
} catch (Exception e) {
	// TODO Auto-generated catch block
		e.printStackTrace();} 
jf=new JFrame("Untitled-Notepad");
ImageIcon i=new ImageIcon("C:\\Users\\CBA\\eclipse-workspace\\Notepad\\notepad.png");
jf.setIconImage(i.getImage());
 jta=new JTextArea();
jf.add(jta);
 jmb=new JMenuBar();
JMenu jm1=new JMenu("File");
JMenu jm2=new JMenu("Edit");
jmt1=new JMenuItem("new window");
JMenuItem jmt2=new JMenuItem("exit");
jmt3=new JMenuItem("save");
jmt4=new JMenuItem("open");

jm1.add(jmt1);
jm1.addSeparator(); 
jm1.add(jmt2);
jm1.addSeparator();
jm1.add(jmt3);
jm1.addSeparator();
jm1.add(jmt4);
jmb.add(jm1);
jmb.add(jm2);
jf.setJMenuBar(jmb);
jf.add(new JScrollPane(jta));
Notepad d=new Notepad();
jmt1.addActionListener(d);
jmt2.addActionListener(d);
jmt3.addActionListener(d);
jmt4.addActionListener(d);
jta.addKeyListener(new KeyAdapter() {
	public void keyTyped(KeyEvent e) {
		isChanged=true;
	}
});
 jf.setSize(340,540);
 jf.setLocationRelativeTo(null);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    jf.addWindowListener(new WindowAdapter() {
		
		
		@Override
		public void windowClosing(WindowEvent e) {
		    if(isChanged) {
			int result=JOptionPane.showConfirmDialog(jf, "Do you want to save changes untitled","Notpad",JOptionPane.YES_NO_CANCEL_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				saveLogic();
				saveFile();
				System.exit(0);				
			}
			else if(result==JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
		    else {
			System.exit(0);	
			}
		}
		
	
	});
    }
 
 public static void saveFile() {
	 int result= jf1.showSaveDialog(jf);
	 if(result==JFileChooser.APPROVE_OPTION) {
	 	File f=jf1.getSelectedFile();
	 	try {
	 		FileWriter fw=new FileWriter(f);
	 		fw.write(jta.getText());
	 		
	 		fw.close();
	 		String s3=f.getName();
	 		String s4=s3;
	 		if(s3.contains(".")) {
	 s4=s3.substring(0,s3.lastIndexOf("."));}
	 jf.setTitle(s4+"-NotePad");
	JOptionPane.showMessageDialog(jf, "your file saved");

	 } catch (IOException e1) {
	 	JOptionPane.showMessageDialog(jf, "your file note saved");
	 		}
	 	isChanged=false;
	 }
	 
	 
 }
 public static void openFile() {
	 JFileChooser jf2=new JFileChooser();
	 int result= jf2.showOpenDialog(jf);
	 if(result==JFileChooser.APPROVE_OPTION) {
	 	File f=jf2.getSelectedFile();
	 	try {
	 		BufferedReader fw=new BufferedReader( new FileReader(f));
	 		String line;
	 		jta.setText("");
	 		while((line=fw.readLine())!=null) {
	 			jta.append(line);
	 		}
	 		
	 		jta.append(line);
	 		fw.close();
	 		String s3=f.getName();
	 		String s4=s3;
	 		if(s3.contains(".")) {
	 s4=s3.substring(0,s3.lastIndexOf("."));}
	 jf.setTitle(s4+"-NotePad");
	
	 } catch (IOException e1) {
	 	
	 }
	 	isChanged=false;
	 }}
 public static void saveLogic() {
	 jf1=new JFileChooser() {
			
		 public void approveSelection() {
		        File f2=getSelectedFile();
		        if(f2.exists() && getDialogType()==SAVE_DIALOG) {
		        	 String s2=f2.getName();
		    int result1=JOptionPane.showConfirmDialog
		(this,"'"+s2+"' file already exits, "
				+ "Do you want to replace it?","Confirm save as",JOptionPane.YES_NO_OPTION);
		if(result1==JOptionPane.YES_OPTION) {
		   super.approveSelection();
		}
		else {
			return;}}
			else {
		super.approveSelection();
			        }}}; 
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		
if(jmt1==e.getSource()) {
	Notepad.main(null);}
else if(jmt3==e.getSource()) {
	saveLogic();
	  saveFile();      
}
else if(jmt4==e.getSource()) {
	openFile();
	saveLogic();
	
}
else {
	System.exit(0);}}}
