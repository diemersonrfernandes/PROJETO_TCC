/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.controleacesso.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    // just to emulate user data and credentials retrieval from a DB, or whatsoever authentication service
    private static Map<String, UserDetails> userRepository = new HashMap<String, UserDetails>();

    static {
        GrantedAuthority authorityAdmin = new GrantedAuthorityImpl("ADMIN");
        GrantedAuthority authoritySuprimento = new GrantedAuthorityImpl("SUPRIMENTO");
        GrantedAuthority authorityGuest = new GrantedAuthorityImpl("VISITANTE");

        /* user1/password1 --> ADMIN */
        Set<GrantedAuthority> authorities1 = new HashSet<GrantedAuthority>();
        authorities1.add(authorityAdmin);
        UserDetails user1 = new UserDetailsImpl("admin", "123", authorities1);
        userRepository.put("admin", user1);

        /* SUPRIMENTO */
        Set<GrantedAuthority> authorities4 = new HashSet<GrantedAuthority>();
        authorities4.add(authoritySuprimento);
        UserDetails user4 = new UserDetailsImpl("suprimento", "123", authorities4);
        userRepository.put("suprimento", user4);
        
        /* user2/password2 --> GUEST */
        Set<GrantedAuthority> authorities2 = new HashSet<GrantedAuthority>();
        authorities2.add(authorityGuest);
        UserDetails user2 = new UserDetailsImpl("visitante", "123", authorities2);
        userRepository.put("visitante", user2);

        /* user3/password3 --> ADMIN + GUEST */
        Set<GrantedAuthority> authorities3 = new HashSet<GrantedAuthority>();
        authorities3.add(authorityAdmin);
        authorities3.add(authorityGuest);
        UserDetails user3 = new UserDetailsImpl("user3", "123", authorities3);
        userRepository.put("user3", user3);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails matchingUser = userRepository.get(username);

        if (matchingUser == null) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }

        return matchingUser;
    }

}
