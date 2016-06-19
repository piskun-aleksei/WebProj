package by.bsuir.webproj.dao;

import by.bsuir.webproj.container.Entity;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.WrapperConnection;

import java.util.ArrayList;

/**
 * Created by Алексей on 12.04.2016.
 */
public abstract class AbstractDAO<T extends Entity> {
    protected WrapperConnection connection;

    public AbstractDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public abstract ArrayList<T> findAll() throws DAOException;

    public abstract boolean addEntity(T entity) throws DAOException;
}
