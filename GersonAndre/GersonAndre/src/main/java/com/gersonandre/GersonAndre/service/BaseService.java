package com.gersonandre.GersonAndre.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();
    public T create(T entity){
        return getRepository().save(entity);
    }
    public T update(T entity){
        return getRepository().save(entity);
    }
    public void delete(ID id){
        getRepository().deleteById(id);
    }
    public Optional<T> findById(ID id){
        return getRepository().findById(id);
    }
    public List<T> findAll(){
        return getRepository().findAll();
    }
}