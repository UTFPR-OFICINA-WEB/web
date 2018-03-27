package br.edu.utfpr.pb.oficinaweb.service.impl;

import br.edu.utfpr.pb.oficinaweb.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class CrudServiceImpl<T, ID extends Serializable> implements CrudService<T, ID> {

    protected abstract JpaRepository<T, ID> getData();

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getData().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return getData().findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return getData().findAll(pageable);
    }

    @Override
    @Transactional
    public void flush() {
        getData().flush();
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        return getData().findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return getData().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getData().count();
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getData().deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getData().delete(entity);
    }

    @Override
    @Transactional
    public void delete(Iterable<? extends T> iterable) {
        getData().deleteAll(iterable);
    }

    @Override
    @Transactional
    public void deleteAll() {
        getData().deleteAll();
    }

    @Override
    @Transactional
    public Iterable<T> save(Iterable<T> iterable) {
        return getData().saveAll(iterable);
    }

    @Override
    @Transactional
    public T saveAndFlush(T entity) {
        return getData().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return getData().save(entity);
    }

}
