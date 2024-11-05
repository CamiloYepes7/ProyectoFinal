package app.controller;

import app.controller.request.CreationInvoiceRequest;
import app.controller.request.CreationUserRequest;
import app.controller.request.PostIncrementAmount;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.controller.validator.InvoiceValidator;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.PartnerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@NoArgsConstructor
@RestController

public class PartnerController{
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PartnerValidator partnerValidator;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private PartnerService service;

    
@PostMapping()
    private ResponseEntity CreateGuest( @RequestBody CreationUserRequest request ) throws Exception{
      try {
            String name = request.getName();
            personValidator.validName(name);
            long document = personValidator.validDocument(request.getDocument());
            long cellPhone = personValidator.validCellphone(request.getCellPhone());
            String userName = request.getUserName();
            userValidator.validUserName(userName);
            String password = request.getPassword();
            userValidator.validPassword(password);
            PersonDto personDto = new PersonDto();
            personDto.setName(name);
            personDto.setDocument(document);
            personDto.setCelphone(cellPhone);
            UserDto userDto = new UserDto();
            userDto.setPersonId(personDto);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setRole("guest");
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(request.getUserSesion());
            GuestDto guestDto = new GuestDto();
            guestDto.setUserId(userDto);
            guestDto.setPartnerId(partnerDto);
            guestDto.setStatus("inactiva");
            this.service.createGuest(guestDto);
            return new ResponseEntity<>("se ha creado el usuario exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    
    }

    
             @PostMapping()
    private ResponseEntity DesactivateGuest(@PathVariable long document)throws Exception{
        try {
            this.service.desactivateGuest(document);
            return ResponseEntity.ok("se ha desactivado correctamente");
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
    }
    
             @PostMapping()
   private ResponseEntity ActivateGuest(@PathVariable long document)throws Exception{
        try {
            this.service.ActivateGuest();
            return ResponseEntity.ok("Invitado activado");
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
    }
         @PostMapping()
       private ResponseEntity<?> MakeConsumption(@RequestBody CreationInvoiceRequest request) throws Exception {
        try {
            int item = invoiceValidator.validItem(request.getItem());
            String description = request.getDescription();
            double amount = invoiceValidator.validAmount(request.getAmount());

            InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
            invoiceDetailDto.setItem(item);
            invoiceDetailDto.setDescription(description);
            invoiceDetailDto.setAmount(amount);

            this.service.registerConsumption(invoiceDetailDto);
            return ResponseEntity.ok("Consumo registrado");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
       }
       
@PostMapping()
  private ResponseEntity UploadFounds(@RequestBody PostIncrementAmount request) throws Exception{
        try {
            double amount = partnerValidator.validAmount(request.getAmount());   
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(request.getUserSesion());
            partnerDto.setAmount(amount);
            this.service.incrementAmount(partnerDto);
            return ResponseEntity.ok("Se incrementaron los fondos correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        
    }
  }
          @PostMapping()

          private ResponseEntity RequestVip(@PathVariable long partnerId) throws Exception{
        try {
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(partnerId);
            partnerDto.setType("pendiente");
           this.service.RequestVip(partnerDto);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
          }

}

    
    
