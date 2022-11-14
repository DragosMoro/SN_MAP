package presentation;

import domain.User;
import exceptions.NetworkException;
import exceptions.RepositoryException;
import exceptions.ValidationException;
import service.Service;

import java.util.Scanner;
import java.util.Vector;

public class UI {
    private final Service<User> service;
    private final Scanner in = new Scanner(System.in);

    public UI(Service<User> service) {
        this.service = service;
    }
    private void addUser() throws RepositoryException, ValidationException
    {
        String firstName;
        String lastName;
        String email;
        System.out.print("First name: ");
        firstName = in.nextLine();
        System.out.print("Last name: ");
        lastName = in.nextLine();
        System.out.print("Email: ");
        email = in.nextLine();
        service.add(firstName, lastName,email);
        System.out.println("User successfully added!");
    }


    private void removeUser() throws RepositoryException
    {
        int id;
        System.out.print("ID of the user: ");
        try{
            id = Integer.parseInt(in.nextLine());

        }
        catch(NumberFormatException e){
            return;
    }

    }
    private void printAll()
    {
    service.getAll().forEach(System.out::println);

    }

    private void addFriendship() throws NetworkException, ValidationException
    {
        int id1;
        int id2;
        System.out.print("First user id: ");
            try {
                id1 = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                return;
            }
            System.out.print("Second user id: ");
            try {
                id2 = Integer.parseInt(in.nextLine());

            } catch (NumberFormatException e) {
                return;
            }
            service.addFriendship(id1, id2);
            System.out.println("There is a friendship between these 2 users now!");
    }

    private void removeFriendship() throws NetworkException, ValidationException
    {
        int id1;
        int id2;
        System.out.print("First user id: ");
        try{
            id1 = Integer.parseInt(in.nextLine());

        }
        catch(NumberFormatException e){
            return;
        }
        System.out.print("Second user id: ");
        try{
            id2 = Integer.parseInt(in.nextLine());

        }
        catch(NumberFormatException e){
            return;
        }
        service.removeFriendship(id1,id2);
        System.out.println("The friendship between these two was removed!");

    }
    private void printNumberOfCommunities() {
        System.out.println("Number of communities: " + service.getNumberOfComunities());
    }
    private void printPopulatedCommunity() throws RepositoryException {
        Vector<User> community = service.mostSociableCommunity();
        for (User user : community) {
            System.out.println(user);
        }
    }
    private void printMenu() {
        System.out.print("""
                Menu:
                1. Show Menu.
                2. Add user.
                3. Remove user.
                4. Show users.
                5. Make friendship.
                6. Remove friendship.
                7. Show number of communities.
                8. Show most populated community.
                9. Exit.
                \s""");
    }
    public void run(){
        int input = 0;
        this.printMenu();
        while (true)
        {
            System.out.print(">>>");
            try {
                input = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            switch (input) {
                case 1:
                    this.printMenu();
                    break;
                case 2:
                    try {
                        this.addUser();
                    } catch (ValidationException | RepositoryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        this.removeUser();
                    } catch (RepositoryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    this.printAll();
                    break;

                case 5:
                    try {
                        this.addFriendship();
                    }
                    catch (NetworkException | ValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        this.removeFriendship();
                    } catch (NetworkException | ValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    this.printNumberOfCommunities();
                    break;
                case 8:
                    try {
                        this.printPopulatedCommunity();
                    } catch (RepositoryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    return;
                default:
                    break;
            }

        }

    }
}

