package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.ClienteLivroControl;
import entity.ClienteLivro;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import persistence.DAOException;

public class ClienteLivroBoundary implements EventHandler<ActionEvent>, 
											    IGerarTela {
	
	private TextField tfCpf = new TextField();
	private TextField tfCodigoLivro = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCpf = new Label("CPF: ");
	private Label lblLivro = new Label("Codigo Livro: ");
	
	private TableView<ClienteLivro> table = new TableView<>();
	private ClienteLivroControl control = new ClienteLivroControl();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
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
		colTextField.setPercentWidth(50);
		paneTxt.getColumnConstraints().addAll(colLabels, colTextField);
		
		panePrincipal.setCenter(paneTxt);
		panePrincipal.setBottom(table);
		
		vincularCampos();
		verifyUser(typeUser);
		generateTable();
		
		paneBotoes.getChildren().addAll(btnAdicionar, btnPesquisar, btnAtualizar, btnExcluir);
		
		paneTxt.add(lblCpf, 0, 5);
		paneTxt.add(tfCpf, 1, 5);
		
		paneTxt.add(lblLivro, 0, 6);
		paneTxt.add(tfCodigoLivro, 1, 6);
		
		paneTxt.add(paneBotoes, 1, 9);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
		return panePrincipal;
	}
	
	@Override
	public void executarComando(String cmd) {
		
		if ("adicionar".equals(cmd)) {
			try {
				control.inserirClienteLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarClienteLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarClienteLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirClienteLivro();
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
			if ( !((isBlank(tfCpf.getText()))) || !((isBlank(tfCodigoLivro.getText()))) ) {
				JOptionPane.showMessageDialog(null, "Consultas de associações não devem ter campos preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfCpf.getText()) || isBlank(tfCodigoLivro.getText())) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("excluir");
			}
		}
	}

	@Override
	public void vincularCampos() {
		
		StringConverter<? extends Number> converter = new IntegerStringConverter();
		Bindings.bindBidirectional(tfCpf.textProperty(), control.getCpfClienteProperty());
		Bindings.bindBidirectional(	tfCodigoLivro.textProperty(), 
				control.getCodigoLivroProperty(), 
				(StringConverter<Number>)converter);
	}

	@Override
	public void generateTable() {
		
		TableColumn<ClienteLivro, String> cpfColumn = new TableColumn<>("CPF");
		cpfColumn.setMinWidth(125);
		
		TableColumn<ClienteLivro, Integer> livroColumn = new TableColumn<>("Codigo Livro");
		livroColumn.setMinWidth(125);
		
		TableColumn<ClienteLivro, String> nomeClienteColumn = new TableColumn<>("Nome Cliente");
		nomeClienteColumn.setMinWidth(250);
		
		TableColumn<ClienteLivro, String> nomeLivroColumn = new TableColumn<>("Nome Livro");
		nomeLivroColumn.setMinWidth(250);
		
		TableColumn<ClienteLivro, String> idiomaLivroColumn = new TableColumn<>("Idioma Livro");
		idiomaLivroColumn.setMinWidth(125);
		
		TableColumn<ClienteLivro, String> anoLivroColumn = new TableColumn<>("Ano Lancamento");
		anoLivroColumn.setMinWidth(150);
		
		cpfColumn.setCellValueFactory( new PropertyValueFactory<ClienteLivro, String>("cpfCliente"));
		livroColumn.setCellValueFactory( new PropertyValueFactory<ClienteLivro, Integer>("codigoLivro"));
		nomeClienteColumn.setCellValueFactory( new PropertyValueFactory<ClienteLivro, String>("nomeCliente"));
		nomeLivroColumn.setCellValueFactory( new PropertyValueFactory<ClienteLivro, String>("nomeLivro"));
		idiomaLivroColumn.setCellValueFactory( new PropertyValueFactory<ClienteLivro, String>("idiomaLivro"));
		anoLivroColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoLancamento().format(formatter)); 
		});
		
		table.getColumns().addAll(cpfColumn, livroColumn, nomeClienteColumn, nomeLivroColumn, idiomaLivroColumn, anoLivroColumn);
		table.setItems(control.getLista());
	}
	
	@Override
	public boolean campoIsBlank() {

		return isBlank(tfCpf.getText()) || isBlank(tfCodigoLivro.getText());
	}
	
	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}
	
	@Override
	public void limparCampos() {
		
		tfCpf.setText("");
		tfCodigoLivro.setText("");
	}
}
