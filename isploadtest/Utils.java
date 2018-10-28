/******************************************************************************
*   
*  This program is an unpublished work fully protected by the United
*  States copyright laws and is considered a trade secret belonging
*  to NET. To the extent that this work may be considered "published,"
*  the following notice applies:
*   
*     "Copyright 2003, National Engineering Technology, all rights reserved."
*   
*  Any unauthorized use, reproduction, distribution, display,
*  modification, or disclosure of this program is strictly prohibited.
*   
* FILE NAME:   $Source: $
*   
* VERSION       DATE      TIME                AUTHOR
* ------------  --------  ------------------  --------------------------------
* $Log: $
*   
******************************************************************************/

public class Utils 
{
   public static String user = "waycare1";
   public static String pass = "Wy9706bb34";
   public static String myAgency = "waycare"; 
   public String getMesgReq(String requestType, String issueAgency, String verbosity)
   {
      String retrn = "" +
      "<informationRequest>" +
         "<messageHeader>" +
            "<sender>" +
               "<agencyName>" + myAgency + "</agencyName>" + 
            "</sender>" + 
            "<messageID>87654321</messageID>" +
            "<timeStamp>" +                    
               "<date>20040826</date>" +  
               "<time>16071800</time>" +
            "</timeStamp>" +
         "</messageHeader>" +
         "<filter>" +
            "<dataTypes>" +
               "<localInformationRequestType>" +
                  "<requestType>" + requestType + "</requestType>" + 
               "</localInformationRequestType>" +
            "</dataTypes>" +
            "<issueAgencies>" +
               "<issueAgency>" + issueAgency + "</issueAgency>" +
            "</issueAgencies>" +
         "</filter>" +
         "<verbosity>" + verbosity + "</verbosity>" +  
      "</informationRequest>";
      return (retrn);
   } 
   public String getUser()
   {
      return user;
   }
   public String getPass()
   {
      return pass;
   }
}
