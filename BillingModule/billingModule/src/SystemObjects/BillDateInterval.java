package SystemObjects;


public class BillDateInterval {
    
    String startDate;
    String endDate;
    
    public BillDateInterval(){
        
    }
    
    public BillDateInterval(String _startDate, String _endDate){
        this.startDate = _startDate;
        this.endDate = _endDate;
    }
    
    public void setStartDate(String _startDate){
         this.startDate = _startDate;
    }
    
    public void setEndDate(String _endDate){
        this.endDate = _endDate;
    }
    
    public String getStartDate(){
        return this.startDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
}
