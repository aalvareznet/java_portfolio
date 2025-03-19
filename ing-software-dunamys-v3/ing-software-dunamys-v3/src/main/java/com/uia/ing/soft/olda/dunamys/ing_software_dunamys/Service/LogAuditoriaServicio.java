package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.LogAuditoria;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.LogAuditoriaRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.UserRepository;

@Service
public class LogAuditoriaServicio{

    @Autowired
    private LogAuditoriaRepositorio repo;

    @Autowired
    private UserRepository userRepo;

    public void guardarAccion(Integer userId, String accion, String tabla){
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            LogAuditoria log =  LogAuditoria.builder()
                                        .usuario(user.get())
                                        .accion(accion)
                                        .fechaHora(LocalDateTime.now())
                                        .tablaAfectada(tabla)
                                        .build();
            repo.save(log);
        }
    }   

}
