package com.t2s.staying.home.T2S.StayingHome.command;

import com.t2s.staying.home.T2S.StayingHome.view.EditDocumentView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;


public class OpenDocument implements ActionListener {

	private EditDocumentView openDocumentView;
	private String fileName;
	private JTextField textArea;

	public OpenDocument(EditDocumentView openDocumentView){this.openDocumentView =openDocumentView;}


	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		JFileChooser dialog = new JFileChooser();
		if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			openFile(dialog.getSelectedFile().getAbsolutePath());
		}
	}




	public void openFile(String absolutePath) {
		try {
			FileReader reader = new FileReader(fileName);
			//textArea.read(reader, null);
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		openDocumentView.openFile();
	}


	


}
