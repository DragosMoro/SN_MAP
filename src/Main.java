import domain.Friendship;
import domain.User;
import domain.validator.FriendshipValidator;
import domain.validator.UserValidator;
import domain.validator.Validator;
import presentation.UI;
import repository.InMemoryRepository;
import repository.Network;
import repository.Repository;
import repository.UserNetwork;
import service.Service;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        Validator<User> userValidator = UserValidator.getInstance();
        Validator<Friendship> friendshipValidator = FriendshipValidator.getInstance();
        Repository<Integer, User> inMemoryRepository = new InMemoryRepository<>();
        Network<User> userNetwork = new UserNetwork();
        Service<User> userService = new UserService(userValidator, friendshipValidator, inMemoryRepository, userNetwork);
        UI userInterface = new UI(userService);

        userInterface.run();
    }
}