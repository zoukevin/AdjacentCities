import java.util.Collection;
import java.util.ArrayList;

public class City {
    private Collection<City> adjacentCities;
    private String name;

    public City(String name) {
        this.name = name;
        this.adjacentCities = new ArrayList<City>();
    }

    /**
     * Name of the city, can be assumed to be unique
     */
    public String getName() {
        return name;
    }

    /**
     * Adjacent city are connected to this city directly by road
     */
    public Collection<City> getAdjacentCities() {
        return adjacentCities;
    }

    public void addAdjacentCity(City city) {
        adjacentCities.add(city);
        city.getAdjacentCities().add(this);
    }

    public boolean canDriveTo(City destination) {

        /*
        //Print formatting to test code
        System.out.println("The City name is " + this.getName());
        for (City i : this.adjacentCities) {
            System.out.println("The Adjacent City is " + i.getName());
        }
        */

        //If destination is already adjacent, end method
        if (this.adjacentCities.contains(destination)) {
            System.out.println("It is possible to reach " + destination.getName());
            return true;
        } else {  
            //Begin loop
            for (City city : this.adjacentCities) {
                //Check for endpoint and if there are multiple adjacents
                if (!city.adjacentCities.contains(destination) && this.adjacentCities.size() > 1) {
                    //Remove cities as we iterate through the possible paths
                    city.adjacentCities.remove(this);
                    boolean result = city.canDriveTo(destination);
                    if (result)  {
                        //Return true if a successful match is found
                        System.out.println("It is possible to reach " + destination.getName());
                        return true;
                    }
                    else {
                        //If there are no matches, prepare to iterate the other direction
                        city.adjacentCities.remove(this);
                        continue;
                    }
                }

                //Final loop to check the opposite direction for matches
                for (int i = 0; i < this.adjacentCities.size(); i++) {
                    city.adjacentCities.remove(this);
                    return city.canDriveTo(destination);
                }         
            }
            //Return false if no successful adjacent matches
            return false;
        }
    }  
    

        /*throw new UnsupportedOperationException("Implement me");*/

    public static void main(String[] args) {
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");
        City e = new City("E");
        City f = new City("F");
        City g = new City("G");
        City h = new City("H");

        ///*
        //Test case 1
        //Check if A can reach H
        a.addAdjacentCity(b);
        b.addAdjacentCity(c);
        c.addAdjacentCity(d);
        d.addAdjacentCity(e);
        e.addAdjacentCity(f);
        f.addAdjacentCity(g);
        g.addAdjacentCity(h);
        //*/

        /*
        //Test case 2
        //Check if A can reach D with a break
        a.addAdjacentCity(b);
        c.addAdjacentCity(d);
        */
        
        System.out.println(e.canDriveTo(g));


    }
}