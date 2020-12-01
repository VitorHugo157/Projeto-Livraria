package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.EdicaoControl;
import entity.Edicao;
import entity.Livro;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import persistence.DAOException;

public class EdicaoBoundary implements EventHandler<ActionEvent>,
									      IGerarTela {
	
	private TextField tfIsbn = new TextField();
	private TextField tfPrecoEdicao = new TextField();
	private TextField tfAnoEdicao = new TextField();
	private TextField tfNumPaginas = new TextField();
	private TextField tfQtdEstoque = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblIsbn = new Label("ISBN: ");
	private Label lblPrecoEdicao = new Label("Preço: ");
	private Label lblAnoEdicao = new Label("Ano da edição: ");
	private Label lblNumPaginas = new Label("Número de pags: ");
	private Label lblQtdEstoque = new Label("Qtd em estoque: ");
	
	TableView<Edicao> table = new TableView<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	EdicaoControl control = new EdicaoControl();
	
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
		
		paneTxt.add(lblIsbn, 0, 5);
		paneTxt.add(tfIsbn, 1, 5);
		
		paneTxt.add(lblPrecoEdicao, 0, 6);
		paneTxt.add(tfPrecoEdicao, 1, 6);
		
		paneTxt.add(lblAnoEdicao, 0, 7);
		paneTxt.add(tfAnoEdicao, 1, 7);
		
		paneTxt.add(lblNumPaginas, 0, 8);
		paneTxt.add(tfNumPaginas, 1, 8);
		
		paneTxt.add(lblQtdEstoque, 0, 9);
		paneTxt.add(tfQtdEstoque, 1, 9);
		
		paneTxt.add(paneBotoes, 1, 12);
		paneTxt.add(new Label(""), 0, 16);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
		return panePrincipal;
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
			if (isBlank(tfIsbn.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o ISBN da edição a ser pesquisado", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfIsbn.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o ISBN da edição a ser excluída", 
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
        Bindings.bindBidirectional(	tfIsbn.textProperty(), 
        							control.getIsbnProperty(), 
        							(StringConverter<Number>)converter);
        StringConverter<? extends Number> converter2 = new DoubleStringConverter();
        Bindings.bindBidirectional(	tfPrecoEdicao.textProperty(), 
        							control.getPrecoEdicaoProperty(), 
        							(StringConverter<Number>)converter2);
        Bindings.bindBidirectional(tfAnoEdicao.textProperty(), 
				control.getAnoEdicaoProperty(),
				dateConverter);
        Bindings.bindBidirectional(	tfNumPaginas.textProperty(), 
				control.getNumPaginasProperty(), 
				(StringConverter<Number>)converter);
        Bindings.bindBidirectional(	tfQtdEstoque.textProperty(), 
				control.getQtdEstoqueProperty(), 
				(StringConverter<Number>)converter);
	}
	
	@Override
	public void generateTable() {
		TableColumn<Edicao, Integer> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(200);
		
		TableColumn<Edicao, Double> precoColumn = new TableColumn<>("Preço");
		precoColumn.setMinWidth(200);
		
		TableColumn<Edicao, String> anoColumn = new TableColumn<>("Ano Edição");
		anoColumn.setMinWidth(200);
		
		TableColumn<Edicao, Integer> numPaginasColumn = new TableColumn<>("Num de Pags");
		numPaginasColumn.setMinWidth(200);
		
		TableColumn<Edicao, Integer> qtdEstoqueColumn = new TableColumn<>("Qtd em Estoque");
		qtdEstoqueColumn.setMinWidth(200);
		
		isbnColumn.setCellValueFactory( new PropertyValueFactory<Edicao, Integer>("isbn"));
		precoColumn.setCellValueFactory( new PropertyValueFactory<Edicao, Double>("precoEdicao"));
		anoColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoEdicao().format(formatter)); 
		});
		numPaginasColumn.setCellValueFactory( new PropertyValueFactory<Edicao, Integer>("numPaginas"));
		qtdEstoqueColumn.setCellValueFactory( new PropertyValueFactory<Edicao, Integer>("qtdEstoque"));
		
		table.getColumns().addAll(isbnColumn, precoColumn, anoColumn, numPaginasColumn, qtdEstoqueColumn);
		table.setItems(control.getEdicoes());
	}
	
	@Override
	public boolean campoIsBlank() {
		return isBlank(tfIsbn.getText()) || isBlank(tfPrecoEdicao.getText()) || 
				isBlank(tfAnoEdicao.getText()) || isBlank(tfNumPaginas.getText()) || 
				isBlank(tfQtdEstoque.getText());
	}
	
	@Override
	public void executarComando(String cmd) {

		if ("adicionar".equals(cmd)) {
			try {
				control.inserirEdicao();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
			
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarEdicao();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarEdicoes();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirEdicao();
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
		
		tfIsbn.setText("");
		tfPrecoEdicao.setText("");
		tfAnoEdicao.setText("");
		tfNumPaginas.setText("");
		tfQtdEstoque.setText("");
	}

}
