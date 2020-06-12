package ani.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ani.DAO.AniDAO;
import ani.domain.AniVO;

public class MemberAddtAction implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionFoward forward = new ActionFoward();
		AniDAO dao = new AniDAO();
		AniVO vo = new AniVO();

   		boolean result=false;
   		
		vo.setMem_id(request.getParameter("mem_id"));
		vo.setMem_pwd(request.getParameter("mem_pwd"));
		vo.setMem_nick(request.getParameter("mem_nick"));
		
//		vo.setJoin_date(Integer.toString(request.getParameter("join_date")));
		
		result = dao.NewMem(vo);
		
		if(!result) {
			System.out.println("데이터 등록 실패");
			return null;
		}
		forward.setFoward(true);
		forward.setPath("./memberList.aho");
		return forward;
	}

}
