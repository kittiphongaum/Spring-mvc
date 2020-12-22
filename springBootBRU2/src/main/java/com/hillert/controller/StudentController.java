package com.hillert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hillert.dao.LoginDao;
import com.hillert.dao.StudentDao;
import com.hillert.model.LoginBean;
import com.hillert.model.StudentBean;

@Controller
public class StudentController {
	@Autowired
	StudentDao studentDao;
	@Autowired
	private LoginDao loginDao;
	
	// path Insert
	
	@RequestMapping("/gotoInsert")
	public String gotoInsert(Model model) {	
		model.addAttribute("messes", "");
		return "insert";
	}
	@RequestMapping("/gotoWelcome")
	public String gotoWelcome(HttpServletRequest request) {	
		List<LoginBean> list = new ArrayList<>();
		list =  loginDao.findAll();
		request.getSession().setAttribute("listUser", list);
		return "welcome";
	}
	
	@RequestMapping("/insert")
	public String insert(Model model,@ModelAttribute("SpringWeb")StudentBean student,String password) {	
		LoginBean bean = new LoginBean();
		bean.setLogUsername(student.getStIdcard());
		bean.setLogPassword(password);
		bean.setLogRole(student.getRoleId());
		try {
			studentDao.insert(student);
			loginDao.insert(bean);
			model.addAttribute("messes", "S");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("messes", "F");
		}
		return "insert";
	}
	
	//  path Update
	@RequestMapping( value = "/gotoUpdate" , method = RequestMethod.POST)
	public String gotoUpdate(Model model,String stIdcard) {	
		StudentBean bean = new StudentBean();
		try {
			bean = studentDao.findByidCard(stIdcard);
			if(bean.getStIdcard() != null) {
				model.addAttribute("messesUpdate", "");
				model.addAttribute("resultBean", bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "update";
	}
	@RequestMapping("/update-data")
	public String update(Model model,@ModelAttribute("SpringWeb")StudentBean student) {	
		StudentBean bean = new StudentBean();
		try {
			studentDao.update(student);

			bean = studentDao.findByidCard(student.getStIdcard());
			model.addAttribute("resultBean", bean);
			model.addAttribute("messesUpdate", "S");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("resultBean", bean);
			model.addAttribute("messesUpdate", "F");
		}
		return "update";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model,String stIdcard,HttpServletRequest request) {	
		List<LoginBean> list = new ArrayList<>();
		try {
			studentDao.delete(stIdcard);
			loginDao.delete(stIdcard);
			list =  loginDao.findAll();
			request.getSession().setAttribute("listUser", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "welcome";
	}
	@RequestMapping( value = "/gotoProfile" , method = RequestMethod.POST)
	public String gotoProfile(Model model,String stIdcard) {
		StudentBean bean = new StudentBean();
		try {
			bean = studentDao.findByidCard(stIdcard);
			if(bean.getStIdcard() != null) {
				model.addAttribute("messesUpdate", "");
				model.addAttribute("resultBean", bean);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "profile";
	}
}
