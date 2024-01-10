package org.bogdans.config

import org.bogdans.security.CustomCorsFilter
import org.bogdans.security.CustomJwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAuthFilter: CustomJwtAuthFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(CustomCorsFilter(), ChannelProcessingFilter::class.java)
            .logout { logout ->
                logout.logoutUrl("/api/v1/auth/logout")
                    .logoutSuccessHandler { request, response, authentication ->
                        SecurityContextHolder.clearContext()
                    }.deleteCookies()
            }

        return http.build()
    }
}