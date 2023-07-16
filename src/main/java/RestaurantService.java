import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        Optional<Restaurant> optionalRestaurant = restaurants.stream().filter(
                restaurant -> restaurant.getName().equals(restaurantName)).findFirst();
        return optionalRestaurant.orElseThrow(() -> new restaurantNotFoundException("Error: Restaurant could not be found"));
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getTotalOrderValue(List<String> selectedItems, Restaurant restaurant) {
        int totalOrderValue = 0;

        List<Item> items = restaurant.getMenu();
        for(Item item: items){
            for(String selectedItem: selectedItems) {
                if(item.getName().equals(selectedItem)){
                    totalOrderValue = totalOrderValue + item.getPrice();
                }
            }
        }
        return totalOrderValue;
    }
}
