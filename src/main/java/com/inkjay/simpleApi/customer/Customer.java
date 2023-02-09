package com.inkjay.simpleApi.customer;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {
        @Id
//        @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 50)
//        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id ;

        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;


        public Customer() {
        }

        public Customer(UUID id, String firstName, String lastName, String email, String phoneNumber) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        public Customer(String firstName, String lastName, String email, String phoneNumber) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
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

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
                return "Customer{" +
                        "id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        '}';
        }
}
