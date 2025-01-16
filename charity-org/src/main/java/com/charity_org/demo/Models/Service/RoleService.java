package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
     RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public  String getRoleById(Long id) {
        return roleRepository.getRoleNameById(id);
    }
}
