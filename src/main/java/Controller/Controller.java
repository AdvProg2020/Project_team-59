package Controller;

import Model.Account.*;
import Model.Application.Request;
import Model.Application.ApplicationType;
import Model.Application.CreatAccountRequest;
import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Comment;
import Model.Good.Good;
import View.Menus.BuyerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Controller {
    private static Manager coreManager;
    private static Account currentAccount;
    private static User currentUser;
    private boolean filteringIsPossible;//not sure
    private boolean hasDigestHappened;//not sure
    private Request request;
    private static Controller instance = new Controller();

    public Controller() {
        request = Request.getInstance();
    }

    public static Controller getInstance() {
        return instance;
    }

    public static void initializer() {
        //TODO initializes core manager
        //TODO sets up current user
    }

    public static void creatAdmin(AccountInformation accountInformation) {
        coreManager = new Manager(accountInformation, Role.MANAGER);
        currentUser = new User();
    }

    public static void purchase() throws Exception {
        if (currentAccount == null) {
            throw new Exception("you must log in first in order to purchase");
        }
        if (!(currentAccount instanceof Buyer)) {
            throw new Exception("only buyer accounts can purchase");
        } else {
            new BuyerView(null, (Buyer) currentAccount);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void sendCreatAccountApplication(String userName, String name, String lastName, String email, String phoneNumber, String password, Role role) {
        Manager.getAccountList().add(new Buyer(new AccountInformation(userName, name, lastName, email, phoneNumber, password), Role.BUYER));
    }

    public static void sendCreatAccountApplication(String userName, String name, String lastName, String email, String phoneNumber, String password, Role role, String companyInformation) {
        Manager.addApplication(new CreatAccountRequest(ApplicationType.CREAT_ACCOUNT, userName, name, lastName, email, phoneNumber, password, role, companyInformation));
    }

    public static double useOffTicket(String offTicketId, Buyer buyer) throws Exception {
        OffTicket offTicket;
        for (OffTicket temporaryOffTicket : buyer.getOffTickets()) {
            if (temporaryOffTicket.getOffTicketId().equalsIgnoreCase(offTicketId)) {
                offTicket = temporaryOffTicket;
                return useOffThicketIfPossible(buyer, offTicket);
            } else {
                throw new Exception("you dont owe such off ticket");
            }
        }
        return buyer.getCartValue();
    }

    public static double useOffThicketIfPossible(Buyer buyer, OffTicket offTicket) throws Exception {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdformat.parse(offTicket.getEndingDate());
        if (date1.compareTo(new Date()) < 0) {
            throw new Exception("off ticket is expired");
        } else if (offTicket.getTimesCanBeUsed() < 1) {
            throw new Exception("no more usages are available for this off ticket");
        } else {
            offTicket.setTimesCanBeUsed(offTicket.getTimesCanBeUsed() - 1);
            double totalValue = (1 - (offTicket.getOffAmount() / 100)) * buyer.getCartValue();
            if (offTicket.getOffAmount() >= (offTicket.getOffAmount() / 100) * buyer.getCartValue()) {
                return totalValue;
            }
            return buyer.getCartValue() - offTicket.getOffAmount();
        }
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static boolean usernameExists(String username) {
        return true;
    }

    public static void terminator() {
        //TODO terminates program by saving all data
    }

    public void goToOffsPage() {
        //TODO opens offs page
    }

    public void goToProductPage() {
        //TODO opens products page
    }

    public void showCategories() {
        //TODO prints categories
    }

    public void showProduct(String productId) {
        //TODO opens products page and increase goods times visited
    }

    public void showCurrentFilters() {
        //TODO prints current filters
    }

    public void filtering() {
        //TODO ?
    }

    public void showAvailableFilters() {
        //TODO prints possible filters
    }

    public void filter(Characteristic characteristic) {
        //TODO filters by characteristic and adds it to filters
    }

    public void disableFilter(Characteristic characteristic) {
        //TODO disables filter
    }

    public void showCurrentSorts() {
        //TODO prints currentSorts
    }

    public void sorting() {
        //TODO ?
    }

    public void showAvailableSorts() {
        //TODO prints possible sorts
    }

    public void sorter(Characteristic characteristic) {
        //TODO sorts by characteristic
    }

    public void disableSort(Characteristic characteristic) {
        //TODO disables sort and resets it to "times visited"
    }

    public void showProducts(ArrayList<Characteristic> filters, Characteristic sort) {
        //TODO filters then sorts then prints products
    }

    public Collection<OffTicket> getoffTickets() {
        return request.getOffTickets().values();
    }

    public Collection<Good> getgoods() {
        return request.getGoods().values();
    }


    public void removeDiscount(String offTicketId) {
        OffTicket offTicket = request.getOffTickets().get(offTicketId);
        request.getOffTickets().remove(offTicket);
    }

    public Collection<ManagerRequest> getRequests() {
        return request.getRequests().values();
    }

    public Collection<Category> getCategories() {
        return request.getCategoories().values();
    }

    public Collection<User> getUsers() {
        return request.getUsers().values();
    }

    public Collection<Account> getAccounts() {
        return request.getAccounts().values();
    }

    public ManagerRequest viewRequest(String requestId) {
        return request.getRequests().get(requestId);
    }

    public Collection<Comment> getcomments() {
        return request.getComments().values();
    }

    public Good viewProduct(String productId) {
        return request.getGoods().get(productId);
        //TODO show product
    }

    public void removeProduct(Good product) {
        request.getGoods().remove(product);

    }

    public void removeUser(Account account) {
        request.getAccounts().remove(account);
    }

    public void removeCategory(Category category) {
        request.getCategoories().remove(category);
    }

    public Category getCategory(String name) {
        return request.getCategoories().get(name);
    }

    public User getUser(String username) {
        return request.getUsers().get(username);
    }

    public Account getAccount(String username) {
        return request.getAccounts().get(username);

    }

    public void addGood(Good good) {
        request.getGoods().put(good.getProductId(), good);
    }

    public void addOffticket(OffTicket offTicket) {
        request.getOffTickets().put(offTicket.getOffTicketId(), offTicket);
    }

    public void addComment(Comment comment, Good product) {
        request.getComments().put(product, comment);
    }

    public void addAccounts(Account account) {
        request.getAccounts().put(account.getAccountInformation().getUsername(), account);

    }

    public void addcategory(Category category) {
        request.getCategoories().put(category.getCategoryName(), category);

    }

    public Good getGood(String productId) {
        return request.getGoods().get(productId);
    }

    public OffTicket getOffticket(String offticketId) {
        return request.getOffTickets().get(offticketId);
    }

    public boolean checkNoDiscountCode(OffTicket offTicket) {
        String offticketId = offTicket.getOffTicketId();
        OffTicket offTicket1 = request.getOffTickets().get(offticketId);
        return offTicket1 == null;
    }

    public Collection<Good> sortbyPrice(Collection<Good> goods) {
        ArrayList<Good> sorted=new ArrayList<>();
        double max;
        while (true) {
            max = 0;
            if (goods.size()==0)
                return sorted;
            else {
                for (Good product : goods) {
                    if (product.getPrice() > max)
                        max = product.getPrice();

                }
                for (Good product : goods) {
                    if (product.getPrice() == max) {
                        sorted.add(product);
                        goods.remove(product);
                    }

                }
            }


        }

    }
    public Collection<Good>sortbyVisit(Collection<Good>goods){
        ArrayList<Good> sorted=new ArrayList<>();
        double max;
        while (true) {
            max = 0;
            if (goods.size()==0)
                return sorted;
            else {
                for (Good product : goods) {
                    if (product.getPrice() > max)
                        max = product.getTimesVisited();

                }
                for (Good product : goods) {
                    if (product.getTimesVisited() == max) {
                        sorted.add(product);
                        goods.remove(product);
                    }

                }
            }


        }
    }
    public Collection<Good> sortbypriceIn(Collection<Good> goods) {
        ArrayList<Good> sorted=new ArrayList<>();
        double min;
        while (true) {
            min = Double.MAX_VALUE;
            if (goods.size()==0)
                return sorted;
            else {
                for (Good product : goods) {
                    if (product.getPrice() <min)
                        min = product.getPrice();

                }
                for (Good good:goods) {
                    if (good.getPrice() == min) {
                        sorted.add(good);
                        goods.remove(good);
                    }

                }
            }


        }
    }

}/*public boolean createDiscountCode(OffTicket offTicket){
    String offticketId=offTicket.getOffTicketId();

    application.getOffTickets().put(offticketId,offTicket);

}*/
