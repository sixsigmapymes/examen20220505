package com.examen.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.examen.Exception.ProductoAlreadyExistsException;
import com.examen.Repository.ProductoRepository;
import com.examen.model.Producto;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

//handles business logic
    //unit testing so mocking
    @Mock
    private ProductoRepository productoRepository;

    @Autowired
    @InjectMocks
    private ProductoServiceImpl productoService;
    private Producto producto1;
    private Producto producto2;
    List<Producto> productoList;

    @BeforeEach
    public void setUp() {
        productoList = new ArrayList<>();
        producto1 = new Producto(1,"Cable Unipolar","Cable Bipolar 6mm x 100",12500);
        producto2 = new Producto(2,"Cable Unipolar","Cable Bipolar 6mm x 100",12500);
        productoList.add(producto1);
        productoList.add(producto2);
    }

    @AfterEach
    public void tearDown() {
        producto1 = producto2 = null;
        productoList = null;
    }

    @Test
    void givenProductToAddShouldReturnAddedProduct() throws ProductoAlreadyExistsException {

        //stubbing
        when(productoRepository.save(any())).thenReturn(producto1);
        productoService.UpdateOrCreate(producto1);
       verify(productoRepository,times(1)).save(any());

    }

    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
    productoRepository.save(producto1);

        when(productoRepository.findAll()).thenReturn(productoList);
        List<Producto> productoList1 =productoService.fimdAllProducto();
        assertEquals(productoList1,productoList);
        verify(productoRepository,times(1)).save(producto1);
        verify(productoRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        Mockito.when(productoRepository.findById(1)).thenReturn(Optional.ofNullable(producto1));
        assertThat(productoService.getProductoByid(producto1.getId())).isEqualTo(producto1);
    }

}