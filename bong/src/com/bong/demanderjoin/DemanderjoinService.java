package com.bong.demanderjoin;

import java.util.List;
import java.util.Map;

public interface DemanderjoinService {
   
  public Demanderjoin readDemanderjoin1(int serviceIdx);
  public Demanderjoin readDemanderjoinLogin(String serviceId);
  
  public int insertDemanderjoin(Demanderjoin dto, String pathname);
  
  public int Demanderjoin2(Demanderjoin dto);
  
  public int deleteDemanderjoin2(Map<String, Object> map);
  
  public int dataCount(Map<String, Object> map);
  public List<Demanderjoin> listDemanderjoin(Map<String, Object> map);
  
}
