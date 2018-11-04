public class PrintJob implements Comparable<PrintJob>
{
    private String user;
    private int priority;
    private int pages;
    
    public PrintJob(String use, int pri, int page)
    {
        user = use;
        priority = pri;
        pages = page;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public int getPriority()
    {
        return priority;
    }
    
    public int getPages()
    {
        return pages;
    }

    @Override
    public int compareTo(PrintJob o)
    {
        return (this.pages*this.priority) - (o.pages*o.priority);
    }
}
