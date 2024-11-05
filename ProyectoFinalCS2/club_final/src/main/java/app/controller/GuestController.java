package app.controller;

import app.controller.request.CreationInvoiceRequest;
import app.controller.request.PostIncrementAmount;
import app.controllervalidator.InvoiceValidator;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.GuestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Getter
@Setter
@NoArgsConstructor
@RestController

public class GuestController {
  @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private GuestService service;

@PostMapping()
    private ResponseEntity CreateInvoice(@RequestBody CreationInvoiceRequest request) throws Exception {  
        try {
            int item = invoiceValidator.validItem(request.getItem());
            String description = request.getDescription();
            invoiceValidator.validDescription(description); 
            double amount = invoiceValidator.validAmount(request.getAmount());

            PersonDto personDto = new PersonDto();
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(request.getUserSesion());
            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setPersonId(personDto);
            invoiceDto.setPartnerId(partnerDto);
            invoiceDto.isStatus("No pago");
            invoiceDto.setAmount(amount);
            invoiceDto.setCreationDate(Utils.getDate()); 
            InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
            invoiceDetailDto.setInvoiceId(invoiceDto);
            invoiceDetailDto.setItem(item);
            invoiceDetailDto.setDescription(description);
            invoiceDetailDto.setAmount(amount);
            this.service.createInvoiceDetail(invoiceDetailDto);
            return ResponseEntity.ok("Factura Generada");
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
            private ResponseEntity BecomeAPartner(@RequestBody PostIncrementAmount request) throws Exception {
        try {
            PartnerDto parnetDto = new PartnerDto();
            UserDto userDto = new UserDto();
            double amount = invoiceValidator.validAmount(request.getAmount());
            parnetDto.setId(request.getUserSesion());
            parnetDto.setUserId(userDto);
            parnetDto.setAmount(amount);
            parnetDto.setType("Regular");
            parnetDto.setCreationDate(Utils.getDate());
            this.service.BecomeAPartner(parnetDto);
            return ResponseEntity.ok("Actualizacion: Socio");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        
    }
         
}