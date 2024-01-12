package org.bogdans.config

import org.bogdans.security.JwtRequestFilter
import org.bogdans.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var authenticationConfiguration: AuthenticationConfiguration

//    @Autowired
//    fun configureGlobal(auth: AuthenticationManagerBuilder, passwordEncoder: PasswordEncoder, userDetailsService: UserDetailsService) {
//        val daoAuthenticationProvider = DaoAuthenticationProvider()
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService)
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder)
//        auth.authenticationProvider(daoAuthenticationProvider)
//    }
    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var userDetailsService: UserDetailsService




    @Bean
    fun jwtRequestFilter(): JwtRequestFilter {
        return JwtRequestFilter(jwtTokenUtil, userDetailsService)
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .cors { cors -> cors.configurationSource(corsConfigurationSource()) }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/authenticate").permitAll()
                    .anyRequest().authenticated()
            }

            .formLogin { formLogin -> formLogin.defaultSuccessUrl("/api/success") }
            .logout { logout -> logout.logoutSuccessUrl("/api/logoutSuccess") }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000") // or use listOf("*") for allowing all origins
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
