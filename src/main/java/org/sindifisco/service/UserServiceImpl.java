package org.sindifisco.service;

import org.sindifisco.model.PasswordResetToken;
import org.sindifisco.model.Usuario;
import org.sindifisco.repository.UsuarioRepository;
import org.sindifisco.repository.security.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Usuario findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(Usuario user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public void updatePassword(Usuario user, String password) {
        user.setSenha(password);
        userRepository.save(user);
    }
}
