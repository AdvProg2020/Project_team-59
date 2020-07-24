package Controller.NetWork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        new BankImpl().run();
    }

    public static class BankImpl {
        private ArrayList<BankAccount> accounts = new ArrayList<>();
        private ArrayList<Receipt>receipts=new ArrayList<>();
        private ServerSocket serverSocket;
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;

        public ArrayList<BankAccount> getAccounts() {
            return accounts;
        }

        public void setAccounts(ArrayList<BankAccount> accounts) {
            this.accounts = accounts;
        }

        public ArrayList<Receipt> getReceipts() {
            return receipts;
        }

        public void setReceipts(ArrayList<Receipt> receipts) {
            this.receipts = receipts;
        }

        private void readAccountsFromDB() {
            File db = new File("src/AccountInfoInBank.txt");
            try {
                Scanner scanner = new Scanner(db);
                while (scanner.hasNextLine()) {
                    String userLine = scanner.nextLine();
                    String[] userEntries = userLine.split("\\s+");
                    accounts.add(new BankAccount(userEntries[0], userEntries[1], userEntries[2], userEntries[3]));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            }
        }
        private void readReceiptsDromDB(){
            File db = new File("src/Receipts.txt");
            try {
                Scanner scanner = new Scanner(db);
                while (scanner.hasNextLine()) {
                    String receiptLine = scanner.nextLine();
                    String[] userEntries = receiptLine.split("\\s+");
                    Reciept_type reciept_type=null;
                    if (userEntries[2].equalsIgnoreCase("deposit"))
                        reciept_type=Reciept_type.DEPOSIT;
                    else if (userEntries[2].equalsIgnoreCase("withdraw"))
                        reciept_type=Reciept_type.WITHDRAW;
                    else if (userEntries[2].equalsIgnoreCase("move"))
                        reciept_type=Reciept_type.MOVE;
                    else {
                        System.out.println("invalid Receipt type");

                    }
                    receipts.add(new Receipt(Integer.parseInt(userEntries[0]),userEntries[1], reciept_type,Double.parseDouble(userEntries[3]),userEntries[4],userEntries[5],userEntries[6]));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            }
        }
        private String getReceiptInfo(){
            StringBuilder receiptInfo = new StringBuilder();
            for (Receipt receipt : receipts) {
                receiptInfo.append(receipt.getId()).append("\t")
                        .append(receipt.getToken()).append("\t")
                        .append(receipt.getReciept_type()).append("\t")
                        .append(receipt.getMoney()).append("\t")
                        .append(receipt.getSourceId()).append("\t")
                        .append(receipt.getDestId()).append("\t")
                        .append(receipt.getDescription()).append("\n");

            }
            return receiptInfo.toString();
        }
        private String getUserInfo() {
            StringBuilder userInfo = new StringBuilder();
            for (BankAccount account : accounts) {
                userInfo.append(account.getFirstName()).append("\t")
                        .append(account.getLastName()).append("\t")
                        .append(account.getUserName()).append("\t")
                        .append(account.getPassword()).append("\n");

            }
            return userInfo.toString();
        }

        private void updateAccountsInDB() {
            String toBeWritten = getUserInfo();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("src/AccountInfoInBank.txt");
                fileOutputStream.write(toBeWritten.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            } catch (IOException e) {
                System.err.println("error updating data base!");
            }
        }

        private void updateReceiptInfo(){
            String toBeWritten = getReceiptInfo();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("src/Receipts.txt");
                fileOutputStream.write(toBeWritten.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            } catch (IOException e) {
                System.err.println("error updating data base!");
            }
        }
        public void addAccount(BankAccount account) {
            accounts.add(account);
            updateAccountsInDB();
        }
        private void addReceipt(Receipt receipt){
            receipts.add(receipt);
            updateReceiptInfo();

        }
        private void deleteAccount(BankAccount bankAccount) {
            accounts.remove(bankAccount);
            updateAccountsInDB();
        }

        private void waitForClient() {
            System.out.println("waiting for client...");
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Hello ");
                dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));


            } catch (IOException e) {
                System.err.println("Error in waiting");
            }
        }

        private void handleClient() {
            String input = "";
           // while (true) {
                try {
                    input = dataInputStream.readUTF();
                    System.out.println("client sent : " + input);
                    if (input.startsWith("create_account")) {
                        String[] info = input.split(" ");
                        try {
                            String firstName = info[1];
                            String lastName = info[2];
                            String userName = info[3];
                            String password = info[4];
                            String repeatePassword = info[5];
                            createAccount(firstName, lastName, userName, password, repeatePassword);
                        }catch (IndexOutOfBoundsException e){
                            System.err.println("in valid info for create");
                        }

                    }
                    else if (input.startsWith("get_token")) {
                        String[] info=input.split(" ");
                        String userName=info[1];
                        String password=info[2];
                        if (correctUserAndPass(userName,password)){
                            getToken(userName);
                        }

                    }
                    else if (input.startsWith("create_receipt")) {
                        String [] info=input.split(" ");
                        String token=info[1];
                        Reciept_type reciept_type=null;
                        if (info[2].equalsIgnoreCase("deposit"))
                            reciept_type=Reciept_type.DEPOSIT;
                        else if (info[2].equalsIgnoreCase("move"))
                            reciept_type=Reciept_type.MOVE;
                        else if (info[2].equalsIgnoreCase("withdraw"))
                            reciept_type=Reciept_type.WITHDRAW;
                        else
                            System.out.println("In valid receipt type...");
                        double money=Double.parseDouble(info[3]);
                        String sourceId=info[4];
                        String destId=info[5];
                        String description=info[6];
                        createReceipt(token,reciept_type,money,sourceId,destId,description);

                    }
                    else if (input.startsWith("get_transactions")) {
                        String[] info=input.split(" ");
                        String token=info[1];
                        String type=info[2];
                        getTransaction(token , type);
                    }
                    else if (input.startsWith("pay")) {
                        String [] info=input.split(" ");
                        int receiptId=Integer.parseInt(info[1]);
                        for (Receipt receipt:receipts) {
                            if (receipt.getId()==receiptId){
                                if (receipt.isPAy())
                                    System.out.println("recieptde is payed before...");
                                else {
                                    pay(receiptId);
                                    return;
                                }
                            }
                        }
                        System.err.println("receipt id is invalid");
                    }
                    else if (input.startsWith("get_balance")) {
                        String[] info=input.split(" ");
                        String token=info[1];
                        getBalance(token);

                    }
                    else if (input.startsWith("exit")) {
                        return;
                    }
                    else
                        System.out.println("in valid input");
                } catch (IOException e) {
                    System.err.println("data base error");
                }
            //}
        }

        private void getTransaction(String token , String type){
            if (type.equals("+")){

            }
            else if (type.equals("-")){

            }
            else if (type.equals("*")){

            }
            else {

            }
        }
        private void pay(int receiptId){
            for (Receipt receipt:receipts) {
                if (receipt.getId()==receiptId){
                    if (receipt.getReciept_type().equals(Reciept_type.DEPOSIT)){

                    }
                    else if (receipt.getReciept_type().equals(Reciept_type.WITHDRAW)){

                    }
                    else if (receipt.getReciept_type().equals(Reciept_type.MOVE)){

                    }

                }
            }
        }
        private String getBalance(String token){
            String response="";
            for (BankAccount account:accounts) {
                System.out.println(account.getToken());
                if (!account.getingToken().isExpire(System.currentTimeMillis())){
                    if (account.getToken().equals(token)){
                        response=String.valueOf(account.getBalance());
                        break;
                    }

                }
                else {
                    response="token expired...";
                    break;
                }

            }
            if (response=="")
                response=("token is invalid");
            try {
                dataOutputStream.writeUTF((response));
                dataOutputStream.flush();
            } catch (IOException e) {
                System.err.println("error in connection");
            }
            return response;
        }
        private boolean correctUserAndPass(String userName,String password){
            for (BankAccount user:accounts) {
                if (user.getUserName().equals(userName)){
                    if (user.getPassword().equals(password))
                        return true;
                    else{
                        System.out.println("in valid user Name or password");
                        return false;
                    }

                }
            }
            System.out.println("in valid user Name or password");
            return false;
        }
        private String getToken(String userName){
            for (BankAccount account:accounts) {
                if (account.getUserName().equals(userName)){
                    try {
                        dataOutputStream.writeUTF(account.getToken());
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        System.err.println("loss connection");
                    }
                    return account.getToken();
                }
            }
            return null;


        }
        private void createAccount(String firstName, String lastName, String userName, String password, String repeatePaeesord) {
            if (!password.equals(repeatePaeesord)) {
                System.err.println("passwords dont match!");
                return;

            } else {
                for (BankAccount account : accounts) {
                    if (account.getUserName().equals(userName)) {
                        System.err.println("user name is not available!");
                        return;
                    }
                }

                BankAccount bankAccount = new BankAccount(firstName, lastName, userName, password);
                String accountNumber = bankAccount.getAccountNumber();
                addAccount(bankAccount);
                try {
                    dataOutputStream.writeUTF(accountNumber);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    System.err.println("error in connection");
                }

            }

        }

        public void createReceipt(String token , Reciept_type reciept_type,double money , String sourceId,String destId ,String description){
            if (money < 0 || money!=(int)money){
                System.err.println("In valid money...");
            }
            Receipt receipt=new Receipt(token,reciept_type,money,sourceId,destId,description);
            int id=receipt.getId();
            addReceipt(receipt);
            try {
                dataOutputStream.writeUTF(String.valueOf(id));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        public void run() {
            System.out.println("IP : " + "127.0.0.1");
            System.out.println("Port : " + "8888");
            readAccountsFromDB();
            readReceiptsDromDB();

            System.out.println(getUserInfo());
            System.out.println(getReceiptInfo());
            try {
                serverSocket = new ServerSocket(8888);
                waitForClient();
                handleClient();
                System.out.println("connection end");
                dataOutputStream.writeUTF("connection end");
                dataOutputStream.flush();
            } catch (IOException e) {
                System.err.println("Error in starting server ");
            }


        }
    }

}
