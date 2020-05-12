package Database;

import Database_Tables.*;


public class Calculate_Fees
{
    public static float Calculate_Fees_Of_Service (CDR cdr_oneUser, Profile_Services profile_Services)
    {
        float cost = 0;
        float round = cdr_oneUser.getDuration_msg_volume()/profile_Services.getRound_amount();
        
        if (cdr_oneUser.getDuration_msg_volume() < profile_Services.getRound_amount())
        {
            round = 1;
        }
               
        if (profile_Services != null)
        {
            if ((cdr_oneUser.getDiala().substring(0, 6)).equals(cdr_oneUser.getDialb().substring(0, 6))) 
            {
                cost = round * profile_Services.getFees_local_same();
            } 
            else if ((cdr_oneUser.getDiala().substring(0,5)).equals(cdr_oneUser.getDialb().substring(0,5)))
            {
                cost = round * profile_Services.getFees_local_diff();
            }
            else if ((cdr_oneUser.getDialb().substring(0, 3)).equalsIgnoreCase("www") || (cdr_oneUser.getDialb().substring(0, 4).equalsIgnoreCase("http"))) 
            {
                cost = round * profile_Services.getFees_local_same();
            } 
            else 
            {
                cost = round * profile_Services.getFees_international();
            }            
        }
        
        return cost;
    }
}
