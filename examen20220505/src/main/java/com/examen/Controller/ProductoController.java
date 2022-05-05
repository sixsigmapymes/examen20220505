package com.examen.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.Exception.ProductoAlreadyExistsException;
import com.examen.Service.ProductoService;
import com.examen.model.Producto;

@RestController
@RequestMapping("api/v1")
public class ProductoController {
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("producto")
    public ResponseEntity<Producto> UpdateOrCreate (@RequestBody Producto producto) throws ProductoAlreadyExistsException {
        Producto saveProducto = productoService.UpdateOrCreate(producto);
        return new ResponseEntity<>(saveProducto, HttpStatus.CREATED);
    }

    @GetMapping("productos")
    public ResponseEntity<List<Producto>> getAllProducts(){

        return new ResponseEntity<List<Producto>>(
                (List <Producto>) productoService.fimdAllProducto(),HttpStatus.OK);
    }

    @GetMapping("producto/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") int id){
        return new ResponseEntity<>(productoService.getProductoByid(id),HttpStatus.OK);
    }

    @DeleteMapping("producto/{id}")
        public ResponseEntity<Producto> deleteProducto(@PathVariable("id") int id) {
        ResponseEntity<Producto> responseEntity;
        Producto deletedProducto = productoService.deleteProductoById(id);
            responseEntity = new ResponseEntity<Producto>(deletedProducto, HttpStatus.OK);
        return responseEntity;
   }

}
