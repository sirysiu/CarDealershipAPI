package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DealershipDAO {

    List<Dealership> findAllDealerships();

}

