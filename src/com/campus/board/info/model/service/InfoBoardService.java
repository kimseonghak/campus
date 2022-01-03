package com.campus.board.info.model.service;

import com.campus.board.info.model.vo.InfoBoard;
import com.campus.board.info.model.vo.InfoPage;

public interface InfoBoardService {

	InfoPage infoboardListAll(int currentPage);
	
	InfoBoard infoboardSelectOne(int infoNo);

	InfoPage search(String type, String keyword, int currentPage);

	int delete(int infoNo, String userId);

	int update(InfoBoard infoBoard);

	int insert(InfoBoard infoBoard);

	int prevInfoBoard(int infoNo);

	int nextInfoBoard(int infoNo);

}
