package FilesName;

public class FilesName 
{
    String fileName;
    String fileDate;
    String filePath;

    public FilesName(String fileName, String fileDate, String filePath) 
    {
        this.fileName = fileName;
        this.fileDate = fileDate;
        this.filePath = filePath;
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
}