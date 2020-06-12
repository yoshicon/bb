package ani.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ani.DAO.AniDAO;
import ani.domain.AniVO;

public class MemberDetailAction implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionFoward foward = new ActionFoward();
		
		AniDAO dao = new AniDAO();
		AniVO vo = null;
		int num = Integer.parseInt(request.getParameter("num"));
		vo = dao.getBoardDetail(num);
		
		request.setAttribute("vo", vo);
		
		foward.setFoward(false);
		foward.setPath("./member/memberView.jsp");
		return foward;
	}

}
