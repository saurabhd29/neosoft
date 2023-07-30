package com.logistics.transport.Controller;

import com.logistics.transport.Dtos.TUserDTO;
import com.logistics.transport.Entites.TransportDetails;
import com.logistics.transport.Exceptions.UserNotFoundException;
import com.logistics.transport.ServiceImpl.Response;
import com.logistics.transport.ServiceImpl.TransportUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transportuser")
@CrossOrigin(value="*")
public class TransportUserController {

    @Autowired
    private TransportUserServiceImpl transportUserServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") int id)
    {
        try {
            TransportDetails user = transportUserServiceImpl.findUserById(id);
            System.out.println(user);

            return ResponseEntity.ok(user);
        }
        catch(UserNotFoundException e){
            return ResponseEntity.ok(null);
        }

    }

    @GetMapping("/find/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email){
        System.out.println(email);
        Optional<TransportDetails> user = transportUserServiceImpl.findByEmail(email);
        System.out.println(user);
        if(user ==null)
            return ResponseEntity.ok(null);
        return ResponseEntity.ok(user);
    }



    @GetMapping("/getallusers")
    public ResponseEntity<?> getAllUsers()
    {
        List<TUserDTO> users = transportUserServiceImpl.getAllUsers();

        if(users!=null)
        {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/transportregister")
    public ResponseEntity<?> TransportRegister(@RequestBody TransportDetails details){

        details.setPassword(passwordEncoder.encode(details.getPassword()));
        System.out.println(details);
        TUserDTO t =transportUserServiceImpl.save(details);
        if(t!=null)
            return Response.success(t);
        return Response.error(null);
    }
}
