package payment;

public class Payment 
{
    public static boolean makeUPIPayment(String upiId, double amount)
    {
        System.out.println("Making UPI payment to " + upiId + " for amount: " + amount);
        if(upiId.equalsIgnoreCase("anshul04"))
        {
            return true;
        }
        return false;
    }

    
    public static boolean makeCreditCardPayment(String cardNumber, String expiryDate, String cvv, double amount, String otp) 
    {
        System.out.println("Making debit card payment with card number " + cardNumber + " for amount: " + amount);
        if(otp.equalsIgnoreCase("0000"))
        {
            return true;
        }
            
        else 
        {
            System.out.println("Invalid OTP. Payment failed.");
            return false;
        }
    }

    public static boolean makeDebitCardPayment(String cardNumber, String expiryDate, String cvv, double amount, String otp) 
    {

        System.out.println("Making debit card payment with card number " + cardNumber + " for amount: " + amount);
        if(otp.equalsIgnoreCase("0000"))
        {
            return true;
        }
            
        else 
        {
            System.out.println("Invalid OTP. Payment failed.");
            return false;
        }
    }

    

}