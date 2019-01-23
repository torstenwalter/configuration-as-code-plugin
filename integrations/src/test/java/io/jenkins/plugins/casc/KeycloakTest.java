package io.jenkins.plugins.casc;

import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.casc.misc.JenkinsConfiguredWithCodeRule;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.KeycloakSecurityRealm;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:mail@torstenwalter.de">Torsten Walter</a>
 */
public class KeycloakTest {

    @Rule
    public JenkinsConfiguredWithCodeRule j = new JenkinsConfiguredWithCodeRule();

    @Test
    @ConfiguredWithCode(value = "KeycloakTest.yml")
    public void configure_keycloak() throws Exception {
        final Jenkins jenkins = j.getInstance();
        final KeycloakSecurityRealm.DescriptorImpl realm = (KeycloakSecurityRealm.DescriptorImpl) jenkins.getDescriptor(KeycloakSecurityRealm.class);
        String keycloakJson = realm.getKeycloakJson();
        assertNotNull(keycloakJson);
        assertTrue(keycloakJson.contains("https://my-keycloak-url/auth"));
    }
}
