package com.hillert.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.hillert.model.StudentBean;
import com.hillert.util.ConnectDB;

@Repository
public class StudentDao {

	public StudentBean findByidCard(String idCard) throws SQLException {
		StudentBean bean = new StudentBean();
		ConnectDB con = new ConnectDB();
		PreparedStatement preperd = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append(" SELECT * FROM tbl_student WHERE st_idcard = ? ");
			preperd = con.openConnect().prepareStatement(sql.toString());
			preperd.setString(1, idCard);
			ResultSet rs = preperd.executeQuery();

			while (rs.next()) {
				bean.setStId(rs.getInt("st_id"));
				bean.setStIdcard(rs.getString("st_idcard"));
				bean.setStFname(rs.getString("st_fname"));
				bean.setStLname(rs.getString("st_lname"));
				bean.setStPhone(rs.getString("st_phone"));
				bean.setRoleId(rs.getString("role_id"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.openConnect().close();
			}
		}

		return bean;
	}

	public void insert(StudentBean bean) {
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append(" INSERT INTO tbl_student(st_idcard,st_fname,st_lname,st_phone,role_id) VALUES(?,?,?,?,?) ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, bean.getStIdcard());
			prepared.setString(2, bean.getStFname());
			prepared.setString(3, bean.getStLname());
			prepared.setString(4, bean.getStPhone());
			prepared.setString(5, bean.getRoleId());
			prepared.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// update
	public void update(StudentBean bean) {
		ConnectDB con = new ConnectDB();
		PreparedStatement prepared = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" UPDATE tbl_student SET  st_fname = ? , st_lname = ?, st_phone = ? WHERE st_idcard = ? ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, bean.getStFname());
			prepared.setString(2, bean.getStLname());
			prepared.setString(3, bean.getStPhone());
			prepared.setString(4, bean.getStIdcard());

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
			sql.append(" DELETE FROM tbl_student WHERE st_idcard = ? ");
			prepared = con.openConnect().prepareStatement(sql.toString());
			prepared.setString(1, id);
			prepared.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	} // end method delete

	/*public static void main(String[] args) throws SQLException {
		StudentBean bean = new StudentBean();
		StudentDao dao = new StudentDao();

		bean = dao.findByidCard("admin");
		System.out.println("Fname = " + bean.getStFname());
	}*/
}
