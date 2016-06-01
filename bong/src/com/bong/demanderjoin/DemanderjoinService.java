package com.bong.demanderjoin;

import java.util.List;
import java.util.Map;

import com.bong.member.Member;

public interface DemanderjoinService {
   
  public Demanderjoin readDemanderjoin1(int serviceIdx);
  public Demanderjoin readDemanderjoinLogin(String userIdx);
  public Demanderjoin readDemanderCheck(String userId);
  public Demanderjoin readDemanderjoinInfo(String userIdx);
  public int insertDemanderjoin(Demanderjoin dto, String pathname);
  public int Demanderjoin2(Demanderjoin dto);
  
  public int updateLastLogin(String userId);
  public int updateDemander2(Demanderjoin dto, String pathname);
  public int deleteDemanderjoin2(Map<String, Object> map);
  
  public int dataCount(Map<String, Object> map);
  public List<Demanderjoin> listDemanderjoin(Map<String, Object> map);
  
}
