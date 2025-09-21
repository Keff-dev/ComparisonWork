import java.util.*;
import java.io.*;

// 1.
class Person implements Comparable<Person>{

    String name;
    double height;
    double weight;

    public Person(String fname, String lname, double height, double weight){
        this.name = fname + " " + lname;
        this.height = height;
        this.weight = weight;
    }

    public Double bmi(double height, double weight){
        return 703 * (weight/(height*height)); 
    }

    public void travel(){
        System.out.println(name + "is a vagabond.");
    }

    public void travel(String location){
        System.out.println(name + "is traveling to " + location + ".");
    }

    public boolean equals(Person b){
        if(this.name.contentEquals(b.name)){
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(Person other, String type){
        Double oBMI = other.bmi(other.height, other.weight);
        Double tBMI = this.bmi(this.height, this.weight);

        if(type.equalsIgnoreCase("bmi")){
            if(oBMI > tBMI){
                return 1;
            } else if(oBMI < tBMI){
                return -1;
            }
            return 0;

        } else {
            if(other.name.length() <= this.name.length()){
                for(int i=0; i<other.name.length();i++){
                    if(this.name.charAt(i) < other.name.charAt(i)){
                        return -1;
                    } else if(this.name.charAt(i) > other.name.charAt(i)){
                        return 1;
                    }
                }
                return 0;
    
            } else {
                for(int i=0; i < this.name.length();i++) {
                    if(this.name.charAt(i) < other.name.charAt(i)){
                        return -1;
                    } else if(this.name.charAt(i) > other.name.charAt(i)){
                        return 1;
                    }
                }
            }
            return 0;
        }

    }

    @Override
    public int compareTo(Person other) {

        if(other.name.length() <= this.name.length()){
            for(int i=0; i<other.name.length();i++){
                if(this.name.charAt(i) < other.name.charAt(i)){
                    return -1;
                } else if(this.name.charAt(i) > other.name.charAt(i)){
                    return 1;
                }
            }
            return 0;

        } else {
            for(int i=0; i < this.name.length();i++) {
                if(this.name.charAt(i) < other.name.charAt(i)){
                    return -1;
                } else if(this.name.charAt(i) > other.name.charAt(i)){
                    return 1;
                }
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return name + " :: " + "Ht = " + height + " Wgt = " + weight + " | BMI = " + String.format( "%.2f", bmi(height, weight)); 
    }
    

}

public class Comparison090425 {

    public static LinkedList<Person> fileInPeople() throws IOException{
        Scanner input = new Scanner(new File("comparisons\\hwDLdFiles\\namesWithHeightWeight.csv")); 
        // Order of CSV: LAST NAME,FIRST NAME,HEIGHT(in.),WEIGHT(lbs.)
        LinkedList<Person> people = new LinkedList<>();

        input.nextLine();
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] vals = line.split(",");
            people.add(new Person(vals[1], vals[0], Double.parseDouble(vals[2]), Double.parseDouble(vals[3])));
        }

        input.close();
        return people;
    }

    public static void printList(String message, LinkedList<Person> list) {
        System.out.println("\n\n" + message +"\n== + -- + -- + ==\n");
        for (Person i : list) {
            System.out.println(i);
        }
    }

    public static Person search(LinkedList<Person> list){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a person's WHOLE name (first m. last) to search the database:: ");
        System.out.println();
        String name = " " + input.nextLine();

        for(Person per : list){
            if(per.name.equalsIgnoreCase(name)){
                input.close();
                return per;
            }
        }
        input.close();
        return null;
        
    }
    public static void main(String[] args) throws IOException {
        LinkedList<Person> list = fileInPeople(); 

        // 1. Implement the (overridden) equals() method for Person class to make two Person objects equal if their names match exactly.
        System.out.println("\n\n### == - PROBLEM 1 COMPARISON - == ###\n");
        System.out.println("Checking .equals() function!");

        boolean check = false;
        for(int i = 1; i < list.size(); i++){
            for(Person guy : list){
                if(list.get(i).equals(guy) && !(list.get(i).toString().equals(guy.toString())) ){
                    System.out.print("Index: " + i + ". ");
                    System.out.println(list.get(i));
                } else if(i == (list.size()-1)) {
                    System.out.println("Matching names and indexes above.");
                    check = true;
                    break;
                }
            }
        
            if(i == (list.size()-1) && check == false) {
                System.out.println("No matching names");
            }
        }
        

        // 2. Implement the default comparison of Person objects by their names in lexicographic order (alphabetically).
        // Add a method to return the value of bmi = (weight*703.0)/(height*height) for the person object. Make sure you have a toString() method to print the person objects.
        System.out.println("\n\n### == - PROBLEM 2 OF COMPARISON - == ###\n");
        System.out.println("Checking if compare to works!");
        
        System.out.println("Name: ");
        System.out.println(list.get(6).compareTo(list.get(90)));
        System.out.println(list.get(6).compareTo(list.get(28)));
        System.out.println(list.get(6).compareTo(list.get(6)));
        System.out.println("\nBMIs: ");
        System.out.println(list.get(6).compareTo(list.get(90), "bmi"));
        System.out.println(list.get(6).compareTo(list.get(28), "bmi"));
        System.out.println(list.get(6).compareTo(list.get(6), "bmi"));
        System.out.println("\n"+ list.get(6) + "  " + list.get(90));
        System.out.println(list.get(6) + "  " + list.get(28));
        System.out.println(list.get(6) + "  " + list.get(6));
        System.out.println("Works as intended, yay!");

        // 3. Write a java program that reads in the the CSV file, and sorts and displays the persons first by names, and then by bmi values. You can do this by putting the person objects in linked lists and calling the sort() method of the linked list.
        // Done third but at top of main() because that's how programs work (they read top to bottom and if I want to do the other things, then I gotta have it reading correctly)
        System.out.println("\n\n### == - PROBLEM 3 OF COMPARISON - == ###\n");
        printList("OG List is here!", list);
        list.sort(null);
        printList("list.sort() list is here!", list);
        
        // What I want: Compare item using comare(other Perosn, "bmi"); 
        // Collections.sort(list, x.compare(y, "bmi"));
        Collections.sort(list, new Comparator<Person>() {
            public int compare(Person a, Person b){
                return a.compareTo(b,"bmi");
            }
        }); // I'm actually so baller for figuring this out C:<
        printList("Collections.sort(list, 'bmi') list is here!", list);
        System.out.println("Lowkey, collections.sort is really fucking confusing-");
        
        // This orders in tallest ot shortest
        Collections.sort(list, new Comparator<Person>() { 
            public int compare(Person x, Person y){ 
                if(x.height < y.height) { // if this obj is taller than other obj, then other is less than, so return -1; 
                    return -1;
                }else if(x.height > y.height) {
                    return 1;
                } else {
                    return 0;
                }  
            }  
        });

        printList("height comparison! Shortest to Tallest", list);

        //Bonus for fun. Add a search function for people by name.
        Person foundPer = search(list);
        if(foundPer == null){
            System.out.println("Not found in CSV file.");
        } else {
            System.out.println(foundPer.toString());
        }
        
    }
}
