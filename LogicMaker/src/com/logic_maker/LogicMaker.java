package com.logic_maker;

public class LogicMaker {

	public static void main(String[] args) {
		LogicMakerService lms = LogicMakerService.getInstance();
		LogicStore ls = LogicStore.getInstance();
		System.out.println(lms.getRecordNumberFromVecTest());
		System.out.println(lms.getRecordNumberFromLogicInfo());
		
//1		lms.insertLogicInfo(ls.getCrab(), "crab");
//2		lms.insertBlackPiecePositions(ls.getCrab());
//3		lms.displayBlackPieces();
	}

}
