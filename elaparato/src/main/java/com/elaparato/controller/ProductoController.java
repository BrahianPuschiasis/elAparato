package com.elaparato.controller;
import com.elaparato.model.Producto;
import com.elaparato.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService prodServ;

    //crear un nuevo producto
    @PostMapping("/productos/create")
    public String createProducto(@RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    //obtener todos los productos
    @GetMapping("/productos/getall")
    public List<Producto> getProductos () {
        return prodServ.getProductos();
    }



   //Modificar los datos de un producto
    @PutMapping("/productos/edit")
    public String editProducto(@RequestBody Producto prod) {
        prodServ.editProducto(prod);
        return "Producto editado correctamente";
    }
}
