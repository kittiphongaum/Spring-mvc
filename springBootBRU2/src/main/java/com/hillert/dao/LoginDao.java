package com.hillert.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hillert.model.LoginBean;
import com.hillert.util.ConnectDB;

@Repository
public class LoginDao {

	public LoginBean login(String idCard, String password, String roleId) {
		LoginBean bean = new LoginBean();
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append(" SELECT * FROM tbl_login WHERE log_username = ? AND log_password = ? ");
			sql.append(" AND log_role = ? AND log_status = 'A' ");

			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, idCard);
			prepared.setString(2, password);
			prepared.setString(3, roleId);

			ResultSet rs = prepared.executeQuery();

			while (rs.next()) {
				bean.setLogId(rs.getInt("log_id"));
				bean.setLogUsername(rs.getString("log_username"));
				bean.setLogPassword(rs.getString("log_password"));
				bean.setLogRole(rs.getString("log_role"));
				bean.setLogStatus(rs.getString("log_status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public List<LoginBean> findAll() {
		List<LoginBean> list = new ArrayList<>();
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append(" SELECT * FROM tbl_login ");
			prepared = con.openConnect().prepareStatement(sql.toString());

			ResultSet rs = prepared.executeQuery();

			while (rs.next()) {
				LoginBean bean = new LoginBean();
				bean.setLogId(rs.getInt("log_id"));
				bean.setLogUsername(rs.getString("log_username"));
				bean.setLogPassword(rs.getString("log_password"));
				bean.setLogRole(rs.getString("log_role"));
				bean.setLogStatus(rs.getString("log_status"));

				list.add(bean);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	// insert
	public void insert(LoginBean bean) {
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append(" INSERT INTO tbl_login(log_username,log_password,log_role,log_status) VALUES(?,?,?,'A') ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, bean.getLogUsername());
			prepared.setString(2, bean.getLogPassword());
			prepared.setString(3, bean.getLogRole());

			prepared.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// update
	public void update(LoginBean bean) {
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" UPDATE tbl_login SET  log_password = ? , log_status = ? WHERE log_id = ? ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, bean.getLogPassword());
			prepared.setString(2, bean.getLogStatus());
			prepared.setInt(3, bean.getLogId());

			prepared.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}// end method update

	// delete
	public void delete(String id) {
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" DELETE FROM tbl_login WHERE log_username = ? ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, id);
			prepared.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	} // end method delete

	// end class
}
