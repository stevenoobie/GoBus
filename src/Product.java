public class Product {
    String description;
    String type;
    int stops;
    double price;
    int availableSeats;
    int numOfTickets;
    String vehicle;
    String trip;
    Product(String description,String type,int stops,double price,int availableSeats){
        this.description=description;
        this.type=type;
        this.stops=stops;
        this.price=price;
        this.availableSeats=availableSeats;
    }
    Product(String description,String type,int stops,int numOfTickets){
        this.description=description;
        this.type=type;
        this.stops=stops;
        this.numOfTickets=numOfTickets;
    }
    Product(String description,String type,int stops,String vehicle){

        this.description=description;
        this.type=type;
        this.stops=stops;
        this.vehicle=vehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    Product (String trip){
        this.trip=trip;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
