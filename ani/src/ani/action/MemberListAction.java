package ani.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ani.DAO.AniDAO;
import ani.domain.AniVO;

public class MemberListAction implements Action {
// dao에 필요한 만큼 정보 조회 명령
	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionFoward foward = new ActionFoward();
		
		int page = 1;
		int limit = 2;
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		if(request.getParameter("limit") != null)
			page = Integer.parseInt(request.getParameter("limit"));
		int start = (page -1) * limit +1;
		int end = page * limit;
		
		AniDAO dao = new AniDAO();
		List<AniVO> boardList = dao.getMemberList(start, end);
		
		int listCount = dao.getMemberCount();
		int maxPage = listCount / limit + (listCount % limit != 0 ? 1 : 0);
		int startPageNum = page-4<0 ? 1 : page-4;
		int endPageNum = (startPageNum+9) > maxPage ? maxPage : startPageNum+9;
		
		request.setAttribute("nowPage", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);		
		request.setAttribute("listCount", listCount);
		request.setAttribute("boardList", boardList);
		
		foward.setFoward(false);			// 포워딩 함
		foward.setPath("./member/memberList.jsp");	// 여기로 함
		
		return foward;
	}

}
