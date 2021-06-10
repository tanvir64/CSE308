import java.util.*;

class Accounts{
    private String account_type;
    private String account_name;
    private Double account_balance;
    private Double loan_amount = 0.0;
    private Double loan_request;
    Double loan_int_rate = 0.10; 
    Boolean has_loan_request = false;
    Accounts(String name,String type,Double amount){
        account_name = name;
        account_type = type;
        account_balance = amount;
        System.out.println(account_type+" account for "+account_name+" Created;initial balance "+account_balance+"$");
    }
    void set_balance(Double amount){account_balance += amount;}
    Double get_balance(){return account_balance;}
    void reduce_balance(Double amount){account_balance -= amount;}    
    Double yearly_loan_int(){return (get_loan_amount() * loan_int_rate);}
    void set_requested_loan(Double amount){loan_request = amount;}              
    Double get_requested_loan(){return loan_request;}
    void set_loan_amount(Double loan){loan_amount = loan;}
    Double get_loan_amount(){return loan_amount;}
    Boolean loan_status(){return has_loan_request;}
    void set_loan_status(Boolean b){has_loan_request = b;}
    void set_loan_request(Double amount){
        has_loan_request = true;
        set_requested_loan(amount);
    }
    
    void deposit(Double amount,String type){
        if(type.equals("Fixed") && amount < 50000){
            System.out.println("Invalid transaction.");
        }
        else{
            set_balance(amount);
            System.out.println(amount+"$ deposited;current balance "+get_balance()+"$");
        }
    }    
    String get_name(){return account_name;}
    String get_type(){return account_type;}
    void yearly_calc(){
        Double loan_int = yearly_loan_int();        
        reduce_balance(loan_int+500);
    }
    Double get_max_loan(){return loan_amount;}
    void withdraw(Double amount,int year){
        if(get_balance()-amount < 0){
            System.out.println("Invalid transaction.Insufficient balance.");
        }                       
    }
    Double interest_rate;
    void set_rate(Double rate){interest_rate = rate;}
}

class Savings_Account extends Accounts{
    Savings_Account(String name,String type,Double amount){
        super(name, type, amount);
    }
    @Override
    void withdraw(Double amount,int year){
        if(get_balance()-amount >= 1000){
            reduce_balance(amount);
            System.out.println(amount+"$ withdrawn successfully;current balance "+get_balance()+"$");
        }
        else if(get_balance()-amount < 0){
            System.out.println("Invalid transaction.current balance "+get_balance()+"$");
        }
        else{
            System.out.println("Insufficient balance for transaction.current balance "+get_balance()+"$");
        }
    }
    Double int_rate = 0.10;
    Double max_loan = 10000.00; 
    @Override
    Double get_max_loan(){return max_loan;}
    @Override
    void yearly_calc(){
        Double loan_int = yearly_loan_int();
        Double interest = (int_rate*get_balance());
        set_balance(interest);       
        reduce_balance(loan_int+500);
    } 
    @Override
    void set_rate(Double rate){int_rate = rate/100;}   
}

class Student_Account extends Accounts{
    Student_Account(String name,String type,Double amount){
        super(name, type, amount);
    }    
    @Override
    void withdraw(Double amount,int year){
        if(get_balance()-amount < 0){
            System.out.println("Invalid transaction.current balance "+get_balance()+"$");
        }
        else{
            if(amount > 10000){
                System.out.println("Invalid transaction;current balance "+get_balance()+"$");
            }
            else{
                reduce_balance(amount);
                System.out.println(amount+"$ withdrawn successfully;current balance "+get_balance()+"$");
            }
        }
    }
    Double int_rate = 0.05;
    Double max_loan = 1000.00;
    @Override
    void set_rate(Double rate){int_rate = rate/100;}
    @Override
    Double get_max_loan(){return max_loan;}
    @Override
    void yearly_calc(){
        Double loan_int = yearly_loan_int();
        Double interest = int_rate*get_balance();
        set_balance(interest);       
        reduce_balance(loan_int);
    }    
}

class Fixed_Account extends Accounts{
    Fixed_Account(String name,String type,Double amount){
        super(name, type, amount);
    }
    @Override
    void withdraw(Double amount,int year){
        if(year < 1){
            System.out.println("Invalid transaction.Account not matured yet.");
        }
        else{
            reduce_balance(amount);
            System.out.println(amount+"$ withdrawn successfully;current balance "+get_balance()+"$");
        }
    }
    Double int_rate = 0.15;
    Double max_loan = 1000000.00;
    @Override
    void set_rate(Double rate){int_rate = rate/100;} 
    @Override
    Double get_max_loan(){return max_loan;} 
    @Override
    void yearly_calc(){
        Double loan_int = yearly_loan_int();
        Double interest = (int_rate*get_balance());
        set_balance(interest);       
        reduce_balance(loan_int+500);
    }   
}

class Employees{
    Boolean has_pending_approval = false;    
    void set_pending_loan(Boolean b){has_pending_approval = b;}
    void lookup(Accounts[] accounts,String user_name,int total_accounts){
        int i=0,found = 0;
        for(;i<total_accounts;i++){
            if(accounts[i].get_name().equals(user_name)){
                found = 1;
                System.out.println(user_name+"'s current balance "+accounts[i].get_balance());
            }
        }
        if((i == total_accounts) && found == 0){
            System.out.println("No such account exists");
        }
    }
    void notify_pending() {
        if(has_pending_approval){
            System.out.println("there are loan approvals pending");
        }   
        else{
            System.out.println("");
        } 
    }
}

class Managing_Director extends Employees{    
    void change_rate(Accounts[] accounts,String ac_type,Double rate,int total_accounts){
        for(int i=0;i<total_accounts;i++){
            if(accounts[i].get_type().equals(ac_type)){
                accounts[i].set_rate(rate);
                System.out.println("Interest rate for "+ac_type+" has been changed to "+Double.toString(rate));
            }
        }
    }    
}

class Officer extends Employees{    
}

class Cashier extends Employees{
}

class Bank{
    private Double internalFund;
    Managing_Director MD;
    Officer O1,O2;
    Cashier C1,C2,C3,C4,C5;    
    Bank(){
        internalFund = 1000000.00;
        System.out.print("Bank Created;");        
        MD = new Managing_Director();
        O1 = new Officer();
        O2 = new Officer();
        C1 = new Cashier();
        C2 = new Cashier();
        C3 = new Cashier();
        C4 = new Cashier();
        C5 = new Cashier();
        System.out.println("MD,O1,O2,C1,C2,C3,C4,C5 created");
    }    
    void reduce_fund(Double amount){internalFund -= amount;} 
    void show_fund(){System.out.println("Internal Fund "+Double.toString(internalFund)+"$");} 
    Boolean bankrupt(){return internalFund < 0;}
    void loan_request(Accounts[] accounts,String ac_name,Double amount,int total_accounts){
        for(int j=0;j<total_accounts;j++){
            if(accounts[j].get_name().equals(ac_name)){
                if(amount > accounts[j].get_max_loan()){
                    System.out.println("Invalid loan amount.Your limit for loan is "+Double.toString(accounts[j].get_max_loan())+"$");                
                }
                else{
                    for(int i=0;i<total_accounts;i++){
                        if(accounts[i].get_name().equals(ac_name)){
                            accounts[i].set_loan_request(amount);
                        }
                    }
                    System.out.println("Loan request successful,sent for approval");
                }
            }
        }                
    } 
    void approve_loan(Accounts[] accounts,int total_accounts){        
        for(int i=0;i<total_accounts;i++){
            if(accounts[i].loan_status()){
                accounts[i].set_loan_amount(accounts[i].get_requested_loan());
                reduce_fund(accounts[i].get_requested_loan());
                if(!bankrupt()){
                    System.out.println("Loan offer for "+accounts[i].get_name()+" approved");
                    accounts[i].set_balance(accounts[i].get_loan_amount());
                    accounts[i].set_loan_status(false);
                }
                else{
                    internalFund += accounts[i].get_requested_loan();
                }
            }
        }        
    }         
}

public class Banking{
    public static void main(String[] args){
        System.out.println("Welcome to Our Banking System"); 
        System.out.println("Type Exit to Close Banking"); 
        int year_count = 0;
        Bank bank = new Bank();                                        
        String ac_name = " ";
        String ac_type = " "; 
        Accounts[] accounts = new Accounts[100];                   
        int total_accounts = 0;
        Boolean flag = true;             

        while(flag){
            Double amount,changed_rate;
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(str," ");            
            String command = st.nextToken();
            if(command.equals("Create")){                            
                ac_name = st.nextToken();                                            
                ac_type = st.nextToken();            
                amount = Double.parseDouble(st.nextToken());
                Boolean new_account = true;
                for(int i=0;i<total_accounts;i++){
                    if(accounts[i].get_name().equals(ac_name)){
                        new_account = false;
                        break;
                    }
                }
                if(!new_account){
                    System.out.println("There is already an existing account with that name.Try other names!!");
                }
                else{
                    if(ac_type.equals("Fixed") && amount<100000){
                        System.out.println("You must deposit at least 100000$ to create an account.");
                    }                    
                    else{
                        if(ac_type.equals("Savings")){
                            accounts[total_accounts] = new Savings_Account(ac_name,ac_type,amount);
                            total_accounts++;
                        }
                        else if(ac_type.equals("Student")){
                            accounts[total_accounts] = new Student_Account(ac_name,ac_type,amount);
                            total_accounts++;
                        }
                        else{
                            accounts[total_accounts] = new Fixed_Account(ac_name,ac_type,amount);
                            total_accounts++;
                        }
                    }
                }
            }
            else if(command.equals("Deposit")){
                String s2 = st.nextToken();
                amount = Double.parseDouble(s2);                
                for(int i=0;i<total_accounts;i++){                    
                    if(accounts[i].get_name().equals(ac_name)){
                        accounts[i].deposit(amount,ac_type);                        
                        break;
                    }                                       
                }                
            }
            else if(command.equals("Query")){
                for(int i=0;i<total_accounts;i++){                    
                    if(accounts[i].get_name().equals(ac_name)){
                        if(accounts[i].get_loan_amount() > 0){
                            System.out.println("Current balance "+accounts[i].get_balance()+"$,loan "+accounts[i].get_loan_amount()+"$");                            
                        }                        
                        else{
                            System.out.println("Current balance "+accounts[i].get_balance()+"$"); 
                        }
                    }                    
                }
            }
            else if(command.equals("Withdraw")){
                amount = Double.parseDouble(st.nextToken());
                for(int i=0;i<total_accounts;i++){
                    if(accounts[i].get_name().equals(ac_name)){
                        accounts[i].withdraw(amount,year_count);
                    }
                }
            }
            else if(command.equals("Close")){
                if(!(ac_name.equals("MD")||ac_name.equals("O1")||ac_name.equals("O2")||ac_name.equals("C1")||ac_name.equals("C2"))||
                ac_name.equals("C3")||ac_name.equals("C4")||ac_name.equals("C5")){
                    System.out.println("Transaction closed for "+ac_name);
                }
                else{
                    System.out.println("Operations closed for "+ac_name);
                }
                                
            }
            else if(command.equals("Open")){
                ac_name = st.nextToken();              
                if(!((ac_name.equals("O1"))||(ac_name.equals("O2"))||(ac_name.equals("MD"))||(ac_name.equals("C1"))||(ac_name.equals("C2"))||(ac_name.equals("C3"))||(ac_name.equals("C4"))||(ac_name.equals("C5")))){                    
                    int found = 0,i;
                    for(i=0;i<total_accounts;i++){
                        if(accounts[i].get_name().equals(ac_name)){
                            found = 1;
                            System.out.println("Welcome back,"+ac_name);
                        }
                    }
                    if((i == total_accounts) && found==0){
                        System.out.println("No such user exists");
                    }
                    
                }
                else{                    
                    if(ac_name.equals("MD")||ac_name.equals("O1")||ac_name.equals("O2")){
                        System.out.print(ac_name+" active,");                        
                        if(ac_name.equals("MD")){
                            bank.MD.notify_pending();
                        }
                        else if(ac_name.equals("O1")){
                            bank.O1.notify_pending();
                        }
                        else{
                            bank.O2.notify_pending();
                        }
                    }
                    else{
                        System.out.println(ac_name+" active;");
                    }
                }
            }
            else if(command.equals("INC")){
                year_count++;
                System.out.println("1 year passed");
                for(int i=0;i<total_accounts;i++){
                    accounts[i].yearly_calc();
                }
            }
            else if(command.equals("Lookup")){
                String user_name = st.nextToken();
                if(ac_name.equals("MD")){
                    bank.MD.lookup(accounts, user_name, total_accounts);
                }
                else if(ac_name.equals("01")||ac_name.equals("02")){
                    bank.O1.lookup(accounts, user_name, total_accounts);
                }
                else{
                    bank.C1.lookup(accounts, user_name, total_accounts);
                }
            }
            else if(command.equals("Request")){
                amount = Double.parseDouble(st.nextToken());
                bank.loan_request(accounts,ac_name,amount,total_accounts);
                bank.O1.set_pending_loan(true);            
                bank.MD.set_pending_loan(true);
                bank.O2.set_pending_loan(true);                
            }
            else if(command.equals("Change")){
                ac_type = st.nextToken();
                changed_rate = Double.parseDouble(st.nextToken());
                if(ac_name.equals("MD")){
                    bank.MD.change_rate(accounts, ac_type, changed_rate, total_accounts);
                }
                else{
                    System.out.println("You don’t have permission for this operation");
                }
            }
            else if(command.equals("Approve")){
                String s1 = st.nextToken();
                if(ac_name.equals("MD")||ac_name.equals("O1")||ac_name.equals("O2")){
                    bank.approve_loan(accounts, total_accounts);
                    bank.MD.set_pending_loan(false);
                    bank.O1.set_pending_loan(false);
                    bank.O2.set_pending_loan(false);         
                }
            }
            else if(command.equals("See")){
                if(!(ac_name.equals("MD"))){
                    System.out.println("You don't have permission for this operation");
                }
                else{
                    bank.show_fund();
                }
            }
            else if(command.equals("Exit")){
                System.out.println("Thanks for using our system"); 
                scanner.close();             
                flag = false;                            
            }            
        }                       
    }
}