package com.cg.spbajaxbankingtransactionjwt.service.role;

import com.cg.spbajaxbankingtransactionjwt.model.Role;
import com.cg.spbajaxbankingtransactionjwt.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void remove(Role role) {

    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
