package model;

import java.util.Arrays;
import java.util.List;

import marytts.signalproc.effects.JetPilotEffect;
import marytts.signalproc.effects.LpcWhisperiserEffect;
import marytts.signalproc.effects.RobotiserEffect;
import marytts.signalproc.effects.StadiumEffect;
import marytts.signalproc.effects.VocalTractLinearScalerEffect;
import marytts.signalproc.effects.VolumeEffect;

public class TTS {
	public void TTS(String message) {
		//Create TextToSpeech
		TextToSpeech tts = new TextToSpeech();
		
		// Setting the Current Voice
		tts.setVoice("cmu-rms-hsmm");
		
		//=========================================================================
		//========================= Let's try different effects=====================
		//=========================================================================
		
		//----- Hey you !-> check the help that is being printed on the console
		//----- You will understand how to use the effects better :)
		
		////////////////////////////avaliable effect////////////////////////////
		//VocalTractLinearScalerEffect
		VocalTractLinearScalerEffect vocalTractLSE = new VocalTractLinearScalerEffect(); //russian drunk effect
		vocalTractLSE.setParams("amount:10");
		
		//JetPilotEffect
		JetPilotEffect jetPilotEffect = new JetPilotEffect(); //epic fun!!!
		jetPilotEffect.setParams("amount:10");
		
		//RobotiserEffect
		RobotiserEffect robotiserEffect = new RobotiserEffect();
		robotiserEffect.setParams("amount:20");
		
		//StadiumEffect
		StadiumEffect stadiumEffect = new StadiumEffect();
		stadiumEffect.setParams("amount:80");
		
		//LpcWhisperiserEffect
		LpcWhisperiserEffect lpcWhisperiserEffect = new LpcWhisperiserEffect(); //creepy
		lpcWhisperiserEffect.setParams("amount:10");
		
		//VolumeEffect
		VolumeEffect volumeEffect = new VolumeEffect(); //be careful with this i almost got heart attack
		volumeEffect.setParams("amount:3");
		
		//Apply the effects
		//----You can add multiple effects by using the method `getFullEffectAsString()` and + symbol to connect with the other effect that you want
		//----check the example below
		tts.getMarytts().setAudioEffects(robotiserEffect.getFullEffectAsString());// + "+" + stadiumEffect.getFullEffectAsString());
		
		//=========================================================================
		//===================== Now let's troll user ==============================
		//=========================================================================
		List<String> arrayList = Arrays.asList(message);
		
		//Loop infinitely	
		arrayList.forEach(word -> tts.speak(word, 2.0f, false, true));
		
	}
}
