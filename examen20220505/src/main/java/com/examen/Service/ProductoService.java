package com.examen.Service;

import java.util.List;

import com.examen.Exception.ProductoAlreadyExistsException;
import com.examen.model.Producto;

public interface ProductoService {

    Producto UpdateOrCreate(Producto producto) throws ProductoAlreadyExistsException;
    List<Producto> fimdAllProducto();
    Producto getProductoByid(int id);
    Producto deleteProductoById(int id);

}
