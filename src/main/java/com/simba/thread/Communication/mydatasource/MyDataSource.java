package com.simba.thread.Communication.mydatasource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyDataSource {

	private LinkedList<Connection> pool = new LinkedList<>();

	private static final int INIT_CONNECTION = 10;

	private static final int MAX_CONNECTION = 10;

	private static final String DRIVER_CLASS = "";

	private static final String USER = "";

	private static final String PASSWORD = "";

	private static final String URL = "";

	private Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public MyDataSource(){
		for (int i = 0; i < INIT_CONNECTION; i++) {
			try {
				Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
				pool.addLast(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Connection getConnection(){
		Connection connection = null;
		lock.lock();
		try {
			while (pool.size()<=0) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(!pool.isEmpty()){
				connection = pool.removeFirst();
			}
			return connection;
		}finally {
			lock.unlock();
		}

	}

	public void release(Connection connection){
		if(connection !=null){
			lock.lock();
			try{

					pool.addLast(connection);
					connection.notifyAll();
			}finally {
				lock.unlock();
			}

		}
	}




}
