package team.dna2.serviceDesk_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.stereotype.Component;
import team.dna2.serviceDesk_client.controllers.ClientApplication;
import team.dna2.serviceDesk_client.models.Role;
import team.dna2.serviceDesk_client.models.User;

import java.io.IOException;

@Component
public class ScreenManager {
    private static ClientApplication clientApplication;
    public static Window mainScreen;
    public static Window secondScreen;
    private static String userRole;

    /**
     * Чтобы переключать экраны
     */
    public ScreenManager() {
        clientApplication = ClientApplication.GetClientApplicationInstance();
    }

    /**
     * Сохранение основного окна, для дальнейшей работы с ним
     */
    public static void UpdateMainScreen() {
        mainScreen = Stage.getWindows().get(0);
    }

    /**
     * Сохранение дополнительного окна (создание обращения...) чтобы было открыто не больше одного
     */
    public static void UpdateSecondScreen() {
        secondScreen = Stage.getWindows().get(1);
    }

    /**
     * Переключение на экран входа в аккаунт при открытии приложения
     */
    public static void InitToLogIn() {
        clientApplication.ChangeScene("LoginScreen.fxml");
    }

    /**
     * WIP
     * Успешный вход в аккаунт
     */
    public static void OpenTickets() {
        UpdateMainScreen();
        userRole = User.currentUser.getRole();
        if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperTicketsScreen.fxml");
        else
            clientApplication.ChangeScene("MemberTicketsScreen.fxml");
    }

    /**
     * Производит выход из аккаунта, позволяя сменить пользователя.
     */
    public static void LogOut() {
        clientApplication.ChangeScene("LoginScreen.fxml");
    }

    /**
     * Создание обращения в новом окне
     */
    public static void CreateTicket() {
        if (Stage.getWindows().size() == 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Невозможно открыть более 1 дополнительного окна", ButtonType.CLOSE);
            alert.showAndWait();

            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ScreenManager.class.getResource("/views/CreateTicketScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 680);
            Stage stage = new Stage();
            stage.setTitle("Создание обращения");
            stage.setScene(scene);
            stage.show();
            stage.requestFocus();
            secondScreen = stage.getOwner();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * WIP
     * Экран просмотра обращения
     */
    public static void ShowTicket() {
        if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperShowTicketScreen.fxml");
        else
            clientApplication.ChangeScene("MemberShowTicketScreen.fxml");
    }

    /**
     * WIP
     * Экран личного профиля
     */
    public static void OpenMyProfile() {
        if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperProfileScreen.fxml");
        else
            clientApplication.ChangeScene("MemberProfileScreen.fxml");
    }

    /**
     * WIP
     * Экран оранизации пользователя
     */
    public static void OpenOrganisation() {
        if (Role.OWNER.getRole().equals(userRole))
            clientApplication.ChangeScene("OwnerOrganisationScreen.fxml");
        else if (Role.MEMBER.getRole().equals(userRole))
            clientApplication.ChangeScene("MemberOrganisationScreen.fxml");
        else if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperOrganisationScreen.fxml");
    }

    /**
     * WIP
     * Экран просмотра справочников
     */
    public static void OpenCompendiums() {
        if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperCompendiumsScreen.fxml");
    }

    /**
     * WIP
     * Экран просмотра статистики
     */
    public static void OpenStatistics() {
        if (Role.DEVELOPER.getRole().equals(userRole))
            clientApplication.ChangeScene("DeveloperStatisticsScreen.fxml");
    }
}