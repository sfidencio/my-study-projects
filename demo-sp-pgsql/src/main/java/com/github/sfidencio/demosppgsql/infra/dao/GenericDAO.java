package com.github.sfidencio.demosppgsql.infra.dao;

public interface GenericDAO<T> {
    T selecionarPorParametro(String parametro);
}