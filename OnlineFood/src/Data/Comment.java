package Data;

public class Comment {
    private String comment;
    private String customerUsername;

    Comment(String comment, String customerUsername){
        this.comment = comment;
        this.customerUsername = customerUsername;
    }

    public String getComment() {
        return comment;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }
}
