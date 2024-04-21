import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/input.txt"));
            String line;
            ArrayList<String[]> flightDataList = new ArrayList<>();
            Map<String, Integer> airlinePassengers = new HashMap<>(); // Map to store total passengers per airline

            // Read each line and split it into three strings based on space
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(" ");
                flightDataList.add(flightData);
            }

            // Initialize counters and storage variables
            int frankfurtFlights = 0;
            int maxPassengerNumber = 0;
            String firstFlightUnder100 = null;
            String mostPassengersAirline = "";
            int mostPassengersCount = 0;

            for (String[] flightData : flightDataList) {
                // Update Frankfurt flight count
                if (flightData[1].equals("Frankfurt")) {
                    frankfurtFlights++;
                }

                // Parse passenger count
                int passengers = Integer.parseInt(flightData[2]);

                // Update max passengers
                if (passengers > maxPassengerNumber) {
                    maxPassengerNumber = passengers;
                }

                // Update the first flight with fewer than 100 passengers
                if (passengers < 100 && firstFlightUnder100 == null) {
                    firstFlightUnder100 = flightData[0] + " " + flightData[1] + " " + flightData[2];
                }

                // Accumulate total passengers per airline
                airlinePassengers.put(flightData[0], airlinePassengers.getOrDefault(flightData[0], 0) + passengers);
            }

            // Determine the airline with the most passengers
            for (Map.Entry<String, Integer> entry : airlinePassengers.entrySet()) {
                if (entry.getValue() > mostPassengersCount) {
                    mostPassengersCount = entry.getValue();
                    mostPassengersAirline = entry.getKey();
                }
            }

            // Output results
            System.out.println("Number of flights going to Frankfurt: " + frankfurtFlights);
            System.out.println("Most passengers on a single flight: " + maxPassengerNumber);
            if (firstFlightUnder100 != null) {
                System.out.println("First flight with fewer than 100 passengers: " + firstFlightUnder100);
            } else {
                System.out.println("No flight has fewer than 100 passengers.");
            }
            System.out.println("Airline with the most total passengers: " + mostPassengersAirline + " with " + mostPassengersCount + " passengers.");

            // Close the reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
