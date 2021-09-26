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
import model.IngredientOfSaucer;
import model.IngredientsList;
import model.Saucer;
import model.SaucerList;

public class LaCucharitaGUI {
	EmployeeList employeeList;
	private ObservableList<Employee> observableListEmployees;
	private List<String> ingredientsToCmbx;
	private IngredientsList ingredientsList;
	private List<IngredientOfSaucer> ingredientsOfSaucer;
	private List<String> ingredientsOfSaucerToRemove;
	private SaucerList saucerList;
	private IngredientOfSaucer ingredientOfSaucer;
	
	public LaCucharitaGUI() {
		employeeList = new EmployeeList();
		ingredientsList = new IngredientsList();
		ingredientsToCmbx = new ArrayList<String>();
		ingredientsOfSaucerToRemove = new ArrayList<String>();
		ingredientsOfSaucer = new ArrayList<IngredientOfSaucer>();
		saucerList = new SaucerList();
	//	ingredientsToSaucer = new ArrayList<String>();
	}
	
	private Stage mainStage;
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	//**************************************************  Atributos  ***************************************************

	
	
	//---------------------- LOGIN ------------------------
	@FXML
	private TextField txtEnterID;

	@FXML
	private PasswordField txtEnterPassword;
	
  	//----------------------------------- CREATE_ACCOUNT ----------------------------
	
	@FXML
    private TextField txtEmployeeNameCA;

    @FXML
    private TextField txtEmployeeIDCA;

    @FXML
    private PasswordField pssEmployeePasswordCA;

    @FXML
    private DatePicker dpEmployeeBirthdayCA;
	
	
	//--------------------------------------------- MODULES ---------------------------------------
    
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
    private TableView<Saucer> tvSaucers;

    @FXML
    private TableColumn<Saucer, String> tcSaucerName;

    @FXML
    private TableColumn<Saucer, Double> tcSaucerPrice;
    
    
    //----------------------- ADD SOUCER ------------------------------
    @FXML
    private TextField txtSaucerName;

    @FXML
    private TextField txtSaucerPrice;

    @FXML
    private TableView<Ingredient> tvSaucerIngredients;

    @FXML
    private TableColumn<Ingredient, String> tcSaucerIngredientName;

    @FXML
    private TableColumn<Ingredient, Double> tcSaucerIngredientQuantity;

    @FXML
    private TableColumn<Ingredient, String> tcSaucerIngredientUnitsOfQuantity;

    @FXML
    private ComboBox<String> cmbxAddIngredientToSaucer;

    @FXML
    private TextField txtRequiredQuantity;
    
    public double checkRequiredQuantity() {
    	double requiredQuantity = 0;
    	if(txtRequiredQuantity.getText().equals("")==false) {
    		requiredQuantity = Double.parseDouble(txtRequiredQuantity.getText());
    	}
    	return requiredQuantity;
    }
    
    @FXML
    private ComboBox<String> cmbxRequiredQuantityUnits;
    

    @FXML
    private ComboBox<String> cmbxIngredientToRemoveFromSaucer;
    
     
    
  //----------------------- Falta hacer  módulo de pedidos  ------------------------------
    
    
    // *********************************************************   METODOS  *************************************************************
   
    
	// ---------------------- LOGIN ------------------------
    
  	@FXML
  	public void logIn(ActionEvent event) throws IOException {
  		String enterID = txtEnterID.getText();
  		String enterPassword = txtEnterPassword.getText();
  		
  		String message = "";
    	if( (employeeList.checkAccount(enterID, enterPassword)) || employeeList.checkCreatorAccount(enterID, enterPassword)) {
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
    public void createAccount(ActionEvent event) throws IOException {
  		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddEmployeeCA.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
    }
  	
  	//--------------------------------------------------- CREATE_ACCOUNT ---------------------------------
  	
  	 @FXML
     public void addEmployeeCA(ActionEvent event) {
  		String message = "";
    	if( (txtEmployeeNameCA.getText()).equals("") == false &&
    			(txtEmployeeIDCA.getText()).equals("") == false &&
    			(dpEmployeeBirthdayCA.getValue().toString()).equals("") == false &&
    			(pssEmployeePasswordCA.getText()).equals("") == false) {
    		
			String name = txtEmployeeNameCA.getText();
			String id = txtEmployeeIDCA.getText();
			String birthday = dpEmployeeBirthdayCA.getValue().toString();
			String password = pssEmployeePasswordCA.getText();

			employeeList.addEmployee(name, id, birthday, password);
			message = "Empleado añadido al restaurante correctamente.";
			confirmationAlert(message);
			
		} else {
			message = "Tiene que llenar todos los campos para crear un nuevo empleado en el restaurante.";
			errorAlert(message);
		}
     }

     @FXML
     public void returnFromAddEmployeeCA(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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
		try {
			initializeTableViewOfIngredients();
		}catch(NullPointerException ex) {
			String message = "Agregue ingredientes a la tabla";
			informationAlert(message);
		}
		
    }

    @FXML
    public void enterToMenuModule(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodMenu.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		
		initializeTableViewOfSaucers();
		
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
    public void toChangePasswordWindow(ActionEvent event) throws IOException {
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
		initializeTableViewOfEmployees();
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
		initializeTableViewOfEmployees();
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
		initializeComboBoxOfUnits();
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
		initializeComboBoxOfUnits();
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
	public void toAddSaucerWindow(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSaucer.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeComboBoxOfUnitsInAddSoucerWindow();
		initializeComboBoxOfIngredientsInAddSoucerWindow();	
		
	}

	// --------------------------------------------------------------- ADD SOUCER ----------------------------------------------------
	@FXML
	public void addIngredientToSaucer(ActionEvent event) {
		String message = "";
		if ( (cmbxAddIngredientToSaucer.getSelectionModel().getSelectedItem().equals("") == false) && (cmbxRequiredQuantityUnits.getSelectionModel().getSelectedItem().equals("")==false) ) {
			String quantityUnits = cmbxRequiredQuantityUnits.getSelectionModel().getSelectedItem();
			for(int i=0; i<(ingredientsList.getIngredients()).size();i++) {
				if (cmbxAddIngredientToSaucer.getSelectionModel().getSelectedItem().equals(ingredientsList.getIngredients().get(i).getIngredientName())) {
					if( (checkRequiredQuantity() < (ingredientsList.getIngredients().get(i).getQuantity())) && (checkRequiredQuantity() != 0) ) {
						ingredientOfSaucer = new IngredientOfSaucer(ingredientsList.getIngredients().get(i).getIngredientName(),checkRequiredQuantity(),quantityUnits );
						ingredientsOfSaucer.add(ingredientOfSaucer);
						ingredientsOfSaucerToRemove.add(ingredientsList.getIngredients().get(i).getIngredientName());
						message = "Ingrediente añadido al platillo satisfactoriamente!";
						confirmationAlert(message);
						
					}else {
						message = "No hay la cantidad que usted requiere para el ingrediente.";
						errorAlert(message);
					}
				}
			}
			initializeComboBoxOfIngredientsToRemoveInAddSoucerWindow();
			initializeTableViewOfIngredientsOFSaucer(); 
		}else {
			message = "Debe llenar todos los campos.";
			errorAlert(message);
		}
		
	}

	
	@FXML
	public void removeIngredientFromSaucer(ActionEvent event) {
		String message = "";
		if (cmbxIngredientToRemoveFromSaucer.getSelectionModel().getSelectedItem().equals("") == false) {
			String ingredientToRemove = cmbxIngredientToRemoveFromSaucer.getSelectionModel().getSelectedItem();
			for (int i = 0; i < ingredientsOfSaucer.size(); i++) { 
				if (ingredientToRemove.equals(ingredientsOfSaucer.get(i).getIngredientName())) {
					ingredientsOfSaucer.remove(i);
					message = "Ingrediente eliminado del platillo satisfactoriamente.";
					confirmationAlert(message);
				}
				initializeTableViewOfIngredientsOFSaucer();
			}
		} else {
			message = "Debe seleccionar el ingrediente que quiere eliminar del platillo.";
			errorAlert(message);
		}
	}
	
	
	@FXML
	public void addSaucer(ActionEvent event) {
		String message = "";
		if ((txtSaucerName.getText().equals("") == false) && (txtSaucerPrice.getText().equals("") == false)) {
			double price = Double.parseDouble(txtSaucerPrice.getText());
			String saucerName = txtSaucerName.getText();
			saucerList.addSaucer(saucerName, price, ingredientsOfSaucer);
			for (int i = 0; i < ingredientsOfSaucer.size(); i++) {
				
				for (int j = 0; j < ingredientsList.getIngredients().size(); j++) {
					String ingredientName = ingredientsList.getIngredients().get(j).getIngredientName();
					if (ingredientsOfSaucer.get(i).getIngredientName().equals(ingredientName)) {
						ingredientsList.getIngredients().get(j).setQuantity((ingredientsList.getIngredients().get(j).getQuantity()) - ingredientsOfSaucer.get(i).getQuantity());
					}
				}
				
			}
			message = "Platillo creado satisfactoriamente!";
			confirmationAlert(message);
			initializeTableViewOfSaucers();
			ingredientsOfSaucer = new ArrayList<IngredientOfSaucer>();
		} else {
			message = "Debe llenar todos los campos";
			errorAlert(message);
		}
	}

	
	@FXML
	public void returnFromAddSaucer(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodMenu.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);

		mainStage.setScene(scene);
		mainStage.show();
		initializeTableViewOfSaucers();
	}
	
	
	
	public void initializeComboBoxOfUnitsInAddSoucerWindow() {
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
		cmbxRequiredQuantityUnits.setItems(observableList);
	}
	
	
	public void initializeComboBoxOfIngredientsInAddSoucerWindow() {
		ObservableList<String> observableListIngredientsToCmbx = FXCollections.observableArrayList(ingredientsToCmbx);
		cmbxAddIngredientToSaucer.setItems(observableListIngredientsToCmbx);
	}
	
	public void initializeComboBoxOfIngredientsToRemoveInAddSoucerWindow() {
		ObservableList<String> observableListIngredientsToCmbx = FXCollections.observableArrayList(ingredientsOfSaucerToRemove);
		cmbxIngredientToRemoveFromSaucer.setItems(observableListIngredientsToCmbx);
	}
	
	public void initializeTableViewOfIngredientsOFSaucer() {
    	ObservableList<Ingredient> observableListIngredientsOfSaucer = FXCollections.observableArrayList(ingredientsOfSaucer); 
    	tvSaucerIngredients.setItems(observableListIngredientsOfSaucer);
    	tcSaucerIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("ingredientName"));
    	tcSaucerIngredientQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("quantity"));
    	tcSaucerIngredientUnitsOfQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("quantityUnits"));
    }
	
	
	public void initializeTableViewOfSaucers() {
    	ObservableList<Saucer> observableListSaucers = FXCollections.observableArrayList(saucerList.getSaucers()); 
    	tvSaucers.setItems(observableListSaucers);
    	tcSaucerName.setCellValueFactory(new PropertyValueFactory<Saucer, String>("nameSaucer"));
    	tcSaucerPrice.setCellValueFactory(new PropertyValueFactory<Saucer, Double>("price"));
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
	
	public void informationAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Restaurante la Cucharita");
		alert.setHeaderText("Información");
		alert.setContentText(message);
		alert.show();
	}

}
