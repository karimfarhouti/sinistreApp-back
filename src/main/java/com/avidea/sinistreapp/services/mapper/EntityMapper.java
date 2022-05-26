package com.avidea.sinistreapp.services.mapper;

/**
 * Mapper class to help convert DTO to Entities and vice versa
 */
public interface EntityMapper<E, D> {
    E toEntity(D dto);
}
