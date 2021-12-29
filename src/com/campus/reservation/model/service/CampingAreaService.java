package com.campus.reservation.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.campus.reservation.model.vo.CampingArea;

public interface CampingAreaService {

	/**
	 * Description : 요청한페이지(currentPage)에 대한 목록을 리턴해주는 메소드(세부페이지)<br>
	 * Date : 2021. 12. 24.<br>
	 * History :<br>
	 * - 작성자 : lee, 날짜 : 2021. 12. 24., 설명 : 최초작성<br>
	 */
	HashMap<String, Object> selectAllList(int bsnNo, int currentPage);

	/**
	 * Description : 요청한페이지(currentPage)에 대한 목록을 리턴해주는 메소드(메인페이지)<br>
	 * Date : 2021. 12. 25.<br>
	 * History :<br>
	 * - 작성자 : lee, 날짜 : 2021. 12. 25., 설명 : 최초작성<br>
	 */
	HashMap<String, Object> selectMainList(int currentPage, String location);

	ArrayList<CampingArea> selectArea(int rsvSeq);

	int reservation(CampingArea list,String userId,String reservSta, String reservEnd);
}
