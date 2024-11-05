package app.controller;

import app.controller.request.CreationUserRequest;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.InvoiceDetailDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.AdminService;
import java.util.List;
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
public  class AdminController {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PartnerValidator partnerValidator;
    @Autowired
    private AdminService service;


 @PostMapping()
public ResponseEntity<?> ClubInvoices() {
    try {
 List<InvoiceDetailDto> listInvoicesDetailDto = (List<InvoiceDetailDto>) this.ClubInvoices();
        return ResponseEntity.ok(listInvoicesDetailDto);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
 @PostMapping()
       public ResponseEntity<?> PartnerInvoice() {
    try {
 List<InvoiceDetailDto> listInvoicesDetailDto = (List<InvoiceDetailDto>) this.PartnerInvoice();
	 return ResponseEntity.ok(listInvoicesDetailDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    
       }
 @PostMapping()
            public ResponseEntity<?> GuestInvoices() {
    try {
 List<InvoiceDetailDto> listInvoicesDetailDto = (List<InvoiceDetailDto>) this.GuestInvoices();
  return ResponseEntity.ok(listInvoicesDetailDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
       
            
           
        }
 @PostMapping()
                 private  ResponseEntity promotiontovip() throws Exception{
        try {
            this.service.promotiontovip();
            return ResponseEntity.ok("Usuarios promovidos");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    
	}
    
 @PostMapping()
    private void CreatePartner(@RequestBody CreationUserRequest request) throws Exception{
        System.out.println("ingrese el nombre ");
        String name = Utils.getReader().nextLine();
	personValidator.validName(name);
	System.out.println("ingrese la cedula ");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
	System.out.println("ingrese el telefono celular");
	long cellPhone = personValidator.validCellphone(Utils.getReader().nextLine());
	System.out.println("ingrese el nombre de usuario ");
	String userName = Utils.getReader().nextLine();
	userValidator.validUserName(userName);
	System.out.println("ingrese la contraseña de");
	String password = Utils.getReader().nextLine();
	userValidator.validPassword(password);
        System.out.println("ingrese el monto inicial");  
        double amount = partnerValidator.validAmount(Utils.getReader().nextLine());
        
	PersonDto personDto = new PersonDto();
	personDto.setName(name);
	personDto.setDocument(document);
	personDto.setCelphone(cellPhone);
	UserDto userDto = new UserDto();
	userDto.setPersonId(personDto);
	userDto.setUserName(userName);
	userDto.setPassword(password);
	userDto.setRole("partner");          
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
            partnerDto.setType("regular");
        partnerDto.setAmount(amount);
        partnerDto.setCreationDate(Utils.getDate());
        this.service.createPartner(userDto);
	System.out.println("se ha creado el usuario exitosamente");
    
    
    }
}


