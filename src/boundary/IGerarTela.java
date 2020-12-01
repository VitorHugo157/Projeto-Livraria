package boundary;

import javafx.scene.layout.Pane;

public interface IGerarTela {
	
	Pane gerarTela(String typeUser);
	void vincularCampos();
	void generateTable();
	void executarComando(String cmd);
	void verifyUser(String typeUser);
	boolean campoIsBlank();
	boolean isBlank(String txt);
	void limparCampos();
}
