package com.t2s.staying.home.T2S.StayingHome.view;

import static com.t2s.staying.home.T2S.StayingHome.ApplicationConstants.LOAD_BUTTON_TEXT;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class EditDocumentView {

	private JButton btnLoadButton;
	private JFrame frame;

	JFileChooser jFileChooser = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	private JTextField textArea;
	private JButton btnSave;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				EditDocumentView window = new EditDocumentView();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public EditDocumentView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoadButton = new JButton(LOAD_BUTTON_TEXT);
		btnLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog = new JFileChooser();
				if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					openFile(dialog.getSelectedFile().getAbsolutePath());

				}

			}
		});
		btnLoadButton.setBounds(26, 103, 89, 23);
		frame.getContentPane().add(btnLoadButton);

		textArea = new JTextField();
		textArea.setBounds(151, 38, 476, 468);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);

		JLabel lblNewLabel = new JLabel("Text Area");
		lblNewLabel.setBounds(331, 13, 89, 14);
		frame.getContentPane().add(lblNewLabel);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog = new JFileChooser();
				if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					saveFile(dialog.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSave.setBounds(26, 158, 89, 23);
		frame.getContentPane().add(btnSave);

		JButton btnConvert = new JButton("Convert");
		btnConvert.setBounds(26, 212, 89, 23);
		frame.getContentPane().add(btnConvert);

		JButton btnConvertealine = new JButton("Convert a line");
		btnConvertealine.setBounds(26, 396, 99, 23);
		frame.getContentPane().add(btnConvertealine);
	}

	private void openFile(String fileName) {
		try {
			FileReader reader = new FileReader(fileName);
			textArea.read(reader, null);
			reader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(btnLoadButton, this, "File" + fileName + "can't be opened.", 0);
		}
	}

	private void saveFile(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			textArea.write(writer);
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(btnSave, this, "File" + fileName + "can't be saved.", 0);

		}
	}
}

/*public void actionPerformed(ActionEvent e) {
String	cn = textChooseN.getText()	.toString();
String  an = textAuthorsN.getText().toString();
String  ta = textArea.getText()	.toString();

try {
	FileWriter writer = new FileWriter("chris.txt",true);
	writer.write(cn);
	writer.write(System.getProperty("line.separator"));
	writer.write(an);
	writer.write(System.getProperty("line.separator"));
	writer.write(ta);
	writer.write(System.getProperty("line.separator"));
	writer.close();
	JOptionPane.showMessageDialog(getRootPane(), "Success");
	
	
} catch(Exception e1) {
	
	JOptionPane.showMessageDialog(getRootPane(), "Error");
	
}

JFileChooser fc=new JFileChooser();
				fc.showSaveDialog(null);
				File f=fc.getSelectedFile()	;
				try {
					FileWriter fw=new FileWriter(f);
					String text =textArea.getText();
					fw.write(text);
					fw.close();
			    }
				catch(IOException e1){
					System.out.println(e1);
				}

} */