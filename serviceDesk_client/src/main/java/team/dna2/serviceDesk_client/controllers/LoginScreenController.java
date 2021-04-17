package team.dna2.serviceDesk_client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginScreenController {
    private ApplicationContext context;

    @FXML
    private Button LogInButton;

    @FXML
    private void LogInClicked() {
        if (LogInButton.getText().equals("Вы успешно вошли в аккаунт!"))
            LogInButton.setText("Наконец-то я сделал этот грёбанный контроллер");
        else
            LogInButton.setText("Вы успешно вошли в аккаунт!");
    }

    public LoginScreenController() {
    }
}
