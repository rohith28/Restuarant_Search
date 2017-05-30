/*
 * Class Name : Row
 * Author : Mounika Uppala, Rohith Uppala 
 * Version : 1.1  
 * Description : This class is defines all the bussiness logic for Insert, Update,Delete, Fetch of a Restaurant.   
 * 
 * 
 * */

package com.bridgew.project.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.bridgew.project.utility.DBUtil;


public class RestaurantDAO {
	 
	
	public boolean insRest(String name,String address, BigInteger phone,String timmings,String rating,String[] foodItem,String[] cost){
		Connection connection=null;
		int ratingRes=Integer.parseInt(rating);
		boolean res=false;
		ResultSet rs=null;
		ResultSet rm=null;
		ResultSet rr=null;
		ResultSet rk=null;
		int fid=0;
		try {
			connection=DBUtil.dbConnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement insStmt=null;
		PreparedStatement serStmt=null;
		PreparedStatement foodStmt=null;
		PreparedStatement foodIns=null;
		PreparedStatement feedbackStmt=null;
		PreparedStatement selStmt=null;
		
		try {
			connection.setAutoCommit(true);
			
			String serStr= "SELECT 1 FROM Restaurant WHERE name= ? and address=?";
			serStmt=connection.prepareStatement(serStr);
			serStmt.setString(1, name);
			serStmt.setString(2, address);
			rs=serStmt.executeQuery();
			
			if(rs.next())
			{
				res=false;
			}
			else{	
				int rid=0;
			String insertString = "INSERT INTO Restaurant(name,address,phone,timmings) VALUES (?,?,?,?);";	
			insStmt=connection.prepareStatement(insertString);
			insStmt.setString(1, name);
			insStmt.setString(2, address);
			insStmt.setBigDecimal(3, new BigDecimal(phone));
			insStmt.setString(4, timmings);
			
			insStmt.executeUpdate();
			
			
			String str="SELECT rid FROM Restaurant WHERE name=? and address=?";
			selStmt=connection.prepareStatement(str);
			selStmt.setString(1, name);
			selStmt.setString(2, address);
			rr=selStmt.executeQuery();
			while(rr.next())
			{
				rid=rr.getInt(1);
			}
			
			
			insStmt=null;
			try {
				DBUtil.dbDisconnect();
				connection=DBUtil.dbConnect();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = new Date(System.currentTimeMillis());
			String feedBackQuery="INSERT INTO feedback(resid,custid,rating,date) VALUES (?,1,?,?)";
			feedbackStmt=connection.prepareStatement(feedBackQuery);
			feedbackStmt.setInt(1, rid);
			feedbackStmt.setInt(2, ratingRes);
			feedbackStmt.setString(3, sdf.format(date1));
			
			feedbackStmt.executeUpdate();
			
			insStmt=null;
			
		
			for(int i=0;i<foodItem.length;i++)
			{
				fid=0;
			String foodQuery="SELECT fid FROM FoodItem  WHERE name=? and cost=?";
			foodStmt=connection.prepareStatement(foodQuery);
			foodStmt.setString(1, foodItem[i]);
			foodStmt.setString(2, cost[i]);
		
			rm=foodStmt.executeQuery();
	
			while(rm.next())
			{
				fid=rm.getInt(1);
				
			}
			if(fid==0)
			{
			 String foodInsrt="INSERT INTO FoodItem(name,cost) VALUES(?,?)";
			 foodIns=connection.prepareStatement(foodInsrt);
			 foodIns.setString(1, foodItem[i]);
			 foodIns.setString(2, cost[i]);
			 foodIns.executeUpdate();
			  foodQuery="SELECT fid FROM FoodItem  WHERE name=? and cost=?";
				foodStmt=connection.prepareStatement(foodQuery);
				foodStmt.setString(1, foodItem[i]);
				foodStmt.setString(2, cost[i]);
	
				ResultSet rl=foodStmt.executeQuery();
	
				while(rl.next())
				{
					fid=rl.getInt(1);
	
				}
	
			}
	
			foodStmt=null;
			foodQuery="SELECT 1 FROM RestaurantMenu WHERE menu=? and foodItem=?";
			foodStmt=connection.prepareStatement(foodQuery);
			foodStmt.setInt(1, rid);
			foodStmt.setInt(2, fid);
			rk=foodStmt.executeQuery();
	
		
			foodStmt=null;
			if(!rk.next())
			{
			foodQuery="INSERT INTO RestaurantMenu(menu,foodItem) VALUES(?,?)";
			
			foodStmt=connection.prepareStatement(foodQuery);
			foodStmt.setInt(1, rid);
			foodStmt.setInt(2, fid);
			foodStmt.executeUpdate();
	
			}
			
			
			}
	
			res=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 if (connection != null) {
		            try {
		                System.out.println(e.getMessage());
		            	System.err.print("Transaction is being rolled back");
		                connection.rollback();
		                
		            } catch(SQLException excep) {
		                System.out.println(excep);
		            }
		}
		
		}finally {
			//try { rs.close(); } catch (Exception e) { /* ignored */ }
			try {rm.close(); } catch (Exception e) { }
		    try { serStmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}
		return res;
	}
	
	
	/* Searching all Restaurant*/
			
	public ResultSet serachAllRes() throws SQLException{
	
		Connection connOne=null;
		ResultSet resultSer=null;
		String serchString=null;
		try {
	
			connOne=DBUtil.dbConnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement serStmt=null;
		try {
			connOne.setAutoCommit(false);
			/*String serchString="SELECT r.rid,r.name,r.address,r.phone,fb.rating,r.timmings,CONCAT(f.name,'-',f.cost) AS foodItem "+
								" FROM Restaurant r JOIN RestaurantMenu rm ON r.rid=rm.menu "+
								" JOIN FoodItem f ON rm.foodItem=f.fid "+
								" JOIN feedback fb ON fb.resid=r.rid "+
								" ORDER BY r.name ";*/
			serchString="SELECT r.rid,r.name,r.address,r.phone,AVG(fb.rating),r.timmings, (SELECT GROUP_CONCAT(f.name,'-',f.cost) FROM FoodItem f JOIN RestaurantMenu rm ON rm.fooditem=f.fid JOIN Restaurant rs ON rs.rid=rm.menu WHERE rs.rid=r.rid ) AS foodItem"+
								" FROM Restaurant r JOIN RestaurantMenu rm ON r.rid=rm.menu"+
								" JOIN FoodItem f ON rm.foodItem=f.fid"+
								" JOIN feedback fb ON fb.resid=r.rid"+
								" GROUP BY r.rid"+
								" ORDER BY r.rid;";
			
			serStmt=connOne.prepareStatement(serchString);
			resultSer=serStmt.executeQuery();
			while(resultSer.next())
			{
				System.out.println(resultSer.getString("name"));
			}
			resultSer.beforeFirst();
			//resList= getRestaurantsList(rs);
			connOne.commit();
			//rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(serchString);
			e.printStackTrace();
		}
		
		return resultSer;
		
	}


/*    Searching the Restaurant by both name and city*/
	
	public ResultSet updateTable(String str) {
		// TODO Auto-generated method stub

		//Connection conn=null;
		Connection connOne=null;
		ResultSet updateRs=null;
		String serchString=null;
		try {
			//conn=DBUtil.dbConnect();
			connOne=DBUtil.dbConnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement serStmt=null;
		try {
			//conn.setAutoCommit(false);
			connOne.setAutoCommit(false);
			if(str.trim().equals(""))
			{
				serchString="SELECT r.rid,r.name,r.address,r.phone,AVG(fb.rating),r.timmings, (SELECT GROUP_CONCAT(f.name,'-',f.cost) FROM FoodItem f JOIN RestaurantMenu rm ON rm.fooditem=f.fid JOIN Restaurant rs ON rs.rid=rm.menu WHERE rs.rid=r.rid ) AS foodItem "+
						" FROM Restaurant r JOIN RestaurantMenu rm ON r.rid=rm.menu"+
						" JOIN FoodItem f ON rm.foodItem=f.fid"+
						" JOIN feedback fb ON fb.resid=r.rid"+
						" GROUP BY r.rid"+ 
						" ORDER BY r.rid;";
				
			}
			else{
			
			serchString="SELECT r.rid,r.name,r.address,r.phone,AVG(fb.rating),r.timmings, (SELECT GROUP_CONCAT(f.name,'-',f.cost) FROM FoodItem f JOIN RestaurantMenu rm ON rm.fooditem=f.fid JOIN Restaurant rs ON rs.rid=rm.menu WHERE rs.rid=r.rid ) AS foodItem"+
								" FROM Restaurant r JOIN RestaurantMenu rm ON r.rid=rm.menu"+
								" JOIN FoodItem f ON rm.foodItem=f.fid"+
								" JOIN feedback fb ON fb.resid=r.rid"+
								" WHERE r.name LIKE ? OR r.address LIKE ?"+
								" GROUP BY r.rid"+
								" ORDER BY r.rid;";
			}
			
			serStmt=connOne.prepareStatement(serchString);
			if(!str.trim().equals(""))
			{
				serStmt.setString(1, "%"+str.trim()+"%");
				serStmt.setString(2, "%"+str.trim()+"%");
			}
			
			updateRs=serStmt.executeQuery();
			connOne.commit();
			/*while(updateRs.next())
			{
				System.out.println(updateRs.getString("name"));
			}
			updateRs.beforeFirst();*/
			//System.out.println("Executed");
			//resList= getRestaurantsList(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured"+serchString);
			e.printStackTrace();
		}
		
		//System.out.println(updateRs.toString());
		return updateRs;	
		
	}
	
	/* Updating the Restaurant rating */
	@SuppressWarnings("resource")
	public boolean rateRest(int resId,String Name, String custMail,int rating) {
		Connection conn=null;
		ResultSet rs=null;
		int custId=0;
		String ratingStr=null;
		String findCust=null;
		boolean result=false;
		try {
			conn=DBUtil.dbConnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement ratingStmt=null;
		try {
		
		conn.setAutoCommit(false);
		findCust="SELECT cid FROM Customer WHERE name=? AND email=?";
		ratingStmt=conn.prepareStatement(findCust);
		ratingStmt.setString(1, Name);
		ratingStmt.setString(2, custMail);
		
			rs=ratingStmt.executeQuery();
		
		if(!rs.next())
		{
			findCust="INSERT INTO Customer(name,email) VALUES(?,?)";
			ratingStmt=conn.prepareStatement(findCust);
			ratingStmt.setString(1, Name);
			ratingStmt.setString(2, custMail);
			ratingStmt.executeUpdate();
			conn.commit();
			findCust="SELECT cid FROM Customer WHERE name=? AND email=?";
			ratingStmt=conn.prepareStatement(findCust);
			ratingStmt.setString(1, Name);
			ratingStmt.setString(2, custMail);
			
				rs=ratingStmt.executeQuery();
		}
		rs.beforeFirst();
		
		while(rs.next())
		{
		custId=rs.getInt("cid");
		}
		findCust="SELECT * FROM feedback WHERE resid=? and custid=?";
		ratingStmt=conn.prepareStatement(findCust);
		ratingStmt.setInt(1, resId);
		ratingStmt.setInt(2, custId);
		ResultSet res=ratingStmt.executeQuery();
		
		if(res.next()){
			ratingStr="UPDATE feedback SET rating=? WHERE resid=? AND custid=?";
			ratingStmt=conn.prepareStatement(ratingStr);
			ratingStmt.setInt(2, resId);
			ratingStmt.setInt(3,custId);
			ratingStmt.setInt(1, rating);
			ratingStmt.executeUpdate();
			result=true;
			
		}
		else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = new Date(System.currentTimeMillis());
		ratingStr="INSERT INTO feedback(resid,custid,rating,date) VALUES(?,?,?,?)";
		ratingStmt=conn.prepareStatement(ratingStr);
		ratingStmt.setInt(1, resId);
		ratingStmt.setInt(2,custId);
		ratingStmt.setInt(3, rating);
		ratingStmt.setString(4, sdf.format(date1));
		ratingStmt.executeUpdate();
		result=true;
		
		}
		conn.commit();
		
		} catch (SQLException e) {
			System.out.println("Error occured in Rating the restaurant");
			e.printStackTrace();
		}finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { ratingStmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		
		
		
		return result;
	}
	
	
	public boolean deleteRes(int rid){
		Connection conn=null;
		String delStr=null;;
		boolean result=false;
		PreparedStatement delStmt=null;
		try {
			conn=DBUtil.dbConnect();
			conn.setAutoCommit(false);
			delStr="DELETE FROM Restaurant WHERE rid=?";
			delStmt=conn.prepareStatement(delStr);
			delStmt.setInt(1, rid);
			delStmt.executeUpdate();
			result=true;
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { delStmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return result;
	}
	
	
}
