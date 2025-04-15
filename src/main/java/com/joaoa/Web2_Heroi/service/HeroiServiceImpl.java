package com.joaoa.Web2_Heroi.service;

import com.joaoa.Web2_Heroi.model.Heroi;
import com.joaoa.Web2_Heroi.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroiServiceImpl implements HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    @Override
    public List<Heroi> getAllHerois(){
        return heroiRepository.findAll();
    }

    @Override
    public void saveHeroi(Heroi heroi){
        this.heroiRepository.save(heroi);
    }

    @Override
    public Heroi getHeroiById(long id) {
        Optional< Heroi > optional = heroiRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Heroi not found with id: " + id);
        }
    }

    @Override
    public void deleteHeroiById(long id) {
        this.heroiRepository.deleteById(id);
    }
}
