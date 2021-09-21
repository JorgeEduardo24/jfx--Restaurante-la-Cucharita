package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import model.Ingredient;
import model.IngredientsList;

public class LaCucharitaGUI {
	EmployeeList employeeList;
	private ObservableList<Employee> observableListEmployees;
	private List<String> ingredientsToCmbx;
	private IngredientsList ingredientsList;
	
	public LaCucharitaGUI() {
		employeeList = new EmployeeList();
		ingredientsList = new IngredientsList();
		ingredientsToCmbx = new ArrayList<String>();
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
    private TableView<Ingredient> tvIngredientInventory;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientName;

    @FXML
    private TableColumn<Ingredient, Double> tcIngredientQuantity;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientQuantityUnits;

    @FXML
    private TextField txtEnterIngredientName;

    @FXML
    private TextField txtEnterIngredientQuantity;

    @FXML
    private ComboBox<String> cmbxQuantityUnits;
    
    
    //---------------------------  MODIFY_INGREDIENT_WINDOW ------------------------------
    @FXML
    private TextField txtQuantityToModify;

    @FXML
    private ComboBox<String> cmbxQuantityUnitsToModify;

    @FXML
    private ComboBox<String> cmbxIngredients;
    
    
    
    
    //----------------------------- REMOVE_INGREDIENT_WINDOW -----------------------------
    @FXML
    private ComboBox<String> cmbxIngredientsRemove;
    
    
 
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
		initializeComboBoxOfUnits();
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
    

	// ----------------------------------------------------- LIST_EMPLOYEES  --------------------------------------------------
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
    
    
    private void initializeTableViewOfEmployees() {
    	observableListEmployees = FXCollections.observableArrayList(employeeList.getEmployees());
    	tvEmployeeList.setItems(observableListEmployees);
    	tcNameEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    	tcEmployeeIdentification.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
    	tcEmployeeBirthday.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
    }


	// --------------------------------------------------------------------- INVENTORY --------------------------------------------------
    
    // ------- GENERAL_INVENTORY ------
    @FXML
    public void addIngredient(ActionEvent event) {
		String message = "";
		if (txtEnterIngredientName.getText().equals("") == false
				&& txtEnterIngredientQuantity.getText().equals("") == false
				&& cmbxQuantityUnits.getSelectionModel().getSelectedItem().equals("") == false) {

			String ingredientName = txtEnterIngredientName.getText();
			double ingredientQuantity = Double.parseDouble(txtEnterIngredientQuantity.getText());
			String quantityUnits = cmbxQuantityUnits.getSelectionModel().getSelectedItem();

			Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity, quantityUnits);
			ingredientsList.addIngredient(ingredient);
			message = "Ingrediente agregado satisfactoriamente.";
			confirmationAlert(message);
			initializeTableViewOfIngredients();
			addIngredientToCmbx(ingredient.getIngredientName());
			
		} else {
			message = "Debe llenar todos los campos para agregar un ingrediente.";
			errorAlert(message);
		}
	}

    @FXML
    public void clearFields(ActionEvent event) {
    	txtEnterIngredientName.setText("");
    	txtEnterIngredientQuantity.setText("");
    }

    @FXML
    public void toModifyIngredientWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyIngredient.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeComboBoxOfModifyUnits();
		initializeComboBoxOfIngredients();
    }

    @FXML
    public void toRemoveIngredientWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveIngredient.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeComboBoxOfIngredientsToRemove();
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
    
    public void initializeTableViewOfIngredients() {
    	ObservableList<Ingredient> observableListIngredients = FXCollections.observableArrayList(ingredientsList.getIngredients()); 
    	tvIngredientInventory.setItems(observableListIngredients);
    	tcIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("ingredientName"));
    	tcIngredientQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("quantity"));
    	tcIngredientQuantityUnits.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("quantityUnits"));
    }
    
    // ---------------------------  MODIFY_INGREDIENT_WINDOW ------------------------------
    
	@FXML
	public void modifyIngredient(ActionEvent event) {
		String message = "";
		if (cmbxIngredients.getSelectionModel().getSelectedItem().equals("") == false) {
			String ingredientsInCmbx = cmbxIngredients.getSelectionModel().getSelectedItem();
			for (int i = 0; i < (ingredientsList.getIngredients()).size(); i++) {
				if (ingredientsInCmbx.equals(ingredientsList.getIngredients().get(i).getIngredientName())) {
					ingredientsList.getIngredients().get(i)
							.setQuantity(Double.parseDouble(txtQuantityToModify.getText()));
					ingredientsList.getIngredients().get(i)
							.setQuantityUnits(cmbxQuantityUnitsToModify.getSelectionModel().getSelectedItem());
					message = "Ingrediente modificado satisfactoriamente";
					confirmationAlert(message);
				}
			}
		} else {
			message = "Seleccione el ingrediente que quiere modificar";
			errorAlert(message);
		}

	}
    
	@FXML
	void returnFromModifyIngredient(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeTableViewOfIngredients();
	}
    
    // --------------------------- REMOVE_INGREDIENT_WINDOW --------------------------------
	@FXML
	public void removeIngredient(ActionEvent event) {
		String message = "";

		if (cmbxIngredientsRemove.getSelectionModel().getSelectedItem().equals("") == false) {
			String ingredientsInCmbx = cmbxIngredientsRemove.getSelectionModel().getSelectedItem();
			for (int i = 0; i < (ingredientsList.getIngredients()).size(); i++) {
				if (ingredientsInCmbx.equals(ingredientsList.getIngredients().get(i).getIngredientName())) {
					ingredientsList.getIngredients().remove(i);
					ingredientsToCmbx.remove(i);
					message = "Ingrediente eliminado satisfactoriamente";
					confirmationAlert(message);
				}
			}
		} else {
			message = "Debe seleccionar el ingrediente que quiere eliminar";
			errorAlert(message);
		}

	}

    @FXML
    public void returnFromRemoveIngredient(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeTableViewOfIngredients();
    }

    
    
	public void initializeComboBoxOfUnits() {
		List<String> quantityUnits = new ArrayList<String>();

		String quantityUnits1 = "Kilogramos";
		String quantityUnits2 = "Gramos";
		String quantityUnits3 = "Mililitros";
		String quantityUnits4 = "Unidades";

		quantityUnits.add(quantityUnits1);
		quantityUnits.add(quantityUnits2);
		quantityUnits.add(quantityUnits3);
		quantityUnits.add(quantityUnits4);

		ObservableList<String> observableList = FXCollections.observableArrayList(quantityUnits);
		cmbxQuantityUnits.setItems(observableList);
	}
    
	public void initializeComboBoxOfModifyUnits() {
		List<String> quantityUnits = new ArrayList<String>();

		String quantityUnits1 = "Kilogramos";
		String quantityUnits2 = "Gramos";
		String quantityUnits3 = "Mililitros";
		String quantityUnits4 = "Unidades";

		quantityUnits.add(quantityUnits1);
		quantityUnits.add(quantityUnits2);
		quantityUnits.add(quantityUnits3);
		quantityUnits.add(quantityUnits4);

		ObservableList<String> observableList = FXCollections.observableArrayList(quantityUnits);
		cmbxQuantityUnitsToModify.setItems(observableList);
	}

	public void addIngredientToCmbx(String ingredientName) {
		ingredientsToCmbx.add(ingredientName);
	}

	public void initializeComboBoxOfIngredients() {
		ObservableList<String> observableListIngredientsToCmbx = FXCollections.observableArrayList(ingredientsToCmbx);
		cmbxIngredients.setItems(observableListIngredientsToCmbx);
	}

	public void initializeComboBoxOfIngredientsToRemove() {
		ObservableList<String> observableListIngredientsToCmbx = FXCollections.observableArrayList(ingredientsToCmbx);
		cmbxIngredientsRemove.setItems(observableListIngredientsToCmbx);
	}
    
    
	// ------------------------------------------------------------ FOOD_MENU --------------------------------------------------
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

	// --------------------------------------------------------------- ADD SOUCER ----------------------------------------------------
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

}
