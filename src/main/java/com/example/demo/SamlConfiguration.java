package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;

@Configuration
public class SamlConfiguration {

    @Value("${spring.security.saml2.relyingparty.registration.azure.entity-id:saml-spring-boot}")
    private String entityId;

    @Value("${spring.security.saml2.relyingparty.registration.azure.assertingparty.entity-id}")
    private String assertingPartyEntityId;

    @Bean
    public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() {
        RelyingPartyRegistration registration = RelyingPartyRegistration
            .withRegistrationId("azure")
            .entityId(entityId)
            .assertionConsumerServiceLocation("{baseUrl}/login/saml2/sso/azure")
            .singleLogoutServiceLocation("{baseUrl}/logout/saml2/slo")
            .assertingPartyDetails(party -> party
                .entityId(assertingPartyEntityId)
                .singleSignOnServiceLocation(assertingPartyEntityId)
                .singleLogoutServiceLocation(assertingPartyEntityId + "/logout")
                .wantAuthnRequestsSigned(true)
            )
            .build();

        return new InMemoryRelyingPartyRegistrationRepository(registration);
    }
}