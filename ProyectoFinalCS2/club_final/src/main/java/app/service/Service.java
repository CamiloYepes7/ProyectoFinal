package app.service;

import app.service.interfaces.AdminService;
import app.service.interfaces.GuestService;
import app.service.interfaces.LoginService;
import app.service.interfaces.PartnerService;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.InvoiceDetailDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
@Getter
@Setter
@NoArgsConstructor
@org.springframework.stereotype.Service


public class Service implements LoginService, AdminService, PartnerService, GuestService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private InvoiceDetailDao invoideDetailDao;
    public static UserDto user;
   
    @Override
    public void login(UserDto userDto) throws Exception {
  UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
	if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
	}
	userDto.setRole(validateDto.getRole());
	user = validateDto;
    }

    @Override
    public void logout() {
user = null;
        System.out.println("Se ha cerrado session");
    }

    @Override
    public void createPartner(UserDto userDto) throws Exception {
this.createUser(userDto);
    }


    @Override
    public void invoiceHistory() throws Exception {
List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            System.out.println("######################");
            System.out.println("ENCABEZADO DE LA FACTURA"
                + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus("sin pagar")
                + "\nDETALLES DE LA FACTURA"
                + "\nID: " + invoiceDetailDto.getId()
                + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
    }
    }

    @Override
    public void invoiceHistoryPartner(long document) throws Exception {
PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        if(this.personDao.existsByDocument(personDto) == false) {
            throw new Exception("no existe una persona con ese documento");
	}
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        if(!"partner".equals(userDto.getRole())){
            throw new Exception("Esta persona no es un socio");
        }
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                System.out.println("######################");
                System.out.println("ENCABEZADO DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                    + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                    + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                    + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                    + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus("sin pagar")
                    + "\nDETALLES DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getId()
                    + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                    + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                    + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
            }
        }
    }

    @Override
    public void invoiceHistoryGuest(long document) throws Exception {
 PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        if(this.personDao.existsByDocument(personDto) == false) {
            throw new Exception("no existe una persona con ese documento");
	}
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        if(!"guest".equals(userDto.getRole())){
            throw new Exception("Esta persona no es un invitado");
        }
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                System.out.println("######################");
                System.out.println("ENCABEZADO DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                    + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                    + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                    + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                    + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus("sin pagar")
                    + "\nDETALLES DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getId()
                    + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                    + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                    + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
            }
        }
    }

    @Override
    public void promotiontovip() throws Exception {
    List<PartnerDto> listPartnersVip = this.partnerDao.findByType("vip");
        if(listPartnersVip.size() == 5){
            throw new Exception("No se pueden hacer mas promociones a VIP. El maximo de 5 VIPs ya esta ocupado.");
        }
        List<PartnerDto> listPartners = this.partnerDao.findByType("pendiente");
        for(PartnerDto partnerDto : listPartners){
            partnerDto.setType("vip");
            this.partnerDao.PartnerVipPromotion(partnerDto);
        }
    }

    @Override
    public void desactivateGuest(long document) throws Exception {
PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(userDto);
        this.guestDao.desactivateGuest(guestDto);    }

    @Override
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
 this.createInvoice(invoiceDetailDto.getInvoiceId());
      this.invoideDetailDao.createInvoiceDetail(invoiceDetailDto);    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto) throws Exception {
 if(user.getRole().equals("partner")){
            PartnerDto partnerDto = this.partnerDao.findByUserId(user);
        }else{
            GuestDto guestDto = this.guestDao.findByUserId(user);
        }
        InvoiceDto InvoiceDto = null;
        this.invoiceDao.createInvoice(InvoiceDto);    }

    @Override
    public void RequestVip(PartnerDto partnerDto) throws Exception {
 UserDto userDto = this.userDao.findByid(partnerDto.getId());
        PartnerDto partner = this.partnerDao.findByUserId(userDto);
        partner.setType(partnerDto.isType());
        this.partnerDao.PartnerVipPromotion(partner);    }

    @Override
    public void incrementAmount(PartnerDto partnerDto) {
      UserDto userDto = null;
        try {
            userDto = this.userDao.findByid(partnerDto.getId());
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        PartnerDto partner = new PartnerDto();
        try {
            partner = this.partnerDao.findByUserId(userDto);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        partner.setAmount(partner.getAmount() + partnerDto.getAmount());
        if(partner.getAmount() > 1000000 && partner.isType().equals("regular")){
          try {
              throw  new Exception("El tope maximo para socios regulares es de 1 Millon");
          } catch (Exception ex) {
              Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
          }
        }else if(partner.getAmount() > 5000000 && partner.isType().equals("vip")) {
          try {
              throw  new Exception("El tope maximo para socios vips es de 5 Millon");
          } catch (Exception ex) {
              Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        
        try {
            this.partnerDao.incrementAmount(partner);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    @Override
    public void registerConsumption(InvoiceDetailDto invoiceDetailDto) throws Exception {
        if (user == null) {
        throw new Exception("Usuario no autenticado");
    }

    if (!"partner".equals(user.getRole()) && !"guest".equals(user.getRole())) {
        throw new Exception("El usuario debe ser un socio o un invitado para registrar consumos");
    }

    if (invoiceDetailDto == null || invoiceDetailDto.getAmount() <= 0) {
        throw new Exception("El detalle de la factura es inválido");
    }

    InvoiceDto invoiceDto = new InvoiceDto();
    invoiceDto.setPartnerId(user.getRole().equals("partner") ? partnerDao.findByUserId(user) : null);

    invoiceDao.createInvoice(invoiceDto);

    invoiceDetailDto.setInvoiceId(invoiceDto);

    invoideDetailDao.createInvoiceDetail(invoiceDetailDto);
    
    System.out.println("Consumo registrado exitosamente.");
    }

  
    @Override
    public void ActivateGuest(){
        try {
            PersonDto personDto = new PersonDto();
            GuestDto GuestDto = null;
            
            personDto = this.personDao.findByDocument(personDto);
            
            UserDto userDto = new UserDto();
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }



    private void createUser(UserDto userDto) throws Exception {
this.createPerson(userDto.getPersonId());
	if(this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
        }
        this.userDao.createUser(userDto);    }

    private void createPerson(PersonDto personDto)throws Exception{
	if(this.personDao.existsByDocument(personDto)) {
            throw new Exception("ya existe una persona con ese documento");
	}
        this.personDao.createPerson(personDto);
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
          this.createUser(guestDto.getUserId());
        UserDto userDto = this.userDao.findByid(guestDto.getPartnerId().getId());
        guestDto.setPartnerId(partnerDao.findByUserId(userDto));

        this.guestDao.createGuest(guestDto);  }

    @Override
    public void BecomeAPartner(PartnerDto partnerDto) throws Exception {
 UserDto userDto = this.userDao.findByid(partnerDto.getId());
        this.userDao.convertPartner(userDto);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(userDto);
        partnerDto.setUserId(userDto);
        this.partnerDao.createPartner(partnerDto);
        this.guestDao.deleteGuest(guestDto);    }

    
}
