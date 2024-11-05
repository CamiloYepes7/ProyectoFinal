
package app.controller;

import app.controllervalidator.UserValidator;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController  {
    
    private UserValidator userValidator;
    private LoginService service;

    private Map<String, ControllerInterface> roles;

    @Autowired
    public LoginController(UserValidator userValidator, LoginService service) {
        this.userValidator = userValidator;
        this.service = service;

        roles = new HashMap<>();
        roles.put("admin", (ControllerInterface) new AdminController());
        roles.put("guest", (ControllerInterface) new GuestController());
        roles.put("partner", (ControllerInterface) new PartnerController());
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
 
            userValidator.validUserName(userDto.getUserName());
            userValidator.validPassword(userDto.getPassword());

            this.service.login(userDto);

            ControllerInterface roleController = roles.get(userDto.getRole());
            if (roleController == null) {
                throw new Exception("Rol inválido");
            }
            roleController.session(); 

            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}