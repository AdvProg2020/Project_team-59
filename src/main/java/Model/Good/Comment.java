package Model.Good;

import Model.Account.Account;
import Model.Account.Buyer;
import Model.log.BuyLog;

public class Comment {
    private Account account;
    private Good good;
    private String title;
    private String content;
    private CommentState commentState;

    public Comment( Good good , Account account , String title , String content ) {
        this.account = account;
        this.good = good;
        this.content = content;
        this.title = title;
        commentState = CommentState.TO_BE_APPROVED;
    }

    public void setCommentState(CommentState commentState) {
        this.commentState = commentState;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Account getBuyer() {
        return account;
    }

    public Good getGood() {
        return good;
    }

    public CommentState getCommentState() {
        return commentState;
    }

    public boolean hasBought(Account account , Good good) {
        if(account instanceof Buyer){
            Buyer buyer = (Buyer) account;
            for (BuyLog buyLog : buyer.getBuyLog()) {
                for (Good boughtGood : buyLog.getGoodsExchanged()) {
                    if(boughtGood.equals(good)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
