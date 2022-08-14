package com.example.myapplication;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Doctor1Activity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public void writeNewUser(String userId, String name, String email, String pwd) {
        User user = new User(name, email,pwd);

        mDatabase.child("users").child(userId).setValue(user);
    }
    public class User {

        public String username;
        public String email;
        public String pwd;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email,String pwd) {
            this.username = username;
            this.email = email;
            this.pwd = pwd;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPwd() {
            return pwd;
        }
    }
    public class Monit {

        public String username;
        public String email;
        public String pwd;

        public Monit() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Monit(String username, String email,String pwd) {
            this.username = username;
            this.email = email;
            this.pwd = pwd;
        }

    }
}