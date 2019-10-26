package com.mitutor.converters;

public interface IConverter <E,D> {
    E fromDto( D dto);
    D fromEntity (E entity);

}
