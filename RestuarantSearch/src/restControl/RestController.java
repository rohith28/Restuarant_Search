/*
 * Class Name : RestController
 * Author : Rohith Uppala ,Mounika Uppala
 * Version : 1.1  
 * Description : This will acts as bridge between the business logic and GUI
 * 
 * 
 * */


package restControl;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.bridgew.project.model.RestaurantDAO;
import com.bridgew.project.model.Row;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class RestController {
	ObservableList<String> typeOfRes=FXCollections.observableArrayList("American","Mexican","Indian","Italian");
	
	@FXML
	private TextArea resultArea;
	@FXML
	private TextField resIdText;
	@FXML
	private TextField newEmailText;
	@FXML
	private TextField nameText;
	@FXML
	private TextField Address;
	@FXML
	private TextField Rating;
	@FXML
	private TextField phone;
	@FXML
	private TextField timmings;
	@FXML
	private TextField foodItems;
	@FXML
	private TextField foodCost;
	//@FXML
	//private ComboBox<String> typeColumn;	
	@FXML
	private TableView<Row> resTable;
	@FXML
	private TableColumn<Row,Integer> resIdColumn;
	@FXML
	private TableColumn<Row,String> resNameColumn;
	@FXML
	private TableColumn<Row,String> resAddressColumn;
	@FXML
	private TableColumn<Row,String> resFoodItems;
	@FXML
	private TableColumn<Row,String> resPhoneColumn;
	@FXML
	private TableColumn<Row, Float> resRatingColumn;
	@FXML
	private TableColumn<Row,String> resTimmingsColumn;
	@FXML
	private ObservableList<Row> data;
	@FXML
	private TextField custRating;
	@FXML
	private TextField custName;
	@FXML
	private TextField custMail;
	
	RestaurantDAO restDAO=new RestaurantDAO();
	
	
	@FXML
    private void initialize () {
		resIdColumn.setCellValueFactory(cellData -> cellData.getValue().resIdColumnProperty().asObject());
		resNameColumn.setCellValueFactory(cellData -> cellData.getValue().resNameColumnProperty());
		resAddressColumn.setCellValueFactory(cellData -> cellData.getValue().resAddressColumnProperty());
		resFoodItems.setCellValueFactory(cellData -> cellData.getValue().resFoodItemsProperty());
		resPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().resPhoneColumnProperty());
		resRatingColumn.setCellValueFactory(cellData -> cellData.getValue().resRatingColumnProperty().asObject());
		resTimmingsColumn.setCellValueFactory(cellData -> cellData.getValue().resTimmingsColumnProperty());
		
		
		/*typeColumn.setItems(typeOfRes);*/
		/*typeColumn=new ComboBox<String>();
		typeColumn.getItems().addAll(
			"Italian",
			"Mexican",
			"Indian",
			"American"
		);*/
		
		resIdText.textProperty().addListener(      (obs, oldText, newText) -> {update();}    );
		update();
    }
	
	@FXML
	private void update(){
		String str=resIdText.getText();
		data=FXCollections.observableArrayList();
		Alert alert = new Alert(AlertType.WARNING);
		ResultSet updateRs=null;
		//System.out.println(updateRs);
				updateRs=restDAO.updateTable(str);
		Row row;
		//System.out.println(updateRs);
		
		try {
			while ( updateRs.next() ) {
				//System.out.println("result has values"+updateRs.getString(2)+updateRs.getString(3));
			row = new Row(updateRs.getInt(1),updateRs.getString(2),updateRs.getString(3),updateRs.getString(4),updateRs.getFloat(5),updateRs.getString(6),updateRs.getString(7));
			data.add(row);
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Problem in loading the Application");
			resultArea.setText("Problem in loading the application");
			alert.showAndWait();

		}
		
		resTable.setItems(data);
	}

	
	//Search By Name
	@FXML
	private void searchRes(ActionEvent actionEvent){
		System.out.println("Search Res");
	
	}
	
	//Delete Res
	@FXML
	private void deleteRes(ActionEvent actionEvent){
		System.out.println("Delete Res");
		Alert alert = new Alert(AlertType.WARNING);
		try{
		Row row=resTable.getSelectionModel().getSelectedItem();
		boolean res=restDAO.deleteRes(row.getRid());
		if(res)
		{
			resultArea.setText("Restaurant deleted successfully");
			resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Restaurant Deleted successfully");

			alert.showAndWait();

		}
		else
		{
			resultArea.setText("Problem occured during deletion");
			resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Error in deleting the restaurant");

			alert.showAndWait();

		}
		}catch(Exception e)
		{
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Please select a restaurant");

			alert.showAndWait();

		}
		
		
	}
	
	//Inserting Rating
	@FXML
	private void updateRating(ActionEvent actionEvent){
		
		String name=custName.getText();
		
		String mail=custMail.getText();
		
		int rating=0;
		Alert alert = new Alert(AlertType.WARNING);
		try{
		
		
		rating=Integer.parseInt(custRating.getText());
		
		Row row=resTable.getSelectionModel().getSelectedItem();
		
		if(name.equals("") || name==null  || name=="" || mail.equals("") || mail=="" || rating>5 || rating==0 || row.getRid()==0 || row.equals("") || row==null)
		{
		

			alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("All Fields are mandatory! Rating should be in scale of 5 and Please select a Restaurant");

		alert.showAndWait();
		resultArea.setText("All fields are manadatory");
		
		}
		else{
		
		
		boolean result=restDAO.rateRest(row.getRid(),name,mail,rating);
		if(result)
		{
			resultArea.setText("Rating has been successfully recorded");
			resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Rating has been successfully updated");

			alert.showAndWait();

		}
		else
		{
			resultArea.setText("Problem occured in rating");
			resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Problem occured in rating");

			alert.showAndWait();


		}
		
		}
		}catch(NumberFormatException ne){
			System.out.println(ne.getMessage());
			resultArea.setText("Please provide rating in Ineger in scale of 5");
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Please provide rating in Ineger in scale of 5");

			alert.showAndWait();

		}catch(Exception e){
			System.out.println(e.getMessage()+"    213548975465");
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Please select a restaurant");

			alert.showAndWait();

		}
		
			}
	
	//Add Res
	@FXML
	private void insertRes(ActionEvent actionEvent){
		
		Alert alert=new Alert(AlertType.WARNING);
		try{
		BigInteger ph= new BigInteger(phone.getText());
		String[] foodItemArr=foodItems.getText().split(";");
		
		String[] costArr=foodCost.getText().split(";");
		for(int i=0;i<foodItemArr.length;i++ )
		{
			System.out.println(foodItemArr[i]);
			
		}
		
			if(nameText.getText().equals("") || Address.getText().equals("") || phone.getText().equals("") || timmings.getText().equals("") || Rating.getText().equals("") || foodItems.getText().equals("") || foodCost.getText().equals(""))
			{
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("All Fields are mandatory! For adding a Restaurant");
				alert.showAndWait();
				resultArea.setText("All fields are manadatory");

			}
			else
			{
			
			boolean res=restDAO.insRest(nameText.getText(), Address.getText(),ph,timmings.getText(),Rating.getText(),foodItemArr,costArr);
		
			if(res)
			{
				resultArea.setText("Restaurant having name: "+nameText.getText()+" inserted succesfully");
				resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Restaurant added successfully");
				alert.showAndWait();
				
				System.out.println(res);
			}
			else
			{
				resultArea.setText("Problem occured during insertion of Restaurant Name"+nameText.getText()+". please check the restaurant exist or not");
				resultArea.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Error occured \n Please check Restaurant already existed or not ");
				alert.showAndWait();
				
				
				
			}
			}
		}catch(NumberFormatException ne){
			System.out.println("Please enter valid phone number");
			resultArea.setText("Please enter valid phone number");
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Please enter valid phone number");
			alert.showAndWait();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Probelm occured in inserting"+nameText.getText());
			resultArea.setText("Problem occured during insertion of Restaurant Name"+nameText.getText());
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Problem occured during adding Restaurant");
			alert.showAndWait();
			
		}
			
	}
	
	//search all Res
	
	@FXML
	private void searchAllRes(ActionEvent actionEvent) throws SQLException{
		
		data=FXCollections.observableArrayList();
		ResultSet rs=null;
		rs=restDAO.serachAllRes();
		Alert alert=new Alert(AlertType.WARNING);
		Row row;
		
			try {
				//rs.beforeFirst();
				while ( rs.next() ) {
				System.out.println("In while Loop");	
				row = new Row(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
				data.add(row);
				
				} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("All Fields are mandatory! For adding a Restaurant");
				alert.showAndWait();
				
			}
		
			resTable.setItems(data);
		
	}
	

}
