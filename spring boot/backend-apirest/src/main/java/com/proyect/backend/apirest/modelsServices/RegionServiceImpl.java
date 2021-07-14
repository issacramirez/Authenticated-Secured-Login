package com.proyect.backend.apirest.modelsServices;

import java.util.List;

import com.proyect.backend.apirest.models.Region;
import com.proyect.backend.apirest.modelsDao.IRegionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return (List<Region>) regionDao.findAll();
    }
    
}
