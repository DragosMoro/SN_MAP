package repository;

import domain.Entity;
import exceptions.RepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository <ID, E extends Entity<ID>> implements Repository<ID, E>{
    private final Map<ID, E> entities;

    public InMemoryRepository() {
        entities = new HashMap<>();
    }

    public void add(E entity) throws IllegalArgumentException, RepositoryException
    {
        if(entity == null)
            throw new IllegalArgumentException("Entity can't be null");

        if(entities.containsKey(entity.getId()))
            throw new RepositoryException("There is an element with the same id\n");
        entities.put(entity.getId(), entity);

    }
    public E remove(ID id) throws RepositoryException
    {
        if(!entities.containsKey(id))
            throw new RepositoryException("There is no element with this id\n");
        return entities.remove(id);
    }
    public E findElementAfterId(ID id) throws RepositoryException
    {
        if(!entities.containsKey(id))
            throw new RepositoryException("There is no element with this id\n");
        return entities.get(id);
    }
    public Collection<E> getAll()
    {
        return entities.values();
    }

}
