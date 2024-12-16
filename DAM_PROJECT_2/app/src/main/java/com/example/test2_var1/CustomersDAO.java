package com.example.test2_var1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomersDAO {

    @Insert
    void insert(Customer Customer);

    @Insert
    void insert(List<Customer> customerList);

    @Query("SELECT * FROM customers")
    List<Customer> getAll();

    @Query("SELECT COUNT(*) FROM customers")
    int count();

    @Query("SELECT * FROM customers order by tipAbonament")
    List<Customer> getCustomerOrdonat();

    @Query("delete from customers")
    void deleteAll();

}
