package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String url = "/board/boardUpdate.jsp";
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		BoardVO bVo =bDao.selectOneBoardByNum(num);
		
		request.setAttribute("board", bVo);
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
		
		
	}

}
