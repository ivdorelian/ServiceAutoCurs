import Domain.CarValidator;
import Repository.CarRepository;
import Service.CarService;
import UI.Console;

public class Main {

    public static void main(String[] args) {
        CarValidator validator = new CarValidator();
        CarRepository repository = new CarRepository(validator);
        CarService service = new CarService(repository);
        service.enterService(1, 1, "CJ02FDS", 2);
        service.enterService(2, 2, "CJ02FDS", 2);
        service.exitService(1, "ulei", 100);
        service.enterService(3, 1, "CJ02FDS", 2);
        service.exitService(2, "ulei", 80);
        service.enterService(4, 2, "CJ02FDS", 2);
        service.exitService(1, "ulei", 60);
        service.enterService(5, 3, "CJ02FDS", 2);
        service.exitService(2, "ulei", 90);
        service.enterService(6, 2, "CJ02FDS", 2);
        service.enterService(9, 12, "CJ02FDS", 2);
        Console console = new Console(service);
        console.run();
    }
}
