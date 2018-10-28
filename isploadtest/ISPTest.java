/******************************************************************************
*   
*  This program is an unpublished work fully protected by the United
*  States copyright laws and is considered a trade secret belonging
*  to NET. To the extent that this work may be considered "published,"
*  the following notice applies:
*   
*     "Copyright 2003, National Engineering Technology, all rights reserved."
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


import com.nateng.riits.isp.vds.*;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Element;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.util.Calendar;
public class ISPTest extends Thread 
{
   Utils utils = new Utils();
   CongestionService congestionService = null;
   CongestionServiceService cs = null;
   String xmlStr = ""; 
   ByteArrayInputStream bais = new ByteArrayInputStream( xmlStr.getBytes());
   FileWriter fw = null; 
   org.w3c.dom.Element rootElement = null;
   int loop = 1;
   int id = 0;
   long interval = 30000; // 30 second
   boolean pretty = false;
   boolean dump = false; 

   protected ISPTest()
   {
   }
   public ISPTest(int id, long interval, boolean dump, boolean pretty)
   {
      this.id = id;
      this.interval = interval;
      this.dump = dump;
      this.pretty = pretty;
   } 
   protected void dump(String fileName)
   {
      try
      {
         if( dump )
         {
            fw = new FileWriter( fileName );
            if( pretty )
            {
               bais.close();
               bais = null;
               bais = new ByteArrayInputStream( xmlStr.getBytes() );
               rootElement = XMLUtils.newDocument( bais ).getDocumentElement();
               XMLUtils.PrettyElementToWriter( rootElement, fw);
            }
            else
            {
               fw.write(xmlStr);
            }
            fw.close();
         }
      }
      catch(Exception e)
      {
         System.out.println("failed dump data to file " + fileName);
      }
   }
   
   public void run()
   {
      
      try
      {
         System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : initilizing CongestionService ... ");
         if( cs == null )
            cs = new CongestionServiceServiceLocator();
         congestionService = cs.getISP_CongestionService();
         System.out.println("At Address: " + cs.getISP_CongestionServiceAddress() + " Thread id = " + id );
      }
      catch( Exception ex )
      {
         System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Failed initilizing CongestionService ... " + ex.getMessage());
      }
      try
      {
         System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Getting Congestion Configuration Data from Caltrans D7 ... " );
         xmlStr = congestionService.getTrafficInfo( utils.getUser(),
                                         utils.getPass(),
                                         utils.getMesgReq("congestionFreeway", "Caltrans-D7", "inventory"));
         dump("xml/" + id + "congestion_configuration_from_d7.xml");
         System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Got Congestion Configuration Data from Caltrans D7 ... ");
      }
      catch( Exception ex )
      {  
         System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Failed getting Congestion Configuration Data from Caltrans D7 ... " + ex.getMessage());
      }
      

      while( loop < 240 )
      {   
         try
         {
            
            // Congestion ////////////////////////
            try
            {
               System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Getting Congestion Realtime Data from Caltrans D7 ... ");
               xmlStr = congestionService.getTrafficInfo( utils.getUser(),
                                         utils.getPass(),
                                         utils.getMesgReq("congestionFreeway", "Caltrans-D7", "real-time"));
               dump("xml/" + id + "congestion_realtime_from_d7.xml");
               System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Got Congestion Realtime Data from Caltrans D7 ... ");
            }
            catch( Exception ex )
            {
               System.out.println(Calendar.getInstance().getTime() + " Thread id = " + id + " : Failed getting Congestion Realtime Data from Caltrans D7 ... " + ex.getMessage());
            }
            
         } 
         catch(Exception e)
         {
            e.printStackTrace();
         }
         try
         {
            Thread.currentThread().sleep(interval);
         }
         catch(Exception ex2)
         {
            System.out.println("failed Thread.currentThread().sleep():" + interval);
         }
         loop++; 
      }
   }
   public static void main(String[] args) throws Exception
   {
      int nT = 1;
      boolean dump = false;
      boolean pretty = false;
      long interval = 30000;
      String usr = "";
      String pass = "";
      String agency = "";
 
      if( args != null && args.length > 0 )
      {
         try
         {
            if( args.length > 1 )
            {
               Long pTime = new Long(args[1]);
               if( (pTime != null) && (pTime.longValue() >= 1000) ) // at least one second
               {
                  interval = pTime.longValue();
               }
            }
         }
         catch(Exception e)
         {        
            interval = 30000;
            System.out.println("failed parse args interval");
         }  
         try
         {
            if( args.length > 2 )
            {
               String str = args[2] + "";
               if( (str != null) && (str.startsWith("dump")) )
               {
                  dump = true;
               }
            }
         }
         catch(Exception e)
         {        
            dump = false;
            System.out.println("failed parse args dump");
         }  
         try
         {
            if( args.length > 3 )
            {
               String str = args[3] + "";
               if( (str != null) && (str.startsWith("pretty")) )
               {
                  pretty = true;
               }
            }
         }
         catch(Exception e)
         {        
            pretty = false;
            System.out.println("failed parse args pretty");
         }  
         try
         {
            if( args.length > 6 )
            {
               usr = args[4] + "";
               pass = args[5] + "";
               agency = args[6] + "";
               Utils.user = usr;
               Utils.pass = pass;
               Utils.myAgency = agency;
               System.out.println("user = " + usr + ". pass = " + pass + ". agency = " + agency);
            }

         }
         catch(Exception e)
         {
            System.out.println("failed parse args user name");
         }
         try
         {
            Integer count = new Integer(args[0]);
            nT = count.intValue();
         }
         catch(Exception e)
         {
            nT = 1;
            System.out.println("failed parse args number threads");
         }

      }

      System.out.println("Note: will start " + nT + " threads" );

      ISPTest[] iaiList = new ISPTest[nT];
      for( int i = 0; i < nT; i++)
      {
         try
         {
            iaiList[i] = new ISPTest(i+1, interval, dump, pretty);
            iaiList[i].start();
            System.out.println("Start thread number: " + (i+1)  + " with interval = " + interval + 
                               " and dump to file = " + dump + " in pretty format = " + pretty );
            Thread.currentThread().sleep(7000);
         }
         catch(Exception e)
         {
            e.printStackTrace();
            System.out.println("Failed start thread # " + (i+1) ) ;
            Thread.currentThread().sleep(7000);
         }
      }
   }


}
