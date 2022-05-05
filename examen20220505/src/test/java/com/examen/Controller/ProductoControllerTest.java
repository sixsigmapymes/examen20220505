package com.examen.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.examen.Service.ProductoService;
import com.examen.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {
    //unit test
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;
    private Producto producto;
   private List<Producto> productoList;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setup(){
        producto = new Producto(1,"Lampara blanca","Lampara blanca 220 v x 10 A",100);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @AfterEach
    void tearDown() {
        producto = null;
    }

    // Test Update or Create Product
    @Test
    public void PostMappingOfProducto() throws Exception{
        when(productoService.UpdateOrCreate(any())).thenReturn(producto);
        mockMvc.perform(post("/api/v1/producto").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(producto))).
                andExpect(status().isCreated());
        verify(productoService,times(1)).UpdateOrCreate(any());
    }
   // Get list of Products
    @Test
    public void GetMappingOfAllProduct() throws Exception {
        when(productoService.fimdAllProducto()).thenReturn(productoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productos").
                contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(producto))).
                andDo(MockMvcResultHandlers.print());
        verify(productoService).fimdAllProducto();
        verify(productoService,times(1)).fimdAllProducto();
    }
    //Find Product by id=1
    @Test
    public void GetMappingOfProductShouldReturnRespectiveProducto() throws Exception {
        when(productoService.getProductoByid(producto.getId())).thenReturn(producto);
        mockMvc.perform(get("/api/v1/producto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(producto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    //Delete Product by id=1
    @Test
    public void DeleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
        when(productoService.deleteProductoById(producto.getId())).thenReturn(producto);
        mockMvc.perform(delete("/api/v1/producto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(producto)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}