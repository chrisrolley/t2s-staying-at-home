package com.t2s.staying.home.T2S.StayingHome;

import java.io.BufferedReader;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.t2s.staying.home.T2S.StayingHome.command.NewDocument;
import com.t2s.staying.home.T2S.StayingHome.view.DocumentEditorView;
import com.t2s.staying.home.T2S.StayingHome.view.NewDocumentView;
import com.t2s.staying.home.T2S.utils.FileUtils;

@SpringBootTest
class ApplicationTests {

	DocumentEditorView documentEditorView = initializeDocumentEditorView();
	NewDocumentView newDocumentView = initializeNewDocumentView();
	@Test
	// To test the creation of a new document we can implement an
	// acceptance test that creates a NewDocument command executes it and
	// then checks whether the contents of the current document object that
	// is held by the Text2SpeechEditorView class is empty.
	void us1Test() {
		NewDocument newDocument = new NewDocument(newDocumentView);
		newDocument.actionPerformed(null);
		BufferedReader bufferedReader = FileUtils.getFileBufferReader(newDocument.getDialog().getSelectedFile().getAbsolutePath());
		assert bufferedReader.lines().collect(Collectors.toList()).isEmpty();
	}


	private DocumentEditorView initializeDocumentEditorView() {
		DocumentEditorView documentEditorView = new DocumentEditorView();
		documentEditorView.getTextArea().setText("test line 1/n test line 2");
		documentEditorView.getTitleJTextField().setText("test title");
		documentEditorView.getAuthorJTextField().setText("test author");
		return documentEditorView;
	}

	public NewDocumentView initializeNewDocumentView() {
		NewDocumentView newDocumentView = new NewDocumentView();
		newDocumentView.getAuthorJTextField().setText("test author");
		newDocumentView.getDocumentTitleJTextField().setText("test title");
		return newDocumentView;
	}
}
