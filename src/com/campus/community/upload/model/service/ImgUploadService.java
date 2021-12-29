package com.campus.community.upload.model.service;

import com.campus.community.upload.model.vo.Image;

public interface ImgUploadService {

	int insert(Image img, String userId);

}
