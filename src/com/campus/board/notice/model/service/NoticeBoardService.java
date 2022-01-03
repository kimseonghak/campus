package com.campus.board.notice.model.service;

import com.campus.board.notice.model.vo.NoticeBoard;
import com.campus.board.notice.model.vo.NoticePage;

public interface NoticeBoardService {

	NoticePage noticeboardListAll(int currentPage);
	
	NoticeBoard noticeboardSelectOne(int noticeNo);

	int update(NoticeBoard noticeBoard);

	int delete(int noticeNo, String userId);

	int insert(NoticeBoard noticeBoard);

	NoticePage search(String type, String keyword, int currentPage);

	int prevNoticeBoard(int noticeNo);

	int nextNoticeBoard(int noticeNo);

}
