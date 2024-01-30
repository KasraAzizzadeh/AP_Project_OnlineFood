package Data;

public class Comment {
    private final String comment;
    private final String customerUsername;

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
