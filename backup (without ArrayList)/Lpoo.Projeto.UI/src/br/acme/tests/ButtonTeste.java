package br.acme.tests;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class ButtonTeste extends Application {

    String nome, regiao, fazExe;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void start(Stage stage) {
        Label lblTitulo = new Label("Question�rio Importante");
        Button btnNome = new Button("Entrar com Nome");
        Button btnRegiao = new Button("Regi�o que mora");
        Button btnExe = new Button("Faz exerc�cio?");
        Button btnRes = new Button("Ver Resultado");

        lblTitulo.setFont(Font.font(24));

        btnNome.setOnAction(e -> {
            TextInputDialog dialogoNome = new TextInputDialog();

            dialogoNome.setTitle("Entrada de nome");
            dialogoNome.setHeaderText("Entre com seu nome");
            dialogoNome.setContentText("Nome:");
            // se o usu�rio fornecer um valor, assignamos ao nome
            dialogoNome.showAndWait().ifPresent(v -> nome = v);

        });
        btnRegiao.setOnAction(e -> {
            // o primeiro par�metro � a escola padr�o e os outros s�o os valores da Choice Box
            ChoiceDialog dialogoRegiao = new ChoiceDialog("Sul", "Sul", "Leste", "Oeste", "Norte");
            dialogoRegiao.setTitle("Entrada de Regi�o");
            dialogoRegiao.setHeaderText("Informe sua regi�o abaixo");
            dialogoRegiao.setContentText("Regi�o:");
            dialogoRegiao.showAndWait().ifPresent(r -> regiao = r.toString());
        });
        btnExe.setOnAction(e -> {
            Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("N�o");
            ButtonType btnAsVezes = new ButtonType("As vezes");
            ButtonType btnNaoResponder = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialogoExe.setTitle("Pergunta sobre exerc�cio");
            dialogoExe.setHeaderText("Informe se voc� faz exerc�cio");
            dialogoExe.setContentText("Voc� faz exerc�cio?");
            dialogoExe.getButtonTypes().setAll(btnSim, btnNao, btnAsVezes, btnNaoResponder);
            dialogoExe.showAndWait().ifPresent(b -> {
                if (b == btnSim) {
                    fazExe = "faz exerc�cio";
                } else if (b == btnNao) {
                    fazExe = "n�o faz exerc�cio";
                } else if (b == btnAsVezes) {
                    fazExe = "faz exerc�cio as vezes";
                } else {
                    fazExe = "n�o quis responder";
                }
            });
        });

        btnRes.setOnAction(e -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Resultado do question�rio");
            dialogoResultado.setContentText(nome + " mora na regi�o " + regiao + " e " + fazExe + ".");
            dialogoResultado.showAndWait();

        });

        VBox raiz = new VBox(20);
        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(lblTitulo, btnNome, btnRegiao, btnExe, btnRes);

        Scene cena = new Scene(raiz, 450, 250);
        stage.setTitle("Di�logos para entradas dos usu�rios");
        stage.setScene(cena);
        stage.show();
    }

}