package com.example.coffeebeans.model.mapper;

import java.util.List;

/**
 * Интерфейс для реализации маппинга данных
 * @param <E>
 * @param <D>
 */
public interface Mappable<E, D>{

    E toEntity(D d);

    D toResponse(E e);

    List<D> toResponseList(List<E> e);
}
