package com.hillert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hillert.dao.LoginDao;
import com.hillert.model.LoginBean;

@Controller
public class LoginController {
	@Autowired
	private LoginDao logindao;

	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("messessError", "");
		return "login";
	}

	@RequestMapping("/login")
	public String authenLogin(String username, String password, String roleId, Model model,
			HttpServletRequest request) {
		String authen = "";
		LoginBean bean = new LoginBean();
		List<LoginBean> findAll = new ArrayList<>();
		try {
			bean = logindao.login(username, password, roleId);
			findAll = logindao.findAll();
			if (bean.getLogUsername() != null) {
				request.getSession().setAttribute("LoginUser", bean);
				request.getSession().setAttribute("listUser", findAll);
				authen = "welcome";

			} else {
				model.addAttribute("messessError", "F");
				authen = "login";
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return authen;
	}

	@RequestMapping("/logout")
	private String logout(HttpServletRequest request, Model model) {
		request.getSession().removeAttribute("LoginUser");
		request.getSession().removeAttribute("listUser");
		model.addAttribute("messessError", "L");

		return "login";
	}
}
