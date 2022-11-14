package repository;

import domain.Friendship;
import exceptions.NetworkException;

import java.util.Vector;

public interface Network <E>{
    void addToNetwork(E entity);
    void removeFromNetwork(E entity);
    void addFriendship(Friendship friendship) throws NetworkException;
    void removeFriendship(Friendship friendship) throws NetworkException;
    int getNumberOfComunities();
    Vector<Integer> mostSociableCommunity();

}
