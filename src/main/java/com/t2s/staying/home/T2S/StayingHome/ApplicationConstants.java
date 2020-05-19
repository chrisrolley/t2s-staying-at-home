package com.t2s.staying.home.T2S.StayingHome;

import java.awt.*;

public class ApplicationConstants {

	// commands factory string values
	public static final String NEW_DOCUMENT_COMMAND = "newDocument";
	public static final String OPEN_DOCUMENT_COMMAND = "openDocument";
	public static final String SAVE_DOCUMENT_COMMAND = "saveEditedDocument";
	public static final String DOC_TO_SPEECH_COMMAND = "text2SpeechDocument";
	public static final String LINE_TO_SPEECH = "text2SpeechLine";
	public static final String REVERSE_ALL_COMMAND = "reverseAll";
	public static final String REVERSE_LINE_COMMAND = "reverseLine";
	public static final String TUNE_VOLUME_COMMAND = "tuneVolume";

	public static final String AUTHOR_METADATA_NAME = "author";
	public static final String TITLE_METADATA_NAME = "title";

	// t2s factory string values
	public static final String FREE_TTS = "freeTTSAdapter";
	public static final String FAKE_TTS= "FakeT2s";

	//VOICES
	public static final String VOICE_KEVIN16 = "kevin16";
	public static final String VOICE_KEVIN = "kevin";
	public static final String VOICE_ALAN = "alan";

	// todo this can go in a configuration file
	public static final String DEFAULT_SAVE_FILE_LOCATION = "C:\\Users\\a.todhri\\Documents\\T2Sdocs\\";

	public static final Font DEFAULT_FONT_SETTINGS = new Font("Tahoma", Font.PLAIN, 16);

}
