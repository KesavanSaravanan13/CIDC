package com.sampleDatabase.jpa.Repository;


import com.sampleDatabase.jpa.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Employees,Integer> {
}
