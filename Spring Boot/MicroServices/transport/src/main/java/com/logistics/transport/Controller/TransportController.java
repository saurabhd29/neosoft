package com.logistics.transport.Controller;

import com.logistics.transport.Entites.TransportOrders;
import com.logistics.transport.ServiceImpl.TransportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transporter")
@CrossOrigin(value = "*")
public class TransportController {
    @Autowired
    private TransportServiceImpl transportServiceImpl;


    @PostMapping("/transportorder")
    public String register(@RequestBody TransportOrders newOrder){
        System.out.println(newOrder);
        String status = transportServiceImpl.saveOrder(newOrder);
        if(status.equals("Success")){
            return "Order created Successfully..";
        }
        return "Error";
    }

    @GetMapping("/getallorders")
    public ResponseEntity<?> getAllOrders()
    {

        return ResponseEntity.ok(transportServiceImpl.getAllOrders());
    }

}
