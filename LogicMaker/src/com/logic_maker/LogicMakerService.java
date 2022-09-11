package com.logic_maker;

import java.util.ArrayList;
import java.util.List;

import com.game.library.VEC2;

public class LogicMakerService {
	
	private static LogicMakerService lms = new LogicMakerService();

	private LogicRepository lr = LogicRepository.getInstance();

	private LogicMakerService() {
		super();
	}
	
	public static LogicMakerService getInstance() {
		return lms;
	}
	
	public void insertLogicInfo(String[][] logic, String name) {
		int nextLogicId = getRecordNumberFromLogicInfo() + 1;
		String logicId = String.valueOf(nextLogicId);
		
		int height = logic.length;
		int width = logic[0].length;
		
		lr.insertLogicInfo(new LogicInfo(logicId, name, height, width));
	}
	
	public void nameLogic() {
		
	}
	
	public int getRecordNumberFromLogicInfo() {
		return lr.getRecordNumberFromLogicInfo();
	}

	public void insertBlackPiecePositions(String[][] logic) {
		
		int nextLogicId = getRecordNumberFromLogicInfo();
		String logicId = String.valueOf(nextLogicId);

		lr.insertBlackPiecePositions(putInBlackBlockPositions(logic), logicId);
	}

	private List<VEC2> putInBlackBlockPositions(String[][] logic) {
		List<VEC2> positions = new ArrayList<>();
		for(int y = 0; y < logic.length; y++) {
			for(int x = 0; x < logic[0].length; x++) {
				if(logic[y][x].equals("¡")) {
					positions.add(new VEC2(x,y));
				}
			}
		}
		return positions;
	}
	
	public void displayBlackPieces() {
		List<LogicPiece> logicPieces = lr.getBlackPiecePositions();
		for(LogicPiece lp: logicPieces) {
			System.out.print(lp.getLogicId() + " ");
			System.out.print(lp.getX() + " ");
			System.out.println(lp.getY());
		}
	}
	
	public List<LogicPiece> getBlackPiecePositions() {
		return lr.getBlackPiecePositions();
	}
	
	public int getRecordNumberFromVecTest() {
		return lr.getRecordNumberFromVecTest();
	}
 }
