package service;

import domain.Friendship;
import domain.User;
import domain.validator.Validator;
import exceptions.NetworkException;
import exceptions.RepositoryException;
import exceptions.ValidationException;
import repository.Network;
import repository.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class UserService implements Service<User> {
    private final Validator<User> userValidator;

    private final Validator<Friendship> friendshipValidator;
    private final Repository<Integer, User> repository;
    private final Network<User> network;

    public UserService(Validator<User> userValidator, Validator<Friendship> friendshipValidator, Repository<Integer, User> repository, Network<User> network) {
        this.userValidator = userValidator;
        this.friendshipValidator = friendshipValidator;
        this.repository = repository;
        this.network = network;
        populate();
    }


    private int getID() {
        Collection<User> list = repository.getAll();
        int ID = -99;
        for (User user : list)
            ID = Math.max(ID, user.getId())+1;
        if (ID == -99) return 1;
        else return ID;
    }
    public void add (String firstName, String lastName, String email) throws ValidationException, RepositoryException
    {
        int id = getID();
        User user = new User(id, firstName,lastName,email);
        userValidator.validate(user);
        repository.add(user);
        network.addToNetwork(user);
    }
    public void remove (int id) throws RepositoryException
    {
        User user = repository.findElementAfterId(id);
        network.removeFromNetwork(user);
        repository.remove(id);
    }
    public void addFriendship(int id1, int id2) throws ValidationException, NetworkException
    {
        Friendship friendship = new Friendship(id1,id2);
        friendshipValidator.validate(friendship);
        network.addFriendship(friendship);
    }
    public void removeFriendship(int id1, int id2) throws ValidationException,NetworkException
    {
        Friendship friendship = new Friendship(id1, id2);
        friendshipValidator.validate(friendship);
        network.removeFriendship(friendship);
    }
    public int getNumberOfComunities()
    {
        return network.getNumberOfComunities();
    }
    public Vector<User> mostSociableCommunity()
    {
        Vector<Integer> communityIds = network.mostSociableCommunity();
        Vector<User> community = new Vector<>();
        for (int id : communityIds) {
            try {
                community.add(repository.findElementAfterId(id));
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }
        return community;
    }
    public Collection<User> getAll()
    {
        return repository.getAll();
    }
    private void populate(){
        try {
            User user1 = new User(1, "Dragos", "Morozan", "morozan.dragos@gmail.com");
            User user2 = new User(2, "Raluca", "Morozan", "morozan.raluca@gmail.com");
            User user3 = new User(3, "Mihnea", "Mihalache", "mihneaesmecher@gmial.com");
            User user4 = new User(4, "Virgil", "Moldovan", "virgilvandijk@yahoo.com");
            User user5 = new User(5, "Paul", "Rop", "psgetare@gmail.com");
            User user6 = new User(6, "Sergent", "Montgomery", "otinaughtynaughty@gmail.com");
            this.repository.add(user1);
            this.repository.add(user2);
            this.repository.add(user3);
            this.repository.add(user4);
            this.repository.add(user5);
            this.repository.add(user6);
            this.network.addToNetwork(user1);
            this.network.addToNetwork(user2);
            this.network.addToNetwork(user3);
            this.network.addToNetwork(user4);
            this.network.addToNetwork(user5);
            this.network.addToNetwork(user6);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }
}
