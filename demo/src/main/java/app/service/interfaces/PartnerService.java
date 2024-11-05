
package app.service.interfaces;


import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;

public interface PartnerService {
    public void createGuest (GuestDto userDto) throws Exception;
    public void desactivateGuest(long document) throws Exception;
        public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;
    public void RequestVip(PartnerDto partnerDto) throws Exception;

    public void incrementAmount(PartnerDto partnerDto);

    public void registerConsumption(InvoiceDetailDto invoiceDetailDto)throws Exception;

    public void ActivateGuest();



}
