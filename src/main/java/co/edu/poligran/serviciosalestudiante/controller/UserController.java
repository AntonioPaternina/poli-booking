package co.edu.poligran.serviciosalestudiante.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.service.MailSenderService;
import co.edu.poligran.serviciosalestudiante.service.UserService;
import co.edu.poligran.serviciosalestudiante.service.dto.PasswordResetTokenDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

@RestController
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailSenderService mailSenderService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping(value = "/user/reset-password", method = RequestMethod.POST)
	public PasswordResetTokenDTO resetPassword(HttpServletRequest request, @RequestParam("email") String email)
			throws UserNotFoundException {
		UserDTO user = userService.findByEmail(email);

		PasswordResetTokenDTO token = userService.createPasswordResetTokenForUser(user);

		mailSenderService.sendPasswordResetTokenEmail(token, getFullAppUrl(request));

		return token;
	}

	private String getFullAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public void showChangePasswordPage(@RequestParam("id") long id, @RequestParam("token") String token,
			HttpServletResponse response) throws InvalidPasswordResetTokenException, IOException {
		userService.validatePasswordResetToken(id, token);
		response.sendRedirect("/updatePassword.html");
	}

	@RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
	public void savePassword(@RequestParam("new-password") String newPassword) {
		userService.changeUserPassword(newPassword);
	}
}