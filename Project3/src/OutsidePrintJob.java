public class OutsidePrintJob extends PrintJob
{
    public OutsidePrintJob(String use, int pri, int page)
    {
        super(use, pri, page);
    }
    
    public double getCost()
    {
        return .1 * getPages();
    }
}
