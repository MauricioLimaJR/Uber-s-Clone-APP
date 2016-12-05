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
        Label lblTitulo = new Label("Questionário Importante");
        Button btnNome = new Button("Entrar com Nome");
        Button btnRegiao = new Button("Região que mora");
        Button btnExe = new Button("Faz exercício?");
        Button btnRes = new Button("Ver Resultado");

        lblTitulo.setFont(Font.font(24));

        btnNome.setOnAction(e -> {
            TextInputDialog dialogoNome = new TextInputDialog();

            dialogoNome.setTitle("Entrada de nome");
            dialogoNome.setHeaderText("Entre com seu nome");
            dialogoNome.setContentText("Nome:");
            // se o usuário fornecer um valor, assignamos ao nome
            dialogoNome.showAndWait().ifPresent(v -> nome = v);

        });
        btnRegiao.setOnAction(e -> {
            // o primeiro parâmetro é a escola padrão e os outros são os valores da Choice Box
            ChoiceDialog dialogoRegiao = new ChoiceDialog("Sul", "Sul", "Leste", "Oeste", "Norte");
            dialogoRegiao.setTitle("Entrada de Região");
            dialogoRegiao.setHeaderText("Informe sua região abaixo");
            dialogoRegiao.setContentText("Região:");
            dialogoRegiao.showAndWait().ifPresent(r -> regiao = r.toString());
        });
        btnExe.setOnAction(e -> {
            Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");
            ButtonType btnAsVezes = new ButtonType("As vezes");
            ButtonType btnNaoResponder = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialogoExe.setTitle("Pergunta sobre exercício");
            dialogoExe.setHeaderText("Informe se você faz exercício");
            dialogoExe.setContentText("Você faz exercício?");
            dialogoExe.getButtonTypes().setAll(btnSim, btnNao, btnAsVezes, btnNaoResponder);
            dialogoExe.showAndWait().ifPresent(b -> {
                if (b == btnSim) {
                    fazExe = "faz exercício";
                } else if (b == btnNao) {
                    fazExe = "não faz exercício";
                } else if (b == btnAsVezes) {
                    fazExe = "faz exercício as vezes";
                } else {
                    fazExe = "não quis responder";
                }
            });
        });

        btnRes.setOnAction(e -> {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Resultado do questionário");
            dialogoResultado.setContentText(nome + " mora na região " + regiao + " e " + fazExe + ".");
            dialogoResultado.showAndWait();

        });

        VBox raiz = new VBox(20);
        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(lblTitulo, btnNome, btnRegiao, btnExe, btnRes);

        Scene cena = new Scene(raiz, 450, 250);
        stage.setTitle("Diálogos para entradas dos usuários");
        stage.setScene(cena);
        stage.show();
    }

}