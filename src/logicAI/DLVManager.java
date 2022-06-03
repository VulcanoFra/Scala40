package logicAI;

import java.util.ArrayList;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.Card;
import model.Player;
import model.Utilities;

public class DLVManager {
	
	private String path;
	private String encodingsNoCalato = "encodings/noCalato.dlv";
	private String encodingsScarto = "encodings/scarto.dlv";
	private String encodingsPesca = "encodings/pesco.dlv";
	private String encodingsAttacco = "encodings/attacco.dlv";
	private DesktopHandler desktopHandler;
	private InputProgram inputVariabile = new ASPInputProgram();
	private InputProgram noCalato = new ASPInputProgram();
	private InputProgram scarto = new ASPInputProgram();
	private InputProgram pesca = new ASPInputProgram();
	private InputProgram attacco = new ASPInputProgram();
	
	
	public DLVManager() {
		
		if (Utilities.os == 0) {
        	path = "lib/dlv2.exe";
        } else if (Utilities.os == 1){
            path = "lib/dlv2";
        } else if (Utilities.os == 2){
			path = "lib/dlv2-macOS-64bit.mac_5";
        }
		
//		desktopHandler = new DesktopHandler(new DLV2DesktopService(path));

		try {
			ASPMapper.getInstance().registerClass(Card.class);
			ASPMapper.getInstance().registerClass(Tris.class);
			ASPMapper.getInstance().registerClass(Quatris.class);
			ASPMapper.getInstance().registerClass(Scala.class);
			ASPMapper.getInstance().registerClass(Scarto.class);
			ASPMapper.getInstance().registerClass(CartaScarto.class);
			ASPMapper.getInstance().registerClass(Pesco.class);
			ASPMapper.getInstance().registerClass(Attacco.class);
			ASPMapper.getInstance().registerClass(CombinazioneTris.class);
			ASPMapper.getInstance().registerClass(CombinazioneScala.class);
//			ASPMapper.getInstance().registerClass(CombinazioneScalaParziale.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
		
		noCalato.addFilesPath(encodingsNoCalato);
		scarto.addFilesPath(encodingsScarto);
		pesca.addFilesPath(encodingsPesca);
		attacco.addFilesPath(encodingsAttacco);
		
		desktopHandler = new DesktopHandler(new DLV2DesktopService(path));
		

		
	}

	
	public void setInputPlayerPesca(Player playerAI, CartaScarto scartata) {
		desktopHandler.removeAll();
		desktopHandler.addProgram(pesca);
		
		inputVariabile.clearAll();
		
		
		try {
			for(Card c : playerAI.getCarteInMano()) {
				inputVariabile.addObjectInput(c);
			}
			
		  inputVariabile.addObjectInput(scartata);
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		desktopHandler.addProgram(inputVariabile);
	}

	
	public void setInputPlayerAttacco(Player playerAI, ArrayList<CombinazioneTris> tris, ArrayList<CombinazioneScala> scale) {
		desktopHandler.removeAll();
		desktopHandler.addProgram(attacco);
		
		inputVariabile.clearAll();
		
		
		try {
			for(Card c : playerAI.getCarteInMano()) {
				inputVariabile.addObjectInput(c);
			}
			
			for (CombinazioneTris t: tris) {
				inputVariabile.addObjectInput(t);
			}		

			for (CombinazioneScala s: scale) {
				inputVariabile.addObjectInput(s);
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		desktopHandler.addProgram(inputVariabile);
	}
	
	
	public void setInputPlayer(Player playerAI) {
		inputVariabile.clearAll();
		
		for(Card c : playerAI.getCarteInMano()) {
			try {
				inputVariabile.addObjectInput(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		desktopHandler.addProgram(inputVariabile);
	}
	
	
	public void setProgramPlayer() {
		
				desktopHandler.removeAll();
				desktopHandler.addProgram(noCalato);
			
		
		
	}
	
	public void setProgramPlayerScarto() {
		desktopHandler.removeAll();
		desktopHandler.addProgram(scarto);
	}	
	
	public void setProgramPlayerPesca() {
		desktopHandler.removeAll();
		desktopHandler.addProgram(pesca);
	}
	
	public void setProgramPlayerAttacco() {
		desktopHandler.removeAll();
		desktopHandler.addProgram(attacco);
	}
	
	public AnswerSets answerSet () {
		
		Output o = desktopHandler.startSync();
		AnswerSets answersets = (AnswerSets) o;
		return answersets;

	}
}
