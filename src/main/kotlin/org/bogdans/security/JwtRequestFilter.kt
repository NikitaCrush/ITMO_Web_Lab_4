package org.bogdans.security

import org.bogdans.util.JwtTokenUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.security.SignatureException

/**
 * JWT request filter for authentication. It intercepts requests to validate JWT tokens.
 */
class JwtRequestFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    /**
     * Internal filter method. It extracts and validates JWT token from the Authorization header.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param filterChain FilterChain
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // Extract the Authorization header.
            val authorizationHeader = request.getHeader("Authorization")

            var username: String? = null
            var jwtToken: String? = null

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extract the token from the header.
                jwtToken = authorizationHeader.substring(7)
                // Extract the username from the token.
                username = jwtTokenUtil.extractUsername(jwtToken)
            }

            // If username is not processed yet and is not part of the SecurityContext.
            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = this.userDetailsService.loadUserByUsername(username)

                // Validate token integrity and expiration.
                if (jwtTokenUtil.validateToken(jwtToken!!, userDetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    // Authenticate the user in the context.
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }

        } catch (e: SignatureException ) {

            // Handle the exception when token signature doesn't match. Possible token tampering or invalid token.
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token invalid or expired")
            return
        }
        // Continue filter chain with the next filter.
        filterChain.doFilter(request, response)

    }


}
