package Service;

import Domain.Car;
import Domain.StandAveragePriceViewModel;
import Repository.CarRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarService {

    private CarRepository repository;

    /**
     * ...
     * @param repository
     */
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    /**
     * ...
     * @param id
     * @param stand
     * @param license
     * @param days
     */
    public void enterService(int id, int stand, String license, int days) {

        Car car = new Car(id, stand, license, days);
        List<Car> cars = repository.getAll();
        for (Car c : cars) {
            if (c.getStand() == stand && !c.isLeftService()) {
                throw new RuntimeException("That stand is already taken!");
            }
        }
        repository.add(car);
    }

    /**
     *
     * @param stand
     * @param report
     * @param price
     */
    public void exitService(int stand, String report, double price) {
        Car carOnStand = null;
        List<Car> cars = repository.getAll();
        for (Car c : cars) {
            if (c.getStand() == stand && !c.isLeftService()) {
                carOnStand = c;
            }
        }

        if (carOnStand != null) {
            carOnStand.setReport(report);
            carOnStand.setPrice(price);
            carOnStand.setLeftService(true);
            repository.update(carOnStand);
        } else {
            throw new RuntimeException("There is no car on the given stand!");
        }
    }

    public List<StandAveragePriceViewModel> getStandPriceAverages() {
        List<StandAveragePriceViewModel> results = new ArrayList<>();
        Map<Integer, List<Double>> pricesForStands = new HashMap<>();

        for (Car c : repository.getAll()) {
            if (c.isLeftService()) {
                int stand = c.getStand();
                double price = c.getPrice();

                if (!pricesForStands.containsKey(stand)) {
                    pricesForStands.put(stand, new ArrayList<>());
                }
                pricesForStands.get(stand).add(price);
            }
        }

        for (int stand : pricesForStands.keySet()) {
            List<Double> prices = pricesForStands.get(stand);
            double average = 0;
            for (double p : prices) {
                average += p;
            }
            average /= prices.size();
            results.add(new StandAveragePriceViewModel(stand, average));
        }

//        results.sort((r1, r2) -> r1.getAveragePrice() > r2.getAveragePrice() ? -1 : 1);
        results.sort((r1, r2) -> {
            if (r1.getAveragePrice() > r2.getAveragePrice())
                return -1;
            else if (r1.getAveragePrice() == r2.getAveragePrice())
                return 0;
            else
                return 1;
        });
        return results;
    }

    public List<Car> getAll() {
        return repository.getAll();
    }
}
