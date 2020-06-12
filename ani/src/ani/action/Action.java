package ani.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	ActionFoward execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
