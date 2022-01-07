package controller.action;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws   ServletException, IOException {
		
		String url = "/board/boardList.jsp";
		BoardDAO bDao = BoardDAO.getInstance();
		
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			
		}
		int listCount = bDao.getListCount(); //총 게시물 개수
		
		List<BoardVO> boardList = bDao.getBoardList(page,limit);
		
		
		int maxPage = listCount % 10 != 0 ? listCount / 10 + 1 : listCount / 10;
//		int maxPage = (int)((double)(listCount/limit + 0.95)); // 총 페이지 개수
		
		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1; 
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("page", page);
		
//		List<BoardVO> boardList = bDao.selectAllBoards();
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher(url).forward(request, response); 
	}

}
