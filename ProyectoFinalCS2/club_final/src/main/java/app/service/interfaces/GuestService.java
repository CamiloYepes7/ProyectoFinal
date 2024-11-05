
package app.service.interfaces;

import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;

public interface GuestService {
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     *
     * @param partnerDto
     * @throws Exception
     */
    public void BecomeAPartner(PartnerDto partnerDto) throws Exception;

    public void registerConsumption(InvoiceDetailDto invoiceDetailDto) throws Exception;
}
