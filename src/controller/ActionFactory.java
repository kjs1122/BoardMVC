package controller;

import controller.action.Action;
import controller.action.BoardCheckPassAction;
import controller.action.BoardCheckPassFormAction;
import controller.action.BoardDeleteAction;
import controller.action.BoardListAction;
import controller.action.BoardUpdateAction;
import controller.action.BoardUpdateFormAction;
import controller.action.BoardViewAction;
import controller.action.BoardWriteAction;
import controller.action.BoardWriteFormAction;

public class ActionFactory {
	
	private ActionFactory() {
		
	}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		
		Action action = null;
		if(command.equals("board_list")){	
			action = new BoardListAction();
		}
		if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		}
		if(command.equals("board_write")) {
			action = new BoardWriteAction();
		}
		if(command.equals("board_view")) {
			action = new BoardViewAction();
		}
		if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		}
		if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		}
		if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		}
		
		if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		}
		if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		}
		
		
		

		return action;
		
	}
	


}
