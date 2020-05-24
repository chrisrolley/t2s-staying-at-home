package com.t2s.staying.home.T2S.StayingHome.command;

import com.t2s.staying.home.T2S.StayingHome.factory.TextToSpeechAPIFactory;
import com.t2s.staying.home.T2S.StayingHome.manager.DocumentManager;
import com.t2s.staying.home.T2S.StayingHome.model.Line;
import com.t2s.staying.home.T2S.StayingHome.tts.TextToSpeechAPI;
import com.t2s.staying.home.T2S.StayingHome.view.DocumentEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.t2s.staying.home.T2S.StayingHome.ApplicationConstants.FREE_TTS;

public class LineToSpeech implements ActionListener {

	private DocumentEditorView view;
	private DocumentManager documentManager = new DocumentManager();
	public LineToSpeech(DocumentEditorView view) {
		this.view = view;
	}
	private TextToSpeechAPIFactory textToSpeech = new TextToSpeechAPIFactory();
	TextToSpeechAPI t2s =  textToSpeech.getTTSApi(FREE_TTS);

	@Override
	public void actionPerformed(ActionEvent e) {
		int lineNumber = view.getLineNumber();
		List<Line> currentLines = documentManager.getCurrentDocument().getLines();
		try {

			for (int n = 0; n < currentLines.size(); n += 1){
				for (String word : currentLines.get(n).getWords()) {
					if (lineNumber == n){t2s.play(word);}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		view.getReplayManager().add(this);
	}
}
