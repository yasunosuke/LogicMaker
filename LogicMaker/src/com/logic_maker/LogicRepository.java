package com.logic_maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.game.library.VEC2;

public class LogicRepository {
	
private static LogicRepository lr = new LogicRepository();
	
	private static final String URL = "**********";
	private static final String USER = "*********";
	private static final String PASSWORD = "**********";
	
	private LogicRepository() {
		super();
	}
	
	public static LogicRepository getInstance() {
		return lr;
	}
	
	public void insertLogicInfo(LogicInfo logicInfo) {
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement("INSERT INTO LogicInfo(logic_id, logic_name, height, width) VALUES(?, ?, ?, ?)");) {
			
			ps.setString(1, logicInfo.getLogicId());
			ps.setString(2, logicInfo.getLogicName());
			ps.setInt(3, logicInfo.getHeight());
			ps.setInt(4, logicInfo.getWidth());
			
			System.out.println(ps.toString());
			
			int count = ps.executeUpdate();

			System.out.println("inserted:" + count);

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}
	}
	
	public int getRecordNumberFromLogicInfo() {
		
		int recordNumber = 0;
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				Statement stmt = con.createStatement();) {

			try(ResultSet rs = stmt.executeQuery("SELECT count(*) FROM LogicInfo;")) {
				while(rs.next()) {

					recordNumber = rs.getInt(1);

				}
			} catch(SQLException e) {
				System.out.println("エラーコードは" + e.getErrorCode());
			}

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}
		
		return recordNumber;

	}
	
	public void insertBlackPiecePositions(List<VEC2> positions, String logicId) {

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement("INSERT INTO BlackPiecePositions(logic_id, x, y) VALUES(?, ?, ?)");) {

			for(VEC2 v: positions) {
				ps.setString(1, logicId);
				ps.setInt(2, v.getX());
				ps.setInt(3, v.getY());
				ps.addBatch();
				System.out.println(ps.toString());
			}

			int[] count = ps.executeBatch();
			System.out.println("count" + count.length);

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}
	}

	public void insertBlackPiecePositions(String[][] logic) {

		List<VEC2> positions = new ArrayList<>();
		for(int y = 0; y < logic.length; y++) {
			for(int x = 0; x < logic[0].length; x++) {
				if(logic[y][x].equals("■")) {
					positions.add(new VEC2(x,y));
				}
			}
		}

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				PreparedStatement ps = con.prepareStatement("INSERT INTO BlackPiecePositions(logic_id, x, y) VALUES(?, ?, ?)");) {

			for(VEC2 v: positions) {
				ps.setInt(1, 2);
				ps.setInt(2, v.getX());
				ps.setInt(3, v.getY());
				ps.addBatch();
				System.out.println(ps.toString());
			}

			int[] count = ps.executeBatch();
			System.out.println("count" + count.length);

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}
	}
	
	public List<LogicPiece> getBlackPiecePositions() {

		List<LogicPiece> logicBlackPieces = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				Statement stmt = con.createStatement();) {

			try(ResultSet rs = stmt.executeQuery("SELECT * FROM BlackPiecePositions")) {
				while(rs.next()) {

					String logicId = rs.getString(1);
					int x = rs.getInt(2);
					int y = rs.getInt(3);

					logicBlackPieces.add(new LogicPiece(logicId, x, y));

				}
			} catch(SQLException e) {
				System.out.println("エラーコードは" + e.getErrorCode());
			}

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}

		return logicBlackPieces;
	}
//	test 用
	public int getRecordNumberFromVecTest() {

		int recordNumber = 0;

		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
				Statement stmt = con.createStatement();) {

			try(ResultSet rs = stmt.executeQuery("SELECT count(*) FROM vectest;")) {
				while(rs.next()) {

					recordNumber = rs.getInt(1);

				}
			} catch(SQLException e) {
				System.out.println("エラーコードは" + e.getErrorCode());
			}

		} catch(SQLException e) {
			System.out.println("エラー" + e.getErrorCode());
		}

		return recordNumber;

	}
}
