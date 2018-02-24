package ch.rhj.dev.repository;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
@WebServlet(name="repository", urlPatterns="/repository/*")
public class RepositoryServlet extends HttpServlet {
	
	private static final String SIGNED_IN =
			"<p>Hello, %1$s! You can <a href='%2$s'>sign out</a>.</p>"
			+ "<p>email: %3$s, nickname: %4$s, userid: %5$s, admin: %6$s</p>";
	
	private static final String SIGNED_OUT =
			"<p>Please <a href='%1$s'>sign in</a>.</p>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		String thisUrl = req.getRequestURI();
		
		resp.setContentType("text/html");
		
		if (req.getUserPrincipal() != null) {
			
			User user = userService.getCurrentUser();
			String email = user.getEmail();
			String nickname = user.getNickname();
			String userid = user.getUserId();
			String admin = Boolean.toString(userService.isUserAdmin());
			
			String username = req.getUserPrincipal().getName();
			String signOutUrl = userService.createLogoutURL(thisUrl);
			String content = String.format(SIGNED_IN,
					username, signOutUrl,
					email, nickname, userid, admin
			);
			
			resp.getWriter().println(content);
			
		} else {
			
			String signInUrl = userService.createLoginURL(thisUrl);
			String content = String.format(SIGNED_OUT, signInUrl);
			
			resp.getWriter().println(content);
		}
	}
}
