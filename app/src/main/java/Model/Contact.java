package Model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phone;
    private boolean isFavorite;
    private int id;
    //link anh
    private String image;


    // Constructor
    public Contact(String name, String phone, boolean isFavorite, int id) {
        this.name = name;
        this.phone = phone;
        this.isFavorite = isFavorite;
        this.id = id;
    }

    // Getter và Setter cho isChecked


    // Các getter và setter khác (nếu cần)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}