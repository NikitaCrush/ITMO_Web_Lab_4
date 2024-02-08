package org.bogdans.config

import org.bogdans.repository.TokenRepository
import org.bogdans.security.JwtRequestFilter
import org.bogdans.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * Configuration class for Spring Security. It defines beans and configurations for security concerns.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var authenticationConfiguration: AuthenticationConfiguration

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var tokenRepository: TokenRepository

    /**
     * Bean definition for JWT Request Filter.
     * @return JwtRequestFilter
     */
    @Bean
    fun jwtRequestFilter(): JwtRequestFilter {
        return JwtRequestFilter(jwtTokenUtil, userDetailsService, tokenRepository)
    }

    /**
     * Bean definition for Authentication Manager.
     * @return AuthenticationManager
     */
    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    /**
     * Security filter chain configuration. It sets up CORS, CSRF, and authorizes requests.
     * @param http HttpSecurity instance to configure.
     * @return SecurityFilterChain
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Add JWT filter before processing requests.
            .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter::class.java)
            // Enable CORS configuration.
            .cors { cors -> cors.configurationSource(corsConfigurationSource()) }
            // Disable CSRF protection as JWT is immune to it.
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { request ->
                request
                    // Allow public access to authentication endpoints.
                    .requestMatchers("/api/register", "/api/authenticate", "/api/logout").permitAll()
                    // All other requests must be authenticated.
                    .anyRequest().authenticated()
            }
        // Build the security filter chain.
        return http.build()
    }

    /**
     * Bean definition for Password Encoder.
     * @return PasswordEncoder
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * Bean definition for CORS Configuration Source.
     * @return CorsConfigurationSource
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins =
            listOf("http://localhost:3000", "http://localhost:3001", "https://literate-live-snapper.ngrok-free.app/") // or listOf("*") for allowing all origins
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
