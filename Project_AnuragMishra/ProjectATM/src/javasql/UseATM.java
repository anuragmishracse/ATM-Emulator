package javasql;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.io.*;
public class UseATM 
{
    public static void main(String ...args)throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root", "@nur@g");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            System.out.println("Enter\n 1. for user-login.\n 2. for admin-login.");
            int k=Integer.parseInt(br.readLine());
            int check=-1;
            if(k==2)
            {
            System.out.println("Enter admin-id!");
            String name=br.readLine();
            System.out.println("Enter password!");
            String pass=br.readLine();
            Statement stmt2=con.createStatement();
            String qad="SELECT * from admin;";
            ResultSet rad=stmt2.executeQuery(qad);
            while(rad.next())
            {
                if(rad.getString("adminid").equals(name)&&rad.getString("password").equals(pass))
                    check=1;
            }
            
            if(check==1)
            {
                System.out.println("Hey Admin!");
                while(true)
                {
                    System.out.println("Enter your choice: \n 1. for adding a user.\n 2. for deleting a user.\n 3. to add money to the account of user.\n 4. to update details of any user.\n 5. to add notes to ATM machine.");
                    int ch=Integer.parseInt(br.readLine());
                    switch(ch)
                    {
                    case 1:
                        System.out.println("Enter the account-number, pin, user-name, balance of new user");
                        String acn1=br.readLine();
                        int p1=Integer.parseInt(br.readLine());
                        String n1 =br.readLine();
                        Double b1=Double.parseDouble(br.readLine());
                        String q1="INSERT into user VALUES('"+ acn1 +"',"+ p1 +",'"+ n1 +"',"+ b1 +")";
                        stmt.executeUpdate(q1);
                        System.out.println("Details of new user added successfully!");
                        break;
                    
                    
                case 2:
                    System.out.println("Enter the account number of the user you want to delete!");
                    int f=-1;
                    String acn2=br.readLine();
                    String q21="SELECT acctno from user;";
                    ResultSet rs = stmt.executeQuery(q21);
                    while(rs.next())
                    {
                        if(rs.getString("acctno").equals(acn2))
                            f=1;
                    }
                    if(f==1)
                    {
                    String q2="DELETE from user where acctno='"+ acn2 +"';";
                    stmt.executeUpdate(q2);
                    System.out.println("User deleted successfully!");
                    }
                    else
                        System.out.println("Sorry, the account number does not exist!");
                    break;
                    
                
                    
                case 3:
                    System.out.println("Enter the account-number of the user!");
                    String acn3=br.readLine();
                    Double balance=-1.;
                    String q3="SELECT bal from user where acctno='"+ acn3 +"';";
                    ResultSet rs1 = stmt.executeQuery(q3);
                    while(rs1.next())
                    {
                        balance=rs1.getDouble("bal");
                    }
                   
                    if(balance!=-1.)
                    {
                    System.out.println("Enter the amount you have to add!");
                    Double b=Double.parseDouble(br.readLine());
                    balance+=b;
                    String q31="UPDATE user SET bal="+ balance +"where acctno='"+ acn3 +"';";
                    stmt.executeUpdate(q31);
                    System.out.println("Balance updated!");
                    }
                    else
                        System.out.println("Sorry, the account number does not exist!");
                   
                    break;
                    
                    
                case 4:
                    System.out.println("Enter the account number to update the details of the user");
                    int f2=-1;
                    String acn4=br.readLine();
                    String q41="SELECT acctno from user;";
                    ResultSet rs2 = stmt.executeQuery(q41);
                    while(rs2.next())
                    {
                        if(rs2.getString("acctno").equals(acn4))
                            f2=1;
                    }
                    if(f2==1)
                    {
                    System.out.println("Enter his new user name!");
                    String n2=br.readLine();
                    String q4="UPDATE user SET uname='"+ n2 +"'where acctno='"+ acn4 +"';"; 
                    stmt.executeUpdate(q4);
                    System.out.println("Details updated successfully!");
                    }
                    else
                        System.out.println("Sorry, the account number does not exist!");
                    break;
                    
                case 5:
                    int dbnote1=0,dbnote2=0,dbnote3=0,dbnote4=0;
                    System.out.println("Enter number of thousand notes!");
                    int note1=Integer.parseInt(br.readLine());
                    System.out.println("Enter number of five hundred notes!");
                    int note2=Integer.parseInt(br.readLine());
                    System.out.println("Enter number of hundred notes!");
                    int note3=Integer.parseInt(br.readLine());
                    System.out.println("Enter number of fifty notes!");
                    int note4=Integer.parseInt(br.readLine());
                    String qnote="SELECT * from money;";
                    ResultSet rnote=stmt2.executeQuery(qnote);
                    while(rnote.next())
                    {
                        dbnote1=rnote.getInt("thousand");
                        dbnote2=rnote.getInt("fivehundred");
                        dbnote3=rnote.getInt("hundred");
                        dbnote4=rnote.getInt("fifty");
                    }
                    dbnote1+=note1;
                    dbnote2+=note2;
                    dbnote3+=note3;
                    dbnote4+=note4;
                    String upnote="UPDATE money SET thousand="+ dbnote1 +",fivehundred="+ dbnote2 +",hundred="+ dbnote3 +",fifty="+ dbnote4 +";";
                    stmt.executeUpdate(upnote);
                    System.out.println("Notes added successfully!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
             System.out.println("Do you want to continue?\nEnter 1 for YES or 2 for NO!");
            int adch=Integer.parseInt(br.readLine());
            if(adch==2)
            {
                System.out.println("Thank You Admin!");
                System.exit(0);
            }
            }    
            }
            else
            System.out.println("Sorry, wrong admin-id or password!");
          } 
           
            
            else if(k==1)
            {
                System.out.println("Enter account number!");
                String user = br.readLine();
                System.out.println("Enter pin!");
                int pin1=Integer.parseInt(br.readLine());
                int check2=-1;
                Statement stmt3 = con.createStatement();
                String qus="SELECT * from user;";
                ResultSet rus=stmt3.executeQuery(qus);
                while(rus.next())
                {
                    if(rus.getString("acctno").equals(user)&& rus.getInt("pin")==(pin1))
                    {
                        System.out.println("User Login successfull!");
                        check2=1;
                    }
                }
                
                if(check2==1)
                {
                    while(true)
                    {
                    System.out.println("Enter\n 1. for cash withrawal.\n 2. to check balance!");
                    int choice = Integer.parseInt(br.readLine());
                    int z1=0,z2=0,z3=0,z4=0,d1=0,d2=0,d3=0,d4=0;
                    if(choice==1)
                    {
                       Double balc=-1.0;
                       String qu1="SELECT bal from user where acctno='"+ user +"';";
                       rus=stmt3.executeQuery(qu1);
                       while(rus.next())
                         balc=rus.getDouble("bal");
                      int tamt=0,thou=0,fiveh=0,hund=0,fif=0;
                      System.out.println("Enter the amount you want to withdraw!");
                      int amt=Integer.parseInt(br.readLine());
                      String qu2="SELECT * from money;";
                      rus=stmt3.executeQuery(qu2);
                      while(rus.next())
                      {
                          thou=rus.getInt("thousand");
                          fiveh=rus.getInt("fivehundred");
                          hund=rus.getInt("hundred");
                          fif=rus.getInt("fifty");
                      }
                      tamt=thou*1000+fiveh*500+hund*100+fif*50;
                      if(balc<=0||balc<amt)
                          System.out.println("You do not have sufficient balance in your account!");
                      else if(amt>tamt)
                          System.out.println("Your amount is larger than currently available amount in ATM!");
                      else if(amt%50!=0)
                          System.out.println("Please enter valid amount...!\nEnter amount in multiple of 50, 100, 500 or 1000!");
                      else if(tamt==0)
                          System.out.println("Sorry!...No amount in ATM!");
                      else if(fif==0&&amt%100==50)
                          System.out.println("There are no Rs 50 note in the bank. Please enter amount in multiple of 100!");
                      else
                      {
                          if(thou>=(amt/1000)){
                          z1=amt/1000;
                          d1=amt%1000;}
                          else if(thou>0)
                          {
                              z1=thou;
                              d1=amt%(thou*1000);
                          }
                          else
                          {
                              z1=0;
                              d1=amt;
                          }
                          if(fiveh>=(d1/500)){
                          z2=d1/500;
                          d2=d1%500;}
                          else if(fiveh>0)
                          {
                              z2=fiveh;
                              d2=d1%(fiveh*500);
                          }
                          else
                          {
                              z2=0;
                              d2=d1;
                          }
                          if(hund>=(d2/100))
                          {
                          z3=d2/100;
                          d3=d2%100;
                          }
                          else if(hund>0)
                          {
                              z3=hund;
                              d3=d2%(hund*100);
                          }
                          else
                          {
                              z3=0;
                              d3=d2;
                          }
                          if(fif>=(d3/50))
                          {
                          z4=d3/50;
                          d4=d3%50;
                          }
                          else if(thou>0)
                          {
                              z4=fif;
                             
                          }
                          else
                          {
                              z4=0;
                              d4=d3;
                          }
                                
                          System.out.println("Withdraw Cash!");
                          System.out.println("Your Amount : "+amt);
                          System.out.println("Do you want receipt?\nEnter 1 for YES or 2 for NO!");
                          int rec=Integer.parseInt(br.readLine());
                          if(rec==1)
                          {
                             if(z1!=0)System.out.println("1000 * "+z1+" = "+z1*1000); 
                             if(z2!=0)System.out.println("500  * "+z2+" = "+z2*500);
                             if(z3!=0)System.out.println("100  * "+z3+" = "+z3*100);
                             if(z4!=0)System.out.println("50   * "+z4+" = "+z4*50);
                             System.out.println("Total Amount withdrawn = "+amt);
                             System.out.println("Thanks for using HPES Bank!");
                          }
                          else if(rec==2)
                              System.out.println("Thanks for using HPES Bank!");
                          else
                              System.out.println("Invalid choice!");
                          thou-=z1;
                          fiveh-=z2;
                          hund-=z3;
                          fif-=z4;
                          String qup="UPDATE money SET thousand="+ thou +", fivehundred="+ fiveh +",hundred="+ hund +",fifty="+ fif +";";
                          stmt3.executeUpdate(qup);
                          balc-=amt;
                          String qup2="UPDATE user SET bal="+ balc +"where acctno='"+ user +"';";
                          stmt3.executeUpdate(qup2);
                      }
                      
                    }
                    else if(choice==2)
                    {
                       Double balc=-1.0;
                       String qu1="SELECT bal from user where acctno='"+ user +"';";
                       rus=stmt3.executeQuery(qu1);
                       while(rus.next())
                         balc=rus.getDouble("bal");
                       System.out.println("Your current balance is : Rs "+balc+" /-");
                    }
                    else
                        System.out.println("Invalid choice!");
                    System.out.println("Do you want to continue?\nEnter 1 for YES or 2 for NO!");
                int ex=Integer.parseInt(br.readLine());
                if(ex==2)
                {
                    System.out.println("Dont forget to take your cash.\nHave a nive day!");
                    System.exit(0);
                }
                }
                }
                else
                    System.out.println("Sorry, wrong account number or password!");
                
                    
            
        }
            else
                System.out.println("Invalid Choice!");
            
        }
        catch(Exception e)
        {
            System.out.println("Exception is : "+e);
            System.out.println("Connection failed!");
        }
        
    }
}
