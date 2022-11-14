package repository;

import domain.Entity;
import exceptions.RepositoryException;

import java.util.Collection;
import java.util.Vector;

public interface Repository <ID, E extends Entity<ID>>{
    void add(E obj) throws RepositoryException;
    E remove(ID id) throws RepositoryException;
    E findElementAfterId(ID id) throws RepositoryException;
    Collection<E> getAll();
}
