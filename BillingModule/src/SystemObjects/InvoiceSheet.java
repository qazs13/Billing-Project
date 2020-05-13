package SystemObjects;

import java.sql.Date;


public class InvoiceSheet {
    
    int billId;
    String customerName;
    String customerNumber;
    String address;
    String profileName;
    Float profileFees, oneTimeServiceFees;
    Float recurringFees, totalVoiceCost, totalSMSCost;
    Float totalDataCost, totalInvoiceBefore;
    Float totalInvoiceAfter;
    String startDate,endDate;
    Date billGenerationDate;
    
    public InvoiceSheet(){
    }
    
    public InvoiceSheet(int _billId,String _customerName,String _customerNumber,String _address,String _profileName,
            Float _profileFees,Float _oneTimeServiceFees,Float _recurringFees,
            Float _totalVoiceCost,Float _totalSMSCost,Float _totalDataCost,
            Float _totalInvoiceBefore,Float _totalInvoiceAfter,String _startDate, 
            String _endDate, Date _billGenerationDate){
        
        this.billId = _billId;
        this.customerName = _customerName;
        this.customerNumber = _customerNumber;
        this.address = _address;
        this.profileName = _profileName;
        this.profileFees = _profileFees;
        this.oneTimeServiceFees = _oneTimeServiceFees;
        this.recurringFees = _recurringFees;
        this.totalVoiceCost = _totalVoiceCost;
        this.totalSMSCost = _totalSMSCost;
        this.totalDataCost = _totalDataCost;
        this.totalInvoiceBefore = _totalInvoiceBefore;
        this.totalInvoiceAfter = _totalInvoiceAfter;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.billGenerationDate = _billGenerationDate;
    }
    
    public InvoiceSheet(String _customerName,String _customerNumber,String _address,String _profileName,
            Float _profileFees,Float _oneTimeServiceFees,Float _recurringFees,
            Float _totalVoiceCost,Float _totalSMSCost,Float _totalDataCost,
            Float _totalInvoiceBefore,Float _totalInvoiceAfter,String _startDate, 
            String _endDate,Date _billGenerationDate){
        
        this.customerName = _customerName;
        this.customerNumber = _customerNumber;
        this.address = _address;
        this.profileName = _profileName;
        this.profileFees = _profileFees;
        this.oneTimeServiceFees = _oneTimeServiceFees;
        this.recurringFees = _recurringFees;
        this.totalVoiceCost = _totalVoiceCost;
        this.totalSMSCost = _totalSMSCost;
        this.totalDataCost = _totalDataCost;
        this.totalInvoiceBefore = _totalInvoiceBefore;
        this.totalInvoiceAfter = _totalInvoiceAfter;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.billGenerationDate = _billGenerationDate;
    }

    public InvoiceSheet(int i, String amr_Walid, String string, String agouza, String orange_100, float f, float f0, float f1, float f2, float f3, float f4, float f5, float f6, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setBillId(int _billId){
        this.billId = _billId;
    }
    
    public void setCustomerName(String _customerName){
        this.customerName = _customerName;
    }
    
    public void setCustomerNumber(String _customerNumber){
        this.customerNumber = _customerNumber;
    }
    
    public void setAddress(String _address){
        this.address = _address;
    }
    
    public void setProfileName(String _profileName){
        this.profileName = _profileName;
    }
    
    public void setProfileFees(Float _profileFees){
        this.profileFees = _profileFees;
    }
    
    public void setOneTimeServiceFees(Float _oneTimeServiceFees){
        this.oneTimeServiceFees = _oneTimeServiceFees;
    }

    public void setRecurringFees(Float _recurringFees){
        this.recurringFees = _recurringFees;
    }
    
    public void setTotalVoiceCost(Float _totalVoiceCost){
        this.totalVoiceCost = _totalVoiceCost;
    }
    
    public void setTotalSMSCost(Float _totalSMSCost){
        this.totalSMSCost = _totalSMSCost;
    }
    
    public void setTotalDataCost(Float _totalDataCost){
        this.totalDataCost = _totalDataCost;
    }

    public void setTotalInvoiceBefore(Float _totalInvoiceBefore){
        this.totalInvoiceBefore = _totalInvoiceBefore;
    }
    
    public void setTotalInvoiceAfter(Float _totalInvoiceAfter){
        this.totalInvoiceAfter = _totalInvoiceAfter;
    }
    
    public void setStartDate(String _startDate){
        this.startDate = _startDate;
    }
    
    public void setEndDate(String _endDate){
        this.endDate = _endDate;
    }
    
    public void setBillGenerationDate(Date _billGenerationDate){
        this.billGenerationDate = _billGenerationDate;
    }

    
    
    
    
    public int getBillId(){
        return this.billId;
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public String getCustomerNumber(){
        return this.customerNumber;
    }
      
    public String getAddress(){
        return this.address;
    }
    
    public String getProfileName(){
        return this.profileName;
    }
    
    public Float getProfileFees(){
        return this.profileFees;
    }
    
    public Float getOneTimeServiceFees(){
        return this.oneTimeServiceFees;
    }

    public Float getRecurringFees(){
        return this.recurringFees;
    }
    
    public Float getTotalVoiceCost(){
        return this.totalVoiceCost;
    }
    
    public Float getTotalSMSCost(){
        return this.totalSMSCost;
    }
    
    public Float getTotalDataCost(){
        return this.totalDataCost;
    }

    public Float getTotalInvoiceBefore(){
        return this.totalInvoiceBefore;
    }
    
    public Float getTotalInvoiceAfter(){
        return this.totalInvoiceAfter;
    }
    
    public String getStartDate(){
        return this.startDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
    
    public Date getBillGenerationDate(){
        return this.billGenerationDate;
    }
    
}
