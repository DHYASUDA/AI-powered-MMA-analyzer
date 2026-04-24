package techskill.demo.DTO;

public class userEdit{
    private long id;
    private String userName;

    public String getUserName(){
        return userName;
    }
    public void setString(String str){
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}