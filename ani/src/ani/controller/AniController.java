package ani.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ani.action.Action;
import ani.action.ActionFoward;
import ani.action.MemberAddtAction;
import ani.action.MemberDetailAction;
import ani.action.MemberListAction;

public class AniController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   try {
		doProcess(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("떴냐");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		ActionFoward forward = null;
		Action action = null;
							// ./memberList.aho
		if(command.equals("./memberList.aho")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/memberWrite.aho")) {
			forward = new ActionFoward();
			forward.setFoward(false);
			forward.setPath("./member/memberWrite.jsp");
		}else if(command.equals("/memberAddAction.aho")) {
			action = new MemberAddtAction();
			forward = action.execute(request, response);
		}else if(command.equals("/memberDetailAction.aho")) {
			action = new MemberDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		System.out.println("false => fowarding" + forward.isFoward());		// 여기에서 "또"오류
//		System.out.println("경로 => " + forward.getPath());
		if (forward != null) {
			if (forward.isFoward()) {
				// sendredirece ㄱㄱ
				response.sendRedirect(forward.getPath());
			} else {
				// forwarding ㄱㄱ
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
			}
		}
	}

}
