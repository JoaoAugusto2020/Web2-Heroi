package com.joaoa.Web2_Heroi.service;

import com.joaoa.Web2_Heroi.model.Heroi;

import java.util.List;

public interface IHeroiService {
    void saveHeroi(Heroi heroi);
    List<Heroi> getAllHerois();
    Heroi getHeroiById(long id);
    void deleteHeroiById(long id);
}
