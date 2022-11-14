package service;

import domain.Friendship;
import exceptions.NetworkException;
import exceptions.RepositoryException;
import exceptions.ValidationException;

import java.util.Collection;
import java.util.Vector;

public interface Service<E>{
    void add (String firstName, String lastName, String email) throws ValidationException, RepositoryException;
    void remove (int id) throws RepositoryException;
    void addFriendship(int id1, int id2) throws ValidationException,NetworkException;
    void removeFriendship(int id1, int id2) throws ValidationException,NetworkException;
    int getNumberOfComunities();
    Vector<E> mostSociableCommunity();
   Collection<E> getAll();
}
