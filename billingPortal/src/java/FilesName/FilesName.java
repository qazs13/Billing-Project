package FilesName;

public class FilesName 
{
    String fileName;
    String fileDate;
    String filePath;
    String filePhoneNumber;

    public FilesName(String fileName, String fileDate, String filePath, String filePhoneNumber) 
    {
        this.fileName = fileName;
        this.fileDate = fileDate;
        this.filePath = filePath;
        this.filePhoneNumber = filePhoneNumber;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public void setFileDate(String fileDate) 
    {
        this.fileDate = fileDate;
    }
    
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }
    
    public void setFilePhoneNumber (String filePhoneNumber)
    {
        this.filePhoneNumber = filePhoneNumber;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public String getFileDate() 
    {
        return fileDate;
    }
    
    public String getFilePath() 
    {
        return filePath;
    }
    
    public String getFilePhoneNumber()
    {
        return filePhoneNumber;
    }
}