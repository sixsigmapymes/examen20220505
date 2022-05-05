package com.examen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.Exception.ProductoAlreadyExistsException;
import com.examen.Repository.ProductoRepository;
import com.examen.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{

    private ProductoRepository productoRepository;

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository){

        this.productoRepository =productoRepository;
    }

    @Override
    public Producto UpdateOrCreate(Producto producto) throws ProductoAlreadyExistsException {
        if(productoRepository.existsById(producto.getId())){
            Producto productoModif= new Producto();
            productoModif.setId(producto.getId());
            productoModif.setNombre(producto.getNombre());
            productoModif.setDescripcion(producto.getDescripcion());
            productoModif.setPrecio(producto.getPrecio());
        }
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> fimdAllProducto() {

        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Producto getProductoByid(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto deleteProductoById(int id) {
        Producto producto = null;
        Optional optional = productoRepository.findById(id);
        if (optional.isPresent()) {
            producto = productoRepository.findById(id).get();
            productoRepository.deleteById(id);
        }
        return producto;
    }

}
