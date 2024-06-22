package BUS;

import DAO.InvoiceDetailDAOImpl;
import DTO.InvoiceDetail;

import java.sql.SQLException;
import java.util.List;

public class InvoiceDetailBUS {
    InvoiceDetailDAOImpl invoiceDetailDAO = new InvoiceDetailDAOImpl();
    public InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail){
        try {
            return invoiceDetailDAO.create(invoiceDetail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<InvoiceDetail> findByInvoiceId(Integer invoiceId){
        return invoiceDetailDAO.findAllByInvoiceId(invoiceId);
    }


    public void updateDetailInvoice(InvoiceDetail invoiceDetail){
        try {
            invoiceDetailDAO.update(invoiceDetail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //delete tat ca cac hoa don co id la ?
    public boolean deleteDetailInvoice(Integer invoiceId){
        try {
            return invoiceDetailDAO.delete(invoiceId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
