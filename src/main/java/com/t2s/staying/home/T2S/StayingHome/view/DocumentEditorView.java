package com.t2s.staying.home.T2S.StayingHome.view;

import com.t2s.staying.home.T2S.StayingHome.factory.CommandsFactory;
import com.t2s.staying.home.T2S.StayingHome.factory.TextToSpeechFactory;
import com.t2s.staying.home.T2S.StayingHome.model.Line;
import com.t2s.staying.home.T2S.StayingHome.tts.FreeTTSAdapter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static com.t2s.staying.home.T2S.StayingHome.ApplicationConstants.*;

public class DocumentEditorView {

	private CommandsFactory commandsFactory = new CommandsFactory();
	private TextToSpeechFactory textToSpeechAPIFactory = new TextToSpeechFactory();

	private static final String LAST_MODIFIED_TIMESTAMP_LABEL_TEXT = "Last modified date:";
	private static final String CREATION_TIMESTAMP_LABEL_TEXT = "Creation date:";
	private static final String TEXT_AREA_LABEL_TEXT = "Text area:";
	private static final String DOCUMENT_TITLE_LABEL_TEXT = "Document title:";
	private static final String TRANSFORM_TO_SPEECH_LABEL_TEXT = "Transform to speech:";
	private static final String TRANSFORM_TO_SPEECH_REVERSED_LABEL_TEXT = "Transform to speech reversed:";
	private static final String ENCODE_DOCUMENT_ATBASH_LABEL_TEXT = "Encode document(Atbash):";
	private static final String ENCODE_DOCUMENT_ROT13_LABEL_TEXT = "Encode document(Rot-13):";
	private static final String AUTHORS_NAME_LABEL_TEXT = "Author's name:";
	private static final String VOICE_VOLUME_LABEL_TEXT = "Voice volume";
	private static final String VOICE_PITCH_LABEL_TEXT = "Voice pitch";
	private static final String VOICE_RATE_LABEL_TEXT = "Voice rate";

	private static final String ALL_LINES_BUTTON_TEXT = "ALL";
	private static final String SELECTED_LINE_BUTTON_TEXT = "SELECTED LINE";
	private static final String LOAD_BUTTON_TEXT = "Load";
	private static final String UPDATE_BUTTON_TEXT = "Save";
	private static final String RETURN_TO_MAIN_MENU_BUTTON_TEXT = "< Main Menu";

	private JFrame frame;
	private JTextField authorTextField;

	private JTextField documentTitleTextField;
	private JLabel creationTimestampPlaceholder;
	private JLabel lModifiedTimestampPlaceholder;


	public JTextArea getTextArea() {
		return textArea;
	}

	private JTextArea textArea;

	private FreeTTSAdapter t2s = new FreeTTSAdapter();

	private JSlider voiceRateSlider = new JSlider(0, 400);

	public JSlider getVoicePitchSlider() {
		return voicePitchSlider;
	}

	private JSlider voicePitchSlider = new JSlider(50, 200);

	public JSlider getVoiceRateSlider() {
		return voiceRateSlider;
	}

	public JSlider getVoiceVolumeSlider() {
		return voiceVolumeSlider;
	}

	private JSlider voiceVolumeSlider = new JSlider(0, 10);

	/**
	 * @wbp.parser.entryPoint
	 */
	public DocumentEditorView() {
		initialize();
		this.frame.setVisible(true);

	}

	private void initialize() {
		JPanel panel = new JPanel(new BorderLayout());
		frame = new JFrame();
		frame.setBounds(100, 100, 895, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel textAreaLabel = new JLabel(TEXT_AREA_LABEL_TEXT);
		textAreaLabel.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(textAreaLabel);

		JLabel creationTimestampLabel = new JLabel(CREATION_TIMESTAMP_LABEL_TEXT);
		creationTimestampLabel.setBounds(569, 407, 86, 14);
		frame.getContentPane().add(creationTimestampLabel);

		creationTimestampPlaceholder = new JLabel("-"); // init value that is going to be modified
		creationTimestampPlaceholder.setBounds(569, 431, 146, 14);
		frame.getContentPane().add(creationTimestampPlaceholder);

		JLabel lModifiedTimestampLabel = new JLabel(LAST_MODIFIED_TIMESTAMP_LABEL_TEXT);
		lModifiedTimestampLabel.setBounds(569, 450, 110, 14);
		frame.getContentPane().add(lModifiedTimestampLabel);

		lModifiedTimestampPlaceholder = new JLabel("-"); // init value that is going to be modified
		lModifiedTimestampPlaceholder.setBounds(572, 474, 146, 14);
		frame.getContentPane().add(lModifiedTimestampPlaceholder);

		//------LOAD BUTTON-----//
		JButton loadButton = new JButton(LOAD_BUTTON_TEXT);
		ActionListener loadDocumentActionListener = commandsFactory.createCommand(OPEN_DOCUMENT_COMMAND, this);
		loadButton.addActionListener(loadDocumentActionListener);
		loadButton.setBounds(728, 437, 130, 23);
		frame.getContentPane().add(loadButton);

		//------SAVE - edited file -----//
		JButton saveButton = new JButton(UPDATE_BUTTON_TEXT);
		ActionListener saveDocumentActionListener = commandsFactory.createCommand(SAVE_DOCUMENT_COMMAND, this);
		saveButton.addActionListener(saveDocumentActionListener);
		saveButton.setBounds(728, 471, 130, 23);
		frame.getContentPane().add(saveButton);

		//------ DocumentToSpeech -----//
		JButton ttsAllButton = new JButton(ALL_LINES_BUTTON_TEXT);
		ActionListener doc2SpeechActionListener = commandsFactory.createCommand(DOC_TO_SPEECH_COMMAND, this);
		ttsAllButton.addActionListener(doc2SpeechActionListener);
		ttsAllButton.setBounds(567, 46, 130, 23);
		frame.getContentPane().add(ttsAllButton);

		//------ Back To MainMenu ----//
		JButton returnToMainMenuButton = new JButton(RETURN_TO_MAIN_MENU_BUTTON_TEXT);
		returnToMainMenuButton.setBounds(728, 403, 130, 23);
		frame.getContentPane().add(returnToMainMenuButton);
		returnToMainMenuButton.addActionListener(e -> {
			new MainView();
			frame.setVisible(false);
		});

		JLabel authorLabel = new JLabel(AUTHORS_NAME_LABEL_TEXT);
		authorLabel.setBounds(567, 220, 101, 14);
		frame.getContentPane().add(authorLabel);

		authorTextField = new JTextField();
		authorTextField.setColumns(10);
		authorTextField.setBounds(567, 246, 130, 20);
		frame.getContentPane().add(authorTextField);

		JLabel documentTitleLabel = new JLabel(DOCUMENT_TITLE_LABEL_TEXT);
		documentTitleLabel.setBounds(728, 220, 130, 14);
		frame.getContentPane().add(documentTitleLabel);

		documentTitleTextField = new JTextField();
		documentTitleTextField.setColumns(10);
		documentTitleTextField.setBounds(728, 246, 130, 20);
		frame.getContentPane().add(documentTitleTextField);

		//---- LineToSpeech -----//
		JButton ttsSelectedButton = new JButton(SELECTED_LINE_BUTTON_TEXT);
		ActionListener lineToSpeechActionListener = commandsFactory.createCommand(LINE_TO_SPEECH, this);
		ttsSelectedButton.addActionListener(lineToSpeechActionListener);
		ttsSelectedButton.setBounds(567, 77, 130, 23);
		frame.getContentPane().add(ttsSelectedButton);

		JLabel transformToSpeechLabel1 = new JLabel(TRANSFORM_TO_SPEECH_LABEL_TEXT);
		transformToSpeechLabel1.setForeground(SystemColor.desktop);
		transformToSpeechLabel1.setVerticalAlignment(SwingConstants.TOP);
		transformToSpeechLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		transformToSpeechLabel1.setBounds(567, 21, 112, 14);
		frame.getContentPane().add(transformToSpeechLabel1);

		JLabel encodeOptionAtBashLabel = new JLabel(ENCODE_DOCUMENT_ATBASH_LABEL_TEXT);
		encodeOptionAtBashLabel.setVerticalAlignment(SwingConstants.TOP);
		encodeOptionAtBashLabel.setHorizontalAlignment(SwingConstants.LEFT);
		encodeOptionAtBashLabel.setBounds(567, 293, 141, 14);
		frame.getContentPane().add(encodeOptionAtBashLabel);

		//---------ATBASH ALL-------------//
		JButton encodeAllButtonAtbash = new JButton(ALL_LINES_BUTTON_TEXT);
		ActionListener  encodeAllAtbashActionListener = commandsFactory.createCommand(ENCODE_ALL_ATBASH, this);
		encodeAllButtonAtbash.addActionListener(encodeAllAtbashActionListener);
		encodeAllButtonAtbash.setBounds(567, 317, 130, 23);
		frame.getContentPane().add(encodeAllButtonAtbash);

		//---------ATBASH LINE-------------//
		JButton encodeSelectedButtonAtbash = new JButton(SELECTED_LINE_BUTTON_TEXT);
		ActionListener  encodeLineAtbashActionListener = commandsFactory.createCommand(ENCODE_LINE_ATBASH, this);
		encodeSelectedButtonAtbash.addActionListener(encodeLineAtbashActionListener);
		encodeSelectedButtonAtbash.setVerticalAlignment(SwingConstants.TOP);
		encodeSelectedButtonAtbash.setBounds(567, 350, 130, 23);
		frame.getContentPane().add(encodeSelectedButtonAtbash);

		JLabel reversedTtsLabel = new JLabel(TRANSFORM_TO_SPEECH_REVERSED_LABEL_TEXT);
		reversedTtsLabel.setBounds(718, 21, 156, 13);
		frame.getContentPane().add(reversedTtsLabel);

		// ----- Reverse ALL -----//
		JButton reverseAllButton = new JButton(ALL_LINES_BUTTON_TEXT);
		ActionListener reverseAllActionListener = commandsFactory.createCommand(REVERSE_ALL_COMMAND, this);
		reverseAllButton.addActionListener(reverseAllActionListener);
		reverseAllButton.setBounds(718, 46, 130, 23);
		frame.getContentPane().add(reverseAllButton);

		// ----- Reverse Line -----//
		JButton reverseLineButton_1 = new JButton(SELECTED_LINE_BUTTON_TEXT);
		ActionListener reverseLineActionListener = commandsFactory.createCommand(REVERSE_LINE_COMMAND, this);
		reverseLineButton_1.addActionListener(reverseLineActionListener);
		reverseLineButton_1.setBounds(718, 77, 130, 23);
		frame.getContentPane().add(reverseLineButton_1);

		//----- RIOT13 ALL---------//
		JButton encodingAllButtonROT13 = new JButton(ALL_LINES_BUTTON_TEXT);
		ActionListener  encodeAllRot13ActionListener = commandsFactory.createCommand(ENCODE_ALL_ROT13, this);
		encodingAllButtonROT13.addActionListener(encodeAllRot13ActionListener);
		encodingAllButtonROT13.setBounds(728, 317, 130, 23);
		frame.getContentPane().add(encodingAllButtonROT13);

		//----- RIOT13 LINE---------//
		JButton encodingLineButtonROT13 = new JButton(SELECTED_LINE_BUTTON_TEXT);
		ActionListener  encodeLineRot13ActionListener = commandsFactory.createCommand(ENCODE_LINE_ROT13, this);
		encodingLineButtonROT13.addActionListener(encodeLineRot13ActionListener);
		encodingLineButtonROT13.setVerticalAlignment(SwingConstants.TOP);
		encodingLineButtonROT13.setBounds(728, 350, 130, 23);
		frame.getContentPane().add(encodingLineButtonROT13);

		JLabel encodeOptionRot13Label = new JLabel(ENCODE_DOCUMENT_ROT13_LABEL_TEXT);
		encodeOptionRot13Label.setVerticalAlignment(SwingConstants.TOP);
		encodeOptionRot13Label.setHorizontalAlignment(SwingConstants.LEFT);
		encodeOptionRot13Label.setBounds(728, 293, 141, 14);
		frame.getContentPane().add(encodeOptionRot13Label);

		//-------------VOLUME-----------//

		ChangeListener volumeChangeListener = textToSpeechAPIFactory.createChangeListener(TUNE_VOLUME_COMMAND, this);
		voiceVolumeSlider.addChangeListener(volumeChangeListener);
//		voiceVolumeSlider.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider) e.getSource();
//				t2s.setVolume(source.getValue());		// pernei kanonika thn value meta einai to lathos
//				System.out.println("source.getValue()  " + source.getValue());
//			}
//		});
		voiceVolumeSlider.setBounds(678, 122, 170, 14);
		frame.getContentPane().add(voiceVolumeSlider);

		JLabel lblVoiceVolume = new JLabel(VOICE_VOLUME_LABEL_TEXT);
		lblVoiceVolume.setVerticalAlignment(SwingConstants.TOP);
		lblVoiceVolume.setHorizontalAlignment(SwingConstants.LEFT);
		lblVoiceVolume.setForeground(Color.BLACK);
		lblVoiceVolume.setBounds(567, 122, 62, 14);
		frame.getContentPane().add(lblVoiceVolume);

		JLabel lblVoicePitch = new JLabel(VOICE_PITCH_LABEL_TEXT);
		lblVoicePitch.setVerticalAlignment(SwingConstants.TOP);
		lblVoicePitch.setHorizontalAlignment(SwingConstants.LEFT);
		lblVoicePitch.setForeground(Color.BLACK);
		lblVoicePitch.setBounds(567, 149, 62, 14);
		frame.getContentPane().add(lblVoicePitch);

		//--------------PITCH-----------------//

//		voicePitchSlider.setValue(0);
		voicePitchSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(((JSlider) e.getSource()).getValue());
				t2s.setPitch(((JSlider) e.getSource()).getValue());
			}
		});
		voicePitchSlider.setBounds(678, 149, 170, 14);
		frame.add(voicePitchSlider);
		frame.getContentPane().add(voicePitchSlider);

		JLabel lblVoiceRate = new JLabel(VOICE_RATE_LABEL_TEXT);
		lblVoiceRate.setVerticalAlignment(SwingConstants.TOP);
		lblVoiceRate.setHorizontalAlignment(SwingConstants.LEFT);
		lblVoiceRate.setForeground(Color.BLACK);
		lblVoiceRate.setBounds(567, 179, 62, 14);
		frame.getContentPane().add(lblVoiceRate);

		//-------------Rate----------------//

//		voiceRateSlider.setValue(0);
		voiceRateSlider.addChangeListener(e -> sliderRateChanged());

		//voiceRateSlider.setMaximum(1);
		voiceRateSlider.setBounds(678, 179, 170, 14);
		//ChangeListener changeListener = textToSpeechAPIFactory.createChangeListener(TUNE_VOLUME_COMMAND, this);
		//voiceRateSlider.addChangeListener(changeListener);
		frame.getContentPane().add(voiceRateSlider);

		textArea = new JTextArea();
		textArea.setBounds(10, 37, 515, 439);
		frame.getContentPane().add(textArea);

		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
//		textArea.setLineWrap(true);
//		textArea.setWrapStyleWord(true);


	}


	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public void updateView(String docTitle, String docAuthor, String docCreationTime, String docLModifiedTime, List<Line> lines) {

		this.documentTitleTextField.setText(docTitle);
		this.authorTextField.setText(docAuthor);
		this.creationTimestampPlaceholder.setText(docCreationTime);
		this.lModifiedTimestampPlaceholder.setText(docLModifiedTime);
		this.textArea.setText("");
		for (Line line : lines) {
			for (String word : line.getWords()) {
				this.textArea.append(word);
				this.textArea.append(" ");
			}
			this.textArea.append("\n");

		}

	}

	public void goToMainView() {
		new MainView();
		frame.setVisible(false);
	}

	public int getLineNumber(){
		int caretPos = textArea.getCaretPosition();
		int lineNumber = 0;
		try {
			lineNumber = textArea.getLineOfOffset(caretPos);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return lineNumber;
	}


	public String getAuthorTextField() {
		return authorTextField.getText();
	}

	public String getDocumentTitleTextField() {
		return documentTitleTextField.getText();
	}

	public void sliderRateChanged(){

		t2s.setRate(voiceRateSlider.getValue());		// pernei kanonika thn value meta einai to lathos
		System.out.println("source.getValue()  " + voiceRateSlider.getValue());

	}
	private void sliderPitchChanged() {
		t2s.setPitch(voicePitchSlider.getValue());		// pernei kanonika thn value meta einai to lathos
		System.out.println("voicePitchSlider.getValue()  " + voicePitchSlider.getValue());
	}

}
