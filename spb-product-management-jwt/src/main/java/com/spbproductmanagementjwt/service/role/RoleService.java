package com.spbproductmanagementjwt.service.role;

import com.spbproductmanagementjwt.model.Role;
import com.spbproductmanagementjwt.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
