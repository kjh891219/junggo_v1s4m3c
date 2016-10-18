package dev.mvc.camera;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;
@Repository("dev.mvc.camera.CameraDAO")
public class CameraDAO implements CameraDAOInter{
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public CameraDAO() {
    System.out.println("--> CameraDAO created.");
  }
  @Override
  public List<CameraVO> list() {
    return mybatis.selectList("camera.list");
  }
  @Override
  public int create(CameraVO vo) {
    return mybatis.insert("camera.create", vo);
  }
  @Override
  public CameraVO read(int ctno) {
    return mybatis.selectOne("camera.read", ctno);
  }
 
  @Override
  public MemberVO test(String userid) { 
    return mybatis.selectOne("camera.test", userid);
  }
  @Override
  public int update(CameraVO cameraVO) {
    return mybatis.update("camera.update", cameraVO);
  }

}
