package ui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;
import model.EmployeeList;

public class LaCucharitaGUI {
	
	EmployeeList employeeList;
	private ObservableList<Employee> observableList;
	
	public LaCucharitaGUI() {
		employeeList = new EmployeeList();
	}
	
	private Stage mainStage;
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	//**************************************************  Atributos  ***************************************************
	
	//----------------------------- WELCOME ---------------------
	//Atributos
	
	
	
	//Métodos
	
	
	//---------------------- LOGIN ------------------------
	@FXML
	private TextField txtEnterID;

	@FXML
	private PasswordField txtEnterPassword;

	
	
	//--------------- MODULES ------------------
	//Atributos

    
    // -------------------------------------- LIST_EMPLOYEES --------------------------
    @FXML
    private TableView<Employee> tvEmployeeList;

    @FXML
    private TableColumn<Employee, String> tcNameEmployeeName;

    @FXML
    private TableColumn<Employee, String> tcEmployeeIdentification;

    @FXML
    private TableColumn<Employee, String> tcEmployeeBirthday;
    
    
    
    // ---------------------- ADD_EMPLOYEE ------------------
    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private PasswordField pssEmployeePassword;

    @FXML
    private DatePicker dpEmployeeBirthday;
    
    
    // ---------------------- CHANGE_PASSWORD ------------------
    
    @FXML
    private TextField txtCheckId;

    @FXML
    private PasswordField pssCurrentPassword;

    @FXML
    private PasswordField pssNewPassword;
    
    
    //------------------------------------- INVENTORY ----------------------
    //Atributos
    @FXML
    private TableView<?> tvIngredientInventory;

    @FXML
    private TableColumn<?, ?> tcIngredientName;

    @FXML
    private TableColumn<?, ?> tcIngredientQuantity;

    @FXML
    private TextField txtEnterIngredientName;

    @FXML
    private TextField txtEnterIngredientQuantity;

    @FXML
    private ComboBox<?> cmbxQuantityUnits;
    
    
 
    // ----------------------- FOOD_MENU ------------------------
    @FXML
    private TableView<?> tcSaucers;

    @FXML
    private TableColumn<?, ?> tcSaucerName;

    @FXML
    private TableColumn<?, ?> tcSaucerPrice;
    
    
    
    //----------------------- ADD SOUCER ------------------------------
    @FXML
    private TextField txtSaucerName;

    @FXML
    private TextField txtSaucerIngredient;

    @FXML
    private TextField txtAmountSaucerIngredient;
    
    
    
  //----------------------- Falta hacer  módulo de pedidos  ------------------------------
    
    
    // *********************************************************   METODOS  *************************************************************
    
	// ----------------------------- WELCOME ---------------------
    
    //Arreglar
    /*@FXML
    void enterAdministratorAccount(ActionEvent event) {

    }
    
    @FXML
    public void enterEmployeeAccount(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
    */

	// ---------------------- LOGIN ------------------------
    
  	@FXML
  	public void logIn(ActionEvent event) throws IOException {
  		String enterID = txtEnterID.getText();
  		String enterPassword = txtEnterPassword.getText();
  		
  		String message = "";
    	if(employeeList.checkAccount(enterID, enterPassword)) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modules.fxml"));
    		fxmlLoader.setController(this);
    		Parent root = fxmlLoader.load();
    		Scene scene = new Scene(root);

    		mainStage.setScene(scene);
    		mainStage.show();
    	}else {
    		message = "No se pudo iniciar sesión. Identificación o contraseña inconrrectos";
    		errorAlert(message);
    	}
  	}
  	
  	@FXML
    void createAccount(ActionEvent event) throws IOException {
  		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEmployeeWindow.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

	// --------------- MODULES ------------------
  	@FXML
    public void enterToInventoryModule(ActionEvent event) throws IOException {
  		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

    @FXML
    public void enterToMenuModule(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodMenu.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

    
    // ///////// Corregir cuando exista el módulo de pedidos ////////////
    @FXML
    public void enterToOrderModule(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodMenu.fxml")); //Change the direction
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

    @FXML
    public void enterToPersonalModule(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListEmployees.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		
		initializeTableViewOfEmployees();
    }

    @FXML
    public void signOff(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
    

	// -------------------------- LIST_EMPLOYEES  ---------------------------
    @FXML
    public void returnFromEmployeeList(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modules.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

    
    @FXML
    public void toAddEmployeeWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEmployeeWindow.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
    
    @FXML
    void toChangePasswordWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

	// ---------------------- ADD_EMPLOYEE ------------------
    @FXML
    public void addEmployee(ActionEvent event) throws IOException {
    	String message = "";
    	if( (txtEmployeeName.getText()).equals("") == false &&
    			(txtEmployeeID.getText()).equals("") == false &&
    			(dpEmployeeBirthday.getValue().toString()).equals("") == false &&
    			(pssEmployeePassword.getText()).equals("") == false) {
    		
			String name = txtEmployeeName.getText();
			String id = txtEmployeeID.getText();
			String birthday = dpEmployeeBirthday.getValue().toString();
			String password = pssEmployeePassword.getText();

			employeeList.addEmployee(name, id, birthday, password);
			message = "Empleado añadido al restaurante correctamente.";
			confirmationAlert(message);
			
		} else {
			message = "Tiene que llenar todos los campos para crear un nuevo empleado en el restaurante.";
			errorAlert(message);
		}
    }

    @FXML
    public void returnFromAddEmployee(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListEmployees.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
    
    
    // ---------------------- CHANGE_PASSWORD ------------------
    
    @FXML
    public void changePassword(ActionEvent event) {
    	String checkId = txtCheckId.getText();
    	String currentPassword = pssCurrentPassword.getText();
    	String newPassword = pssNewPassword.getText();
    	
    	String message = "";
    	if(employeeList.checkAccount(checkId, currentPassword)) {
    		employeeList.changePassword(checkId, currentPassword, newPassword);
    		message = "Cambio de contraseña realizado satisfactoriamente!";
    		confirmationAlert(message);
    	}else {
    		message = "No se puede cambiar la contraseña. La cédula o la contraseña actual son incorrectas. Por favor, intente de nuevo";
    		errorAlert(message);
    	}
    }

    @FXML
    public void returnFromChangePassword(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListEmployees.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }


	// ------------------------------------- INVENTORY ----------------------
    @FXML
    public void addIngredient(ActionEvent event) {

    }

    @FXML
    public void clearFields(ActionEvent event) {

    }

    @FXML
    public void modifyIngredient(ActionEvent event) {

    }

    @FXML
    public void removeIngredient(ActionEvent event) {

    }

    @FXML
    public void returnFromInventory(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modules.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

	// ----------------------- FOOD_MENU ------------------------
    @FXML
    public void returnFromFoodMenu(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modules.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }

    @FXML
    public void toAddSaucerWindow(ActionEvent event) {

    }
    

	// ----------------------- ADD SOUCER ------------------------------
    @FXML
    public void addSaucer(ActionEvent event) {

    }

    @FXML
    public void addSaucerIngredient(ActionEvent event) {

    }

    @FXML
    public void returnFromAddSaucer(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodMenu.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
    
    
    public void confirmationAlert(String message) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Restaurante La Cucharita");
		alert.setHeaderText("Accion exitosa!");
		alert.setContentText(message);
		alert.show();
    }
    
    public void errorAlert(String message) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Restaurante La Cucharita");
		alert.setHeaderText("Error");
		alert.setContentText(message);
		alert.show();
    }
    
    private void initializeTableViewOfEmployees() {
    	observableList = FXCollections.observableArrayList(employeeList.getEmployees());
    	tvEmployeeList.setItems(observableList);
    	tcNameEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    	tcEmployeeIdentification.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
    	tcEmployeeBirthday.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
    }
}
