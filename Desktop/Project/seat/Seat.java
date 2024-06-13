package seat;
import java.util.UUID;
public class Seat 
{
    private String seatNumber;
    private String pnr;

    public Seat(String seatNumber) 
    {
        this.seatNumber = seatNumber;
        this.pnr = generatePNR();
    }

    public String getSeatNumber()
    {
        return seatNumber;
    }

    public String getPNR() 
    {
        return pnr;
    }

    private String generatePNR() 
    {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
}