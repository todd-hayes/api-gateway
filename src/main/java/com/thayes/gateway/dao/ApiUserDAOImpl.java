package com.thayes.gateway.dao;

import com.thayes.gateway.entity.ApiUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiUserDAOImpl implements ApiUserDAO {

    private final EntityManager entityManager;

    @Autowired
    ApiUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(ApiUser apiUser) {
        entityManager.persist(apiUser);
    }

    @Override
    public List<ApiUser> findAll() {
        TypedQuery<ApiUser> query = entityManager.createQuery("FROM ApiUser", ApiUser.class);
        return query.getResultList();
    }

    @Override
    public List<ApiUser> findByUserName(String userName) {
        TypedQuery<ApiUser> query = entityManager.createQuery("FROM ApiUser WHERE userName LIKE :user_name", ApiUser.class);
        query.setParameter("user_name", userName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateApiUser(ApiUser apiUser) {
        entityManager.merge(apiUser);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM ApiUser").executeUpdate();
    }

}
