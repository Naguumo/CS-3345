
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Printer
{
    public static void main(String[] args)
    {
        BinaryHeap<PrintJob> heap = new BinaryHeap<>();
        
        Scanner in;
        try
        {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex)
        {
            System.out.println("Error File Not Found!");
            return;
        }
        
        while(in.hasNextLine())
        {
            String s = in.nextLine();
            String[] temp = s.split("\t");
            
            PrintJob job;
            if(temp[3].equals("I"))
            {
                job = new PrintJob(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            }
            else
                job = new OutsidePrintJob(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            
            heap.insert(job);
        }
        
        while(!heap.isEmpty())
        {
            PrintJob job = heap.deleteMin();
            String outside = (job instanceof OutsidePrintJob) ? 
                    "which costs $" + ((OutsidePrintJob)job).getCost() :
                    "";
            
            System.out.printf("User %s with Priority %d is printing %d Pages %s \n", 
                    job.getUser(), 
                    job.getPriority(), 
                    job.getPages(), 
                    outside);
        }
    }
}
