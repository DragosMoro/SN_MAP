package domain;

public class User extends Entity<Integer>{
    public String firstName;
    public String lastName;
    public String email;

    public User(int id, String firstName, String lastName, String email) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ID: " + super.getId()+ ' ' +
                "| first name: " + firstName + ' ' +
                "| last name: " + lastName + ' ' +
                "| email: " + email ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
