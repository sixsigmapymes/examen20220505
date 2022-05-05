package com.examen.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.examen.model.Producto;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {

        producto = new Producto(1,"Cable Bipolar","Cable Bipolar 6mm x 100",12500);
        producto1 = new Producto(1,"Cable Bipolar","Cable Bipolar 2mm x 100",2500);
        producto2 = new Producto(1,"Cable Bipolar","Cable Bipolar 2mm x 100",2500);
    }

    @AfterEach
    public void tearDown() {
        productoRepository.deleteAll();
        producto = null;
    }

    @Test
    public void givenProductToAddShouldReturnAddedProduct(){

        productoRepository.save(producto);
        Producto fetchedProducto = productoRepository.findById(producto.getId()).get();
        assertEquals(producto1.getNombre(),producto2.getNombre());
        assertEquals(1, fetchedProducto.getId());
    }

    @Test
    public void GivenGetAllProductShouldReturnListOfAllProducts(){
        producto1 = new Producto(1,"Cable Unipolar","Cable Bipolar 6mm x 100",12500);
        producto2 = new Producto(2,"Cable Bipolar","Cable Bipolar 2mm x 100",2500);
        productoRepository.save(producto1);
        productoRepository.save(producto2);

        List<Producto> productoList = (List<Producto>) productoRepository.findAll();
        assertEquals("Cable Bipolar", productoList.get(1).getNombre());

    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        Producto product1 = new Producto(6,"Cable Unipolar","Cable Bipolar 6mm x 100",12500);
        Producto product2 = productoRepository.save(producto1);

        Optional<Producto> optional =  productoRepository.findById(product2.getId());
        assertEquals(producto2.getId(), optional.get().getId());
        assertEquals(producto2.getNombre(), optional.get().getNombre());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct() {
        Producto producto = new Producto(4,"Cable Unipolar","Cable Bipolar 6mm x 100",12500);
        productoRepository.save(producto);
        productoRepository.deleteById(producto.getId());
        Optional optional = productoRepository.findById(producto.getId());
        assertEquals(Optional.empty(), optional);
    }



}