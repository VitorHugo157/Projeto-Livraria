package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.ClienteControl;
import control.LivroControl;
import entity.Cliente;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import persistence.DAOException;

public class ClienteBoundary implements EventHandler<ActionEvent>,
										   IGerarTela {
	
	private TextField tfCpf = new TextField();
	private TextField tfNome = new TextField();
	private TextField tfNascimento = new TextField();
	private TextField tfLogradouro = new TextField();
	private TextField tfNumLogra = new TextField();
	private TextField tfComplemento = new TextField();
	private TextField tfTelefone = new TextField();
	private TextField tfEmail = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCpf = new Label("CPF: ");
	private Label lblNome = new Label("Nome: ");
	private Label lblNascimento = new Label("Nascimento: ");
	private Label lblLogradouro = new Label("Logradouro: ");
	private Label lblNumLogra = new Label("Número: ");
	private Label lblComplemento = new Label("Complemento: ");
	private Label lblTelefone = new Label("Telefone: ");
	private Label lblEmail = new Label("Email: ");
	
	TableView<Cliente> table = new TableView<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	ClienteControl control = new ClienteControl();
	
	@Override
	public Pane gerarTela(String typeUser) {
		
		BorderPane panePrincipal = new BorderPane();
		GridPane paneTxt = new GridPane();
		FlowPane paneBotoes = new FlowPane();
		table = new TableView<>();
		
		paneTxt.setHgap(20);
		paneTxt.setVgap(15);
		paneBotoes.setVgap(10);
		paneBotoes.setHgap(25);
		
		ColumnConstraints colLabels = new ColumnConstraints();
		colLabels.setHalignment(HPos.RIGHT);
		colLabels.setPercentWidth(12);

		ColumnConstraints colTextField = new ColumnConstraints();
		colTextField.setPercentWidth(25);
		paneTxt.getColumnConstraints().addAll(colLabels, colTextField);
		
		panePrincipal.setCenter(paneTxt);
		panePrincipal.setBottom(table);
		
		vincularCampos();
		verifyUser(typeUser);
		generateTable();
		
		paneBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnExcluir);
		
		paneTxt.add(lblCpf, 0, 5);
		paneTxt.add(tfCpf, 1, 5);
		
		paneTxt.add(lblNome, 0, 6);
		paneTxt.add(tfNome, 1, 6);
		paneTxt.add(btnPesquisar, 2, 6);
		
		paneTxt.add(lblNascimento, 0, 7);
		paneTxt.add(tfNascimento, 1, 7);
		
		paneTxt.add(lblLogradouro, 0, 8);
		paneTxt.add(tfLogradouro, 1, 8);
		
		paneTxt.add(lblNumLogra, 0, 9);
		paneTxt.add(tfNumLogra, 1, 9);
		
		paneTxt.add(lblComplemento, 0, 10);
		paneTxt.add(tfComplemento, 1, 10);
		
		paneTxt.add(lblTelefone, 0, 11);
		paneTxt.add(tfTelefone, 1, 11);
		
		paneTxt.add(lblEmail, 0, 12);
		paneTxt.add(tfEmail, 1, 12);
		
		paneTxt.add(paneBotoes, 1, 15);
		
		paneTxt.add(new Label(""), 1, 18);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
//		tfCpf.textProperty().addListener(observable -> verificaCpf());
		
		return panePrincipal;
	}

//	private void verificaCpf() {
//		
//		if ( (tfCpf.getText().length() == 3) && !(".".equals(String.valueOf(tfCpf.getText().charAt(2))))) {
//			tfCpf.setText(tfCpf.getText() + ".");
//		}
//	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAdicionar) {
			if (campoIsBlank()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("adicionar");
			}
		
		} else if (e.getTarget() == btnAtualizar) {
			if (campoIsBlank()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("atualizar");
			}
		} else if (e.getTarget() == btnPesquisar) {
			executarComando("pesquisar");
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfCpf.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o CPF do cliente a ser excluido", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("excluir");
			}
		}
	}
	
	@Override
	public void vincularCampos() {
		
		StringConverter<? extends Number> converter = new IntegerStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter(formatter, formatter);
		Bindings.bindBidirectional(tfCpf.textProperty(), control.getCpfClienteProperty());
		Bindings.bindBidirectional(tfNome.textProperty(), control.getNomeClienteProperty());
		Bindings.bindBidirectional(tfNascimento.textProperty(), 
				control.getNascimentoClienteProperty(), dateConverter);
		Bindings.bindBidirectional(tfLogradouro.textProperty(), control.getLogradouroClienteProperty());
		Bindings.bindBidirectional(	tfNumLogra.textProperty(), 
				control.getNumLograClienteProperty(), (StringConverter<Number>)converter);
		Bindings.bindBidirectional(tfComplemento.textProperty(), control.getComplementoProperty());
		Bindings.bindBidirectional(tfTelefone.textProperty(), control.getTelefoneClienteProperty());
		Bindings.bindBidirectional(tfEmail.textProperty(), control.getEmailClienteProperty());
		
	}
	
	@Override
	public void generateTable() {
		TableColumn<Cliente, String> cpfColumn = new TableColumn<>("CPF");
		cpfColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
		nomeColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> nascimentoColumn = new TableColumn<>("Nascimento");
		nascimentoColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> logradouroColumn = new TableColumn<>("Logradouro");
		logradouroColumn.setMinWidth(200);
		
		TableColumn<Cliente, Integer> numLograColumn = new TableColumn<>("Número");
		numLograColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> complementoColumn = new TableColumn<>("Complemento");
		complementoColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> telefoneColumn = new TableColumn<>("Telefone");
		telefoneColumn.setMinWidth(200);
		
		TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(200);
		
		cpfColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("cpfCliente"));
		nomeColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("nomeCliente"));
		nascimentoColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getNascimentoCliente().format(formatter)); 
		});
		logradouroColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("logradouroCliente"));
		numLograColumn.setCellValueFactory( new PropertyValueFactory<Cliente, Integer>("numLograCliente"));
		complementoColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("complemento"));
		telefoneColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("telefone"));
		emailColumn.setCellValueFactory( new PropertyValueFactory<Cliente, String>("email"));
		
		table.getColumns().addAll(cpfColumn, nomeColumn, nascimentoColumn, logradouroColumn, numLograColumn, complementoColumn, telefoneColumn, emailColumn);
		table.setItems(control.getClientes());
	}
	
	@Override
	public boolean campoIsBlank() {
		return isBlank(tfCpf.getText()) || isBlank(tfNome.getText()) || isBlank(tfNascimento.getText()) ||
				isBlank(tfLogradouro.getText()) || isBlank(tfNumLogra.getText()) || isBlank(tfTelefone.getText()) ||
				isBlank(tfEmail.getText());
	}
	
	@Override
	public void executarComando(String cmd) {

		if ("adicionar".equals(cmd)) {
			try {
				control.inserirCliente();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarCliente();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarCliente();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirCliente();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void verifyUser(String typeUser) {
		if ("user".equals(typeUser)) {
			btnAdicionar.setVisible(false);
			btnExcluir.setVisible(false);
			btnAtualizar.setVisible(false);
		}
	}

	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}

	@Override
	public void limparCampos() {
		
		tfCpf.setText("");
		tfNome.setText("");
		tfNascimento.setText("");
		tfLogradouro.setText("");
		tfNumLogra.setText("");
		tfComplemento.setText("");
		tfTelefone.setText("");
		tfEmail.setText("");
	}

}
