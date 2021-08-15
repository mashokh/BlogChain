package model;


public class Blog {
    private String title;
    private String text;
    private int created_by;
    private String created_at;
    private int category_id;
    private int id;

    public Blog(int id, String title, String text, int created_by, String created_at, int category_id) {
        this.title = title;
        this.text = text;
        this.created_by = created_by;
        this.created_at = created_at;
        this.category_id = category_id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTruncatedText(){
        String result = "";
        String str = this.getText();
        int j = 0;
        int wordsToShow = 30;
        while(j < str.length()){
            result = result + str.charAt(j);
            if(str.charAt(j) == ' '){
                wordsToShow -= 1;
            }
            if(wordsToShow == 0){
                j = str.length();
                result += "...";
            }
            j += 1;
        }
        return result;
    }
}
