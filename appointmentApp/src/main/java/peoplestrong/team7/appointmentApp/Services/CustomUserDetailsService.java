package peoplestrong.team7.appointmentApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import peoplestrong.team7.appointmentApp.Configuration.CustomUserDetails;
import peoplestrong.team7.appointmentApp.Models.User;
import peoplestrong.team7.appointmentApp.Repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Fetch UserDetails From Database
        User user = userRepository.findByEmail(username).get();

        if(user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
